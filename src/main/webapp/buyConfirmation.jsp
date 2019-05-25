<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Buy Confirmation</title>
    <link rel="stylesheet" href="http://localhost:8082/myStyle.css">
</head>
<body>
<h3>Buy Confirmation</h3>
<form action="/buy" method="post">
    <input hidden type="text" name="orderId" value="${orderId}">
    <input type="password" title="password" name="buyCodeConfirmation">
    <input type="submit" value="confirm">
    <h4><c:out value="${error}"></c:out></h4>
</form>

</body>
</html>
