package dev.sinhnx.threelayer.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import dev.sinhnx.threelayer.persistance.Customer;
import dev.sinhnx.threelayer.persistance.Item;
import dev.sinhnx.threelayer.persistance.Order;

class OrderDAL implements DAL<Order> {
    @Override
    public int insert(Order order) {
        // if there are no items, do not add an order
        if (order.getItems() == null || order.getItems().isEmpty()) {
            return 0;
        }
        // if customer not exists then customer is "no name" with customer_id=1
        Customer customer = order.getCustomer();
        if (customer == null || customer.getCustomerName() == null || customer.getCustomerName().isEmpty()) {
            customer = new Customer();
            customer.setCustomerId(1);
        }
        int customerId = order.getCustomer().getCustomerId();
        try (Connection con = DbUtil.getConnection(); Statement stm = con.createStatement();) {
            con.setAutoCommit(false);
            // lock table to insert data
            stm.execute("lock tables Customers write, Orders write, Items write, OrderDetails write;");
            if (customerId <= 0) {
                // insert Customer
                try (PreparedStatement pstm = con
                        .prepareStatement("insert into Customers(customer_name, customer_address) values(?,?);");) {
                    pstm.setString(1, order.getCustomer().getCustomerName());
                    pstm.setString(2, order.getCustomer().getCustomerAddress());
                    if (pstm.executeUpdate() <= 0) {
                        throw new SQLException();
                    }
                } catch (SQLException ex) {
                    return rollbackTransaction(con);
                }
                // get new customerId
                try (ResultSet rs = stm
                        .executeQuery("select customer_id from Customers order by customer_id desc limit 1;");) {
                    if (rs.next()) {
                        order.getCustomer().setCustomerId(rs.getInt("customer_id"));
                    } else {
                        throw new SQLException();
                    }
                } catch (SQLException e) {
                    return rollbackTransaction(con);
                }
            } else if (customerId > 1) {
                // get customer by id
                try (ResultSet rs = stm.executeQuery("select * from Customers where customer_id=" + customerId);) {
                    if (rs.next()) {
                        order.getCustomer().setCustomerName(rs.getString("customer_name"));
                        order.getCustomer().setCustomerAddress(rs.getString("customer_address"));
                    } else {
                        throw new SQLException();
                    }
                } catch (SQLException ex) {
                    return rollbackTransaction(con);
                }
            }

            // insert Order
            try (PreparedStatement pstm = con
                    .prepareStatement("insert into Orders(customer_id, order_status) values (?, ?);");) {
                pstm.setInt(1, customerId);
                pstm.setInt(2, Order.CREATE_NEW_ORDER_STATUS);
                if (pstm.executeUpdate() <= 0) {
                    throw new SQLException();
                }
            } catch (SQLException ex) {
                return rollbackTransaction(con);
            }
            // get new order_id
            try (ResultSet rs = stm.executeQuery("select LAST_INSERT_ID() as order_id;");) {
                if (rs.next()) {
                    order.setOrderId(rs.getInt("order_id"));
                } else {
                    throw new SQLException();
                }
            } catch (SQLException ex) {
                return rollbackTransaction(con);
            }

            // insert OrderDetails
            for (Item item : order.getItems()) {
                if (item.getItemId() <= 0 || item.getAmount() <= 0) {
                    throw new SQLException();
                }
                // insert OrderDetails
                try (PreparedStatement pstm = con.prepareStatement(
                        "insert into OrderDetails(order_id, item_id, unit_price, quantity) values(?,?,?,?)");) {
                    pstm.setInt(1, order.getOrderId());
                    pstm.setInt(2, item.getItemId());
                    pstm.setDouble(3, item.getUnitPrice());
                    pstm.setInt(4, item.getAmount());
                    if (pstm.executeUpdate() <= 0) {
                        throw new SQLException();
                    }
                } catch (SQLException ex) {
                    return rollbackTransaction(con);
                }
                // update amount of Item
                try (PreparedStatement pstm = con
                        .prepareStatement("update Items set amount=amount-? where item_id=?");) {
                    pstm.setInt(1, item.getAmount());
                    pstm.setInt(2, item.getItemId());
                    if (pstm.executeUpdate() <= 0) {
                        throw new SQLException();
                    }
                } catch (SQLException ex) {
                    return rollbackTransaction(con);
                }
            }
            // commit transaction
            con.commit();
            // unlock table
            stm.execute("unlock tables;");
            // set auto commit is true
            con.setAutoCommit(true);
        } catch (SQLException ex) {
            return 0;
        }
        return 1;
    }

    private int rollbackTransaction(Connection con) throws SQLException {
        // rollback transaction
        con.rollback();
        // unlock tables
        Statement stm = con.createStatement();
        stm.execute("unlock tables;");
        // set auto commit is true
        con.setAutoCommit(true);
        return 0;
    }

    @Override
    public List<Order> search(Order e) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Order getById(Order e) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Order> search(String whereClause) {
        // TODO Auto-generated method stub
        return null;
    }
}