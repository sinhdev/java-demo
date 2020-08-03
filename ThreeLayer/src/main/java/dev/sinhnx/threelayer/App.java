package dev.sinhnx.threelayer;

import java.util.List;

import dev.sinhnx.threelayer.bl.ItemBL;
import dev.sinhnx.threelayer.persitance.Item;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        List<Item> lstItems = new ItemBL().getAll();
        for (Item item : lstItems) {
            System.out.println(item);
        }
    }
}
