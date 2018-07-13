<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Home</title>
		<link rel='stylesheet' href="css/style.css">
	</head>
	<body>
		<p>You have visited this page <%= request.getSession().getAttribute("count") %> time(s).</p>
		<a href="/date">Click here for the date</a><br>
		<a href="/time">Click here for the time</a><br>

		<p class='red'><c:out value="${error}"/></p>
		<div class='bushido'>
			<h2>What is the code?</h2>
			<form action="/check" method="post">
				<input type="text" name='code'>
				<button>Try Code</button>
			</form>
		</div>

		<h1>Log in here</h1>
		<div class='survey'>
			<form action="/survey" method="POST">
				Your name: <br><input type="text" name="name"><br>
				<select name="location">
					<option value="San Jose">San Jose</option>
					<option value="Mars">Mars</option>
					<option value="Tysons">Tysons</option>
				</select><br>
				<select name="language">
					<option value="Python">Python</option>
					<option value="Java">Java</option>
					<option value="English">English</option>
				</select><br>
				Comment(optional): <br>
				<textarea name="comment" cols="30" rows="10"></textarea><br>
				<button>Submit</button>
			</form>
		</div>

		<h1 onClick="javascript:window.location.href='/gold'">Play Ninja Gold</h1>
	</body>
</html>