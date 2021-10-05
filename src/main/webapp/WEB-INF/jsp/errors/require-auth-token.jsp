<%@include file="../parts/head.jsp" %>

<div class="orbeon">
    <h1>Waiting for Auth token to be passed and verified.</h1>
    <h3>Refresh the page if this does not happen automatically in 20 seconds</h3>
    <a class="text-blue-500" href="<%= contextPath %>/?project=<%= projectId %>">Go Home</a>
</div>

<script>
    setTimeout(function (token) {
        if (!!token) {
            location.href = "<%= contextPath %>/forms?project=<%= projectId %>"
        }
    }, 10000, authorizationToken?.trim());
</script>

<%@include file="../parts/foot.jsp" %>
