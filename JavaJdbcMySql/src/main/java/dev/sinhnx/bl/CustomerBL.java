package dev.sinhnx.bl;

import java.util.List;

import dev.sinhnx.dal.CustomerDAL;
import dev.sinhnx.persitance.Customer;

public class CustomerBL {
    private CustomerDAL dal = new CustomerDAL();

    public List<Customer> getAllCustomers() {
        return dal.getAll();
    }

    public boolean addCustomer(Customer customer) {
        return dal.insertCustomer(customer) > 0;
    }

    public int addCustomerAndGetCustomerId(Customer customer) {
        return dal.insertCustomer(customer);
    }
}