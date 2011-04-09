package plantmon.entity;

import java.util.ArrayList;
import plantmon.entity.item.FarmItem;
import plantmon.entity.item.FoodItem;

public class Inventory{
    /**
     * jumlah inventory terisi
     */
    private int isi;
    /**
     * kapasitas maksimum dari inventory
     */
    private int capacity;
    /**
     * container item
     */
    ArrayList<Item>  item;
    /**
     * konstruktor inventory dengan ukuran dinamis dan ~tidak terbatas
     */
    public Inventory(){
        item = new ArrayList<Item>();
        isi = 0;
        capacity = 9999;
    }
    /**
     * konstruktor inventory dengan ukuran maksimum capacity
     * @param capacity
     */
    public Inventory(int capacity) {
        item = new ArrayList<Item>(capacity);
        isi = 0;
        this.capacity = capacity;
    }
    /**
     *
     * @return banyak item yang ada diinventory
     */
    public int getSize () {
        return isi;
    }
    /**
     * 
     * @return kapasitas maksimum inventory
     */
    public int getCapacity(){
        return capacity;
    }
    /**
     *
     * @param i
     * @return item yang berada di indeks ke i
     */
    public Item getItem(int i) {
        Item result = null;
        try {
            result = item.get(i);
        } catch(ArrayIndexOutOfBoundsException e){}
        return result;
    }
    public void add(Item it){
        if (isi<capacity){
            item.add(it);
            isi++;
        } else {
            throw new IndexOutOfBoundsException("Inventory Penuh!");
        }
    }
    public Inventory getFarmItem(){
        Inventory farm = new Inventory();
        for (Item i:item){
            if (i instanceof FarmItem){
                farm.add(i);
            }
        }
        return farm;
    }


    public Inventory getFoodItem(){
        Inventory food = new Inventory();
        for (Item i:item){
            if (i instanceof FoodItem){
                food.add(i);
            }
        }
        return food;
    }
}