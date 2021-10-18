<%@include file="parts/head.jsp" %>

<div class="flex flex-col">
    <div class="flex flex-row justify-end pb-4">
        <a class="btn btn-primary" href="<%=contextPath%>/forms">Navigate Form sets</a>
    </div>

    <%
        API.embedFormJava(
                request,
                out,
                (String) model.getAttribute("app"),
                (String) model.getAttribute("form"),
                (String) model.getAttribute("action"),
                (String) model.getAttribute("id"),
                null,
                headers
        );
    %>
</div>

<%@include file="parts/foot.jsp" %>
