package plantmon.entity.item;

import javax.swing.JPanel;
import plantmon.entity.Item;
import plantmon.game.ImageEntity;

public class FoodItem extends Item {
    int efek;//efek dari buah yang dimakan player

    public int getEfek(){
        return efek;
    }
    public void setEfek(int e){
        efek=e;
    }

    public FoodItem(int IDitem, JPanel panel) {
        gambar = new ImageEntity(panel);
        IDItem = IDitem;
        if (IDitem == BuKentang) {
            name = "Kentang";
            costSell = 720;
            efek = 2;
            //gambar.load(null);
        } else if (IDitem == BuLobak) {
            name = "Lobak";
            costSell = 540;
            efek = 0;
            //gambar.load(null);
        } else if (IDitem == BuTimun) {
            name = "Timun";
            costSell = 900;
            efek = 1;
            //gambar.load(null);
        } else if (IDitem == BuKubis) {
            name = "Kubis";
            costSell = 2250;
            efek = 3;
            //gambar.load(null);
        } else if (IDitem == BuNanas) {
            name = "Nanas";
            costSell = 4500;
            efek = 3;
            //gambar.load(null);
        } else if (IDitem == BuJagung) {
            name = "Jagung";
            costSell = 900;
            efek = 0;
            //gambar.load(null);
        } else if (IDitem == BuTomat) {
            name = "Tomat";
            costSell = 540;
            efek = 0;
            //gambar.load(null);
        } else if (IDitem == BuBawang) {
            name = "Bawang";
            costSell = 720;
            efek = 2;
            //gambar.load(null);
        } else if (IDitem == BuWortel) {
            name = "Wortel";
            costSell = 1080;
            efek = 0;
            //gambar.load(null);
        } else if (IDitem == BuTerong) {
            name = "Terong";
            costSell = 720;
            efek = 4;
            //gambar.load(null);
        } else if (IDitem == BuUbi) {
            name = "Ubi";
            costSell = 1080;
            efek = 0;
            //gambar.load(null);
        } else if (IDitem == BuPaprika) {
            name = "Paprika";
            costSell = 360;
            efek = 4;
            //gambar.load(null);
        }
    }
}

