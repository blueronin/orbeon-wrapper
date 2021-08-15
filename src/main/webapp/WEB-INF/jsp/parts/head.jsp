<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ page import="org.orbeon.oxf.fr.embedding.servlet.API" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="org.springframework.validation.support.BindingAwareModelMap" %>

<%-- Setup global variables/properties from Controller attributes --%>
<spring:eval expression="@environment.getProperty('app.orbeon-url')" var="orbeonUrl" />
<spring:eval expression="@environment.getProperty('app.api-url')" var="apiUrl" />

<%
    BindingAwareModelMap model = new BindingAwareModelMap();
    if (request.getAttribute("model") != null) {
        model = (BindingAwareModelMap) request.getAttribute("model");
    }
    String contextPath = request.getContextPath().trim();
    // Prepare global variables and tokens/cookies
    String authCookieName = "AUTHORIZATION";
    String authorizationToken = "";

    Cookie[] cookies     = request.getCookies();
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
%>

<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="minimum-scale=1, initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no, shrink-to-fit=no"/>
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
        <% for (String header : headers.keySet()) {
            if (!header.equals("sec-ch-ua")) {
        %>
        headers["<%= header %>"] = "<%= headers.get(header) %>";
        <%
            }
        }
        %>
        <% for (String attr : model.keySet()) { %>
        model["<%= attr %>"] = "<%= model.getAttribute(attr) %>";
        <% } %>

        function getCookie(name) {
            const match = document.cookie.match(RegExp('(?:^|;\\s*)' + name + '=([^;]*)'));
            return match ? match[1] : null;
        }
    </script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/2.2.7/tailwind.css" integrity="sha512-EPpc8hp3vb3PUXYMC+39/OwsEAc50QgthpyVEJMqwoV98YJIvhWi7QJ6tcY7JtshRB5ufQYztle/Mg1AZQw6CQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha512-SfTiTlX6kk+qitfevl/7LibUOeJWlt9rbyDn92a1DqWOw9vWG2MFoays0sgObmWazO5BQPiFucnnEAjpAB+/Sw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="<spring:url value="/css/main.css" />" />

    <script type="text/javascript" src="/orbeon/xforms-server/baseline.js?updates=fr"></script>
</head>
<body class="w-full p-2">
