/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plantmon.states;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JFrame;
import plantmon.entity.movingObject.Player;
import plantmon.game.ImageEntity;

/**
 *
 * @author asus
 */
public class GameFinish extends ParentState{
    Player player;
        int startx;
    int starty;
    int play;
    int i;
    JButton buttonNew;
    JButton buttonLoad;

    public GameFinish(Object[] args){
        super();
        setLayout(null);
        ID = GAMEOVER;
        startx = 0;
        starty = 0;
        backbuffer = new BufferedImage(640,480, BufferedImage.TYPE_INT_ARGB);
        g2d = backbuffer.createGraphics();
        background = new ImageEntity(this);
        background.load("picture/Rumah.png");
        play = 20;
        i = 1;
    }

    @Override
    public void run(){
        active = true;
        while (active) {
//            System.out.format("There are currenty %d Thread running\n",Thread.activeCount());
            try {
                Thread.sleep(20);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            repaint();
            gameUpdate();
//            System.out.print(""+player.position().IntX()/Utilities.GRIDSIZE+""+player.position().IntY()/Utilities.GRIDSIZE+"\n");
        }

    }
    public void gameUpdate() {
        if (play > 0) {
            play--;
            slideShow(i);
        } else {
            play = 20;
            i++;
        }
        
        if (i == 3) {
            play = 10000000;
        }
    }
    
    public void updated(){
        g2d.drawImage(background.getImage(), 0, 0, this);
    }
    
    @Override public void paintComponent(Graphics g) {
//        System.out.println("paintComponent - CobaOpeh");
        updated();
        g.drawImage(backbuffer,0, 0, this);
    }

    public void slideShow(int i) {
        if (i == 1) {
            background.load("picture/Rumah.png");
        } else if (i == 2) {
            background.load("picture/Lahan.png");
        } else if (i == 3) {
            background.load("picture/LahanDark.png");
            buttonNew = new JButton("New Game");
            buttonNew.setLayout(null);
            buttonNew.setBounds(245, 300, 150, 20);
            buttonNew.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    Game.instance().goTo(ParentState.HOME,new Object[0]);
                    }
            });
            add(buttonNew);
        }
    }

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Plantmon");
        mainFrame.setSize(640, 480);
//        mainFrame.setResizable(false);
        //mainFrame.add(p, BorderLayout.CENTER);
//        JPanel panel = new JPanel();
//        mainFrame.add(panel);
        //mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        Game.instance().setFrame(mainFrame);
        Game.instance().goTo(ParentState.GAMEFINISH,null);
    }
}
