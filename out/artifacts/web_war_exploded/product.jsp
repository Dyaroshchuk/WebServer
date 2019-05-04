<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Personal Area</title>
    <link rel="stylesheet" href="http://localhost:8082/myStyle.css">
</head>
<body>
<h3><c:out value="${welcome}"></c:out></h3>
<table border='1' width='70%'>
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Price</th>
        <th>Buy</th>
        <c:forEach var="product" items ="${products}" >
    <tr><td>${product.getName()}</td>
        <td>${product.getDescription()}</td>
        <td>${product.getPrice()}</td>
        <td><a href="buy?id=${product.getId()}">Buy</a> </td>
    </tr>
    </c:forEach>
</table>
</body>
</html>
