package dev.sinhnx.threelayer.persistance;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Order {
    public static final int CREATE_NEW_ORDER_STATUS = 1;
    private int orderId;
    private Date orderDate;
    private int orderStatus;
    private Customer customer;
    private List<Item> items;

    public Order() {
        orderId = 0;
        orderStatus = CREATE_NEW_ORDER_STATUS;
        customer = null;
        items = new ArrayList<>();
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(item);
    }
}