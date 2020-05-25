package dev.sinhnx.bl;

import java.util.List;

import dev.sinhnx.dal.CustomerDAL;
import dev.sinhnx.persitance.Customer;

public class CustomerBL {
    public List<Customer> getAllCustomers() {
        return new CustomerDAL().getAll();
    }
}