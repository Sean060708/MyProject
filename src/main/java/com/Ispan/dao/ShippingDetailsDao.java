package com.Ispan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ShippingDetailsDao {
	public void addShippingDetails(int cartItemId, String name, String address, String tel, int shippingMethod, int shippingFee) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
            conn = ds.getConnection();
            
            
            String sql = "INSERT INTO SHIPPING_DETAILS VALUES (?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cartItemId);
            ps.setString(2, name);
            ps.setString(3, address);
            ps.setString(4, tel);
            ps.setInt(5, shippingMethod);
            ps.setInt(6, shippingFee);
            ps.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            ps.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
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
	public int getShippingDetailsIdByCartItemId(int cartItemId) throws SQLException, NamingException {
        int shippingDetailsId = -1;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
            conn = ds.getConnection();
            String sql = "SELECT SHIPPING_DETAILS_ID\r\n"
            		+ "FROM SHIPPING_DETAILS\r\n"
            		+ "WHERE CART_ITEM_ID = ?;";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cartItemId);
            rs = ps.executeQuery();

            if (rs.next()) {
                shippingDetailsId = rs.getInt("SHIPPING_DETAILS_ID");
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

        return shippingDetailsId;
    }
}
