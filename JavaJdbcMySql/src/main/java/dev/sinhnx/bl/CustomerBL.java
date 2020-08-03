package dev.sinhnx.bl;

import java.util.List;

import dev.sinhnx.dal.CustomerDAL;
import dev.sinhnx.dal.DAL;
import dev.sinhnx.dal.DalFactory;
import dev.sinhnx.persitance.Customer;

public class CustomerBL {
    private CustomerDAL dal = new CustomerDAL();

    public List<Customer> getAllCustomers() {
        return dal.getAll();
    }

    public boolean addCustomer(Customer customer) {
        DAL<Customer> dal = DalFactory.getDAL(Customer.class);
        return dal.insert(customer) > 0;
    }

    public int addCustomerAndGetCustomerId(Customer customer) {
        return dal.insertCustomer(customer);
    }
}