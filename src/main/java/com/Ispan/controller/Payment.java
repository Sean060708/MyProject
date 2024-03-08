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


@WebServlet("/Payment")
public class Payment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Payment() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = 1;
		CartItemDao cartItemDao = new CartItemDao();
		List<CartItemBean> cartItems = cartItemDao.getCartItemsByUserId(userId);
        request.setAttribute("cartItems", cartItems);
        request.setAttribute("userId", userId);
        request.getRequestDispatcher("/view/Payment.jsp").forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
