<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>

<%@include file="../parts/head.jsp" %>

<div class="orbeon">
    <h1>User has been authenticated successfully</h1>
    <h3>
        Redirecting you to the main page shortly.
        Refresh the page If this does not happen automatically in 10 seconds
    </h3>
    <a class="text-blue-500" href="<%= contextPath %>/forms?project=${projectParam}">Go Home</a>
</div>

<script>
    setTimeout(function () {
        location.href = "<%= contextPath %>/forms?project=${projectParam}"
    }, 2000);
</script>

<%@include file="../parts/foot.jsp" %>
