<%@include file="../parts/head.jsp" %>

<div class="orbeon">
    <h1>User has been authenticated successfully</h1>
    <h3>
        Redirecting you to the main page shortly.
        Refresh the page If this does not happen automatically in 10 seconds
    </h3>
    <a class="text-blue-500" href="<%= contextPath %>/forms?project=${projectId}">Go Home</a>
</div>

<script>
    setTimeout(function () {
        location.href = "<%= contextPath %>/forms?project=${projectId}"
    }, 2000);
</script>

<%@include file="../parts/foot.jsp" %>
