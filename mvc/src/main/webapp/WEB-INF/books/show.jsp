<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${book.getTitle()}</title>
</head>
<body>
    <h1>${book.getTitle()}</h1>
    <h3>Description</h3>
    <p>${book.getDescription()}</p>
    <p>${book.getNumberOfPages()} pages.</p>
    <p>Language: ${book.getLanguage()}</p>
    <a href="/books/${book.id}/edit">Edit Book</a>
    <form action="/books/${book.id}" method="post">
        <input type="hidden" name="_method" value="delete">
        <input type="submit" value="Delete">
    </form>
</body>
</html>