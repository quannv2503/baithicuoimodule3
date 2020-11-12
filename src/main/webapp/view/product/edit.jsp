<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Product Management Application</title>
</head>
<body>
<center>
    <h1>Product Management</h1>
    <h2>
        <a href="/welcome?action=product">List All Product</a>
    </h2>
</center>
<div align="center">
    <form method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    Edit Product
                </h2>
            </caption>
            <tr>
                <th> Name:</th>
                <td>
                    <input type="text" name="name" size="45"
                           value="<c:out value='${product.name}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>price:</th>
                <td>
                    <input type="text" name="price" id="price" size="45" value="<c:out value='${product.price}' />"/>
                </td>
            </tr>
            <tr>
                <th>quantity:</th>
                <td>
                    <input type="text" name="quantity" id="quantity" size="45" value="<c:out value='${product.quantity}' />"/>
                </td>
            </tr>
            <tr>
                <th>color:</th>
                <td>
                    <input type="text" name="color" id="color" size="45" value="<c:out value='${product.color}' />"/>
                </td>
            </tr>
            <tr>
                <th>description:</th>
                <td>
                    <input type="text" name="description" id="description" size="45" value="<c:out value='${product.description}' />"/>
                </td>
            </tr>
            <tr>
                <th>category:</th>
                <td>
                    <select name="category_id">
                        <option value="1">Quần áo</option>
                        <option value="2">Giầy dép</option>
                        <option value="3">Túi xách</option>
                        <option value="4">Khác</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>