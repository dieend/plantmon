package plantmon.entity;

import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import plantmon.game.ImageEntity;

public abstract class Item {
    protected String name;
    protected int costBuy;
    protected int costSell;
    protected int IDItem;
    protected ImageEntity gambar;
    protected String deskripsi;
    private ImageIcon image1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;

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
    final public static int BiStroberi = 12;
    final public static int BiLabu = 13;
    final public static int BiBayam = 14;

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
    final public static int BuStroberi = 32;
    final public static int BuLabu = 33;
    final public static int BuBayam = 34;

    final public static int Water = 50;
    final public static int HolyWater = 51;
    final public static int Antitoxin = 52;
    final public static int EyeDrop = 53;
    final public static int HotWater = 54;

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
//        label5 = new JLabel("Attack Status: " + this.getAtkStatus());
//        label5.setFont(new Font("Times New Roman", Font.BOLD, 16));
//        label5.setBounds(250,20,150,30);
//        label6 = new JLabel("Deffense Status: " + this.getDefStatus());
//        label6.setFont(new Font("Times New Roman", Font.BOLD, 16));
//        label6.setBounds(250,0,150,30);
//        label7 = new JLabel("Int Status: " + this.getIntStatus());
//        label7.setFont(new Font("Times New Roman", Font.BOLD, 16));
//        label7.setBounds(120,60,150,30);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
//        panel.add(label5);
//        panel.add(label6);
//        panel.add(label7);
        return panel;
    }
}