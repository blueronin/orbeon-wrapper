<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="head.jsp" %>

<div class="row">
    <div class="col-2 pr-0">
        <h5 class="title-settings capitalize">Project Forms</h5>
        <div class="panel-group" id="accordion">
            <c:forEach var="forms" items="${groupedForms}">
                <div class="panel">
                    <h5 class="title-settings p-2 text-capitalize">
                        <a data-toggle="collapse" href="#collapse-${forms.key}" role="button" class="title-settings border-0 p-2" aria-expanded="true" aria-controls="collapse-${forms.key}">
                            <span class="caret-success">&#x25B8;</span> ${forms.key}
                        </a>
                    </h5>
                    <div id="collapse-${forms.key}" class="collapse multi-collapse pl-3" data-parent="#accordion">
                        <ul class="nav nav-pills flex-column">
                            <c:forEach items="${forms.value}" var="form">
                                <li id="${form.canonicalName}" class="text-capitalize nav-item pr-4 orbeon-form-item">
                                    <a href="?form=${form.name}#${form.canonicalName}" class="nav-link">
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
                </div>
            </c:forEach>
        </div>
    </div>

    <div id="my-form" class="col-10 pl-0">
        <%
            API.embedFormJava(
                    request,
                    out,
                    "orbeon",
                    selectedForm,
                    "new",
                    null,
                    null,
                    headers
            );
        %>
    </div>
</div>

<%@include file="foot.jsp" %>
