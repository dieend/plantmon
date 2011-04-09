package plantmon.entity;

// maaf yang item masih belum -.-
public class Item{
    String name;
    int efekBuah;//efek dari buah yang dimakan player
    int costBuy;
    int costSell;
    int costSellBibit;
    int typeTanaman;
    int typeBuah;
    int IDitem;
    boolean bibit;


    public String getName() {
        return name;
    }
    public int getCostBuy(){
        return costBuy;
    }
    public int getCostSell(){
        return costSell;
    }
    public int getTipeBuah(){
        return typeBuah;
    }
    public int getTipeTanaman(){
        return typeTanaman;
    }
    public int getEfekBuah(){
        return efekBuah;
    }
    public int getIDitem(){
        return IDitem;
    }
    boolean isBibit(){
        return bibit;
    }
    public void setEfekBuah(int e){
        efekBuah=e;
    }
    public void setCostBuy(int e){
        costBuy=e;
    }
    public void setCostSell(int e){
        costSell=e;
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
    public void setIDitem(int e){
        IDitem=e;
    }
    public void setBibit(boolean e){
        bibit=e;
    }
    public void setNama(String s){
        name =s;
    }

    public Item (){
    }//konstruktor
    public Item makeBibit(String e){
    Item item=new Item();
    item.setNama(e);
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
    //musti disatuin dengan kelas Grid_Plant
//    public static Item makeBuah(Grid_Plant GP){
//    Item item = new Item();
//    if (GP.getTypeTanaman() == 1)	{item.setIDitem(12);item.setTypeTanaman(1);item.setTypeBuah(13);item.setEfekBuah(2);item.setCostBuy(150);item.setCostSell(720);item.setCostSellBibit(75);item.setBibit(false);item.setNama("kentang");}
//    else if(GP.getTypeTanaman() == 2)	{item.setIDitem(13);item.setTypeTanaman(2);item.setTypeBuah(14);item.setEfekBuah(0);item.setCostBuy(120);item.setCostSell(540);item.setCostSellBibit(60);item.setBibit(false);item.setNama("lobak");}
//    else if(GP.getTypeTanaman() == 3)	{item.setIDitem(14);item.setTypeTanaman(3);item.setTypeBuah(15);item.setEfekBuah(1);item.setCostBuy(200);item.setCostSell(900);item.setCostSellBibit(100);item.setBibit(false);item.setNama("timun");}
//    else if(GP.getTypeTanaman() == 4)	{item.setIDitem(15);item.setTypeTanaman(4);item.setTypeBuah(16);item.setEfekBuah(3);item.setCostBuy(500);item.setCostSell(2250);item.setCostSellBibit(250);item.setBibit(false);item.setNama("kubis");}
//    else if(GP.getTypeTanaman() == 5)	{item.setIDitem(16);item.setTypeTanaman(5);item.setTypeBuah(17);item.setEfekBuah(0);item.setCostBuy(300);item.setCostSell(900);item.setCostSellBibit(150);item.setBibit(false);item.setNama("jagung");}
//    else if(GP.getTypeTanaman() == 6)	{item.setIDitem(17);item.setTypeTanaman(6);item.setTypeBuah(18);item.setEfekBuah(0);item.setCostBuy(200);item.setCostSell(540);item.setCostSellBibit(100);item.setBibit(false);item.setNama("tomat");}
//    else if(GP.getTypeTanaman() == 7)	{item.setIDitem(18);item.setTypeTanaman(7);item.setTypeBuah(19);item.setEfekBuah(2);item.setCostBuy(150);item.setCostSell(720);item.setCostSellBibit(75);item.setBibit(false);item.setNama("bawang");}
//    else if(GP.getTypeTanaman() == 8)	{item.setIDitem(19);item.setTypeTanaman(8);item.setTypeBuah(20);item.setEfekBuah(3);item.setCostBuy(1000);item.setCostSell(4500);item.setCostSellBibit(500);item.setBibit(false);item.setNama("nanas");}
//    else if(GP.getTypeTanaman() == 9)	{item.setIDitem(20);item.setTypeTanaman(9);item.setTypeBuah(21);item.setEfekBuah(0);item.setCostBuy(300);item.setCostSell(1080);item.setCostSellBibit(150);item.setBibit(false);item.setNama("wortel");}
//    else if(GP.getTypeTanaman() == 10)	{item.setIDitem(21);item.setTypeTanaman(10);item.setTypeBuah(22);item.setEfekBuah(4);item.setCostBuy(120);item.setCostSell(720);item.setCostSellBibit(60);item.setBibit(false);item.setNama("terong");}
//    else if(GP.getTypeTanaman() == 11)	{item.setIDitem(22);item.setTypeTanaman(11);item.setTypeBuah(23);item.setEfekBuah(0);item.setCostBuy(300);item.setCostSell(1080);item.setCostSellBibit(150);item.setBibit(false);item.setNama("ubi");}
//    else if(GP.getTypeTanaman() == 12)	{item.setIDitem(23);item.setTypeTanaman(12);item.setTypeBuah(24);item.setEfekBuah(4);item.setCostBuy(150);item.setCostSell(360);item.setCostSellBibit(75);item.setBibit(false);item.setNama("paprika");}
//    else {return null;}
//    return item;
//    }

//============================================ DRIVER======================================================
public static void main(String[] str){
    Item item = new Item();
    item.setNama("ubi");
    System.out.println("Nama buah : "+item.getName());
    
}

}