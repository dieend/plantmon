/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plantmon.states;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import plantmon.entity.Canceller;
import plantmon.entity.Time;
import plantmon.entity.movingObject.Player;
import plantmon.game.ImageEntity;
import plantmon.system.Actionable;
import plantmon.system.Selectable;
import plantmon.system.Utilities;

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
        starty = 0;
        backbuffer = new BufferedImage(640,480, BufferedImage.TYPE_INT_ARGB);
        g2d = backbuffer.createGraphics();
        background = new ImageEntity(this);
        background.load("picture/Rumah.png");
        buttonNew = new JButton("New Game");
        buttonNew.setLayout(null);
        buttonNew.setBounds(100, 245, 150, 20);
        buttonNew.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                Game.instance().goTo(ParentState.HOME,new Object[0]);
                }
        });
        add(buttonNew);
        buttonLoad = new JButton("New Game");
        buttonLoad.setLayout(null);
        buttonLoad.setBounds(200, 245, 150, 20);
        buttonLoad.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                Game.instance().load("tes.txt");
                }
        });
        add(buttonLoad);

    }
}
