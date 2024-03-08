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


@WebServlet("/DeleteCartItem")
public class DeleteCartItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteCartItem() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		int userId = 1;
        CartItemDao cartItemDAO = new CartItemDao();

        boolean success = false;
		try {
			success = cartItemDAO.deleteCartItem(itemId);
		} catch (NamingException e) {
			e.printStackTrace();
		}

        if (success) {
        	List<CartItemBean> cartItems = cartItemDAO.getCartItemsByUserId(userId);
            request.setAttribute("cartItems", cartItems);
            request.getRequestDispatcher("/view/ShoppingCar.jsp").forward(request, response);
        } else {
            response.getWriter().println("刪除失敗");
        }

        

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
