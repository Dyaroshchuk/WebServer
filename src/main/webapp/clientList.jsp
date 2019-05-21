<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Client List</title>
    <link rel="stylesheet" href="http://localhost:8082/myStyle.css">
</head>
<body>
<a href="admin">Admin page</a>
<h1>Clients List</h1>
<h3><c:out value="${error}"></c:out></h3>
<h3><c:out value="${message}"></c:out></h3>
<table border='1' width='100%'>
    <tr>
        <th>Name</th>
        <th>Password</th>
        <th>Email</th>
        <th>Edit</th>
        <th>Delete</th>
    <c:forEach var="client" items ="${clients}" >
    <tr><td>${client.getLogin()}</td>
    <td>${client.getPassword()}</td>
    <td>${client.getEmail()}</td>
    <td><a href="editTable?name=${client.getLogin()}">edit</a></td>
        <td><a href="delete?name=${client.getLogin()}">delete</a> </td>
    </tr>
    </c:forEach>
</table>
<a href="addClient.jsp">Add Client</a>
</body>
</html>