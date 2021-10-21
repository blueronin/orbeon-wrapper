<%@ page import="java.util.Objects" %>
<%@include file="parts/head.jsp" %>

<div class="flex flex-col">
    <div class="flex flex-row justify-end pb-4">
        <% if (!Objects.equals(model.getAttribute("action"), "summary")) { %>
        <a class="btn btn-secondary mr-2" href="<%=contextPath%>/forms/orbeon/builder/summary">Forms Summary</a>
        <a class="btn btn-primary mr-2" href="<%=contextPath%>/forms/orbeon/builder/new">Create new</a>
        <% } %>

        <a class="btn btn-primary" href="<%=contextPath%>/forms">Show Submitted Forms</a>
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
