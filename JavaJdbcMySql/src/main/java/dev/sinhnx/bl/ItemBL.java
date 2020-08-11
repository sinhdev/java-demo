package dev.sinhnx.bl;

import java.util.List;

import dev.sinhnx.dal.ItemDAL;
import dev.sinhnx.persistance.Item;

public class ItemBL {
    private ItemDAL itemDAL = new ItemDAL();

    public List<Item> getAll() {
        return itemDAL.getAll();
    }

    public boolean addItem(Item item) {
        // return itemDAL.insertItem(item) > 0;
        return itemDAL.insertItem(item) > 0;
    }

    public Item getById(int itemId) {
        return itemDAL.getById(itemId);
    }
}