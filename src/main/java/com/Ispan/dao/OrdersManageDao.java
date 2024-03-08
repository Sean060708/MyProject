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

import com.Ispan.bean.OrdersManageBean;


public class OrdersManageDao {
    public List<OrdersManageBean> getOrdersWithDetails() throws SQLException {
        List<OrdersManageBean> orders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
            conn = ds.getConnection();
            String sql = "SELECT DISTINCT " +
                    "o.ORDER_ID, " +
                    "o.BUYER_ID, " +
                    "o.SELLER_ID, " +
                    "s.SHIPPING_METHOD, " +
                    "s.NAME AS SHIPPING_NAME, " +
                    "s.ADDRESS AS SHIPPING_ADDRESS, " +
                    "s.TEL AS SHIPPING_TEL, " +
                    "p.PRODUCT_NAME, " +
                    "ci.QUANTITY, " +
                    "o.ORDER_STATUS, " +
                    "pd.STATUS AS PAYMENT_STATUS, " +
                    "(p.PRICE * ci.QUANTITY) + s.SHIPPING_FEE AS PAYMENT_AMOUNT, " +
                    "o.CREATED_AT " +
                    "FROM " +
                    "ORDERS o " +
                    "JOIN " +
                    "SHIPPING_DETAILS s ON o.SHIPPING_DETAILS_ID = s.SHIPPING_DETAILS_ID " +
                    "JOIN " +
                    "ORDER_ITEM oi ON o.ORDER_ID = oi.ORDER_ID " +
                    "JOIN " +
                    "CART_ITEM ci ON oi.CART_ITEM_ID = ci.CART_ITEM_ID " +
                    "JOIN " +
                    "PRODUCT p ON ci.PRODUCT_ID = p.PRODUCT_ID " +
                    "JOIN " +
                    "PAYMENT_DETAILS pd ON o.BUYER_ID = pd.USER_ID";

            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
            	 OrdersManageBean ordersManage = new OrdersManageBean();
            	 ordersManage.setOrderId(rs.getInt("ORDER_ID"));
            	 ordersManage.setBuyerId(rs.getInt("BUYER_ID"));
            	 ordersManage.setSellerId(rs.getInt("SELLER_ID"));
            	 ordersManage.setOrderStatus(rs.getInt("ORDER_STATUS"));
            	 ordersManage.setShippingName(rs.getString("SHIPPING_NAME"));
            	 ordersManage.setShippingAddress(rs.getString("SHIPPING_ADDRESS"));
            	 ordersManage.setShippingMethod(rs.getInt("SHIPPING_METHOD"));
            	 ordersManage.setShippingTel(rs.getString("SHIPPING_TEL"));
            	 ordersManage.setProductName(rs.getString("PRODUCT_NAME"));
            	 ordersManage.setQuantity(rs.getInt("QUANTITY"));
            	 ordersManage.setPaymentStatus(rs.getString("PAYMENT_STATUS"));
            	 ordersManage.setPaymentAmount(rs.getInt("PAYMENT_AMOUNT"));
            	 ordersManage.setCreatedAt(rs.getTimestamp("CREATED_AT"));
            	 orders.add(ordersManage);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return orders;
    }
    
