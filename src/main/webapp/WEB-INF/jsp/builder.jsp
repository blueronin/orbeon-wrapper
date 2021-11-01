<%@include file="parts/head.jsp" %>

<div class="flex flex-col">
    <div class="flex flex-row pb-2 items-center z-10">
        <h3 class="text-base font-bold">
            <%
                if (Objects.equals(model.getAttribute("action"), "summary")) {
                    out.println("Builder Summary");
                } else if (Objects.equals(model.getAttribute("action"), "edit")) {
                    out.println("Editing form ".concat(String.valueOf(model.getAttribute("id")).substring(0, 10)).concat("..."));
                } else {
                    out.println("Build your new form");
                }
            %>
        </h3>

        <div class="flex flex-grow"></div>

        <% if (!Objects.equals(model.getAttribute("action"), "summary")) { %>
        <a class="btn btn-secondary mr-2" href="<%=contextPath%>/forms/orbeon/builder/summary"><i class="fa fa-list text-black"></i> Builder Summary</a>
        <a class="btn btn-primary mr-2" href="<%=contextPath%>/forms/orbeon/builder/new"><i class="fa fa-plus text-black"></i> Create new form</a>
        <% } %>
    </div>

    <div class="w-full">
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
</div>

<%@include file="parts/foot.jsp" %>
