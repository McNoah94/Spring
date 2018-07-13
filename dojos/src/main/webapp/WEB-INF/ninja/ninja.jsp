<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Ninja</title>
</head>
<body>
    <h1>Make a new Ninja</h1>
    <form:form action="/ninja" method="post" modelAttribute="ninja">
        <p>
            <form:label path="firstName">First Name:</form:label><br>
            <form:errors path="firstName"/>
            <form:input path="firstName"/>
        </p>
        <p>
            <form:label path="lastName">Last Name:</form:label><br>
            <form:errors path="lastName"/>
            <form:input path="lastName"/>
        </p>
        <p>
            <form:label path="age">Age:</form:label><br>
            <form:errors path="age"/>
            <form:input type="number" path="age"/>
        </p>
        <p>
            <form:label path="dojo">Dojo:</form:label><br>
            <form:errors path="dojo"/>
            <form:select type="number" path="dojo">
                <c:forEach items="${dojos}" var="dojo">
                    <form:option value="${dojo.id}">${dojo.name}</form:option>
                </c:forEach>
            </form:select>
        </p>
        <button>Submit</button>
    </form:form>
    <a href="/">Home</a>
</body>
</html>