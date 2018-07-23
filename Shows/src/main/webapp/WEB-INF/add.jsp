<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/33.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>New Show</title>
</head>
<body>
    <div class="formBox">
        <h3>New Show</h3>
        <form:form action="/shows/new" method="post" methodAttribute="show">
            <p>
                <form:label path="title">Title</form:label>
                <form:input type="text" path="title"/>
            </p>
            <p>
                <form:label path="network">Network</form:label>
                <form:input type="text" path="network"/>
            </p>
            <button class="btn">Submit</button>
        </form:form>
    </div>
</body>
</html>