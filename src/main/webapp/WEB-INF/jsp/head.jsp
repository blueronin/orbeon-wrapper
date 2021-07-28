<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ page import="org.orbeon.oxf.fr.embedding.servlet.API" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="org.springframework.validation.support.BindingAwareModelMap" %>

<%-- Setup global variables/properties from Controller attributes --%>
<spring:eval expression="@environment.getProperty('app.orbeon-url')" var="orbeonUrl" />

<%
    BindingAwareModelMap model = new BindingAwareModelMap();
    if (request.getAttribute("model") != null) {
        model = (BindingAwareModelMap) request.getAttribute("model");
    }

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
    String projectId = session.getAttribute("projectId").toString();
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
        const orbeonUrl = "${orbeonUrl}"
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
    </script>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/2.2.7/tailwind.css" integrity="sha512-EPpc8hp3vb3PUXYMC+39/OwsEAc50QgthpyVEJMqwoV98YJIvhWi7QJ6tcY7JtshRB5ufQYztle/Mg1AZQw6CQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="<spring:url value="/css/main.css" />" />
</head>
<body class="container-fluid py-3 px-0">
