<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <meta charset="UTF-8">
    <title>購物車</title>
</head>
<body>
    <h1>購物車</h1>
    <c:choose>
        <c:when test="${empty cartItems}">
            <p>你的購物車是空的。</p>
            <a href="../Project/Product">前往購物</a>
        </c:when>
        <c:otherwise>

            <table border="1">
                <tr>
                    <th>商品名稱</th>
                    <th>價格</th>
                    <th>數量</th>
                    <th>總價</th>
					<th>操作</th>
                </tr>
                <c:forEach items="${cartItems}" var="item">
                    <tr>
                        <td>${item.product.productName}</td>
						<td>${item.product.price}</td>
                        <td>
                        <form id="updateForm_${item.cartItemId}" action="UpdateQuantity" method="post">
				            <input type="hidden" name="itemId" value="${item.cartItemId}">
				            <button type="button" class="decrement" data-id="${item.cartItemId}">-</button>
				            <input type="number" id="quantity_${item.cartItemId}" name="quantity" value="${item.quantity}" min="1" max="10">
				            <button type="button" class="increment" data-id="${item.cartItemId}">+</button>
        				</form>
                        </td>
						<td>${item.quantity * item.product.price}</td>
							
                        <td><button type="button" class="delete" data-id="${item.cartItemId}">删除</button></td>
                    	
                    </tr>
                </c:forEach>
            </table>
            <c:set var="totalPrice" value="0" />
    		<c:forEach items="${cartItems}" var="item">
        		<c:set var="subtotal" value="${item.quantity * item.product.price}" />
        		<c:set var="totalPrice" value="${totalPrice + subtotal}" />
    		</c:forEach>
    		<p>總共金額: ¥${totalPrice}</p>
    		<a href="../Project/Payment">去買單</a>
    		<a href="../Project/Product">繼續購物</a>    		
        </c:otherwise>
    </c:choose>
    <script>
    $(document).ready(function() {
    	$(document).on('click', '.increment', function() {
            var itemId = $(this).data('id');
            var input = $('#quantity_' + itemId);
            var newValue = parseInt(input.val()) + 1;
            if (newValue <= 10) {
                input.val(newValue);
                updateQuantity(itemId, newValue);
            }
        });

    	$(document).on('click', '.decrement', function() {
            var itemId = $(this).data('id');
            var input = $('#quantity_' + itemId);
            var newValue = parseInt(input.val()) - 1;
            if (newValue >= 1) {
                input.val(newValue);
                updateQuantity(itemId, newValue);
            }
        });

    });
        $('input[name="quantity"]').on('change', function() {
            var itemId = $(this).attr('id').split('_')[1];
            var quantity = $(this).val();
            updateQuantity(itemId, quantity);
        });

        $('.delete').on('click', function() {
            var itemId = $(this).data('id');
            var confirmation = confirm('確定要刪除此商品嗎？');
            if (confirmation) {
                $.ajax({
                    type: "POST",
                    url: "DeleteCartItem",
                    data: { itemId: itemId },
                    success: function(data) {
                        location.reload(); 
                    },
                    error: function(xhr, status, error) {
                        console.error(xhr.responseText);
                    }
                });
            }
        });

    function updateQuantity(itemId, quantity) {
        $.ajax({
            type: "POST",
            url: "UpdateQuantity",
            data: { itemId: itemId, quantity: quantity },
            success: function(data) {
                location.reload(); 
            },
            error: function(xhr, status, error) {
                console.error(xhr.responseText);
            }
        });
    }
</script>

</body>
</html>