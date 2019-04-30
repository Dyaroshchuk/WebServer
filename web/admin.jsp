<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Page</title>
</head>
<body>
<style>
    h3, form{
        text-align: center;
    }
</style>
<h3><c:out value="${welcome}"></c:out></h3>
<form action="clientList">
    <br><input type="submit" value="Client List"/>
</form>
</body>
</html>
