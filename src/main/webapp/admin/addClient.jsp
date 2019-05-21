<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Client</title>
</head>
<body>
<h1>Add Client</h1>
<form action="/add" method="post">
    login <input type="text" name="login"/><br><br>
    email <input type="text" name="email"><br><br>
    password <input type="password" name="password" required="required"><br><br>
    <h3>Client role</h3>
    <input type="radio" name="role" value="USER">User<br>
    <input type="radio" name="role" value="ADMIN">Admin<br>
    <input type="submit"/>
</form>
<h3><h3><c:out value="${message}"></c:out></h3></h3>
</body>
</html>
