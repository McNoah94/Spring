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
    <title>Edit</title>
</head>
<body>
    <h1>Edit "${task.getTitle()}"</h1>
    <div class="formBox">
                <p style="font-size: 10px; color:red;">${errors}</p>
        <form:form action="/tasks/${task.getId()}/edit" method="post" modelAttribute="task">
            <p>
                <form:label path="title">Title</form:label><br>
                <form:input path="title" value="${task.getTitle()}"/>
            </p>
            <p>
                <form:label path="assignee">Assignee</form:label><br>
                <form:select path="assignee">
                    <option value="${task.getAssignee()}">${task.getAssignee().getName()}</option>
                    <c:forEach items="${users}" var="u">
                        <c:if test="${task.getAssignee().getName() != u.getName()}">
                            <option value="${u.getId()}">${u.getName()}</option>
                        </c:if>
                    </c:forEach><br>
                </form:select>
            </p>
            <p>
                <form:label path="priority">Priority</form:label><br>
                <form:select value="${task.getPriority()}" path="priority">
                    <option value="${task.getPriority()}">${task.getPriority()}</option>
                    <c:if test="${task.getPriority() != 'Low'}">
                        <option value="Low">Low</option>
                    </c:if>
                    <c:if test="${task.getPriority() != 'Medium'}">
                        <option value="Medium">Medium</option>
                    </c:if>
                    <c:if test="${task.getPriority() != 'High'}">
                        <option value="High">High</option>
                    </c:if>
                </form:select>
            </p>
            <p>
                <form:input type="hidden" readonly="true" path="creator" value="${user.getId()}"/>
            </p>
            

            <button class="btn"> Submit</button>
        </form:form>
        <a href="/tasks">Back</a>
    </div>
</body>
</html>