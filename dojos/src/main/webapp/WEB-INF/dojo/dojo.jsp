<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Dojo</title>
</head>
<body>
    <h1>Make a new Dojo</h1>
    <form:form action="/dojo" method="post" modelAttribute="dojo">
        <p>
            <form:label path="name">Name:</form:label><br>
            <form:errors path="name"/>
            <form:input path="name"/>
        </p>
        <button type="submit">Submit</button>
    </form:form>
    <a href="/">Home</a>
</body>
</html>