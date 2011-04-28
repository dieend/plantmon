/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plantmon.states;

import java.awt.Graphics;
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
        background.load("picture/C1.png");
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
        
        if (i == 12) {
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
            background.load("picture/C1.png");
        } else if (i == 2) {
            background.load("picture/C2.png");
        } else if (i == 3) {
            background.load("picture/C3.png");
        } else if (i == 4) {
            background.load("picture/C4.png");
        } else if (i == 5) {
            background.load("picture/C5.png");
        } else if (i == 6) {
            background.load("picture/C6.png");
        } else if (i == 7) {
            background.load("picture/C7.png");
        } else if (i == 8) {
            background.load("picture/C8.png");
        } else if (i == 9) {
            background.load("picture/C9.png");
        } else if (i == 10) {
            background.load("picture/C10.png");
        } else if (i == 11) {
            background.load("picture/C11.png");
        } else if (i == 12) {
            Game.instance().goTo(ParentState.FRONTSTATE, null);
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
