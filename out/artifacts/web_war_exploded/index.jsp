<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
<style>
    h1, h3, h4, form {
        text-align: center;
    }
</style>
<h1>Home Page</h1>

<form action="login" method="post">
    login <input type="text" name="login"><br><br>
    password <input type="password" name="password" required="required"><br><br>
    <input type="submit" value="Sign In"/>
</form>
<h4 style="color: indianred"><c:out value="${error}"></c:out></h4>
<c:if test="${login != null}">
<h3 style="color: green"><c:out value="${login}, you have successfully registered!"></c:out></h3>
</c:if>
<form action="registration">
    Don't have an account?<br><input type="submit" value="Sign Up"/>
</form>

</body>
</html>
