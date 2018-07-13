<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='stylesheet' href="css/gold.css">
<title>Ninja Gold</title>
</head>
<body>
	<div class='wrapper'>
        <div class='goldBox'>
            Your Gold: <textarea name="gold" cols="10" rows="1" readonly>${gold}</textarea>
        </div><br>
        <div class='boxes'>
            <div class='box'>
                <h1>Farm</h1>
                <p>(earn 10-20 gold)</p>
                <form action="/addGold" method="POST">
                    <input type="hidden" value="farm" name="box">
                    <button>Find Gold!</button>
                </form>
            </div>

            <div class='box'>
                <h1>Cave</h1>
                <p>(earn 5-10 gold)</p>
                <form action="/addGold" method="POST">
                    <input type="hidden" value="cave" name="box">
                    <button>Find Gold!</button>
                </form>
            </div>

            <div class='box'>
                <h1>House</h1>
                <p>(earn 2-5 gold)</p>
                <form action="/addGold" method="POST">
                    <input type="hidden" value="house" name="box">
                    <button>Find Gold!</button>
                </form>
            </div>

            <div class='box'>
                <h1>Casino</h1>
                <p>(earn/lose 0-50 gold)</p>
                <form action="/addGold" method="POST">
                    <input type="hidden" value="casino" name="box">
                    <button>Gamble!</button>
                </form>
            </div>
        </div>
        <div "actBox">
            <h1>Activities:</h1>
            <div class="acts">
                <c:forEach var="s" items="${activities}">
                    <p class='grn'><c:out value="${s}"/></p>
                </c:forEach>
            </div>
        </div>
    </div>
</body>
</html>