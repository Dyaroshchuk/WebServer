<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order Details</title>
    <link rel="stylesheet" href="http://localhost:8082/myStyle.css">
</head>
<body>
<table border='1' width='70%'>
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Price</th>
        <c:forEach var="product" items ="${order}" >
    <tr><td>${product.getName()}</td>
        <td>${product.getDescription()}</td>
        <td>${product.getPrice()}</td>
    </tr>
    </c:forEach>
</table>
<form action="/buy" method="get">
    <input type="text" name="orderId" value="${orderId}" hidden readonly>
    <br><input type="submit" value="Buy Now!"/>
</form>
</body>
</html>
