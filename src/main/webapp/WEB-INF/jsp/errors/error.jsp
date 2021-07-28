<%@ taglib prefix="th" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: reiosantos
  Date: 24/07/2021
  Time: 23:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Error Occurred</title>
</head>
<body>

<h1>Something went wrong! ${statusCode}</h1>
<h2>An error has occurred. Please contact the administrator; - template generic</h2>
<a href="<%= request.getContextPath().trim() %>">Go Home</a>

<h3>Message: ${errorMessage}</h3>
</body>
</html>
