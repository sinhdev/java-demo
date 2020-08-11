package dev.sinhnx.threelayer.bl;

import java.util.List;

import dev.sinhnx.threelayer.dal.DAL;
import dev.sinhnx.threelayer.dal.DalFactory;
import dev.sinhnx.threelayer.persistance.Item;

public class ItemBL {
    private DAL<Item> dal = DalFactory.getDAL(Item.class);

    public List<Item> getAll() {
        Item item = null;
        return dal.search(item);
    }

    public boolean insertItem(Item item) {
        return dal.insert(item) > 0;
    }

    public Item getById(int itemId) {
        Item item = new Item();
        item.setItemId(itemId);
        return dal.getById(item);
    }

    public List<Item> searchByName(String itemName) {
        return dal.search("item_name like '%" + itemName + "%'");
    }
}