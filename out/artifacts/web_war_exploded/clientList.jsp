<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Client List</title>
</head>
<body>
<a href="index.jsp">Home page</a>
<h1>Clients List</h1>
<table border='1' width='100%'>
    <tr>
        <th>Name</th>
        <th>Password</th>
        <th>Edit</th>
        <th>Delete</th>
    <c:forEach var="client" items ="${clients}" >
    <tr><td>${client.getLogin()}</td>
    <td>${client.getPassword()}</td>
    <td><a href="editTable?name=${client.getLogin()}">edit</a></td>
        <td><a href="delete?name=${client.getLogin()}">delete</a> </td>
    </tr>
    </c:forEach>
</table>
</body>
</html>