<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <title>${event.getName()}</title>
</head>
<body>
    <div id="wrapper" style="display: flex; flex-direction: column;justify-content: space-around; width: 50vw;margin-left: 25vw;text-align: center;">
        <div class="header" style="display: flex; justify-content: space-between;">
            <h1>${event.getName()}</h1>
            <a href="/home" style="margin-top:25px;">Back</a>
        </div>
        <div style="display: flex; justify-content: space-around; margin-top:25px;">
            <div class="eventBox">
                <div style="display:flex;">
                    <h4>Host:</h4>
                    <p style="margin-top:12px; margin-left: 15px;">${event.getHost().getFname()} ${event.getHost().getLname()}</p>
                </div>
                <div style="display:flex;">
                    <h4>Date:</h4>
                    <p style="margin-top:12px; margin-left: 15px;"><fmt:formatDate pattern="MMMMMM dd, yyyy" value="${event.getDate()}"/></p>
                </div>
                <div style="display:flex;">
                        <h4>Location:</h4>
                        <p style="margin-top:12px; margin-left: 15px;">${event.getLocation()}, ${event.getState()}</p>
                    </div>
                <div><br>
                    <h4>Attendees</h4>
                    <table class="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Location</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${event.getAttendees()}" var="user">
                                <tr>
                                    <td>${user.getFname()} ${user.getLname()}</td>
                                    <td>${user.getLocation()}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="msgBox">
                <h1>Messages</h1>
                <div class="messages" style="height: 50%; width: 100%; overflow-y: auto; border: 1px solid black; border-radius: 15px;">
                    
                </div>
                <div class="formBox" style="display: flex; flex-direction: column;">
                        <form:form method="post" action="/events/${event.getId()}" modelAttribute="msg">
                            <form:label path="content">Post a message:</form:label><br>
                            <form:errors path="content"/>
                            <form:textarea path="content"/><br>
                            <input type="submit" class="btn" value="Submit"/>
                            <form:input type="hidden" path="event" value="${event}"/>
                        </form:form>
                    </div>
            </div>
        </div>
    </div>
</body>
</html>