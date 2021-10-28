<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="parts/head.jsp" %>

<div class="flex flex-col">
    <div class="flex flex-row pb-4 items-center">
        <div class="flex flex-grow"></div>

        <a class="btn btn-primary" href="<%=contextPath%>/forms/orbeon/builder">
            <i class="fa fa-list text-black"></i> Form Builder Summary
        </a>
    </div>

    <div data-role="flex flex-col">
        <c:forEach items="${forms}" var="form">
            <div class="flex flex-row bg-white rounded-lg py-2 my-3">
                <!-- INFO AND LABELS -->
                <div class="w-1/2">
                    <div class="flex flex-row">
                        <div class="p-4 items-center check-box-list-item">
                            <label>
                                <input type="checkbox" value="${form.canonicalName}" class="hover-show"
                                       data-action="select"/>
                            </label>
                        </div>

                        <div data-role="thumbnail-holder" class="pr-2">
                            <a href="<%=contextPath%>/forms/${form.application}/${form.name}/summary"
                               class="list-group-item-thumbnail"
                               style="background-image: url('<spring:url value="/img/form.png"/>');"
                               title="">
                            </a>
                        </div>

                        <div class="flex flex-col">
                            <div class="flex flex-row">
                                <p style="margin: 0; white-space: nowrap; overflow: hidden; text-overflow: ellipsis; max-width: 85%;"
                                   class="left">
                                    <a href="<%=contextPath%>/forms/${form.application}/${form.name}/summary"
                                       class="rev-file-link"
                                       title="${form.canonicalName}">${form.name}, ${form.application}
                                    </a>
                                </p>
                                <span>&nbsp;&nbsp;&nbsp;${form.versionCount} version(s)</span>
                            </div>

                            <div class="flex flex-row">
                                <p style="margin: 0; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
                                    <small>
                                        <span>#${form.version}</span>
                                        <span>&nbsp;&nbsp;&nbsp;${form.title[0].text}</span>
                                    </small>
                                </p>
                            </div>
                            <div class="flex flex-row">
                                <p style="margin: 0; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
                                    <small>
                                        <span>${form.description[0].text}</span>
                                    </small>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- ACTIONS -->
                <div class="w-1/6 text-center">
                    <small>
                        <span data-role="uploaded-time"
                              class="text-muted">Modified ${form.timePassed(form.lastModifiedTime)}</span>
                    </small>
                </div>

                <div class="w-2/6 text-right action-section">
                    <span class="action-buttons float-left">
                        <a href="<%=contextPath%>/forms/${form.application}/${form.name}/new" data-toggle="tooltip"
                           data-placement="bottom" data-original-title="Print" class="mr-2 text-primary" data-action="new">
                            <i class="fa fa-plus-circle fa-2x"></i>
                        </a>
                        <a href="#" data-toggle="tooltip" data-placement="bottom" data-original-title="Share"
                           class="text-muted" data-action="share">
                            <i class="fa fa-share fa-2x"></i>
                        </a>
                    </span>

                    <div class="dropdown">
                        <button class="btn dropdown-toggle more-list" type="button"
                                id="list-item-dropdown-${form.canonicalName}" data-role="${form.canonicalName}"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                            <i class="fa fa-ellipsis-h"></i>
                        </button>
                        <ul class="dropdown-menu dropdown-menu-right" data-role="${form.canonicalName}"
                            aria-labelledby="list-item-dropdown-${form.canonicalName}">
                            <li><a href="<%=contextPath%>/forms/${form.application}/${form.name}/new"
                                   class="text-primary" data-action="new">New</a></li>
                            <li><a href="#"
                                   class="text-primary" data-action="share" data-source="dropdown">Share</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="<%=contextPath%>/forms/${form.application}/${form.name}/summary"
                                   data-action="edit" class="text-default">Summary</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#" class="text-danger" data-action="delete">Delete</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<%@include file="parts/foot.jsp" %>
