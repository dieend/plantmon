package plantmon.entity;

import plantmon.game.ImageEntity;

public class Item {
    protected String name;
    protected int costBuy;
    protected int costSell;
    protected int IDItem;
    protected ImageEntity gambar;
    protected String deskripsi;

    final public static int BiKentang = 0;
    final public static int BiLobak = 1;
    final public static int BiTimun = 2;
    final public static int BiKubis = 3;
    final public static int BiJagung = 4;
    final public static int BiTomat = 5;
    final public static int BiBawang = 6;
    final public static int BiNanas = 7;
    final public static int BiWortel = 8;
    final public static int BiTerong = 9;
    final public static int BiUbi = 10;
    final public static int BiPaprika = 11;

    final public static int BuKentang = 20;
    final public static int BuLobak = 21;
    final public static int BuTimun = 22;
    final public static int BuKubis = 23;
    final public static int BuJagung = 24;
    final public static int BuTomat = 25;
    final public static int BuBawang = 26;
    final public static int BuNanas = 27;
    final public static int BuWortel = 28;
    final public static int BuTerong = 29;
    final public static int BuUbi = 30;
    final public static int BuPaprika = 31;

    final public static int Water = 50;
    final public static int HolyWater = 51;
    final public static int Antitoxin = 52;
    final public static int EyeDrop = 53;
    final public static int HotWater = 53;

    final public static int Bandanna = 70;
    final public static int HeadBand = 71;
    final public static int PointedHat = 72;
    final public static int Robe = 73;
    final public static int Tunic = 74;
    final public static int LeatherCoat = 75;
    final public static int BrassArmor = 76;
    final public static int WoodenShield = 77;
    final public static int WoodenShoes = 78;
    final public static int Leggings = 79;
    final public static int Gloves = 80;
    final public static int Cape = 81;
    final public static int Circlet = 82;

    public String getName(){
        return name;
    }
    public int getCostBuy(){
        return costBuy;
    }
    public int getCostSell(){
        return costSell;
    }
    public int getIDitem(){
        return IDItem;
    }
    public void setName(String e){
        name = e;
    }
    public void setCostBuy(int e){
        costBuy=e;
    }
    public void setCostSell(int e){
        costSell=e;
    }
    public void setIDitem(int e){
        IDItem=e;
    }
    public String getDeskripsi() {
        return deskripsi;
    }
    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}