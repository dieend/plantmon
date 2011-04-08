package plantmon.entity;

import java.util.ArrayList;

public class Inventory{
    int size;
    int maxSlot = 0;
   ArrayList<Item> [] item;
    public Inventory() {
        item = new ArrayList[maxSlot];
    }
    public int getSize () {
        return size;
    }

    public Item getItem(int i) {
        Item result = item[i].get(item[i].size());
        return result;
    }
}