<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/33.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Shows</title>
</head>
<body>
    <div class="headerBox" style="display: flex; justify-content: space-around;">
        <h1>Hello, ${user.getName()}</h1>
        <a href="/logout">Logout</a>
    </div>
    
    <div class="tvBox">
        <h3>TV Shows</h3>
        <table class="table striped-table">
            <thead>
                <tbody>
                    <tr>
                        <th>Title</th>
                        <th>Network</th>
                        <th>Avg Rating</th>
                    </tr>
                </tbody>
            </thead>
        </table>
    </div>
    <a href="http://localhost:8080/shows/new/">Add show</a>
</body>
</html>