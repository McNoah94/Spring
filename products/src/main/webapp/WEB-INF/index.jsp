<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>We gots us some products up in hurr</h1>
    <div class='formBox' style="display: flex; justify-content: space-around;">
        <div class="productForm">
            <h1>New product</h1>
            <form:form action="/product" method="post" modelAttribute="product">
                <p>
                    <form:label path="name">Name:</form:label><br>
                    <form:errors path="name"/>
                    <form:input path="name"/>
                </p>
                <p>
                    <form:label path="price">Price:</form:label><br>
                    <form:errors path="price"/>
                    <form:input min="0" steps="0.01" type="number" path="price"/>
                </p>
                <p>
                    <form:label path="description">Description:</form:label><br>
                    <form:errors path="description"/>
                    <form:textarea path="description"/>
                </p>
                <button>Submit</button>
            </form:form>
        </div>

        <div class="categoryForm">
            <h1>New Category</h1>
            <form:form action="/product" method="post" modelAttribute="product">
                <p>
                    <form:label path="name">Name:</form:label>
                    <form:errors path="name"/>
                    <form:input path="name"/>
                </p>
            </form:form>
        </div>
    </div>
</body>
</html>