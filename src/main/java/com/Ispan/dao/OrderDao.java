package com.Ispan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.Ispan.bean.OrdersBean;

public class OrderDao {
	public void insertOrder(OrdersBean order) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
            conn = ds.getConnection();
            String sql = "INSERT INTO ORDERS (BUYER_ID, SELLER_ID, SHIPPING_DETAILS_ID, ORDER_STATUS, CREATED_AT, MODIFIED_AT) VALUES (?, ?, ?, ?, GETDATE(), GETDATE())";            
            ps = conn.prepareStatement(sql);
            ps.setInt(1, order.getBuyerId());
            ps.setInt(2, order.getSellerId());
            ps.setInt(3, order.getShippingDetailsId());
            ps.setInt(4, order.getOrderStatus());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
	
	public int getOrderIdByShippingDetailsId(int shippingDetailsId) throws SQLException, NamingException {
	    int orderId = -1;
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        Context context = new InitialContext();
	        DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
	        conn = ds.getConnection();
	        String sql = "SELECT ORDER_ID FROM ORDERS WHERE SHIPPING_DETAILS_ID = ?";
	        ps = conn.prepareStatement(sql);
	        ps.setInt(1, shippingDetailsId);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            orderId = rs.getInt("ORDER_ID");
	        }
	    } finally {
	        if (rs != null) {
	            rs.close();
	        }
	        if (ps != null) {
	            ps.close();
	        }
	        if (conn != null) {
	            conn.close();
	        }
	    }

	    return orderId;
	}
}
