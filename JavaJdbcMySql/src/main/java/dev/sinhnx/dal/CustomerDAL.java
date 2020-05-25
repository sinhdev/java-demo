package dev.sinhnx.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dev.sinhnx.persitance.Customer;

public class CustomerDAL {
    public List<Customer> getAll() {
        try (Connection con = DbUtil.getConnection()) {
            return getCustomers(con.createStatement(), "select * from customers");
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private List<Customer> getCustomers(Statement stm, String sql) throws SQLException {
        List<Customer> lst = new ArrayList<>();
        ResultSet rs = stm.executeQuery(sql);
        while (rs.next()) {
            Customer c = new Customer();
            c.setCustomerId(rs.getInt("customer_id"));
            c.setCustomerName(rs.getString("customer_name"));
            c.setCustomerAddress(rs.getString("customer_address"));
            lst.add(c);
        }
        return lst;
    }
}