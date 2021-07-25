<%@include file="head.jsp" %>

<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="brand" href="#">Orbeon Forms Embedding Demo</a>
            <div class="nav-collapse collapse">
                <ul class="nav">
                    <li><a href="?form=bookshelf&project=${projectId}">Bookshelf</a></li>
                    <li><a href="?form=dmv-14&project=${projectId}">DMV-14</a></li>
                    <li><a href="?form=w9&project=${projectId}">W-9</a></li>
                    <li><a href="?form=controls&project=${projectId}">Controls</a></li>
                    <li><a href="?form=contact&project=${projectId}">Contact</a></li>
                    <li><a href="?form=builder&project=${projectId}">Form Builder</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>

<div id="my-form" class="container">
    <%
        if (selectedForm.equals("builder")) {
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
        }
    %>
</div>

<%@include file="foot.jsp" %>
