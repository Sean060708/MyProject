package com.Ispan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.Ispan.bean.OrderItemBean;

public class OrderItemDao {
	 public void insertOrderItem(OrderItemBean orderItem) throws SQLException, NamingException {
		 	Connection conn = null;
	        PreparedStatement ps = null;
	        try {
	        Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
            conn = ds.getConnection();
            String sql = "INSERT INTO ORDER_ITEM (ORDER_ID, CART_ITEM_ID, CREATED_AT, MODIFIED_AT) VALUES (?, ?, GETDATE(), GETDATE())";
            ps = conn.prepareStatement(sql);
	            ps.setInt(1, orderItem.getOrderId());
	            ps.setInt(2, orderItem.getCartItemId());
	            ps.execute();
	        }catch (Exception e) {
	        	e.printStackTrace();
			}finally {
				try {
	                if (ps != null) ps.close();
	                if (conn != null) conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
			}
	 }
}	
