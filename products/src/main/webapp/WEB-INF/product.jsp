<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${product.getName()}</title>
</head>
<body style="width: 50vw; margin-left: 25vw">
    <h1>${product.getName()}</h1>
    <h3>${product.getDescription()}</h3>
    <h3>$${product.getPrice()}</h3>
    <div class="catBoxes" style="display: flex; justify-content:space-around;">
        <div class="listBox">
            <ul>
                <c:forEach items="${product.getCategories()}" var="categoryVar">
                    <li>${categoryVar.name}</li>
                </c:forEach>
            </ul>
        </div>
        <div class="formBox">
            <form:form action="/product/${product.getId()}" method="post" modelAttribute="category1">
                <form:label path="name">Category</form:label>
                <form:errors path="name"/>
                <form:select path="name">
                    <c:forEach items="${categories}" var="category">
                        <p>
                            <form:option value="${category.getId()}">${category.getName()}</form:option>
                        </p>
                    </c:forEach>
                </form:select>
                <button>Submit</button>
            </form:form>
        </div>
    </div>
</body>
</html>