package plantmon.entity;
/**
 * Masih salah, harusnya ada jumlah item di tiap slot... saya lupa -.-"
 */
import java.lang.Integer;
import java.util.ArrayList;
import plantmon.entity.item.FarmItem;
import plantmon.entity.item.WarItem;
import plantmon.entity.item.FoodItem;

public class Inventory{
    int size;
    ArrayList<Item>  item;
    ArrayList<Integer> jumlahitem;
    int muatan;
    
    public Inventory(int max) {
        item = new ArrayList<Item>(max);
        jumlahitem=new ArrayList<Integer>(max);
        size = 0;//merupakan ukuran dari Array item
        muatan = 0;//merupakan ukuran dari Array jumlah item
    }
    public Inventory(){
        item = new ArrayList<Item>();
        jumlahitem = new ArrayList<Integer>();
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

    public void add(Item it, int jumlah){
        int i=size;
        boolean found=false;
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

}