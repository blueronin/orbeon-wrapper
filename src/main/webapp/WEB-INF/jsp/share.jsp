<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="parts/head.jsp" %>

<div class="flex flex-col px-2 sm:px-8 md:px-24">
    <% if (currentUser == null) { %>

        <%@include file="user-info.jsp" %>

    <% } else { %>

        <div class="flex flex-row pb-2 items-center">
            <h3 class="text-base font-bold">
                Completing new <%= model.getAttribute("form") %>
            </h3>
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
                        (String) model.getAttribute("query"),
                        headers
                );
            %>
        </div>

    <% } %>

</div>

<%@include file="parts/foot.jsp" %>
