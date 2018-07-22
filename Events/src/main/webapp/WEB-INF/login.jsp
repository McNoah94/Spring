<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Login</title>
</head>
<body>
    <div id="logBox" class="form-group">
        <h1>Login</h1>
        <p style="color: red; font-size:12px;"><c:out value="${error}"/></p>
        <form method="post" action="/login">
            Email: <input type="text" name="logEmail" class="form-control"><br>
            Password: <input type="password" name="logPass" class="form-control"><br>
            <input type="submit" class="btn" value="Login" style="box-shadow: 2px 2px 5px grey;"/>
        </form>
    </div>
</body>
</html>