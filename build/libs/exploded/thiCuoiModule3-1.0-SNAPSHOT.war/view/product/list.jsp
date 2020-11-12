<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer List</title>
</head>
<body>
<h1>Product</h1>
<p>
    <a href="/welcome?action=create">Create new Product</a>
</p>
<div>
    <form method="post" action="/welcome?action=seach">
        <p>Seach Name:</p>
        <input type="text" name="name">
        <input type="submit" value="Seach">
    </form>
</div>
<table border="1">
    <tr>
        <td>name</td>
        <td>price</td>
        <td>quantity</td>
        <td>color</td>
        <td>description</td>
        <td>category</td>
        <td>Edit</td>
        <td>Delete</td>
    </tr>
    <c:forEach items='${requestScope["productList"]}' var="product">
        <tr>
            <td>${product.getName()}</td>
            <td>${product.getPrice()}</td>
            <td>${product.getQuantity()}</td>
            <td>${product.getColor()}</td>
            <td>${product.getDescription()}</td>
            <td>${product.getCategory().getName()}</td>
            <td><a href="/welcome?action=edit&id=${product.getId()}">edit</a></td>
            <td><a href="/welcome?action=delete&id=${product.getId()}">delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>