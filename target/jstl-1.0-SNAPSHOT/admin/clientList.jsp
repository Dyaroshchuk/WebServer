<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Client List</title>
    <link rel="stylesheet" href="http://localhost:8082/myStyle.css">
</head>
<body>
<h1>Clients List</h1>
<h3><c:out value="${message}"></c:out></h3>
<table border='1' width='100%'>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Password</th>
        <th>Email</th>
        <th>Edit</th>
        <th>Delete</th>
    <c:forEach var="client" items ="${clients}" >
    <tr><td>${client.getId()}</td>
    <td>${client.getLogin()}</td>
    <td>${client.getPassword()}</td>
    <td>${client.getEmail()}</td>
    <td><a href="editClientForm?clientId=${client.getId()}">edit</a></td>
    <td><a href="deleteClient?clientId=${client.getId()}">delete</a></td>
    </tr>
    </c:forEach>
</table>
<form action="/admin">
    <br><input type="submit" value="Admin Page"/>
</form>
<form action="/admin/addClientForm">
    <br><input type="submit" value="Add Client"/>
</form>

</body>
</html>