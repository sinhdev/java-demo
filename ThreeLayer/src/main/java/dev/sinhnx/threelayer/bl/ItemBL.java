package dev.sinhnx.threelayer.bl;

import java.util.List;

import dev.sinhnx.threelayer.dal.DAL;
import dev.sinhnx.threelayer.dal.DalFactory;
import dev.sinhnx.threelayer.persitance.Item;

public class ItemBL {
    private DAL<Item> dal = DalFactory.getDAL(Item.class);

    public List<Item> getAll() {
        return dal.search(null);
    }
}