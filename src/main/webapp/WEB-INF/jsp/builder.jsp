<%@include file="head.jsp" %>

<div class="flex flex-col">
    <div class="flex flex-row justify-end pb-4">
        <a class="btn btn-primary" href="<%=contextPath%>/forms">Navigate Form sets</a>
    </div>

    <%
        API.embedFormJava(
                request,
                out,
                "orbeon",
                "builder",
                "new",
                null,
                null,
                headers
        );
    %>
</div>

<%@include file="foot.jsp" %>
