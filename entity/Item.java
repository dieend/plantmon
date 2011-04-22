package plantmon.entity;

import java.awt.Font;
import java.awt.Image;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class Item implements Serializable{
    protected String name;
    protected int costBuy;
    protected int costSell;
    protected int IDItem;
    protected int efekItem;
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

    final public static int Normal = 0;
    final public static int Paralyze = 1;
    final public static int Poison = 2;
    final public static int Dead = 3;

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
    public int getEfek(){
        return efekItem;
    }
    public void setEfek(int efek){
        efek =efekItem;
    }
    public JPanel get_Info(){
        JPanel panel;

        panel = new JPanel();
        panel.setLayout(null);
        ImageIcon image1 = null;
        JLabel label1;
        JLabel label2;
        JLabel label3;
        JLabel label4;
        JLabel label5;
        JLabel label6;
        JLabel label7;
        //Untuk Armor
        if(this.getIDitem()==Robe){
        image1=new ImageIcon(getClass().getResource("robe.jpg"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label3 = new JLabel("Harga Beli : " + this.getCostBuy());
        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label3.setBounds(120,20,150,30);
        panel.add(label3);
        label4 = new JLabel("Harga Jual : " + this.getCostSell());
        label4.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label4.setBounds(120,40,150,30);
        panel.add(label4);
        }
        if(this.getIDitem()==Tunic){
        image1=new ImageIcon(getClass().getResource("tunic.jpg"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label3 = new JLabel("Harga Beli : " + this.getCostBuy());
        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label3.setBounds(120,20,150,30);
        panel.add(label3);
        label4 = new JLabel("Harga Jual : " + this.getCostSell());
        label4.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label4.setBounds(120,40,150,30);
        panel.add(label4);
        }
        if(this.getIDitem()==LeatherCoat){
        image1=new ImageIcon(getClass().getResource("leather coat.jpg"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label3 = new JLabel("Harga Beli : " + this.getCostBuy());
        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label3.setBounds(120,20,150,30);
        panel.add(label3);
        label4 = new JLabel("Harga Jual : " + this.getCostSell());
        label4.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label4.setBounds(120,40,150,30);
        panel.add(label4);
        }
        if(this.getIDitem()==BrassArmor){
        image1=new ImageIcon(getClass().getResource("brass armor.jpg"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label3 = new JLabel("Harga Beli : " + this.getCostBuy());
        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label3.setBounds(120,20,150,30);
        panel.add(label3);
        label4 = new JLabel("Harga Jual : " + this.getCostSell());
        label4.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label4.setBounds(120,40,150,30);
        panel.add(label4);
        }
        if(this.getIDitem()==Bandanna){
        image1=new ImageIcon(getClass().getResource("bandanna.jpg"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label3 = new JLabel("Harga Beli : " + this.getCostBuy());
        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label3.setBounds(120,20,150,30);
        panel.add(label3);
        label4 = new JLabel("Harga Jual : " + this.getCostSell());
        label4.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label4.setBounds(120,40,150,30);
        panel.add(label4);
        }
        if(this.getIDitem()==HeadBand){
        image1=new ImageIcon(getClass().getResource("headband.jpg"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label3 = new JLabel("Harga Beli : " + this.getCostBuy());
        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label3.setBounds(120,20,150,30);
        panel.add(label3);
        label4 = new JLabel("Harga Jual : " + this.getCostSell());
        label4.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label4.setBounds(120,40,150,30);
        panel.add(label4);
        }
        if(this.getIDitem()==PointedHat){
        image1=new ImageIcon(getClass().getResource("pointed.jpg"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label3 = new JLabel("Harga Beli : " + this.getCostBuy());
        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label3.setBounds(120,20,150,30);
        panel.add(label3);
        label4 = new JLabel("Harga Jual : " + this.getCostSell());
        label4.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label4.setBounds(120,40,150,30);
        panel.add(label4);
        }
        if(this.getIDitem()==WoodenShield){
        image1=new ImageIcon(getClass().getResource("wooden shield.jpg"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label3 = new JLabel("Harga Beli : " + this.getCostBuy());
        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label3.setBounds(120,20,150,30);
        panel.add(label3);
        label4 = new JLabel("Harga Jual : " + this.getCostSell());
        label4.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label4.setBounds(120,40,150,30);
        panel.add(label4);
        }
        if(this.getIDitem()==WoodenShoes){
        image1=new ImageIcon(getClass().getResource("wooden shoes.jpg"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label3 = new JLabel("Harga Beli : " + this.getCostBuy());
        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label3.setBounds(120,20,150,30);
        panel.add(label3);
        label4 = new JLabel("Harga Jual : " + this.getCostSell());
        label4.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label4.setBounds(120,40,150,30);
        panel.add(label4);
        }
        if(this.getIDitem()==Leggings){
        image1=new ImageIcon(getClass().getResource("leggings.jpg"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label3 = new JLabel("Harga Beli : " + this.getCostBuy());
        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label3.setBounds(120,20,150,30);
        panel.add(label3);
        label4 = new JLabel("Harga Jual : " + this.getCostSell());
        label4.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label4.setBounds(120,40,150,30);
        panel.add(label4);
        }
        if(this.getIDitem()==Gloves){
        image1=new ImageIcon(getClass().getResource("gloves.jpg"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label3 = new JLabel("Harga Beli : " + this.getCostBuy());
        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label3.setBounds(120,20,150,30);
        panel.add(label3);
        label4 = new JLabel("Harga Jual : " + this.getCostSell());
        label4.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label4.setBounds(120,40,150,30);
        panel.add(label4);
        }
        if(this.getIDitem()==Cape){
        image1=new ImageIcon(getClass().getResource("cape.jpg"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label3 = new JLabel("Harga Beli : " + this.getCostBuy());
        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label3.setBounds(120,20,150,30);
        panel.add(label3);
        label4 = new JLabel("Harga Jual : " + this.getCostSell());
        label4.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label4.setBounds(120,40,150,30);
        panel.add(label4);
        }
        if(this.getIDitem()==Circlet){
        image1=new ImageIcon(getClass().getResource("circlet.jpg"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label3 = new JLabel("Harga Beli : " + this.getCostBuy());
        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label3.setBounds(120,20,150,30);
        panel.add(label3);
        label4 = new JLabel("Harga Jual : " + this.getCostSell());
        label4.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label4.setBounds(120,40,150,30);
        panel.add(label4);
        }
        //Untuk Bibit
        if(this.getIDitem()==BiKentang){
        image1=new ImageIcon(getClass().getResource("bibit kentang.png"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label3 = new JLabel("Harga Beli : " + this.getCostBuy());
        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label3.setBounds(120,20,150,30);
        panel.add(label3);
        }
        if(this.getIDitem()==BiLobak){
        image1=new ImageIcon(getClass().getResource("bibit lobak.png"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label3 = new JLabel("Harga Beli : " + this.getCostBuy());
        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label3.setBounds(120,20,150,30);
        panel.add(label3);
        }
        if(this.getIDitem()==BiTimun){
        image1=new ImageIcon(getClass().getResource("bibit timun.png"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label3 = new JLabel("Harga Beli : " + this.getCostBuy());
        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label3.setBounds(120,20,150,30);
        panel.add(label3);
        }
        if(this.getIDitem()==BiKubis){
        image1=new ImageIcon(getClass().getResource("bibit kubis.png"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label3 = new JLabel("Harga Beli : " + this.getCostBuy());
        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label3.setBounds(120,20,150,30);
        panel.add(label3);
        }
        if(this.getIDitem()==BiNanas){
        image1=new ImageIcon(getClass().getResource("bibit nanas.png"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label3 = new JLabel("Harga Beli : " + this.getCostBuy());
        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label3.setBounds(120,20,150,30);
        panel.add(label3);
        }
        if(this.getIDitem()==BiTomat){
        image1=new ImageIcon(getClass().getResource("bibit tomat.png"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label3 = new JLabel("Harga Beli : " + this.getCostBuy());
        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label3.setBounds(120,20,150,30);
        panel.add(label3);
        }
        if(this.getIDitem()==BiBawang){
        image1=new ImageIcon(getClass().getResource("bibit bawang.png"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label3 = new JLabel("Harga Beli : " + this.getCostBuy());
        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label3.setBounds(120,20,150,30);
        panel.add(label3);
        }
        if(this.getIDitem()==BiWortel){
        image1=new ImageIcon(getClass().getResource("bibit wortel.png"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label3 = new JLabel("Harga Beli : " + this.getCostBuy());
        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label3.setBounds(120,20,150,30);
        panel.add(label3);
        }
        if(this.getIDitem()==BiTerong){
        image1=new ImageIcon(getClass().getResource("bibit terong.png"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label3 = new JLabel("Harga Beli : " + this.getCostBuy());
        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label3.setBounds(120,20,150,30);
        panel.add(label3);
        }
        if(this.getIDitem()==BiUbi){
        image1=new ImageIcon(getClass().getResource("bibit ubi.png"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label3 = new JLabel("Harga Beli : " + this.getCostBuy());
        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label3.setBounds(120,20,150,30);
        panel.add(label3);
        }
        if(this.getIDitem()==BiPaprika){
        image1=new ImageIcon(getClass().getResource("bibit paprika.png"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label3 = new JLabel("Harga Beli : " + this.getCostBuy());
        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label3.setBounds(120,20,150,30);
        panel.add(label3);
        }
        if(this.getIDitem()==BiStroberi){
        image1=new ImageIcon(getClass().getResource("bibit stroberi.png"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label3 = new JLabel("Harga Beli : " + this.getCostBuy());
        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label3.setBounds(120,20,150,30);
        panel.add(label3);
        }
        if(this.getIDitem()==BiLabu){
        image1=new ImageIcon(getClass().getResource("bibit labu.png"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label3 = new JLabel("Harga Beli : " + this.getCostBuy());
        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label3.setBounds(120,20,150,30);
        panel.add(label3);
        }
        //Untuk Buah
        if(this.getIDitem()==BuKentang){
        image1=new ImageIcon(getClass().getResource("buah kentang.png"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label4 = new JLabel("Harga Jual : " + this.getCostSell());
        label4.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label4.setBounds(120,40,150,30);
        panel.add(label4);
        }
        if(this.getIDitem()==BuLobak){
        image1=new ImageIcon(getClass().getResource("buah lobak.png"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label4 = new JLabel("Harga Jual : " + this.getCostSell());
        label4.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label4.setBounds(120,40,150,30);
        panel.add(label4);
        }
        if(this.getIDitem()==BuTimun){
        image1=new ImageIcon(getClass().getResource("buah timun.png"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label4 = new JLabel("Harga Jual : " + this.getCostSell());
        label4.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label4.setBounds(120,40,150,30);
        panel.add(label4);
        }
        if(this.getIDitem()==BuKubis){
        image1=new ImageIcon(getClass().getResource("buah kubis.png"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label4 = new JLabel("Harga Jual : " + this.getCostSell());
        label4.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label4.setBounds(120,40,150,30);
        panel.add(label4);
        }
        if(this.getIDitem()==BuNanas){
        image1=new ImageIcon(getClass().getResource("buah nanas.png"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label4 = new JLabel("Harga Jual : " + this.getCostSell());
        label4.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label4.setBounds(120,40,150,30);
        panel.add(label4);
        }
        if(this.getIDitem()==BuTomat){
        image1=new ImageIcon(getClass().getResource("buah tomat.png"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label4 = new JLabel("Harga Jual : " + this.getCostSell());
        label4.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label4.setBounds(120,40,150,30);
        panel.add(label4);
        }
        if(this.getIDitem()==BuBawang){
        image1=new ImageIcon(getClass().getResource("buah bawang.png"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label4 = new JLabel("Harga Jual : " + this.getCostSell());
        label4.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label4.setBounds(120,40,150,30);
        panel.add(label4);
        }
        if(this.getIDitem()==BuWortel){
        image1=new ImageIcon(getClass().getResource("buah wortel.png"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label4 = new JLabel("Harga Jual : " + this.getCostSell());
        label4.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label4.setBounds(120,40,150,30);
        panel.add(label4);
        }
        if(this.getIDitem()==BuTerong){
        image1=new ImageIcon(getClass().getResource("buah terong.png"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label4 = new JLabel("Harga Jual : " + this.getCostSell());
        label4.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label4.setBounds(120,40,150,30);
        panel.add(label4);
        }
        if(this.getIDitem()==BuUbi){
        image1=new ImageIcon(getClass().getResource("buah ubi.png"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label4 = new JLabel("Harga Jual : " + this.getCostSell());
        label4.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label4.setBounds(120,40,150,30);
        panel.add(label4);
        }
        if(this.getIDitem()==BuPaprika){
        image1=new ImageIcon(getClass().getResource("buah paprika.png"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label4 = new JLabel("Harga Jual : " + this.getCostSell());
        label4.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label4.setBounds(120,40,150,30);
        panel.add(label4);
        }
        if(this.getIDitem()==BuStroberi){
        image1=new ImageIcon(getClass().getResource("buah stroberi.png"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label4 = new JLabel("Harga Jual : " + this.getCostSell());
        label4.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label4.setBounds(120,40,150,30);
        panel.add(label4);
        }
        if(this.getIDitem()==BuLabu){
        image1=new ImageIcon(getClass().getResource("buah labu.png"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label4 = new JLabel("Harga Jual : " + this.getCostSell());
        label4.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label4.setBounds(120,40,150,30);
        panel.add(label4);
        }
        //Untuk WarItem
        if(this.getIDitem()==Water){
        image1=new ImageIcon(getClass().getResource("water.jpg"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label3 = new JLabel("Harga Beli : " + this.getCostBuy());
        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label3.setBounds(120,20,150,30);
        panel.add(label3);
        }
        if(this.getIDitem()==HolyWater){
        image1=new ImageIcon(getClass().getResource("holy water.jpg"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label3 = new JLabel("Harga Beli : " + this.getCostBuy());
        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label3.setBounds(120,20,150,30);
        panel.add(label3);
        }
        if(this.getIDitem()==Antitoxin){
        image1=new ImageIcon(getClass().getResource("antitoxin.jpg"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label3 = new JLabel("Harga Beli : " + this.getCostBuy());
        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label3.setBounds(120,20,150,30);
        panel.add(label3);
        }
        if(this.getIDitem()==EyeDrop){
        image1=new ImageIcon(getClass().getResource("eye drop.jpg"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label3 = new JLabel("Harga Beli : " + this.getCostBuy());
        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label3.setBounds(120,20,150,30);
        panel.add(label3);
        }
        if(this.getIDitem()==HotWater){
        image1=new ImageIcon(getClass().getResource("hot water.jpg"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label3 = new JLabel("Harga Beli : " + this.getCostBuy());
        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label3.setBounds(120,20,150,30);
        panel.add(label3);
        }


        label1 = new JLabel(image1);
        label1.setBounds(0,0,100,100);
        panel.add(label1);
        label2 = new JLabel("Nama : " + this.getName());
        label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label2.setBounds(120,0,150,30);
        panel.add(label2);
        
        return panel;
    }
}