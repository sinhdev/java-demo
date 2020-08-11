package dev.sinhnx.threelayer.persistance;

public class Item {
    public static int INACTIVE = 0;
    public static int ACTIVE = 1;

    private int itemId;
    private String itemName;
    private double unitPrice;
    private int amount;
    private short itemStatus;
    private String description;

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setItemStatus(short itemStatus) {
        this.itemStatus = itemStatus;
    }

    public short getItemStatus() {
        return itemStatus;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return itemId + " - " + itemName + " - " + unitPrice + " - " + itemStatus;
    }
}