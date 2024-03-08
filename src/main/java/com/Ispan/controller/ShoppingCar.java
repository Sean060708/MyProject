package com.Ispan.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Ispan.bean.CartItemBean;
import com.Ispan.dao.CartItemDao;
import com.Ispan.dao.ShoppingCarDao;


@WebServlet("/ShoppingCar")
public class ShoppingCar extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ShoppingCar() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = 1;

		ShoppingCarDao shoppingCarDao = new ShoppingCarDao();
		boolean hasShoppingCart = shoppingCarDao.hasShoppingCart(userId);

		if (!hasShoppingCart) {
		    shoppingCarDao.createShoppingCart(userId);
		}

		CartItemDao cartItemDao = new CartItemDao();
		List<CartItemBean> cartItems = cartItemDao.getCartItemsByUserId(userId);
        request.setAttribute("cartItems", cartItems);
        request.getRequestDispatcher("/view/ShoppingCar.jsp").forward(request, response);
    }
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
