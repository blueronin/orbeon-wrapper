<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="parts/head.jsp" %>

<c:choose>
    <c:when test="<%= currentUser != null && (currentUser.isAdmin(teamSlug) || currentUser.isProjectManager(teamSlug)) %>">
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

                <a href="javascript:window.location.reload(true)" class="btn btn-primary w-12 text-center mx-2"><i class="fa fa-refresh"></i></a>

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
    </c:when>
    <c:otherwise>
        <div class="orbeon">
            <h1>Not authorized to access this page.</h1>
            <a class="text-blue-500" href="<%= contextPath %>/forms?project=${projectParam}">Go Home</a>
        </div>
    </c:otherwise>
</c:choose>

<%@include file="parts/foot.jsp" %>
