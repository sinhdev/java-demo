package dev.sinhnx.bl;

import java.util.List;

import dev.sinhnx.dal.DAL;
import dev.sinhnx.dal.DalFactory;
import dev.sinhnx.dal.ItemDAL;
import dev.sinhnx.persitance.Item;

public class ItemBL {
    private ItemDAL itemDAL = new ItemDAL();
    private DAL<Item> dal = DalFactory.getDAL(Item.class);

    public List<Item> getAll() {
        return itemDAL.getAll();
    }

    public boolean addItem(Item item) {
        // return itemDAL.insertItem(item) > 0;
        return dal.insert(item) > 0;
    }

    public Item getById(int itemId) {
        return itemDAL.getById(itemId);
    }
}