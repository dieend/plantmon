package plantmon.entity.unmoveable;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import plantmon.entity.Item;
import plantmon.entity.item.FoodItem;
import plantmon.game.GridMap;
import plantmon.game.TalkPanel;
import plantmon.states.Game;

public class Lobak extends Plant{
    public Lobak(GridMap map, JPanel panel, Graphics2D g2d,int gx, int gy,int status) {
        super(map,panel,g2d,gx,gy);
        setFase(status - 1);
        typeTanaman = 2;
        season = 0;
        happyMeter = 0;
        titikDewasa = 2;
        titikPanen = 5;
        umur = 8;
        panenBerulang = false;
    }
     @Override public JPanel get_Info(){
        ImageIcon image1;
        JLabel label1;
        JLabel label2;
        JLabel label3 = null;
        JLabel label4;

        JPanel panel;
        panel = new TalkPanel();
        panel.setLayout(null);
        image1=new ImageIcon(getClass().getResource("lobak.png"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label1 = new JLabel(image1);
        label1.setBounds(0,0,100,100);
        panel.add(label1);
        label2 = new JLabel("Nama : Lobak");
        label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label2.setBounds(100,0,150,30);
        if(this.getFase()==BIBITNOSIRAM){
            label3 = new JLabel("Fase : Bibit belum disiram");}
        if(this.getFase()==BIBITSIRAM){
            label3 = new JLabel("Fase : Bibit sudah disiram");}
        if(this.getFase()==REMAJANOSIRAM){
            label3 = new JLabel("Fase : Tanaman belum disiram");}
        if(this.getFase()==REMAJASIRAM){
            label3 = new JLabel("Fase : Tanaman sudah disiram");}
        if(this.getFase()==BIBITMATI){
            label3 = new JLabel("Fase : Bibit mati");}
        if(this.getFase()==DEWASANOSIRAM){
            label3 = new JLabel("Fase : Tanaman belum disiram");}
        if(this.getFase()==DEWASASIRAM){
            label3 = new JLabel("Fase : Tanaman sudah disiram");}
        if(this.getFase()==TANAMANMATI){
            label3 = new JLabel("Fase : Bibit sudah disiram");}

        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label3.setBounds(100,30,300,30);
        label4 = new JLabel("Umur : " + this.getUmur());
        label4.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label4.setBounds(100,60,300,30);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        return panel;
    }
    @Override public void setPanen(){
        Game.instance().getInventory().add(new FoodItem(Item.BuLobak, null),1);
        super.setPanen();
    }
    public static void main(String[] str){
        JFrame frame=new JFrame();
        BufferedImage bf = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Lobak a = new Lobak(new GridMap(), new JPanel(), bf.createGraphics(), 0,0,1);
        frame.getContentPane().add(a.get_Info());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
    @Override
    public void setFase(int i) {
        fase = i;
        if (fase == DEWASANOSIRAM || fase == DEWASASIRAM){
            entity.load("picture/lobakpanen.png");//, 1, 1, Utilities.GRIDSIZE, Utilities.GRIDSIZE);
        } else if (fase == REMAJANOSIRAM || fase == REMAJASIRAM){
            entity.load("picture/lobakremaja.png");//, 1, 1, Utilities.GRIDSIZE, Utilities.GRIDSIZE);
        } else {
            super.setFase(i);
        }
    }
}
