package com.Ispan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ShoppingCarDao {
	public boolean hasShoppingCart(int userId) {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    boolean hasCart = false;
	    try {
	        Context context = new InitialContext();
	        DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
	        conn = ds.getConnection();
	        
	        String sql = "SELECT * FROM SHOPPING_CAR WHERE USER_ID = ?";
	        ps = conn.prepareStatement(sql);
	        ps.setInt(1, userId);
	        rs = ps.executeQuery();
	        
	        hasCart = rs.next(); 
	    } catch (SQLException | NamingException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return hasCart;
	}
	public void createShoppingCart(int userId) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
            conn = ds.getConnection();
            
            
            String sql = "INSERT INTO SHOPPING_CAR (USER_ID) VALUES (?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.executeUpdate();

        } catch (SQLException | NamingException e) {
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
}
