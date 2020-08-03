package dev.sinhnx.threelayer.persitance;

public class Customer {
    private int customerId;
    private String customerName;
    private String customerAddress;

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    @Override
    public String toString() {
        return this.customerId + " - " + this.customerName + " - " + this.customerAddress;
    }
}