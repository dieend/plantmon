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
import plantmon.states.FarmState;
import plantmon.states.Game;
import plantmon.states.HomeState;
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
    JPanel panel;
    public Portal(GridMap map, JPanel panel, Graphics2D g2d,int gridX,int gridY){
        super(map, panel, g2d);
        this.panel = panel;
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
            if (panel instanceof FarmState){
                item = new JMenuItem("Teleport Home");
                item.addActionListener(new Teleport(selected,ParentState.HOME));
                menu.add(item);
                item = new JMenuItem("Teleport Store");
                item.setEnabled(false);
                menu.add(item);
            }
            if (panel instanceof HomeState){
                item = new JMenuItem("Teleport Farm");
                item.addActionListener(new Teleport(selected,ParentState.FARMSTATE));
                menu.add(item);
            }
            menu.pack();
            return menu;
        }
        return null;
    }
    @Override public void draw(){
        
    }

    class Teleport extends RunnableListener {
        int where;
        public Teleport(Selectable selected,int where){
            super(selected);
            this.where = where;
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
                Game.instance().goTo(where, new Object[0]);
            }
        }
    }
}
