package plantmon.entity.item;

public class WarItem{
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
    public Item Item(String name) {
        Item item = new Item();
        FarmItem farmitem = new FarmItem();
        if(name.equals("kentang"))      {item.setIDitem(0);farmitem.setTypeTanaman(1);farmitem.setTypeBuah(13);farmitem.setEfekBuah(1);item.setCostBuy(150);item.setCostSell(720);farmitem.setCostSellBibit(75);farmitem.setBibit(true);}
        else if(name.equals("lobak"))	{item.setIDitem(1);farmitem.setTypeTanaman(2);farmitem.setTypeBuah(14);farmitem.setEfekBuah(2);item.setCostBuy(120);item.setCostSell(540);farmitem.setCostSellBibit(60);farmitem.setBibit(true);}
        else if(name.equals("timun"))	{item.setIDitem(2);farmitem.setTypeTanaman(3);farmitem.setTypeBuah(15);farmitem.setEfekBuah(1);item.setCostBuy(200);item.setCostSell(900);farmitem.setCostSellBibit(100);farmitem.setBibit(true);}
        else if(name.equals("kubis"))	{item.setIDitem(3);farmitem.setTypeTanaman(4);farmitem.setTypeBuah(16);farmitem.setEfekBuah(4);item.setCostBuy(500);item.setCostSell(2250);farmitem.setCostSellBibit(250);farmitem.setBibit(true);}
        else if(name.equals("jagung"))	{item.setIDitem(4);farmitem.setTypeTanaman(5);farmitem.setTypeBuah(17);farmitem.setEfekBuah(1);item.setCostBuy(300);item.setCostSell(900);farmitem.setCostSellBibit(150);farmitem.setBibit(true);}
        else if(name.equals("tomat"))	{item.setIDitem(5);farmitem.setTypeTanaman(6);farmitem.setTypeBuah(18);farmitem.setEfekBuah(1);item.setCostBuy(200);item.setCostSell(540);farmitem.setCostSellBibit(100);farmitem.setBibit(true);}
        else if(name.equals("bawang"))	{item.setIDitem(6);farmitem.setTypeTanaman(7);farmitem.setTypeBuah(19);farmitem.setEfekBuah(2);item.setCostBuy(150);item.setCostSell(720);farmitem.setCostSellBibit(75);farmitem.setBibit(true);}
        else if(name.equals("nanas"))	{item.setIDitem(7);farmitem.setTypeTanaman(8);farmitem.setTypeBuah(20);farmitem.setEfekBuah(4);item.setCostBuy(1000);item.setCostSell(4500);farmitem.setCostSellBibit(500);farmitem.setBibit(true);}
        else if(name.equals("wortel"))	{item.setIDitem(8);farmitem.setTypeTanaman(9);farmitem.setTypeBuah(21);farmitem.setEfekBuah(1);item.setCostBuy(300);item.setCostSell(1080);farmitem.setCostSellBibit(150);farmitem.setBibit(true);}
        else if(name.equals("terong"))	{item.setIDitem(9);farmitem.setTypeTanaman(10);farmitem.setTypeBuah(22);farmitem.setEfekBuah(4);item.setCostBuy(120);item.setCostSell(720);farmitem.setCostSellBibit(60);farmitem.setBibit(true);}
        else if(name.equals("ubi"))     {item.setIDitem(10);farmitem.setTypeTanaman(11);farmitem.setTypeBuah(23);farmitem.setEfekBuah(2);item.setCostBuy(300);item.setCostSell(1080);farmitem.setCostSellBibit(150);farmitem.setBibit(true);}
        else if(name.equals("paprika"))	{item.setIDitem(11);farmitem.setTypeTanaman(12);farmitem.setTypeBuah(24);farmitem.setEfekBuah(4);item.setCostBuy(150);item.setCostSell(360);farmitem.setCostSellBibit(75);farmitem.setBibit(true);}
        else {item = null;}
        return item;
    }
}
