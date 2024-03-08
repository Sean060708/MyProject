package com.Ispan.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Ispan.bean.OrdersManageBean;
import com.Ispan.dao.OrdersManageDao;



@WebServlet("/OrdersManagement")
public class OrdersManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public OrdersManagement() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String queryType = request.getParameter("queryType");
        switch (queryType) {
        case "all":
        	OrdersManageDao ordersManageDao = new OrdersManageDao();
    		List<OrdersManageBean> orders = null;
    		try {
    			orders = ordersManageDao.getOrdersWithDetails();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		request.setAttribute("orders", orders);
            request.getRequestDispatcher("/view/OrdersManage.jsp").forward(request, response);
            break;
        case "byOrderId":
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            OrdersManageDao ordersManageDao2 = new OrdersManageDao();
            OrdersManageBean order = null;
            try {
            	
                order = ordersManageDao2.getOrderById(orderId);
                if (order != null) {
                    request.setAttribute("order", order);
                    request.getRequestDispatcher("/view/OrderById.jsp").forward(request, response);
                } else {
                    response.sendRedirect("/error.jsp");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        
        break;
        case "byDate":
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            OrdersManageDao ordersManageDao3 = new OrdersManageDao();
            List<OrdersManageBean> ordersByDate = null;
            try {
                ordersByDate = ordersManageDao3.getOrdersWithDate(startDate, endDate);
                if (ordersByDate != null && !ordersByDate.isEmpty()) {
                    request.setAttribute("orders", ordersByDate);
                    request.getRequestDispatcher("/view/OrdersManage.jsp").forward(request, response);
                } else {
                    response.sendRedirect("/error.jsp");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            break;
        default:
            break;
    }
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
