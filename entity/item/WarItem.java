package plantmon.entity.item;

import plantmon.entity.Item;

public class WarItem extends Item{
    int tipeWarItem;
    int tipePeluru;
    int costImproveItem;
    
	
    public int getTipeWarItem(){
        return tipeWarItem;
    }
    public int getTipePeluru(){
        return tipePeluru;
    }
    public int getCostImproveItem(){
	return costImproveItem;
    }
    public void setTipeWarItem(int e){
        tipeWarItem=e;
    }
    public void setTipePeluru(int e){
        tipePeluru=e;
    }
    public void setCostImproveItem(int e){
        costImproveItem=e;
    }
    public static WarItem ItemFactory(String name) {
        WarItem item = new WarItem();
        if(name.equals("armor1"))      {item.setIDitem(71);}
        else {item = null;}
        return item;
    }
}
