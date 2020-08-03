package dev.sinhnx.threelayer.dal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dev.sinhnx.threelayer.persitance.Customer;

public class CustomerDAL implements DAL<Customer> {

    private Customer getCustomer(final ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomerId(rs.getInt("customer_id"));
        customer.setCustomerName(rs.getString("customer_name"));
        customer.setCustomerAddress(rs.getString("customer_address"));
        return customer;
    }

    @Override
    public int insert(Customer customer) {
        int result = 0;
        String callStoreProcedure = "{call sp_createCustomer(?,?,?)}";
        try (CallableStatement cstm = DbUtil.getConnection().prepareCall(callStoreProcedure);) {
            cstm.setString(1, customer.getCustomerName());
            cstm.setString(2, customer.getCustomerAddress());
            cstm.registerOutParameter(3, java.sql.Types.INTEGER);
            cstm.execute();
            result = cstm.getInt(3);
        } catch (Exception e) {
            result = -1;
        }
        return result;
    }

    @Override
    public List<Customer> search(Customer e) {
        String sql = "select * from customers";
        List<Customer> lst = new ArrayList<>();
        try (Connection con = DbUtil.getConnection();
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                lst.add(getCustomer(rs));
            }
        } catch (SQLException ex) {
        }
        return lst;
    }

    @Override
    public Customer getById(Customer e) {
        // TODO Auto-generated method stub
        return null;
    }
}