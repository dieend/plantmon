package plantmon.entity.movingObject;

import java.awt.Graphics2D;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import plantmon.entity.unmoveable.Land;
import plantmon.game.GridMap;
import plantmon.states.Game;
import plantmon.system.Actionable;
import plantmon.system.Cancellable;
import plantmon.system.Jobable;
import plantmon.system.RunnableListener;
import plantmon.system.Selectable;
import plantmon.system.Utilities;

public class PulmosisLand extends PulmosisBattle implements Cancellable,
                                                    Selectable,Actionable {
    private int sumWater;
    private boolean watered;
    
    public PulmosisLand (GridMap map, JPanel panel, Graphics2D g2d, int type) {
        super(map,panel,g2d,type,false);
        if (type == PulmosisBattle.Kentang) {
            sumWater  = 0;
        }
    }

    public JPopupMenu getMenu(Selectable selected){
        if (selected instanceof Player){
            final Player player = (Player) selected;
            JPopupMenu menu = new JPopupMenu();
            JMenuItem item;
            item = new JMenuItem("talk");
            item.addActionListener(new Talk(selected));
            menu.add(item);
            if (type == PulmosisBattle.Kentang) {
                if (!watered) {
                    menu.add(item);
                    JMenuItem item4;
                    item4 = new JMenuItem("water");
                    item4.addActionListener(new Water(selected));
                    menu.add(item4);
                }
            }
            menu.pack();
            return menu;
        }
        return null;
    }

    class Water extends RunnableListener {
        public Water(Selectable selected){
            super(selected);
        }
        public void run() {
            Jobable player = (Jobable) selected;
            int gx = (int)PulmosisLand.this.position().IntX();
            int gy = (int)PulmosisLand.this.position().IntY();
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
                sumWater += 1;
                watered = true;
            }
        }
    }

    class Talk extends RunnableListener {
        public Talk(Selectable selected){
            super(selected);
        }
        public void run() {
            Jobable player = (Jobable) selected;
            int gx = (int)PulmosisLand.this.position().IntX();
            int gy = (int)PulmosisLand.this.position().IntY();
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
            }
        }
    }

    public boolean isFullWatered () {
        return (sumWater >= 10);
    }

    public void setWatered (boolean water) {
        this.watered = water;
    }

}
