package plantmon.entity;
/**
 * Masih salah, harusnya ada jumlah item di tiap slot... saya lupa -.-"
 */
import java.lang.Integer;
import java.util.ArrayList;
import java.util.Hashtable;
import plantmon.entity.item.FarmItem;
<<<<<<< .mine
import plantmon.entity.item.WarItem;
=======
import plantmon.entity.item.FoodItem;
>>>>>>> .r20

public class Inventory{
<<<<<<< .mine
    int size;
    int isi;
    ArrayList<Item>  item;
    ArrayList<Integer> jumlahitem;
    boolean found = false;
    int muatan;
    
    public Inventory(int max) {
        item = new ArrayList<Item>(max);
        jumlahitem=new ArrayList<Integer>(max);
        size = 0;//merupakan ukuran dari Array item
        muatan = 0;//merupakan ukuran dari Array jumlah item
=======
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
>>>>>>> .r20
    }
<<<<<<< .mine

    public Item getSlot(int noslot){
	return (item.get(size));
=======
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
>>>>>>> .r20
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
<<<<<<< .mine
    int getJumlah(int noslot){
        return (jumlahitem.get(muatan));
=======
    public Item getItem(int i) {
        Item result = null;
        try {
            result = item.get(i);
        } catch(ArrayIndexOutOfBoundsException e){}
        return result;
>>>>>>> .r20
    }
<<<<<<< .mine

    public void add(Item it, int jumlah){
        int i=size;

            while(i>=0 && !found){
                if(item.get(i) != null){
                    if(item.get(i).getIDitem() == it.getIDitem()){//pengecekan pada Array item apakah isinya sama
                        muatan = muatan + jumlah;
                        jumlahitem.set(i, muatan);
                        found = true;
                        }
                    else{
                        i--;
                    }
                }
                else{
                    item.set(i, it);
                    jumlahitem.set(i, jumlah);
                }
            }
=======
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
>>>>>>> .r20
    }
<<<<<<< .mine

    public void delete(Item it, int jumlah){
        int i = size;
        if (item.get(i) != null) {
            if (jumlahitem.get(i) > jumlah) {
                muatan = muatan-jumlah;
=======
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
>>>>>>> .r20
            }
            else if (jumlahitem.get(i) == jumlah) {
                    muatan = 0;
                    item.set(i, it);
            }
	}
    }
    
    
//    public Inventory getFarmItem(){
//        Inventory farm = new Inventory(item.size());
//        for (Item i:item){
//            if (i instanceof FarmItem){
//                farm.add(i);
//            }
//        }
//        return farm;
//    }
//
//    public Inventory getWarItem(){
//        Inventory war = new Inventory(item.size());
//        for(Item i:item){
//            if (i instanceof WarItem){
//                war.add(i);
//            }
//        }
//        return war;
//    }
//
//    public Inventory getFoodItem(){
//        Inventory food = new Inventory(item.size());
//        for(Item i:item){
//            if(i instanceof FoodItem){
//                food.add(i);
//            }
//        }
//        return food;
//    }
//
//    public Inventory getArmorItem(){
//        Inventory armor = new Inventory(item.size());
//        for(Item i:item){
//            if(i instanceof ArmorItem){
//                armor.add(i);
//            }
//        }
//        return armor;
//    }

<<<<<<< .mine
    

=======

    public Inventory getFoodItem(){
        Inventory food = new Inventory();
        for (Item i:item){
            if (i instanceof FoodItem){
                food.add(i);
            }
        }
        return food;
    }
>>>>>>> .r20
}