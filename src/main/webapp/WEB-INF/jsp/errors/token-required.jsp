<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@include file="../parts/head.jsp" %>

<div class="orbeon">
    <h1>Unable to authenticate user.</h1>
    <h3>Refresh the page if this does not happen automatically in 20 seconds</h3>
    <a class="text-blue-500" href="<%= contextPath %>/forms?project=${projectParam}">Go Home</a>
</div>

<script>
    setTimeout(function (token) {
        if (!!token) {
            location.href = "<%= contextPath %>/forms?project=${projectParam}"
        }
    }, 10000, authorizationToken?.trim());
</script>

<%@include file="../parts/foot.jsp" %>
