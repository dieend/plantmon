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
    /**
     * membuat item berdasarkan nama item
     * @param name
     * @return
     */
    public static FarmItem ItemFactory(String name) {
        FarmItem farmitem = new FarmItem();
        if(name.equals("kentang"))      {farmitem.setIDitem(0);farmitem.setTypeTanaman(1);farmitem.setTypeBuah(13);farmitem.setEfekBuah(1);farmitem.setCostBuy(150);farmitem.setCostSell(720);farmitem.setCostSellBibit(75);farmitem.setBibit(true);}
        else if(name.equals("lobak"))	{farmitem.setIDitem(1);farmitem.setTypeTanaman(2);farmitem.setTypeBuah(14);farmitem.setEfekBuah(2);farmitem.setCostBuy(120);farmitem.setCostSell(540);farmitem.setCostSellBibit(60);farmitem.setBibit(true);}
        else if(name.equals("timun"))	{farmitem.setIDitem(2);farmitem.setTypeTanaman(3);farmitem.setTypeBuah(15);farmitem.setEfekBuah(1);farmitem.setCostBuy(200);farmitem.setCostSell(900);farmitem.setCostSellBibit(100);farmitem.setBibit(true);}
        else if(name.equals("kubis"))	{farmitem.setIDitem(3);farmitem.setTypeTanaman(4);farmitem.setTypeBuah(16);farmitem.setEfekBuah(4);farmitem.setCostBuy(500);farmitem.setCostSell(2250);farmitem.setCostSellBibit(250);farmitem.setBibit(true);}
        else if(name.equals("jagung"))	{farmitem.setIDitem(4);farmitem.setTypeTanaman(5);farmitem.setTypeBuah(17);farmitem.setEfekBuah(1);farmitem.setCostBuy(300);farmitem.setCostSell(900);farmitem.setCostSellBibit(150);farmitem.setBibit(true);}
        else if(name.equals("tomat"))	{farmitem.setIDitem(5);farmitem.setTypeTanaman(6);farmitem.setTypeBuah(18);farmitem.setEfekBuah(1);farmitem.setCostBuy(200);farmitem.setCostSell(540);farmitem.setCostSellBibit(100);farmitem.setBibit(true);}
        else if(name.equals("bawang"))	{farmitem.setIDitem(6);farmitem.setTypeTanaman(7);farmitem.setTypeBuah(19);farmitem.setEfekBuah(2);farmitem.setCostBuy(150);farmitem.setCostSell(720);farmitem.setCostSellBibit(75);farmitem.setBibit(true);}
        else if(name.equals("nanas"))	{farmitem.setIDitem(7);farmitem.setTypeTanaman(8);farmitem.setTypeBuah(20);farmitem.setEfekBuah(4);farmitem.setCostBuy(1000);farmitem.setCostSell(4500);farmitem.setCostSellBibit(500);farmitem.setBibit(true);}
        else if(name.equals("wortel"))	{farmitem.setIDitem(8);farmitem.setTypeTanaman(9);farmitem.setTypeBuah(21);farmitem.setEfekBuah(1);farmitem.setCostBuy(300);farmitem.setCostSell(1080);farmitem.setCostSellBibit(150);farmitem.setBibit(true);}
        else if(name.equals("terong"))	{farmitem.setIDitem(9);farmitem.setTypeTanaman(10);farmitem.setTypeBuah(22);farmitem.setEfekBuah(4);farmitem.setCostBuy(120);farmitem.setCostSell(720);farmitem.setCostSellBibit(60);farmitem.setBibit(true);}
        else if(name.equals("ubi"))     {farmitem.setIDitem(10);farmitem.setTypeTanaman(11);farmitem.setTypeBuah(23);farmitem.setEfekBuah(2);farmitem.setCostBuy(300);farmitem.setCostSell(1080);farmitem.setCostSellBibit(150);farmitem.setBibit(true);}
        else if(name.equals("paprika"))	{farmitem.setIDitem(11);farmitem.setTypeTanaman(12);farmitem.setTypeBuah(24);farmitem.setEfekBuah(4);farmitem.setCostBuy(150);farmitem.setCostSell(360);farmitem.setCostSellBibit(75);farmitem.setBibit(true);}
        else {farmitem = null;}
        return farmitem;
    }
}
