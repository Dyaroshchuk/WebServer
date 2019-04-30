<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EditClient</title>
</head>
<body>
<style>
    h1, form {
        text-align: center;
    }
</style>
<h1>Edit Client</h1>
<form action="edit" method="post">
    login <input type="text" name="login" value="${client.getLogin()}"><br><br>
    password <input type="password" name="password" required="required" value="${client.getPassword()}"><br><br>
    <input type="submit" value="save"/>
</form>
</body>
</body>
</html>
