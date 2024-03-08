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

import com.Ispan.bean.CartItemBean;
import com.Ispan.bean.ProductBean;

public class CartItemDao {
	public boolean addToCart(int userId, int productId) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean addedToCart = false;
        
        try {
            Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
            conn = ds.getConnection();
            String sql = "INSERT INTO CART_ITEM (CAR_ID, PRODUCT_ID, QUANTITY) VALUES ((SELECT CAR_ID FROM SHOPPING_CAR WHERE USER_ID = ?), ?, 1)";;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                addedToCart = true;
            }
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
        
        return addedToCart;
    }
	public List<CartItemBean> getCartItemsByUserId(int userId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<CartItemBean> cartItems = new ArrayList<>();
        try {
            Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
            conn = ds.getConnection();

            String sql = "SELECT ci.*, p.PRODUCT_NAME AS productName,p.PRODUCT_ID AS productId, p.PRICE AS price FROM CART_ITEM ci INNER JOIN PRODUCT p ON ci.PRODUCT_ID = p.PRODUCT_ID WHERE ci.CAR_ID IN (SELECT CAR_ID FROM SHOPPING_CAR WHERE USER_ID = ?) AND ci.IS_DELETED = 0";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            while (rs.next()) {
                CartItemBean cartItem = new CartItemBean();
                cartItem.setCartItemId(rs.getInt("CART_ITEM_ID"));
                cartItem.setCarId(rs.getInt("CAR_ID"));
                cartItem.setProductId(rs.getInt("PRODUCT_ID"));
                cartItem.setQuantity(rs.getInt("QUANTITY"));
                
                ProductBean product = new ProductBean();
                product.setProductName(rs.getString("productName"));
                product.setPrice(rs.getDouble("price"));
                product.setProductId(rs.getInt("productId"));
                cartItem.setProduct(product);
                cartItems.add(cartItem);
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
        return cartItems;
    }
	
	public boolean updateQuantity(int cartItemId, int newQuantity) throws NamingException {
		Connection conn = null;
        PreparedStatement ps = null;
        boolean success = false;

        try {
        	Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
            conn = ds.getConnection();
            String sql = "UPDATE CART_ITEM SET QUANTITY = ? WHERE CART_ITEM_ID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, newQuantity);
            ps.setInt(2, cartItemId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return success;
    }
	public boolean deleteCartItem(int cartItemId) throws NamingException {
		Connection conn = null;
        PreparedStatement ps = null;
        boolean success = false;

        try {
        	Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
            conn = ds.getConnection();
            String sql = "DELETE FROM CART_ITEM WHERE CART_ITEM_ID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cartItemId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return success;
    }
	public void clearCart(int cartItemId) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
            conn = ds.getConnection();

            String sql = "UPDATE CART_ITEM SET IS_DELETED = 1 WHERE CART_ITEM_ID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cartItemId);
            ps.executeUpdate();
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
