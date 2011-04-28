package plantmon.entity;
/**
 * Masih salah, harusnya ada jumlah item di tiap slot... saya lupa -.-"
 */
import java.io.Serializable;
import java.util.ArrayList;
import plantmon.entity.item.ArmorItem;
import plantmon.entity.item.FarmItem;
import plantmon.entity.item.WarItem;
import plantmon.entity.item.FoodItem;

public class Inventory implements Serializable{
    int size;
    ArrayList<Item> item;
    ArrayList<Integer> jumlahitem;
    int muatan;
    
    public Inventory(int max) {
        item = new ArrayList<Item>(max);
        jumlahitem=new ArrayList<Integer>(max);
        size = 0;//merupakan ukuran dari Array item
        muatan = 0;//merupakan ukuran dari Array jumlah item
    }

    public Inventory() {
        item = new ArrayList<Item>();
        jumlahitem = new ArrayList<Integer>();
        size = 0;
        muatan = 0;
    }

    public int getJumlah(int noslot){
        return (jumlahitem.get(noslot));
    }
    
    public synchronized Item getItem(int i) {
        Item result = null;
        try {
            result = item.get(i);
        } catch(ArrayIndexOutOfBoundsException e){}
        return result;
    }

    public synchronized void add(Item it, int jumlah) {
        int i = 0;
        boolean found=false;
        int temp;
        
        while(i<size && !found) {
            if(item.get(i).getIDitem() == it.getIDitem()) {//pengecekan pada Array item apakah isinya sama
                temp = jumlahitem.get(i) + jumlah;
                jumlahitem.set(i, temp);
                found = true;
//                System.out.print("Tambah item");
            } else {
                i++;
            }
        }
        if (!found) {
//            System.out.print("Tambah item");
            item.add(i,it);
            jumlahitem.add(i,jumlah);
            size++;
            muatan++;
        }
    }
    
    public synchronized void delete(Item it, int jumlah){
        int i = 0;
        int temp;
        boolean found = false;
        while (i <= size && !found) {
            if(item.get(i) == it) {
                if (jumlahitem.get(i) > jumlah) {
                    temp = jumlahitem.get(i) - jumlah;
                    jumlahitem.set(i, temp);
                } else if (jumlahitem.get(i) <= jumlah) {
                    item.remove(i);
                    jumlahitem.remove(i);
                    size--;
                    muatan--;
                }
                found = true;
            } else {
                i++;
            }
        }
    }

    
    
    public synchronized Inventory getFarmItem(){
        Inventory farm = new Inventory();
        for (int i = 0; i < item.size(); i++){
            if (item.get(i) instanceof FarmItem){
                farm.add(item.get(i),jumlahitem.get(i));
            }
        }
        return farm;
    }

    public synchronized Inventory getWarItem(){
        Inventory war = new Inventory(item.size());
        for(int i = 0; i < item.size(); i++){
            if (item.get(i) instanceof WarItem){
                war.add(item.get(i),jumlahitem.get(i));
            }
        }
        return war;
    }

    public synchronized Inventory getFoodItem(){
        Inventory food = new Inventory(item.size());
        for(int i = 0; i < item.size(); i++){
            if(item.get(i) instanceof FoodItem){
                food.add(item.get(i),jumlahitem.get(i));
            }
        }
        return food;
    }

    public synchronized Inventory getArmorItem(){
        Inventory armor = new Inventory(item.size());
        for(int i = 0; i < item.size(); i++){
            if(item.get(i) instanceof ArmorItem){
                armor.add(item.get(i),jumlahitem.get(i));
            }
        }
        return armor;
    }

    public synchronized int getSize() {
        return size;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int getJumItem (Item it) {
        int a = 0;
        for (int i = 0; i < item.size(); i++) {
            if (item.get(i).getIDitem() == it.getIDitem()) {
                a = jumlahitem.get(i);
            }
        }
        return a;
    }

//    public boolean isEmpty(Inventory i) {
//        if (i.getSize() == 0) {
//            return true;
//        } else {
//            return false;
//        }
//    }
}