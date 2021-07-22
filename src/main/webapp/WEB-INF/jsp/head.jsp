<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<%@ page import="org.orbeon.oxf.fr.embedding.servlet.API" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Enumeration" %>

<%-- Setup global variables/properties from Controller attributes --%>
<spring:eval expression="@environment.getProperty('app.orbeon-url')" var="orbeonUrl" />

<%
    // Prepare global variables and tokens/cookies
    String authCookieName = "AUTHORIZATION";
    String authorizationToken = "";

    Cookie[] cookies     = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().toUpperCase().equals(authCookieName))
                authorizationToken = cookie.getValue();
        }
    }
    String  selectedForm      = request.getParameter("form");
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
    <title>Basestone Orbeon Embedding</title>

    <script type="text/javascript">
        // Load/Set JS global variables from JSP. can be accessed from any
        // JS file included after this script
        const orbeonUrl = "${orbeonUrl}"
        const selectedForm = "<%= selectedForm %>";
        const authCookieName = "<%= authCookieName %>";
        const authorizationToken = "<%= authorizationToken %>";
        const headers = {};
        <% for (String header : headers.keySet()) { %>
        headers["<%= header %>"] = "<%= headers.get(header) %>";
        <% } %>
    </script>

    <link rel="stylesheet" href="//getbootstrap.com/2.3.2/assets/css/bootstrap.css">
    <link rel="stylesheet" href="//getbootstrap.com/2.3.2/assets/css/bootstrap-responsive.css">

    <script type="text/javascript" src="<spring:url value="/js/events.js" />"></script>
    <script type="text/javascript" src="<spring:url value="/js/main.js" />"></script>
    <link rel="stylesheet" href="<spring:url value="/css/main.css" />" />
    <script type="text/javascript" src="${orbeonUrl}/xforms-server/baseline.js?updates=fr"></script>
</head>
<body>
