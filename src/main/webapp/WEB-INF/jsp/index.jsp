<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="parts/head.jsp" %>

<div class="flex flex-col">
    <div class="flex flex-row justify-end pb-4">
        <a class="btn btn-primary" href="<%=contextPath%>/forms/builder">Create new form Set</a>
    </div>

    <div class="flex flex-row">
        <div class="w-1/5 pr-1">
            <h5 class="title-settings capitalize">Project Forms</h5>
            <div id="accordion">
                <c:forEach var="forms" items="${groupedForms}">
                    <h5 class="title-settings text-capitalize cursor-pointer">
                        <span class="caret-success">&#x25B8;</span> &nbsp; ${forms.key}
                    </h5>
                    <div id="collapse-${forms.key}" class="pl-6">
                        <ul class="flex flex-col">
                            <c:forEach items="${forms.value}" var="form">
                                <li id="${form.canonicalName}" class="text-capitalize py-1 pr-1">
                                    <a href="<%=contextPath%>/forms/${form.application}/${form.name}/new/?form=${form.canonicalName}">
                                        <span class="fa fa-caret-down"></span>
                                        <c:choose>
                                            <c:when test="${form.version != null}">${form.name} - v${form.version}</c:when>
                                            <c:otherwise>${form.name}</c:otherwise>
                                        </c:choose>
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:forEach>
            </div>
        </div>

        <div id="form-container" class="w-4/5 pl-1">
            <%
                if (model != null && model.getAttribute("form") != null) {
                    API.embedFormJava(
                            request,
                            out,
                            (String) model.getAttribute("app"),
                            (String) model.getAttribute("form"),
                            (String) model.getAttribute("action"),
                            null,
                            null,
                            headers
                    );
                } else {
            %>

            <div class="orbeon">
                <h1>Orbeon Forms - No Form Selected</h1>
                <c:choose>
                    <c:when test="${groupedForms.size() > 0}">
                        <p>Select a from from the list available on the left</p>
                    </c:when>
                    <c:otherwise><p>You do not have any forms available.</p></c:otherwise>
                </c:choose>
            </div>

            <% } %>
        </div>
    </div>
</div>

<%@include file="parts/foot.jsp" %>
