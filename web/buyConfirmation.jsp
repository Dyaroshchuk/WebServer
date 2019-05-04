<%--
  Created by IntelliJ IDEA.
  User: ноут
  Date: 03.05.2019
  Time: 18:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Buy Confirmation</title>
</head>
<body>
<h3>Buy Confirmation</h3>
<form action="/buy" method="post">
    <input hidden type="text" name="good_id" value="${productId}">
    <input type="password" title="password" name="code">
    <input type="submit" value="confirm">
</form>

</body>
</html>
