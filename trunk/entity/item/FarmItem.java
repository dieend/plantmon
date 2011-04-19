package plantmon.entity.item;

import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import plantmon.entity.Item;

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
        //gambar = new ImageEntity(panel);
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
        } else if (IDitem == BiStroberi) {
            name = "Stroberi";
            deskripsi = "bibit Stroberi";
            costBuy = 150;
            efek = 4;
            //gambar.load(null);
        } else if (IDitem == BiLabu) {
            name = "Labu";
            deskripsi = "bibit Labu";
            costBuy = 500;
            efek = 4;
            //gambar.load(null);
        }
        costSell = costBuy / 2;
    }
    public void draw() {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void update() {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

        public static void main(String[] str){
        JFrame frame=new JFrame();
        BufferedImage bf = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        FarmItem a = new FarmItem(Item.BiWortel, new JPanel());
        frame.getContentPane().add(a.get_Info());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setVisible(true);
    }
}
