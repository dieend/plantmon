/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plantmon.entity.item;

import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import plantmon.entity.Item;

/**
 *
 * @author Nugraha Siburian
 */
public class ArmorItem extends Item {
    private int atkStatus;
    private int defStatus;
    private int intStatus;
    private int position;
    private ImageIcon image1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;

    final public static int Head = 0;
    final public static int Body = 1;
    final public static int Shield = 2;
    final public static int Foot = 3;
    final public static int Hand = 4;
    final public static int Pant = 5;

    public ArmorItem(int IDitem,JPanel panel) {
        //gambar = new ImageEntity(panel);
        IDItem = IDitem;
        if (IDitem == Robe) {
            costBuy = 100;
            name = "Robe";
            deskripsi = "DEF +1";
            defStatus = 1;
            atkStatus = 0;
            intStatus = 0;
            position = Body;
            //gambar.load(null);
        } else if (IDitem == Tunic) {
            costBuy = 200;
            name = "Tunic";
            deskripsi = "DEF +2";
            defStatus = 2;
            atkStatus = 0;
            intStatus = 0;
            position = Body;
            //gambar.load(null);
        } else if (IDitem == LeatherCoat) {
            costBuy = 700;
            name = "Leather Coat";
            deskripsi = "DEF +4";
            defStatus = 4;
            atkStatus = 0;
            intStatus = 0;
            position = Body;
            //gambar.load(null);
        } else if (IDitem == BrassArmor) {
            costBuy = 1000;
            name = "Brass Armor";
            deskripsi = "DEF +5";
            defStatus = 5;
            atkStatus = 0;
            intStatus = 0;
            position = Body;
            //gambar.load(null);
        } else if (IDitem == Bandanna) {
            costBuy = 50;
            name = "Bandanna";
            deskripsi = "DEF +1";
            defStatus = 1;
            atkStatus = 0;
            intStatus = 0;
            position = Head;
            //gambar.load(null);
        } else if (IDitem == HeadBand) {
            costBuy = 300;
            name = "HeadBand";
            deskripsi = "DEF +2";
            defStatus = 2;
            atkStatus = 0;
            intStatus = 0;
            position = Head;
            //gambar.load(null);
        } else if (IDitem == PointedHat) {
            costBuy = 1200;
            name = "Pointed Hat";
            deskripsi = "DEF +5";
            defStatus = 5;
            atkStatus = 0;
            intStatus = 0;
            position = Head;
            //gambar.load(null);
        } else if (IDitem == WoodenShield) {
            costBuy = 300;
            name = "Wooden Shield";
            deskripsi = "DEF +2";
            defStatus = 2;
            atkStatus = 0;
            intStatus = 0;
            position = Shield;
            //gambar.load(null);
        } else if (IDitem == WoodenShoes) {
            costBuy = 100;
            name = "Wooden Shoes";
            deskripsi = "DEF +1";
            defStatus = 1;
            atkStatus = 0;
            intStatus = 0;
            position = Foot;
            //gambar.load(null);
        } else if (IDitem == Leggings) {
            costBuy = 200;
            name = "Leggings";
            deskripsi = "DEF +2";
            defStatus = 2;
            atkStatus = 0;
            intStatus = 0;
            position = Pant;
            //gambar.load(null);
        } else if (IDitem == Gloves) {
            costBuy = 300;
            name = "Gloves";
            deskripsi = "DEF +2";
            defStatus = 2;
            atkStatus = 0;
            intStatus = 0;
            position = Hand;
            //gambar.load(null);
        } else if (IDitem == Cape) {
            costBuy = 400;
            name = "Cape";
            deskripsi = "DEF +2";
            defStatus = 2;
            atkStatus = 0;
            intStatus = 0;
            position = Head;
            //gambar.load(null);
        } else if (IDitem == Circlet) {
            costBuy = 600;
            name = "Circlet";
            deskripsi = "DEF +3";
            defStatus = 3;
            atkStatus = 0;
            intStatus = 0;
            position = Head;
            //gambar.load(null);
        }
        costSell = costBuy / 2;
    }

    public int getAtkStatus() {
        return atkStatus;
    }

    public void setAtkStatus(int atkStatus) {
        this.atkStatus = atkStatus;
    }

    public int getDefStatus() {
        return defStatus;
    }

    public void setDefStatus(int defStatus) {
        this.defStatus = defStatus;
    }

    public int getIntStatus() {
        return intStatus;
    }

    public void setIntStatus(int intStatus) {
        this.intStatus = intStatus;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public JPanel get_Info(){
        JPanel panel;
        panel = new JPanel();
        panel.setLayout(null);
        if(this.getName().equals("Robe")){
        image1=new ImageIcon(getClass().getResource("robe.jpg"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        }
        if(this.getName().equals("Tunic")){
        image1=new ImageIcon(getClass().getResource("tunic.jpg"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        }
        if(this.getName().equals("Leather Coat")){
        image1=new ImageIcon(getClass().getResource("leather coat.jpg"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        }
        if(this.getName().equals("Brass Armor")){
        image1=new ImageIcon(getClass().getResource("brass armor.jpg"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        }
        if(this.getName().equals("Bandanna")){
        image1=new ImageIcon(getClass().getResource("bandanna.jpg"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        }
        if(this.getName().equals("HeadBand")){
        image1=new ImageIcon(getClass().getResource("headband.jpg"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        }
        if(this.getName().equals("Pointed Hat")){
        image1=new ImageIcon(getClass().getResource("pointed.jpg"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        }
        if(this.getName().equals("Wooden Shield")){
        image1=new ImageIcon(getClass().getResource("wooden shield.jpg"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        }
        if(this.getName().equals("Wooden Shoes")){
        image1=new ImageIcon(getClass().getResource("wooden shoes.jpg"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        }
        if(this.getName().equals("Leggings")){
        image1=new ImageIcon(getClass().getResource("leggings.jpg"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        }
        if(this.getName().equals("Gloves")){
        image1=new ImageIcon(getClass().getResource("gloves.jpg"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        }
        if(this.getName().equals("Cape")){
        image1=new ImageIcon(getClass().getResource("cape.jpg"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        }
        if(this.getName().equals("Circlet")){
        image1=new ImageIcon(getClass().getResource("circlet.jpg"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        }

        label1 = new JLabel(image1);
        label1.setBounds(0,0,100,100);
        panel.add(label1);
        label2 = new JLabel("Nama : " + this.getName());
        label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label2.setBounds(120,0,150,30);
        label3 = new JLabel("Harga Beli : " + this.getCostBuy());
        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label3.setBounds(120,20,150,30);
        label4 = new JLabel("Harga Jual : " + this.getCostSell());
        label4.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label4.setBounds(120,40,150,30);
        label5 = new JLabel("Attack Status: " + this.getAtkStatus());
        label5.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label5.setBounds(250,20,150,30);
        label6 = new JLabel("Deffense Status: " + this.getDefStatus());
        label6.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label6.setBounds(250,0,150,30);
        label7 = new JLabel("Int Status: " + this.getIntStatus());
        label7.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label7.setBounds(120,60,150,30);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        panel.add(label5);
        panel.add(label6);
        panel.add(label7);
        return panel;
    }
    public static void main(String[] str){
        JFrame frame=new JFrame();
        BufferedImage bf = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        ArmorItem a = new ArmorItem(Item.Circlet, new JPanel());
        frame.getContentPane().add(a.get_Info());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setVisible(true);
    }
}