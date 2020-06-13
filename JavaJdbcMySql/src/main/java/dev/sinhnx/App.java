package dev.sinhnx;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dev.sinhnx.bl.CustomerBL;
import dev.sinhnx.bl.ItemBL;
import dev.sinhnx.bl.OrderBL;
import dev.sinhnx.persitance.Customer;
import dev.sinhnx.persitance.Item;
import dev.sinhnx.persitance.Order;

/**
 * Test connection to MySql
 *
 */
public class App {
    public static void main(String[] args) {
        try (Connection con = dev.sinhnx.dal.DbUtil.getConnection("./orderDb.properties");) {
            System.out.println("Connected to MySql Server.");
        } catch (SQLException ex) {
            System.out.println("Connection Error!");
        }

        // insert Customer
        Customer customer = new Customer();
        customer.setCustomerName("sinhnx");
        customer.setCustomerAddress("Hà Nội");
        System.out.println("Insert Customer: " + new CustomerBL().addCustomer(customer));

        showAllCustomers();
        // insertItem();
        showAllItems();

        insertOrder();
    }

    private static void insertOrder() {
        ItemBL ibl = new ItemBL();
        OrderBL obl = new OrderBL();

        Customer customer = new Customer();
        customer.setCustomerName("customerName");
        customer.setCustomerAddress("customerAddress");
        Order order = new Order();
        order.setCustomer(customer);
        Item item = ibl.getById(1);
        item.setAmount(1);
        order.addItem(item);

        item = ibl.getById(3);
        item.setAmount(-1);
        order.addItem(item);
        if (obl.insertOrder(order)) {
            System.out.println("Insert order complete!");
        } else {
            System.err.println("Insert order failed!");
        }
    }

    private static void showAllItems() {
        ItemBL ibl = new ItemBL();
        System.out.println("\nItems List:");
        List<Item> lst = ibl.getAll();
        for (Item item : lst) {
            System.out.println(item);
        }
    }

    private static void insertItem() {
        ItemBL ibl = new ItemBL();
        System.out.println("Insert New Item:");
        if (ibl.addItem(inputItem())) {
            System.out.println("Insert item complete!");
        } else {
            System.err.println("Insert item failed!");
        }
    }

    private static Item inputItem() {
        Item item = new Item();
        Scanner kb = new Scanner(System.in);
        System.out.print("Item Name: ");
        item.setItemName(kb.nextLine());
        System.out.print("Unit Price: ");
        item.setUnitPrice(kb.nextDouble());
        System.out.print("Amount: ");
        item.setAmount(kb.nextInt());
        kb.close();
        return item;
    }

    private static void showAllCustomers() {
        System.out.println("\nCustomers List:");
        List<Customer> lst = new CustomerBL().getAllCustomers();
        for (Customer customer : lst) {
            System.out.println(customer);
        }
    }
}