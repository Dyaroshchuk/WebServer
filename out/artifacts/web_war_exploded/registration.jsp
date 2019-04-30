<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
</head>
<body>
<style>
    h1 {
        text-align: center;
    }
    form {
        text-align: center;
    }
</style>
<h1>Registration Form</h1>
<form action="registration" method="post">
    login <input type="text" name="login"/><br/><br>
    password <input type="password" name="password" required="required"><br/><br>
    <input type="submit"/>
</form>
</body>
</html>
