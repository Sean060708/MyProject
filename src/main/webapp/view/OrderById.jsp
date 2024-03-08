<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <title>訂單詳情</title>
</head>
<body>
    <h1>訂單詳情</h1>
    <table border="1">
        <tr>
            <th>訂單ID</th>
            <th>買家ID</th>
            <th>賣家ID</th>
            <th>運送方式</th>		        
            <th>收件人姓名</th>
            <th>收件人地址</th>
            <th>收件人電話</th>
            <th>商品名稱</th>
            <th>數量</th>
            <th>訂單狀態</th>
            <th>支付狀態</th>
            <th>支付金額</th>
            <th>創建時間</th>
            <th>操作</th>
		    <th>操作</th>
        </tr>
        <tr>
            <td>${order.orderId}</td>
            <td>${order.buyerId}</td>
            <td>${order.sellerId}</td>
            <td>${order.shippingMethod == 1 ? '宅配到家' : order.shippingMethod == 2 ? '超商物流' : ''}</td>
            <td>${order.shippingName}</td>
            <td>${order.shippingAddress}</td>
            <td>${order.shippingTel}</td>
            <td>${order.productName}</td>
            <td>${order.quantity}</td>
            <td>${order.orderStatus == 0 ? '已成立' : order.orderStatus == 1 ? '已出貨' : order.orderStatus == 2 ? '已完成' : ''}</td>
            <td>${order.paymentStatus == 0 ? '未付款' : order.paymentStatus == 1 ? '已付款' : ''}</td>
            <td>${order.paymentAmount}</td>
            <td>${order.createdAt}</td>
            <td><a href="#">修改</a></td>
			<td><a href="#">刪除</a></td>
        </tr>
    </table>
</body>
</html>
