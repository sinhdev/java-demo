package dev.sinhnx.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dev.sinhnx.persitance.Customer;
import dev.sinhnx.persitance.Item;
import dev.sinhnx.persitance.Order;

public class OrderDAL {
    public int insert(Order order) {
        // if customer not exists then customer is "no name" with customer_id=1
        if (order.getCustomer() == null || order.getCustomer().getCustomerName() == null
                || order.getCustomer().getCustomerName().equals("")) {
            Customer customer = new Customer();
            customer.setCustomerId(1);
            order.setCustomer(customer);
        }
        // if there are no items, do not add an order
        if (order.getItems() == null || order.getItems().size() <= 0) {
            return 0;
        }
        int result = 0;
        try (Connection con = DbUtil.getConnection()) {
            con.setAutoCommit(false);
            ResultSet rs = null;
            PreparedStatement pstm = null;
            Statement stm = null;
            try {
                stm = con.createStatement();
                // lock table to insert data
                stm.execute("lock tables Customers write, Orders write, Items write, OrderDetails write;");

                int customerId = order.getCustomer().getCustomerId();
                if (customerId <= 0) {
                    // insert Customer
                    pstm = con.prepareStatement("insert into Customers(customer_name, customer_address) values(?,?);");
                    pstm.setString(1, order.getCustomer().getCustomerName());
                    pstm.setString(2, order.getCustomer().getCustomerAddress());
                    pstm.close();
                    if ((result += pstm.executeUpdate()) == 0) {
                        throw new SQLException();
                    }
                    // get new customerId
                    rs = stm.executeQuery("select customer_id from Customers order by customer_id desc limit 1;");
                    if (rs.next()) {
                        order.getCustomer().setCustomerId(rs.getInt("customer_id"));
                        rs.close();
                    } else {
                        throw new SQLException();
                    }
                } else if (customerId > 1) {
                    // get customer by id
                    rs = stm.executeQuery("select * from Customers where customer_id=" + customerId);
                    if (rs.next()) {
                        order.getCustomer().setCustomerName(rs.getString("customer_name"));
                        order.getCustomer().setCustomerAddress(rs.getString("customer_address"));
                        rs.close();
                    } else {
                        throw new SQLException();
                    }
                }

                // insert Order
                pstm = con.prepareStatement("insert into Orders(customer_id, order_status) values (?, ?);");
                pstm.setInt(1, customerId);
                pstm.setInt(2, Order.OrderStatus.CREATE_NEW_ORDER);
                result = pstm.executeUpdate();
                pstm.close();
                if (result <= 0) {
                    throw new SQLException();
                }
                // get new order_id
                rs = stm.executeQuery("select LAST_INSERT_ID() as order_id;");
                if (rs.next()) {
                    order.setOrderId(rs.getInt("order_id"));
                    rs.close();
                } else {
                    throw new SQLException();
                }

                // insert OrderDetails
                for (Item item : order.getItems()) {
                    if (item.getItemId() <= 0 || item.getAmount() <= 0) {
                        throw new SQLException();
                    }
                    // insert OrderDetails
                    pstm = con.prepareStatement(
                            "insert into OrderDetails(order_id, item_id, unit_price, quantity) values(?,?,?,?)");
                    pstm.setInt(1, order.getOrderId());
                    pstm.setInt(2, item.getItemId());
                    pstm.setDouble(3, item.getUnitPrice());
                    pstm.setInt(4, item.getAmount());
                    result = pstm.executeUpdate();
                    pstm.close();
                    if (result <= 0) {
                        throw new SQLException();
                    }
                    // update amount of Item
                    pstm = con.prepareStatement("update Items set amount=amount-? where item_id=?");
                    pstm.setInt(1, item.getAmount());
                    pstm.setInt(2, item.getItemId());
                    result = pstm.executeUpdate();
                    pstm.close();
                    if (result <= 0) {
                        throw new SQLException();
                    }
                }

                // commit transaction
                con.commit();
                // unlock table
                stm.execute("unlock tables;");
            } catch (SQLException ex) {
                // rollback transaction
                con.rollback();
                result = 0;
            }
        } catch (SQLException ex) {
            result = 0;
        }
        return result;
    }
}