package com.Ispan.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Ispan.bean.OrderItemBean;
import com.Ispan.bean.OrdersBean;
import com.Ispan.dao.CartItemDao;
import com.Ispan.dao.OrderDao;
import com.Ispan.dao.OrderItemDao;
import com.Ispan.dao.ProductDao;
import com.Ispan.dao.ShippingDetailsDao;


@WebServlet("/InsertShippingDetails")
public class InsertShippingDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public InsertShippingDetails() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cartItemId = Integer.parseInt(request.getParameter("cartItemId"));
        String name = request.getParameter("name_" + cartItemId);
        String address = request.getParameter("address_" + cartItemId);
        String tel = request.getParameter("tel_" + cartItemId);
        String parameterName = "shippingFee_" + cartItemId;
        String parameterValue = request.getParameter(parameterName);
        System.out.println("Parameter Name: " + parameterName + ", Parameter Value: " + parameterValue);
        int shippingMethod = Integer.parseInt(request.getParameter("shippingMethod_" + cartItemId));
        int shippingFee = Integer.parseInt(request.getParameter("shippingFee_" + cartItemId));
        ShippingDetailsDao shippingDetailsDao = new ShippingDetailsDao();
        shippingDetailsDao.addShippingDetails(cartItemId, name, address, tel, shippingMethod, shippingFee);

        Integer productId = null;
        String productIdParam = request.getParameter("productId");
        if (productIdParam != null && !productIdParam.isEmpty()) {
            try {
                productId = Integer.parseInt(productIdParam);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        System.out.println("productId: " + productId);
        ProductDao productDao = new ProductDao();
        int sellerId = 1;
        try {
            sellerId = productDao.getSellerIdByProductId(productId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }

        int buyerId = Integer.parseInt(request.getParameter("userId"));
        int shippingDetailsId = 0;
        try {
            shippingDetailsId = shippingDetailsDao.getShippingDetailsIdByCartItemId(cartItemId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        int orderStatus = 0;
        OrdersBean order = new OrdersBean();
        order.setBuyerId(buyerId);
        order.setSellerId(sellerId);
        order.setShippingDetailsId(shippingDetailsId);
        order.setOrderStatus(orderStatus);
        System.out.println("shippingDetailsId" + shippingDetailsId);
        OrderDao orderDAO = new OrderDao();
        int orderId = 0;
        try {
            orderDAO.insertOrder(order);
            orderId = orderDAO.getOrderIdByShippingDetailsId(shippingDetailsId);
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }

        if (orderId > 0) {
            OrderItemBean orderItem = new OrderItemBean();
            System.out.println("Order ID: " + orderId);
            orderItem.setOrderId(orderId);
            orderItem.setCartItemId(cartItemId);
            OrderItemDao orderItemDao = new OrderItemDao();
            try {
                orderItemDao.insertOrderItem(orderItem);
            } catch (SQLException | NamingException e) {
                e.printStackTrace();
            }

            CartItemDao cartItemDao = new CartItemDao();
            try {
                cartItemDao.clearCart(cartItemId);
            } catch (SQLException | NamingException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("清空失敗");
        }
    }



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
