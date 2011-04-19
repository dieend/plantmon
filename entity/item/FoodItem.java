package plantmon.entity.item;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
        } else if (IDitem == BuStroberi) {
            name = "Stroberi";
            costSell = 450;
            efek = 1;
            //gambar.load(null);
        } else if (IDitem == BuLabu) {
            name = "Labu";
            costSell = 2250;
            efek = 3;
            //gambar.load(null);
        }
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
        FoodItem a = new FoodItem(Item.BuWortel, new JPanel());
        frame.getContentPane().add(a.get_Info());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setVisible(true);
    }
}

