package plantmon.entity.item;

import javax.swing.JPanel;
import plantmon.entity.Item;
import plantmon.game.ImageEntity;

public class FarmItem extends Item {
    int efek;//efek dari buah yang dimakan player
    int season;

    final public static int Spring = 0;
    final public static int Summer = 1;
    final public static int Fall = 2;

    public int getEfek(){
        return efek;
    }
    public void setEfek(int e){
        efek=e;
    }
  
    public FarmItem(int IDitem, JPanel panel) {
        gambar = new ImageEntity(panel);
        IDItem = IDitem;
        if (IDitem == BiKentang) {
            name = "Kentang";
            deskripsi = "bibit Kentang";
            costBuy = 150;
            efek = 1;
            //gambar.load(null);
        } else if (IDitem == BiLobak) {
            name = "Lobak";
            deskripsi = "bibit Lobak";
            costBuy = 120;
            efek = 2;
            //gambar.load(null);
        } else if (IDitem == BiTimun) {
            name = "Timun";
            deskripsi = "bibit Timun";
            costBuy = 200;
            efek = 1;
            //gambar.load(null);
        } else if (IDitem == BiKubis) {
            name = "Kubis";
            deskripsi = "bibit Kubis";
            costBuy = 500;
            efek = 4;
            //gambar.load(null);
        } else if (IDitem == BiNanas) {
            name = "Nanas";
            deskripsi = "bibit Nanas";
            costBuy = 1000;
            efek = 4;
            //gambar.load(null);
        } else if (IDitem == BiJagung) {
            name = "Jagung";
            deskripsi = "bibit Jagung";
            costBuy = 300;
            efek = 1;
            //gambar.load(null);
        } else if (IDitem == BiTomat) {
            name = "Tomat";
            deskripsi = "bibit Tomat";
            costBuy = 200;
            efek = 1;
            //gambar.load(null);
        } else if (IDitem == BiBawang) {
            name = "Bawang";
            deskripsi = "bibit Bawang";
            costBuy = 150;
            efek = 2;
            //gambar.load(null);
        } else if (IDitem == BiWortel) {
            name = "Wortel";
            deskripsi = "bibit Wortel";
            costBuy = 300;
            efek = 1;
            //gambar.load(null);
        } else if (IDitem == BiTerong) {
            name = "Terong";
            deskripsi = "bibit Terong";
            costBuy = 120;
            efek = 4;
            //gambar.load(null);
        } else if (IDitem == BiUbi) {
            name = "Ubi";
            deskripsi = "bibit Ubi";
            costBuy = 300;
            efek = 1;
            //gambar.load(null);
        } else if (IDitem == BiPaprika) {
            name = "Paprika";
            deskripsi = "bibit Paprika";
            costBuy = 150;
            efek = 4;
            //gambar.load(null);
        }
        costSell = costBuy / 2;
    }
}
