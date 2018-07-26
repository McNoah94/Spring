<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/33.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Tasks</title>
</head>
<body style=" width: 50%;">
	<div class="header" style="display: flex; justify-content: space-around;">
        <h1>Hello, ${use.getName()}</h1>
        <a href="/logout">Log out</a>
    </div>
    <div class="tableBox">
        <table class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th>Task</th>
                    <th>Creator</th>
                    <th>Assignee</th>
                    <th>Priority</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${tasks}" var="task">
                    <c:if test="${task.isCompleted() == false}">
                        <tr>
                            <td><a href="/tasks/${task.getId()}">${task.getTitle()}</a></td>
                            <td>${task.getCreator().getName()}</td>
                            <td>${task.getAssignee().getName()}</td>
                            <td>${task.getPriority()}</td>
                        </tr>
                    </c:if>
                </c:forEach>
            </tbody>
        </table>
        <a href="/tasks/new" style="display: flex; float: right;"><button class="btn">Create task</button></a>
    </div>
</body>
</html>