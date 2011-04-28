/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plantmon.states;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import plantmon.entity.Time;
import plantmon.entity.movingObject.Player;
import plantmon.game.ImageEntity;

/**
 *
 * @author asus
 */
public class GameOver extends ParentState{
    Player player;
        int startx;
    int starty;
    JButton buttonNew;
    JButton buttonLoad;

    public GameOver(Object[] args){
        super();
        setLayout(null);
        ID = GAMEOVER;
        startx = 0;
        Game.instance().playMusic("005-Defeat01.mid");
        starty = 0;
        backbuffer = new BufferedImage(640,480, BufferedImage.TYPE_INT_ARGB);
        g2d = backbuffer.createGraphics();
        background = new ImageEntity(this);
        background.load("picture/Game Over.png");
        buttonNew = new JButton("New Game");
        buttonNew.setLayout(null);
        buttonNew.setBounds(245, 300, 150, 20);
        buttonNew.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Game.instance().StopMusic();
                    Time t = new Time();
                    Time.instance().load(t);
                    Game.instance().goTo(ParentState.HOME,new Object[0]);
                }
        });
        add(buttonNew);
        buttonLoad = new JButton("Load Game");
        buttonLoad.setLayout(null);
        buttonLoad.setBounds(245, 350, 150, 20);
        buttonLoad.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Game.instance().StopMusic();
                    Game.instance().load("tes.txt");
                }
        });
        add(buttonLoad);
    }
}
