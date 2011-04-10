package plantmon.entity.item;

import javax.swing.JPanel;
import plantmon.entity.Item;
import plantmon.game.ImageEntity;

public class FoodItem extends Item {
    int efek;//efek dari buah yang dimakan player

    final public static int Kentang = 0;
    final public static int Lobak = 1;
    final public static int Timun = 2;
    final public static int Kubis = 3;
    final public static int Jagung = 4;
    final public static int Tomat = 5;
    final public static int Bawang = 6;
    final public static int Nanas = 7;
    final public static int Wortel = 8;
    final public static int Terong = 9;
    final public static int Ubi = 10;
    final public static int Paprika = 11;

    public int getEfek(){
        return efek;
    }
    public void setEfek(int e){
        efek=e;
    }

    public FoodItem(int IDitem, JPanel panel) {
        gambar = new ImageEntity(panel);
        IDItem = IDitem;
        if (IDitem == Kentang) {
            name = "Kentang";
            costSell = 720;
            efek = 2;
            gambar.load(null);
        } else if (IDitem == Lobak) {
            name = "Lobak";
            costSell = 540;
            efek = 0;
            gambar.load(null);
        } else if (IDitem == Timun) {
            name = "Timun";
            costSell = 900;
            efek = 1;
            gambar.load(null);
        } else if (IDitem == Kubis) {
            name = "Kubis";
            costSell = 2250;
            efek = 3;
            gambar.load(null);
        } else if (IDitem == Nanas) {
            name = "Nanas";
            costSell = 4500;
            efek = 3;
            gambar.load(null);
        } else if (IDitem == Jagung) {
            name = "Jagung";
            costSell = 900;
            efek = 0;
            gambar.load(null);
        } else if (IDitem == Tomat) {
            name = "Tomat";
            costSell = 540;
            efek = 0;
            gambar.load(null);
        } else if (IDitem == Bawang) {
            name = "Bawang";
            costSell = 720;
            efek = 2;
            gambar.load(null);
        } else if (IDitem == Wortel) {
            name = "Wortel";
            costSell = 1080;
            efek = 0;
            gambar.load(null);
        } else if (IDitem == Terong) {
            name = "Terong";
            costSell = 720;
            efek = 4;
            gambar.load(null);
        } else if (IDitem == Ubi) {
            name = "Ubi";
            costSell = 1080;
            efek = 0;
            gambar.load(null);
        } else if (IDitem == Paprika) {
            name = "Paprika";
            costSell = 360;
            efek = 4;
            gambar.load(null);
        }
    }
}

