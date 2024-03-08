package com.Ispan.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Ispan.dao.CartItemDao;


@WebServlet("/CartItem")
public class CartItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CartItem() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 int userId = 1;
	     int productId = Integer.parseInt(request.getParameter("productId"));

	        CartItemDao cartItem = new CartItemDao();
	        boolean addedToCart = cartItem.addToCart(userId, productId);

	        if (addedToCart) {
	            response.sendRedirect("ShoppingCart.jsp");
	        } else {
	            response.getWriter().println("添加到購物車失敗。");
	        }	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
