package plantmon.entity.unmoveable;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import plantmon.game.GridMap;

public class Kentang extends Plant{
    private ImageIcon image1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    public Kentang(GridMap map, JPanel panel, Graphics2D g2d,int gx, int gy,int status) {
        super(map,panel,g2d,gx,gy);
        setFase(status - 1);
        typeTanaman = 1;
        season = 0;
        happyMeter = 0;
        titikDewasa = 5;
        titikPanen = 8;
        umur = 10;
        panenBerulang = false;
    }
     @Override public JPanel get_Info(){
        JPanel panel;
        panel = new JPanel();
        panel.setLayout(null);
        image1=new ImageIcon(getClass().getResource("kentang.jpg"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label1 = new JLabel(image1);
        label1.setBounds(0,0,100,100);
        panel.add(label1);
        label2 = new JLabel("Nama : Kentang");
        label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label2.setBounds(100,0,150,30);
        label3 = new JLabel("Fase : " + this.getFase());
        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label3.setBounds(100,30,150,30);
        panel.add(label2);
        panel.add(label3);
        return panel;
    }
    public static void main(String[] str){
        JFrame frame=new JFrame();
        BufferedImage bf = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Kentang a = new Kentang(new GridMap(), new JPanel(), bf.createGraphics(), 0,0,0);
        frame.getContentPane().add(a.get_Info());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
}
