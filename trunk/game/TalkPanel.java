/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plantmon.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author asus
 */
public class TalkPanel extends JPanel{
    transient Image bg;
    public TalkPanel() {
        super(null);
        try{
            bg = ImageIO.read(this.getClass().getResource("picture/Percakapan.png"));
        } catch(IOException e){}
        JButton close = new JButton("x");
        close.setFont(new Font("Calibri", Font.PLAIN, 12));
        close.setMargin(new Insets(0, 0, 0, 0));
        close.setBounds(610, 25, 20, 20);
        close.setBackground(Color.ORANGE);
        close.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                TalkPanel.this.setVisible(false);
            }
        });
        add(close);
    }

    @Override
    public void paintComponent(Graphics g){

//       double scaleX = this.getWidth() / (double) bg.getWidth(null);
//       double scaleY = this.getHeight() / (double) bg.getHeight(null);
//       AffineTransform xform = AffineTransform.getScaleInstance(scaleX,scaleY);
//       ((Graphics2D) g).drawImage(bg, xform, this);
       g.drawImage(bg, 0, 0, this);
    }

    public static void main(String[] args){
        TalkPanel p = new TalkPanel();
        JFrame f = new JFrame();
        JButton b = new JButton("fail");
        b.setBounds(0,0, 100, 50);
        p.add(b);
        p.updateUI();
        f.add(p);
        f.setSize(new Dimension(640, 200));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
