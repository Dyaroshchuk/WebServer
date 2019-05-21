<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Page</title>
    <link rel="stylesheet" href="http://localhost:8082/myStyle.css">
</head>
<body>
<h3><c:out value="${welcome}"></c:out></h3>
<h3><c:out value="${error}"></c:out></h3>
<form action="/adim/clientList">
    <br><input type="submit" value="Client List"/>
</form>
<form action="/admin/productList">
    <br><input type="submit" value="Product List">
</form>
</body>
</html>
