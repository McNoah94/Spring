<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Home</title>
	</head>
	<body>
		<p>You have visited this page <%= request.getSession().getAttribute("count") %> time(s).</p>
		<a href="/date">Click here for the date</a><br>
		<a href="/time">Click here for the time</a><br>
	</body>
</html>