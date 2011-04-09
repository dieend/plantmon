package plantmon.entity.item;

import plantmon.entity.Item;


public class FarmItem extends Item {
    int efekBuah;//efek dari buah yang dimakan player
    int costSellBibit;
    int typeTanaman;
    int typeBuah;
    boolean bibit;
    public int getTipeBuah(){
        return typeBuah;
    }
    public int getTipeTanaman(){
        return typeTanaman;
    }
    public int getEfekBuah(){
        return efekBuah;
    }
    boolean isBibit(){
        return bibit;
    }
    public void setEfekBuah(int e){
        efekBuah=e;
    }
    public void setCostSellBibit(int e){
        costSellBibit=e;
    }
    public void setTypeTanaman(int e){
        typeTanaman=e;
    }
    public void setTypeBuah(int e){
        typeBuah=e;
    }
    public void setBibit(boolean e){
        bibit=e;
    }
    public static FarmItem makeBibit(String name){
        FarmItem item=new FarmItem();
        item.setNama(name);
        if(name.equals("kentang"))          {item.setIDitem(0);item.setTypeTanaman(1);item.setTypeBuah(13);item.setEfekBuah(1);item.setCostBuy(150);item.setCostSell(720);item.setCostSellBibit(75);item.setBibit(true);}
        else if(name.equals("lobak"))	{item.setIDitem(1);item.setTypeTanaman(2);item.setTypeBuah(14);item.setEfekBuah(2);item.setCostBuy(120);item.setCostSell(540);item.setCostSellBibit(60);item.setBibit(true);}
        else if(name.equals("timun"))	{item.setIDitem(2);item.setTypeTanaman(3);item.setTypeBuah(15);item.setEfekBuah(1);item.setCostBuy(200);item.setCostSell(900);item.setCostSellBibit(100);item.setBibit(true);}
        else if(name.equals("kubis"))	{item.setIDitem(3);item.setTypeTanaman(4);item.setTypeBuah(16);item.setEfekBuah(4);item.setCostBuy(500);item.setCostSell(2250);item.setCostSellBibit(250);item.setBibit(true);}
        else if(name.equals("jagung"))	{item.setIDitem(4);item.setTypeTanaman(5);item.setTypeBuah(17);item.setEfekBuah(1);item.setCostBuy(300);item.setCostSell(900);item.setCostSellBibit(150);item.setBibit(true);}
        else if(name.equals("tomat"))	{item.setIDitem(5);item.setTypeTanaman(6);item.setTypeBuah(18);item.setEfekBuah(1);item.setCostBuy(200);item.setCostSell(540);item.setCostSellBibit(100);item.setBibit(true);}
        else if(name.equals("bawang"))	{item.setIDitem(6);item.setTypeTanaman(7);item.setTypeBuah(19);item.setEfekBuah(2);item.setCostBuy(150);item.setCostSell(720);item.setCostSellBibit(75);item.setBibit(true);}
        else if(name.equals("nanas"))	{item.setIDitem(7);item.setTypeTanaman(8);item.setTypeBuah(20);item.setEfekBuah(4);item.setCostBuy(1000);item.setCostSell(4500);item.setCostSellBibit(500);item.setBibit(true);}
        else if(name.equals("wortel"))	{item.setIDitem(8);item.setTypeTanaman(9);item.setTypeBuah(21);item.setEfekBuah(1);item.setCostBuy(300);item.setCostSell(1080);item.setCostSellBibit(150);item.setBibit(true);}
        else if(name.equals("terong"))	{item.setIDitem(9);item.setTypeTanaman(10);item.setTypeBuah(22);item.setEfekBuah(4);item.setCostBuy(120);item.setCostSell(720);item.setCostSellBibit(60);item.setBibit(true);}
        else if(name.equals("ubi"))         {item.setIDitem(10);item.setTypeTanaman(11);item.setTypeBuah(23);item.setEfekBuah(2);item.setCostBuy(300);item.setCostSell(1080);item.setCostSellBibit(150);item.setBibit(true);}
        else if(name.equals("paprika"))	{item.setIDitem(11);item.setTypeTanaman(12);item.setTypeBuah(24);item.setEfekBuah(4);item.setCostBuy(150);item.setCostSell(360);item.setCostSellBibit(75);item.setBibit(true);}
        else {item = null;}
        return item;
    }
}
