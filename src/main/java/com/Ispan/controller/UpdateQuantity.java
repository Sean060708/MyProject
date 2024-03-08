package com.Ispan.controller;

import java.io.IOException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Ispan.bean.CartItemBean;
import com.Ispan.dao.CartItemDao;


@WebServlet("/UpdateQuantity")
public class UpdateQuantity extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateQuantity() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cartItemId = Integer.parseInt(request.getParameter("itemId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
		int userId = 1;

        CartItemDao cartItemDao = new CartItemDao();
        boolean success = false;
		try {
			success = cartItemDao.updateQuantity(cartItemId, quantity);
		} catch (NamingException e) {
			e.printStackTrace();
		}
        if (success) {
        	List<CartItemBean> cartItems = cartItemDao.getCartItemsByUserId(userId);
            request.setAttribute("cartItems", cartItems);
            request.getRequestDispatcher("/view/ShoppingCar.jsp").forward(request, response);
        } else {
            response.getWriter().println("修改失敗");
        }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