    public OrdersManageBean getOrderById(int orderId) throws SQLException {
    	Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        OrdersManageBean ordersManage = null;

        try {
            Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
            conn = ds.getConnection();
            String sql = "SELECT DISTINCT " +
                    "o.ORDER_ID, " +
                    "o.BUYER_ID, " +
                    "o.SELLER_ID, " +
                    "s.SHIPPING_METHOD, " +
                    "s.NAME AS SHIPPING_NAME, " +
                    "s.ADDRESS AS SHIPPING_ADDRESS, " +
                    "s.TEL AS SHIPPING_TEL, " +
                    "p.PRODUCT_NAME, " +
                    "ci.QUANTITY, " +
                    "o.ORDER_STATUS, " +
                    "pd.STATUS AS PAYMENT_STATUS, " +
                    "(p.PRICE * ci.QUANTITY) + s.SHIPPING_FEE AS PAYMENT_AMOUNT, " +
                    "o.CREATED_AT " +
                    "FROM " +
                    "ORDERS o " +
                    "JOIN " +
                    "SHIPPING_DETAILS s ON o.SHIPPING_DETAILS_ID = s.SHIPPING_DETAILS_ID " +
                    "JOIN " +
                    "ORDER_ITEM oi ON o.ORDER_ID = oi.ORDER_ID " +
                    "JOIN " +
                    "CART_ITEM ci ON oi.CART_ITEM_ID = ci.CART_ITEM_ID " +
                    "JOIN " +
                    "PRODUCT p ON ci.PRODUCT_ID = p.PRODUCT_ID " +
                    "JOIN " +
                    "PAYMENT_DETAILS pd ON o.BUYER_ID = pd.USER_ID " +
                    "WHERE o.ORDER_ID = ?";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();

            if(rs.next()) {
            	ordersManage = new OrdersManageBean();
            	ordersManage.setOrderId(rs.getInt("ORDER_ID"));
            	ordersManage.setBuyerId(rs.getInt("BUYER_ID"));
            	ordersManage.setSellerId(rs.getInt("SELLER_ID"));
            	ordersManage.setOrderStatus(rs.getInt("ORDER_STATUS"));
             	ordersManage.setShippingName(rs.getString("SHIPPING_NAME"));
            	ordersManage.setShippingAddress(rs.getString("SHIPPING_ADDRESS"));
            	ordersManage.setShippingMethod(rs.getInt("SHIPPING_METHOD"));
            	ordersManage.setShippingTel(rs.getString("SHIPPING_TEL"));
            	ordersManage.setProductName(rs.getString("PRODUCT_NAME"));
            	ordersManage.setQuantity(rs.getInt("QUANTITY"));
            	ordersManage.setPaymentStatus(rs.getString("PAYMENT_STATUS"));
            	ordersManage.setPaymentAmount(rs.getInt("PAYMENT_AMOUNT"));
            	ordersManage.setCreatedAt(rs.getTimestamp("CREATED_AT"));
            	 
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return ordersManage;
    }
    
    public List<OrdersManageBean> getOrdersWithDate(String startDate, String endDate) throws SQLException {
        List<OrdersManageBean> orders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
            conn = ds.getConnection();
            String sql = "SELECT DISTINCT " +
                    "o.ORDER_ID, " +
                    "o.BUYER_ID, " +
                    "o.SELLER_ID, " +
                    "s.SHIPPING_METHOD, " +
                    "s.NAME AS SHIPPING_NAME, " +
                    "s.ADDRESS AS SHIPPING_ADDRESS, " +
                    "s.TEL AS SHIPPING_TEL, " +
                    "p.PRODUCT_NAME, " +
                    "ci.QUANTITY, " +
                    "o.ORDER_STATUS, " +
                    "pd.STATUS AS PAYMENT_STATUS, " +
                    "(p.PRICE * ci.QUANTITY) + s.SHIPPING_FEE AS PAYMENT_AMOUNT, " +
                    "o.CREATED_AT " +
                    "FROM " +
                    "ORDERS o " +
                    "JOIN " +
                    "SHIPPING_DETAILS s ON o.SHIPPING_DETAILS_ID = s.SHIPPING_DETAILS_ID " +
                    "JOIN " +
                    "ORDER_ITEM oi ON o.ORDER_ID = oi.ORDER_ID " +
                    "JOIN " +
                    "CART_ITEM ci ON oi.CART_ITEM_ID = ci.CART_ITEM_ID " +
                    "JOIN " +
                    "PRODUCT p ON ci.PRODUCT_ID = p.PRODUCT_ID " +
                    "JOIN " +
                    "PAYMENT_DETAILS pd ON o.BUYER_ID = pd.USER_ID " +
                    "WHERE o.CREATED_AT BETWEEN ? AND ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            rs = ps.executeQuery();

            while (rs.next()) {
                OrdersManageBean ordersManage = new OrdersManageBean();
                ordersManage.setOrderId(rs.getInt("ORDER_ID"));
                ordersManage.setBuyerId(rs.getInt("BUYER_ID"));
                ordersManage.setSellerId(rs.getInt("SELLER_ID"));
                ordersManage.setOrderStatus(rs.getInt("ORDER_STATUS"));
                ordersManage.setShippingName(rs.getString("SHIPPING_NAME"));
                ordersManage.setShippingAddress(rs.getString("SHIPPING_ADDRESS"));
                ordersManage.setShippingMethod(rs.getInt("SHIPPING_METHOD"));
                ordersManage.setShippingTel(rs.getString("SHIPPING_TEL"));
                ordersManage.setProductName(rs.getString("PRODUCT_NAME"));
                ordersManage.setQuantity(rs.getInt("QUANTITY"));
                ordersManage.setPaymentStatus(rs.getString("PAYMENT_STATUS"));
                ordersManage.setPaymentAmount(rs.getInt("PAYMENT_AMOUNT"));
                ordersManage.setCreatedAt(rs.getTimestamp("CREATED_AT"));
                orders.add(ordersManage);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return orders;
    }

}
