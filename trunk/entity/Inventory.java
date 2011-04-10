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
        int i=size;
        boolean found=false;
            while(i>=0 && !found){
                if (i != 0) {
                    if(item.get(i) != null) {
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
                        item.add(i, it);
                        jumlahitem.add(i, jumlah);
                        size++;
                        muatan++;
                        found = true;
                    }
                } else {
                    item.add(i, it);
                    jumlahitem.add(i, jumlah);
                    size++;
                    muatan++;
                    found = true;
                }
            }
    }
    
    public void delete(Item it, int jumlah){
        int i = size;
        boolean found = false;
        if (item.get(i) != null) {
            if (jumlahitem.get(i) > jumlah) {
                muatan = muatan-jumlah;
            }
            else if (jumlahitem.get(i) == jumlah) {
                    muatan = 0;
                    item.set(i, it);
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