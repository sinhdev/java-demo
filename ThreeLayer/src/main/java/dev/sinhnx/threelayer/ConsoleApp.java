package dev.sinhnx.threelayer;

import java.util.List;

import dev.sinhnx.threelayer.bl.ItemBL;
import dev.sinhnx.threelayer.persitance.Item;

/**
 * Hello world!
 *
 */
public class ConsoleApp {
    public static void main(String[] args) {
        List<Item> lstItems = new ItemBL().searchByName("Item");
        for (Item item : lstItems) {
            System.out.println(item);
        }
    }
}
