package plantmon.entity;

import java.util.ArrayList;
import plantmon.entity.item.FarmItem;

public class Inventory{
    int size;
    int isi;
    ArrayList<Item>  item;
    
    public Inventory(int max) {
        item = new ArrayList<Item>(max);
        size = 0;
    }
    public int getSize () {
        return size;
    }

    public Item getItem(int i) {
        Item result = item.get(i);
        return result;
    }
    public void add(Item it){
        if (size<item.size()){
            item.add(size, it);
            size++;
        } else {
            throw new IndexOutOfBoundsException("Inventory Penuh!");
        }
    }
    public Inventory getFarmItem(){
        Inventory farm = new Inventory(item.size());
        for (Item i:item){
            if (i instanceof FarmItem){
                farm.add(i);
            }
        }
        return farm;
    }

}