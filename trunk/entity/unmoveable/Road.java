/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plantmon.entity.unmoveable;

import java.awt.Graphics2D;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import plantmon.entity.Unmoveable;
import plantmon.entity.movingObject.Player;
import plantmon.game.GridMap;
import plantmon.game.Point2D;
import plantmon.system.Actionable;
import plantmon.system.RunnableListener;
import plantmon.system.Selectable;
import plantmon.system.Utilities;


public class Road extends Unmoveable implements Actionable{
    
    public Road(GridMap map, JPanel panel, Graphics2D g2d,int i, int j){
        super(map, panel, g2d);
        entity.setPosition(new Point2D(i*Utilities.GRIDSIZE, j*Utilities.GRIDSIZE));
        
    }
    @Override public void draw(){
        
    }
    @Override
    public void init() {
    }

    public JPopupMenu getMenu(Selectable selected) {
        if (selected instanceof Player){
            final Player player = (Player) selected;
            JPopupMenu menu = new JPopupMenu();
            JMenuItem item;
            item = new JMenuItem("move");
            item.addActionListener(new Move(selected));
            menu.add(item);
            menu.pack();
            return menu;
        }
        return null;
    }
    class Move extends RunnableListener {
        public Move(Selectable selected){
            super(selected);
        }
        public void run() {
            Player player = (Player) selected;
            int gx = (int)Road.this.getPosition().X();
            int gy = (int)Road.this.getPosition().Y();
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
                player.getCreature().setFinalPosition(gx+10, gy+10);
            }
        }
    }
}
