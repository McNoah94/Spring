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
    <title>New Task</title>
</head>
<body>
    <h1>New Task</h1>
    <div class="formBox">
            <p style="font-size: 10px; color:red;">${errors}</p>
            <p style="font-size: 10px; color:red;"><form:errors path="task.*"/></p>
        <form:form action="/tasks/new" method="post" modelAttribute="task">
        
        
            <p>
                <form:label path="title">Title</form:label><br>
                <form:errors path="title"/>
                <form:input path="title"/>
            </p>
            <p>
                <form:label path="assignee">Assignee</form:label><br>
                <form:select path="assignee">
                    <c:forEach items="${users}" var="u">
                        <option value="${u.getId()}">${u.getName()}</option>
                    </c:forEach><br>   
                </form:select>
            </p>
            <p>
                <form:label path="priority">Priority</form:label><br>
                <form:select path="priority">
                    <option value="Low">Low</option> 
                    <option value="Medium">Medium</option> 
                    <option value="High">High</option> 
                </form:select>
            </p>
            <p>
                <form:input type="hidden" readonly="true" path="creator" value="${user.getId()}"/>
            </p>

            <button class="btn"> Submit</button>
        </form:form>
        <a href="/tasks"><button class="btn">Back</button></a>
    </div>
</body>
</html>