<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="parts/head.jsp" %>

<div class="flex flex-col">
    <div class="flex flex-row pb-4 items-center">
        <h3 class="text-base font-bold">
            <%
                String form = String.valueOf(model.getAttribute("form"));

                if (Objects.equals(model.getAttribute("action"), "summary")) {
                    out.println("Viewing ".concat(form).concat(" summary"));
                } else if (Objects.equals(model.getAttribute("action"), "new")) {
                    out.println("Complete new ".concat(form));
                } else if (Objects.equals(model.getAttribute("action"), "edit")) {
                    String id = String.valueOf(model.getAttribute("id"));
                    out.println("Editing ".concat(form).concat(", submission ").concat(id.substring(0, 10)).concat("..."));
                } else if (Objects.equals(model.getAttribute("action"), "view")) {
                    String id = String.valueOf(model.getAttribute("id"));
                    out.println("Viewing ".concat(form).concat(", submission ").concat(id.substring(0, 10)).concat("..."));
                }
            %>
        </h3>

        <a class="btn btn-default text-primary font-medium ml-4" href="<%=contextPath%>/forms">
            <i class="fa fa-long-arrow-left"></i> Home
        </a>

        <div class="flex flex-grow"></div>

        <a class="btn btn-primary" href="<%=contextPath%>/forms/orbeon/builder">
            <i class="fa fa-list text-black"></i> Form Builder Summary
        </a>
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