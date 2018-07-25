<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/33.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Login and Registration</title>
</head>
<body>
    <div class="wrapper" style="display: flex; justify-content: space-around; width:50vw; margin-left:25vw">
        <div id="regBox" class="form-group">
            <h1>Register</h1>

            <p style="font-size: 10px; color:red;"><form:errors path="user.*"/></p>
            
            <form:form method="POST" action="/" modelAttribute="user">
                <p>
                    <form:label path="fname">First name:</form:label>
                    <form:input class="form-control" path="fname"/>
                </p>
                <p>
                    <form:label path="lname">Last name:</form:label>
                    <form:input class="form-control" path="lname"/>
                </p>
                <p>
                    <form:label path="email">Email:</form:label>
                    <form:input class="form-control" type="email" path="email"/>
                </p>
                <p>
                    <form:label path="password">Password:</form:label>
                    <form:input class="form-control" type="password" path="password"/>
                </p>
                <p>
                    <form:label path="passwordConfirmation">Password Confirmation:</form:label>
                    <form:password class="form-control" path="passwordConfirmation"/>
                </p>
                <input type="submit" class="btn" value="Register!" style="box-shadow: 2px 2px 5px grey;"/>
            </form:form>
        </div>

        <div id="logBox" class="form-group">
            <h1>Login</h1>
            <p style="color: green; font-size:12px;"><c:out value="${success}"/></p>
            <p style="color: red; font-size:12px;"><c:out value="${error}"/></p>
            <form method="post" action="/login">
                Email: <input type="text" name="logEmail" class="form-control"><br>
                Password: <input type="password" name="logPass" class="form-control"><br>
                <input type="submit" class="btn" value="Login" style="box-shadow: 2px 2px 5px grey;"/>
            </form>
        </div>
    </div>
</body>
</html>