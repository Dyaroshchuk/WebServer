<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Client List</title>
    <link rel="stylesheet" href="http://localhost:8082/myStyle.css">
</head>
<body>
<h1>Product List</h1>
<h3><c:out value="${message}"></c:out></h3>
<table border='1' width='100%'>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Description</th>
        <th>Price</th>
        <th>Edit</th>
        <th>Delete</th>
        <c:forEach var="product" items ="${products}" >
    <tr><td>${product.getId()}</td>
        <td>${product.getName()}</td>
        <td>${product.getDescription()}</td>
        <td>${product.getPrice()}</td>
        <td><a href="editProductForm?productId=${product.getId()}">edit</a></td>
        <td><a href="deleteProduct?productId=${product.getId()}">delete</a> </td>
    </tr>
    </c:forEach>
</table>
<form action="/admin">
    <br><input type="submit" value="Admin Page"/>
</form>
<form action="/admin/addProductForm">
    <br><input type="submit" value="Add Product"/>
</form>
</body>
</html>
