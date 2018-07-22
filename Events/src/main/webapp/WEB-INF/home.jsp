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
    <title>Home</title>
</head>
<body>
    <div id="wrapper" style="display: flex; flex-direction: column; width: 50vw; margin-left:25vw;">
        <div class="headBox" style="display: flex; justify-content: space-around;">
            <h1>Welcome, ${user.getFname()}.</h1>
            <a href="/logout">Logout</a>
        </div>
        <p>${attr}</p>

        <div class="stateBox">
            <h3>Here are some events in your state</h3>
            
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Date</th>
                        <th>Location</th>
                        <th>Host</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${instate}" var="e">
                            <tr>
                                <td><a href="/events/${e.getId()}">${e.getName()}</a></td>
                                <td><fmt:formatDate pattern="MMMMMM dd, yyyy" value="${e.getDate()}"/></td>
                                <td>${e.getLocation()}</td>
                                <td>${e.getHost().getFname()}</td>
                                <c:if test="${!e.getAttendees().contains(user)}">
                                    <td><a href="/events/${e.getId()}/join">Join</a></td>
                                </c:if>
                                <c:if test="${e.getAttendees().contains(user)}">
                                    <td><a href="/events/${e.getId()}/cancel">Cancel</a></td>
                                </c:if>
                            </tr>
                        </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="stateBox2">
            <h3>Here are some events that are outside your state</h3>
            <table class="table table-striped table-bordered">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Date</th>
                            <th>Location</th>
                            <th>Host</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${outstate}" var="e">
                            <tr>
                                <td><a href="/events/${e.getId()}">${e.getName()}</a></td>
                                <td><fmt:formatDate pattern="MMMMMM dd, yyyy" value="${e.getDate()}"/></td>
                                <td>${e.getLocation()}</td>
                                <td>${e.getHost().getFname()}</td>
                                <c:if test="${!e.getAttendees().contains(user)}">
                                    <td><a href="/events/${e.getId()}/join">Join</a></td>
                                </c:if>
                                <c:if test="${e.getAttendees().contains(user)}">
                                    <td><a href="/events/${e.getId()}/cancel">Cancel</a></td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
        </div>

        <div class="formBox" style="width:20%;">
            <h4>Create a new event</h4>

            <c:if test="${errors != null}">
                <c:forEach items="${errors}" var="error">
                        <p style="font-size: 10px; color:red;">${error.defaultMessage}</p>
                </c:forEach>
            </c:if>
                
            <form:form method="post" action="/events" modelAttribute="event">
                <p>
                    <form:label path="name">Event name</form:label><br>
                    <form:input type="text" path="name" class="form-control"/>
                </p>
                <p>
                    <label for="date">Date</label>
                    <form:input type="date" id="date" path="date" class="form-control"/>
                </p>
                <div style="display:flex;">
                    <p>
                        <form:label path="location">Location</form:label><br>
                        <form:input class="form-control" path="location"/>
                    </p>
                    <p>
                        <form:label path="state">State</form:label><br>
                        <form:select class="form-control" path="state">
                            <c:forEach items="${states}" var="aState">
                                <form:option value="${aState}" path="state">${aState}</form:option>
                            </c:forEach>
                        </form:select>
                    </p>
                </div>
                <form:input type="hidden" value="${user.getId()}" path="host"/>
                <input type="submit" class="btn"/>
            </form:form>
        </div>
    </div>
</body>
</html>