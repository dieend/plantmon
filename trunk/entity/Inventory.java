package plantmon.entity;
/**
 * Masih salah, harusnya ada jumlah item di tiap slot... saya lupa -.-"
 */
import java.util.ArrayList;
import java.util.Hashtable;
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
    ArrayList<Item> item;
    /**
     * container jumlah tiap item yang ada inventory
     */
    ArrayList<Integer> jumlahItem;
    /**
     * konstruktor inventory dengan ukuran dinamis dan ~tidak terbatas
     */
    public Inventory(){
        item = new ArrayList<Item>();
        jumlahItem = new ArrayList<Integer>();
        isi = 0;
        capacity = 9999;
    }
    /**
     * konstruktor inventory dengan ukuran maksimum capacity
     * @param capacity
     */
    public Inventory(int capacity) {
        item = new ArrayList<Item>(capacity);
        jumlahItem = new ArrayList<Integer>(capacity);
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
    /**
     * menambah item ke inventory. Jika berhasil (lebih kecil dari kapasitas maksimum Inventory)
     * maka return true, jika gagal return false
     * @param it item;
     * @return keberhasilan
     */
    public boolean add(Item it){
        if (isi<capacity){
            item.add(it);
            isi++;
            return true;
        } else {
            return false;
        }
    }
    /**
     * mengembalikan inventory yang berisikan semua farm item yang dimiliki inventory tersebut.
     * item di inventory ini sama dengan inventory aslinya, jadi jika menghapus inventory
     * @return
     */
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