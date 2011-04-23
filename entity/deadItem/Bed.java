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
import plantmon.system.Actionable;
import plantmon.system.RunnableListener;
import plantmon.system.Selectable;
import plantmon.system.Utilities;

public class Bed extends Unmoveable implements Actionable{
    public Bed(GridMap map, JPanel panel, Graphics2D g2d,int gridX, int gridY) {
        super(map, panel, g2d);
        entity.setPosition(new Point2D(gridX*Utilities.GRIDSIZE, gridY*Utilities.GRIDSIZE));
        init();
    }
    public JPopupMenu getMenu(Selectable selected) {
        JPopupMenu popmenu = new JPopupMenu();
        JMenuItem menu = new JMenuItem("Sleep");
        menu.addActionListener(new Sleep(selected));
        popmenu.add(menu);
        return popmenu;
    }
    
    @Override public void draw(){
     //   super.draw();
    }

    @Override
    public void init() {
    }
    public class Sleep extends RunnableListener {
        public Sleep(Selectable selected) {
            super(selected);
        }
        public void run() {
            Player player = (Player) selected;
            int gx = (int)Bed.this.getPosition().X();
            int gy = (int)Bed.this.getPosition().Y();
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
                map.popCancel(gx, gy);
                Game.instance().changeDay();
            }
        }

    }
}
