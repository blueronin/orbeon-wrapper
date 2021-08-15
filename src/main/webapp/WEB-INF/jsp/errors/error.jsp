<%@include file="../parts/head.jsp"%>

<div class="orbeon">
    <h1>Something went wrong! ${statusCode}</h1>
    <h2>An error has occurred. Please contact the administrator; - template generic</h2>
    <a href="<%= contextPath %>/">Go Home</a>

    <h3>Message: ${errorMessage}</h3>
</div>

<%@include file="../parts/foot.jsp"%>
