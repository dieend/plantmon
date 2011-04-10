package plantmon.entity;
/**
 * Masih salah, harusnya ada jumlah item di tiap slot... saya lupa -.-"
 */
import java.util.ArrayList;
import plantmon.entity.item.ArmorItem;
import plantmon.entity.item.FarmItem;
import plantmon.entity.item.WarItem;
import plantmon.entity.item.FoodItem;

public class Inventory {
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

    public Item getSlot(int noslot){
	return (item.get(size));
    }

    int getJumlah(int noslot){
        return (jumlahitem.get(muatan));
    }
    
    public Item getItem(int i) {
        Item result = null;
        try {
            result = item.get(i);
        } catch(ArrayIndexOutOfBoundsException e){}
        return result;
    }

    public void add(Item it, int jumlah) {
        int i = 0;
        boolean found=false;
        int temp;
        
        while(i<size && !found) {
            if(item.get(i).getIDitem() == it.getIDitem()) {//pengecekan pada Array item apakah isinya sama
                temp = jumlahitem.get(i) + jumlah;
                jumlahitem.set(i, temp);
                found = true;
            } else {
                i++;
            }
        }
        if (!found) {
            item.add(i,it);
            jumlahitem.add(i,jumlah);
            size++;
            muatan++;
        }
    }
    
    public void delete(Item it, int jumlah){
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

    
    
    public Inventory getFarmItem(){
        Inventory farm = new Inventory();
        for (int i = 0; i < item.size(); i++){
            if (item.get(i) instanceof FarmItem){
                farm.add(item.get(i),jumlahitem.get(i));
            }
        }
        return farm;
    }

    public Inventory getWarItem(){
        Inventory war = new Inventory(item.size());
        for(int i = 0; i < item.size(); i++){
            if (item.get(i) instanceof WarItem){
                war.add(item.get(i),jumlahitem.get(i));
            }
        }
        return war;
    }

    public Inventory getFoodItem(){
        Inventory food = new Inventory(item.size());
        for(int i = 0; i < item.size(); i++){
            if(item.get(i) instanceof FoodItem){
                food.add(item.get(i),jumlahitem.get(i));
            }
        }
        return food;
    }

    public Inventory getArmorItem(){
        Inventory armor = new Inventory(item.size());
        for(int i = 0; i < item.size(); i++){
            if(item.get(i) instanceof ArmorItem){
                armor.add(item.get(i),jumlahitem.get(i));
            }
        }
        return armor;
    }

    public int getSize() {
        return size;
    }
}