package com.Ispan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import com.Ispan.bean.ProductBean;

public class ProductDao {

		
		public List<ProductBean> getAllProducts(){
			Connection conn = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        List<ProductBean> products = new ArrayList<>();
	        try {
	            Context context = new InitialContext();
	            DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
	            conn = ds.getConnection();
	            ps = conn.prepareStatement("SELECT PRODUCT_ID, SELLER_ID, PRODUCT_NAME, PRICE FROM PRODUCT");
	            rs = ps.executeQuery();
	            
	            while (rs.next()) {
	            	ProductBean product = new ProductBean();
	            	product.setProductId(rs.getInt("PRODUCT_ID"));
	                product.setSellerId(rs.getInt("SELLER_ID"));
	                product.setProductName(rs.getString("PRODUCT_NAME"));
	                product.setPrice(rs.getDouble("PRICE"));
	                products.add(product);
	            }
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
	        
	        return products;
	    }
		
		public int getSellerIdByProductId(int productId) throws SQLException, NamingException {
	        Connection conn = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        int sellerId = -1; 
	        
	        try {
	            Context context = new InitialContext();
	            DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
	            conn = ds.getConnection();
	            
	            String sql = "SELECT SELLER_ID FROM PRODUCT WHERE PRODUCT_ID = ?";
	            ps = conn.prepareStatement(sql);
	            ps.setInt(1, productId);
	            
	            rs = ps.executeQuery();
	            if (rs.next()) {
	                sellerId = rs.getInt("SELLER_ID");
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
	        return sellerId;
	    }
	}


