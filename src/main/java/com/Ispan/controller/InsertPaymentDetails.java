package com.Ispan.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Ispan.dao.PaymentDetailsDao;

@WebServlet("/InsertPaymentDetails")
public class InsertPaymentDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public InsertPaymentDetails() {
        super();
    }
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("userId"));
        int amount = Integer.parseInt(request.getParameter("amount"));
        int paymentMethod = Integer.parseInt(request.getParameter("paymentMethod"));
        int status = Integer.parseInt(request.getParameter("statusValue"));
	PaymentDetailsDao paymentDetailsDao = new PaymentDetailsDao();
	paymentDetailsDao.addPaymentDetails(userId,amount,paymentMethod,status);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
