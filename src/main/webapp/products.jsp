<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Personal Area</title>
    <link rel="stylesheet" href="http://localhost:8082/myStyle.css">
</head>
<body>
<h3><c:out value="${welcome}"></c:out></h3>
<h4><c:out value="${approved}"></c:out></h4>
<table border='1' width='70%'>
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Price</th>
        <th>Add to Cart</th>
        <c:forEach var="product" items ="${products}" >
    <tr><td>${product.getName()}</td>
        <td>${product.getDescription()}</td>
        <td>${product.getPrice()}</td>
        <td><a href="addToOrder?id=${product.getId()}">Add to Cart</a> </td>
    </tr>
    </c:forEach>
</table>
<form action="/orderDetails">
    <br><input type="submit" value="Cart Items ${order}">
</form>
<h3><c:out value="${message}"></c:out></h3>
</body>
</html>
