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
    public void setTypeWarItem(int e){
        tipeWarItem=e;
    }
    public void setTypePeluru(int e){
        tipePeluru=e;
    }
    public void setCostImproveItem(int e){
        costImproveItem=e;
    }
<<<<<<< .mine
    public Item Item(String name) {
        Item item = new Item();
        WarItem waritem = new WarItem();
        if(name.equals("kentang"))      {item.setIDitem(0);waritem.setTypeWarItem(1);waritem.setTypePeluru(13);waritem.setCostImproveItem(1);}
        else if(name.equals("lobak"))	{item.setIDitem(1);waritem.setTypeWarItem(2);waritem.setTypePeluru(14);waritem.setCostImproveItem(2);}
        else if(name.equals("timun"))	{item.setIDitem(2);waritem.setTypeWarItem(3);waritem.setTypePeluru(15);waritem.setCostImproveItem(1);}
        else if(name.equals("kubis"))	{item.setIDitem(3);waritem.setTypeWarItem(4);waritem.setTypePeluru(16);waritem.setCostImproveItem(4);}
        else if(name.equals("jagung"))	{item.setIDitem(4);waritem.setTypeWarItem(5);waritem.setTypePeluru(17);waritem.setCostImproveItem(1);}
        else if(name.equals("tomat"))	{item.setIDitem(5);waritem.setTypeWarItem(6);waritem.setTypePeluru(18);waritem.setCostImproveItem(1);}
        else if(name.equals("bawang"))	{item.setIDitem(6);waritem.setTypeWarItem(7);waritem.setTypePeluru(19);waritem.setCostImproveItem(2);}
        else if(name.equals("nanas"))	{item.setIDitem(7);waritem.setTypeWarItem(8);waritem.setTypePeluru(20);waritem.setCostImproveItem(4);}
        else if(name.equals("wortel"))	{item.setIDitem(8);waritem.setTypeWarItem(9);waritem.setTypePeluru(21);waritem.setCostImproveItem(1);}
        else if(name.equals("terong"))	{item.setIDitem(9);waritem.setTypeWarItem(10);waritem.setTypePeluru(22);waritem.setCostImproveItem(4);}
        else if(name.equals("ubi"))     {item.setIDitem(10);waritem.setTypeWarItem(11);waritem.setTypePeluru(23);waritem.setCostImproveItem(2);}
        else if(name.equals("paprika"))	{item.setIDitem(11);waritem.setTypeWarItem(12);waritem.setTypePeluru(24);waritem.setCostImproveItem(4);}
=======
    public static WarItem ItemFactory(String name) {
        WarItem item = new WarItem();
        if(name.equals("armor1"))      {item.setIDitem(71);}
>>>>>>> .r20
        else {item = null;}
        return item;
    }
}
