package plantmon.entity.unmoveable;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import plantmon.game.GridMap;

public class Terong extends Plant{
    private ImageIcon image1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    public Terong(GridMap map, JPanel panel, Graphics2D g2d,int gx, int gy,int status) {
        super(map,panel,g2d,gx,gy);
        setFase(status - 1);
        typeTanaman = 10;
        season = 2;
        happyMeter = 0;
        titikDewasa = 7;
        titikPanen = 10;
        umur = 20;
        panenBerulang = true;
    }
     public JPanel get_Info(){
        JPanel panel;
        panel = new JPanel();
        panel.setLayout(null);
        image1=new ImageIcon(getClass().getResource("terong.jpg"));
        label1 = new JLabel(image1);
        label1.setBounds(40,10,300,300);
        panel.add(label1);
        label2 = new JLabel("Nama : Terong");
        label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label2.setBounds(5,210,200,200);
        label3 = new JLabel("Fase : " + this.getFase());
        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
//        label4 = new JLabel("Info : " + this.getInfo());
//        label4.setFont(new Font("Times New Roman", Font.BOLD, 16));
//        label4.setBounds(5,300,100,100);
        panel.add(label2);
        panel.add(label3);
//        panel.add(label4);
        return panel;
    }
    public static void main(String[] str){
        JFrame frame=new JFrame();
        BufferedImage bf = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Terong a = new Terong(new GridMap(), new JPanel(), bf.createGraphics(), 0,0,0);
        frame.getContentPane().add(a.get_Info());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
}

