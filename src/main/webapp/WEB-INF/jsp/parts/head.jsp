<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ page import="org.orbeon.oxf.fr.embedding.servlet.API" %>
<%@ page import="java.util.Objects" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="org.springframework.validation.support.BindingAwareModelMap" %>
<%@ page import="io.orbeon.wrapper.models.user.CurrentUser" %>
<%@ page import="io.orbeon.wrapper.models.project.Project" %>

<%-- Setup global variables/properties from Controller attributes --%>
<spring:eval expression="@environment.getProperty('app.orbeon-url')" var="orbeonUrl"/>
<spring:eval expression="@environment.getProperty('app.api-url')" var="apiUrl"/>

<%
    BindingAwareModelMap model = new BindingAwareModelMap();
    if (request.getAttribute("model") != null) {
        model = (BindingAwareModelMap) request.getAttribute("model");
    }
    String contextPath = request.getContextPath().trim();
    // Prepare global variables and tokens/cookies
    String authCookieName = "AUTHORIZATION";
    String authorizationToken = "";

    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().toUpperCase().equals(authCookieName)) {
                authorizationToken = cookie.getValue();
                break;
            }
        }
    }
    String projectId = null;
    Object pId = session.getAttribute("projectId");
    if (pId != null) {
        projectId = pId.toString();
    }
    request.setAttribute("projectId", projectId);

    Project project = null;
    Object pjt = session.getAttribute("project");
    if (pjt != null) {
        project = (Project) pjt;
    }
%>

<%
    // Prepare headers to be sent to orbeon
    HashMap<String, String> headers = new HashMap<>();
    Enumeration<String> headersNames = request.getHeaderNames();
    while (headersNames.hasMoreElements()) {
        String name = headersNames.nextElement();
        headers.putIfAbsent(name, request.getHeader(name));
    }
    headers.putIfAbsent("Authorization", "OAuth ".concat(authorizationToken));
    headers.putIfAbsent("ProjectId", projectId);
    if (project != null && project.getTeam() != null) {
        headers.put("ProjectId", String.valueOf(project.getId()));
        headers.putIfAbsent("TeamId", String.valueOf(project.getTeam().getId()));
        headers.putIfAbsent("TeamSlug", String.valueOf(project.getTeam().getSlug()));
    }
    CurrentUser currentUser = (CurrentUser) session.getAttribute("user");
    if (currentUser != null) {
        String teamSlug = headers.get("TeamSlug");
        headers.putIfAbsent("orbeon-header", currentUser.toOrbeonHeaderString(teamSlug));
    }
    headers.remove("sec-ch-ua"); // this causes issues when passing to JS
    headers.remove("sec-ch-ua-platform"); // this causes issues when passing to JS
%>

<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport"
          content="minimum-scale=1, initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no, shrink-to-fit=no"/>
    <title>Basestone Orbeon Embedding</title>

    <script type="text/javascript">
        // Load/Set JS global variables from JSP. can be accessed from any
        // JS file included after this script
        const contextPath = "<%= contextPath %>"
        const orbeonUrl = "${orbeonUrl}"
        const apiUrl = "${apiUrl}"
        const authCookieName = "<%= authCookieName %>";
        const authorizationToken = "<%= authorizationToken %>";
        const projectId = "<%= projectId %>";
        const model = {};
        const headers = {};
        const isShared = <%= model.getAttribute("isShared") %>;
        headers["orbeon-header"] = <%= headers.get("orbeon-header") %>;

        <% for (String header : headers.keySet()) {
            if (header.equals("orbeon-header")) continue;
        %>
        headers["<%= header %>"] = "<%= headers.get(header) %>";
        <% } %>

        <% for (String attr : model.keySet()) { %>
        model["<%= attr %>"] = "<%= model.getAttribute(attr) %>";
        <% } %>
    </script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/2.2.7/tailwind.css"
          integrity="sha512-EPpc8hp3vb3PUXYMC+39/OwsEAc50QgthpyVEJMqwoV98YJIvhWi7QJ6tcY7JtshRB5ufQYztle/Mg1AZQw6CQ=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
          integrity="sha512-SfTiTlX6kk+qitfevl/7LibUOeJWlt9rbyDn92a1DqWOw9vWG2MFoays0sgObmWazO5BQPiFucnnEAjpAB+/Sw=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="stylesheet" href="<spring:url value="/css/main.css" />"/>

    <script type="text/javascript" src="/orbeon/xforms-server/baseline.js?updates=fr"></script>
</head>
<body class="w-full p-2">

<c:set var="isShared" value='<%= model.getAttribute("isShared") %>' />
<c:if test="${isShared == null || !isShared}">
    <div class="tabs mb-2 border-b">
        <ul class="nav list-none w-full p-0 m-0">
            <li><a href="#form-runner"><i class="fa fa-list"></i> Forms</a></li>
            <li><a href="#form-builder"><i class="fa fa-building"></i> Builder</a></li>
        </ul>
        <div id="form-runner"></div>
        <div id="form-builder"></div>
    </div>
</c:if>
