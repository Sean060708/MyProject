<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>
    <h1>訂單商品</h1>
    <table border="1">
        <c:forEach items="${cartItems}" var="item">
        <tr>
            <th>商品名稱</th>
            <th>價格</th>	
            <th>数量</th>
            <th>總價</th>

        </tr>
            <tr>
                <td>${item.product.productName}</td>
                <td>${item.product.price}</td>
                <td>${item.quantity}</td>
                <td class="total-price">${item.quantity * item.product.price}</td>

            </tr>
            <tr>
            <td colspan="4"> 
                <form method="post" action="../Project/InsertShippingDetails"> 
                    <fieldset>
                        <%int userId = (int) request.getAttribute("userId");%>
						<input type="hidden" id="userId" name="userId" value="<%= userId %>">
				        <input type="hidden" name="cartItemId" value="${item.cartItemId}">
						<input type="hidden" name="productId" value="${item.productId}">               
                        <label for="name_${item.cartItemId}">收件人姓名:</label>
                            <input type="text" id="name_${item.cartItemId}" name="name_${item.cartItemId}" required><br>
                            <label for="address_${item.cartItemId}">收件人地址:</label>
                            <input type="text" id="address_${item.cartItemId}" name="address_${item.cartItemId}" required><br>
                            <label for="tel_${item.cartItemId}">收件人電話:</label>
                            <input type="tel" id="tel_${item.cartItemId}" name="tel_${item.cartItemId}" required><br>
                    	</fieldset>
                    	<fieldset>
                        	<legend>運送信息</legend>
                        	<label for="shippingMethod_${item.cartItemId}">運送方式:</label>
                           	<select id="shippingMethod_${item.cartItemId}" name="shippingMethod_${item.cartItemId}" required onchange="calculateShippingFee('${item.cartItemId}')">
                                <option value="">請選擇</option>
                                <option value="1">超商物流</option>
                                <option value="2">宅配到家</option>
                            </select><br>
                            <label for="shippingFee_${item.cartItemId}">運費:</label>
                            <input type="text" id="shippingFee_${item.cartItemId}" name="shippingFee_${item.cartItemId}" required readonly><br>                		
                		</fieldset>
                      		<button type="submit" class="shippingFormButton" style="display:none" >提交</button>
                	</form>
            	</td>
            </tr>
        </c:forEach>
    </table>
        <form method="post" action="../Project/InsertPaymentDetails">
        <fieldset>
            <legend>支付信息</legend>
            <%int userId = (int) request.getAttribute("userId");%>
				<input type="hidden" id="userId" name="userId" value="<%= userId %>">
                <label for="amount">金額:</label>
                <input type="text" id="amount" name="amount" required><br>
                <label for="paymentMethod">支付方式:</label>
                <select id="paymentMethod" name="paymentMethod" required>
                    <option value="">請選擇支付方式</option>
                    <option value="1">信用卡</option>
                    <option value="2">貨到付款</option>
                </select><br>
                <label for="status">狀態:</label>
                <input type="text" id="status" name="status" value="未付款" required readonly>
                <input type="hidden" id="statusValue" name="statusValue" value="0">
        		<br>
        		<button type="submit" id="paymentFormButton" style="display:none">下訂單</button>
        </fieldset>
    </form>
    		<a href="#" id="nextStep">下訂單</a>

    
	
    <script>
    function calculateShippingFee(cartItemId) {
        var shippingMethod = document.getElementById("shippingMethod_" + cartItemId).value;
        var shippingFeeInput = document.getElementById("shippingFee_" + cartItemId);

        if (shippingMethod === "1") {
            shippingFeeInput.value = "60";
        } else if (shippingMethod === "2") {
            shippingFeeInput.value = "120";
        } else {
            shippingFeeInput.value = ""; 
        }

        calculateTotalAmount(); 
    }

    function calculateTotalAmount() {
        var totalPriceInputs = document.querySelectorAll('.total-price');
        var totalAmount = 0;

        totalPriceInputs.forEach(function(input) {
            totalAmount += parseFloat(input.textContent);
        });

        var totalShippingFee = 0;
        var shippingFeeInputs = document.querySelectorAll('input[name^="shippingFee_"]');
        shippingFeeInputs.forEach(function(input) {
            if (input.value !== "") {
                totalShippingFee += parseFloat(input.value);
            }
        });

        var amountInput = document.getElementById('amount');
        amountInput.value = totalAmount + totalShippingFee;
    }
    document.addEventListener("DOMContentLoaded", function() {
        calculateTotalAmount();
    });
    $(document).ready(function() {
        $("#nextStep").on('click', function(event) {
            event.preventDefault();

            var confirmed = confirm("是否要下訂單？");
            if (confirmed) {
                $(".shippingFormButton").each(function() {
                    var form = $(this).closest("form");
                    $.ajax({
                        type: form.attr('method'),
                        url: form.attr('action'),
                        data: form.serialize(),
                        success: function(response) {
                            console.log(response);
                        },
                        error: function(xhr, status, error) {
                            console.error(error);
                        }
                    });
                });

                var paymentForm = $("#paymentFormButton").closest("form");
                $.ajax({
                    type: paymentForm.attr('method'),
                    url: paymentForm.attr('action'),
                    data: paymentForm.serialize(),
                    success: function(response) {
                        console.log(response);
                        var productIds = []; 
                        $("input[name='productId']").each(function() {
                            productIds.push($(this).val()); 
                        });
                        var userId = $("#userId").val(); 
                        var productIdQueryString = "productId=" + productIds[0]; 
                        for (var i = 1; i < productIds.length; i++) {
                            productIdQueryString += "&productId=" + productIds[i]; 
                        } 
                        window.location.href = "../Project/GoIndex?" + productIdQueryString + "&userId=" + userId;
                    },
                    error: function(xhr, status, error) {
                        console.error(error);
                    }
                });

                alert("新增訂單成功");
            }
        });
    });

	</script>
</body>
</html>