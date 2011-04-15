/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plantmon.entity.deadItem;

import java.awt.Graphics2D;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import plantmon.entity.Unmoveable;
import plantmon.entity.movingObject.Player;
import plantmon.game.GridMap;
import plantmon.game.Point2D;
import plantmon.states.Game;
import plantmon.states.ParentState;
import plantmon.system.Actionable;
import plantmon.system.RunnableListener;
import plantmon.system.Selectable;
import plantmon.system.Utilities;

/**
 *
 * @author asus
 */
public class Portal extends Unmoveable implements Actionable{
    Selectable selected;
    public Portal(GridMap map, JPanel panel, Graphics2D g2d,int gridX,int gridY){
        super(map, panel, g2d);
        entity.setPosition(new Point2D(gridX*Utilities.GRIDSIZE, gridY*Utilities.GRIDSIZE));
        init();
    }
    public void init(){
        //entity.load("picture/land.png", 1, 1, Utilities.GRIDSIZE, Utilities.GRIDSIZE);
        //entity.setFrameDelay(5);
    }
    public JPopupMenu getMenu(Selectable selected){
        if (selected instanceof Player){
            final Player player = (Player) selected;
            JPopupMenu menu = new JPopupMenu();
            JMenuItem item;
            item = new JMenuItem("Teleport Home");
            item.addActionListener(new Teleport(selected));
            menu.add(item);           
            menu.pack();
            return menu;
        }
        return null;
    }
    @Override public void draw(){
        
    }

    class Teleport extends RunnableListener {
        public Teleport(Selectable selected){
            super(selected);
        }
        public void run() {
            Player player = (Player) selected;
            int gx = (int)Portal.this.getPosition().X();
            int gy = (int)Portal.this.getPosition().Y();
            Object lock = new Object();
            Boolean[] cancel = new Boolean[1];
            player.move(gx, gy, lock,cancel);
            synchronized(lock){
                try {
                    lock.wait();
                } catch (InterruptedException e){
                    return;
                }
            }
            if (!cancel[0]){
                map.pop(gx, gy);
                Game.instance().goTo(ParentState.HOME, new Object[0]);
            }
        }
    }
}
