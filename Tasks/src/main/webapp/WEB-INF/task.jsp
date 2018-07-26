<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>${task.getTitle()}</title>
</head>
<body>
    <div style="display: flex; justify-content: space-around; width:25%;">
        <h1>${task.getTitle()}</h1>
        <a href="/tasks">Back</a>
    </div>
    <p>Creator: ${task.getCreator().getName()}</p>
    <p>Assignee: ${task.getAssignee().getName()}</p>
    <p>Priority: ${task.getPriority()}</p>
    
    <c:if test="${task.getCreator().getId() == use.getId()}">
        <div>
            <a href="/tasks/${task.getId()}/edit"><button class="btn">Edit</button></a>
            <a href="/tasks/${task.getId()}/remove"><button class="btn">Delete</button></a><br><br>
        </div>
    </c:if>
    <c:if test="${task.getAssignee().getId() == use.getId()}">
        <a href="/tasks/${task.getId()}/complete"><button class="btn">Completed</button></a>
    </c:if>
</body>
</html>