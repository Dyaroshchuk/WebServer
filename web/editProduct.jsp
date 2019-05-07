<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EditClient</title>
    <link rel="stylesheet" href="http://localhost:8082/myStyle.css">
</head>
<body>
<h1>Edit Client</h1>
<form action="edit" method="post">
    ID <input type="text" name="productId" value="${product.getId()}" readonly><br><br>
    Name <input type="text" name="name" value="${product.getName()}"><br><br>
    Description <input type="text" name="description" value="${product.getDescription()}"><br><br>
    Price <input type="text" name="price" value="${product.getPrice()}"><br><br>
    <input type="submit" value="save"/>
</form>
</body>
</body>
</html>
