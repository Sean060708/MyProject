<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品列表</title>
</head>
<body>
	<a href="../Project/ShoppingCar">前往購物車</a>
	<h1>商品列表</h1>
    <table border="1">
        <tr>
            <th>產品ID</th>
            <th>賣家ID</th>
            <th>產品名稱</th>
            <th>價格</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${products}" var="product">
            <tr>
                <td>${product.productId}</td>
                <td>${product.sellerId}</td>
                <td>${product.productName}</td>
                <td>${product.price}</td>
                <td><button onclick="confirmAddToCart(${product.productId}, '${product.productName}')">增加到購物車</button></td>
            </tr>
        </c:forEach>
    </table>
   
    <script>
    function confirmAddToCart(productId, productName) {
        var confirmed = confirm("確定要將商品 '" + productName + "' 添加到購物車嗎？");
        if (confirmed) {
            addToCart(productId);
        }
    }
    function addToCart(productId) {
        var xhr = new XMLHttpRequest();
        var url = "CartItem?productId=" + productId;
        xhr.open("GET", url, true);
        xhr.send();
    }
</script>
</body>
</html>