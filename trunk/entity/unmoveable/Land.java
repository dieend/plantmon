package plantmon.entity.unmoveable;

import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import plantmon.entity.Inventory;
import plantmon.entity.Unmoveable;
import plantmon.entity.movingObject.Player;
import plantmon.game.GridMap;
import plantmon.system.Actionable;
import plantmon.system.RunnableListener;
import plantmon.system.Selectable;

public class Land extends Unmoveable implements Actionable{
    // bisa disiram, dicangkul, diput?

    final public static int NORMAL = 0;
    final public static int PLOWED = 1;
    final public static int WATERED = 2;
    
    int status;
    public Land(GridMap map, JPanel panel, Graphics2D g2d){
        super(map, panel, g2d);
        init();
    }
    public void init(){
        status = Land.NORMAL;
    }
    public JPopupMenu getMenu(Selectable selected){
        if (selected instanceof Player){
            final Player player = (Player) selected;
            JPopupMenu menu = new JPopupMenu();
            JMenuItem item;
            item = new JMenuItem("move");
            item.addActionListener(new Move(selected));
            menu.add(item);
            if (status == NORMAL) {
                JMenuItem item2;
                item2 = new JMenuItem("plow");
                item2.addActionListener(new Plow(selected));
                menu.add(item2);
            } else {
                JMenu item3;
                item3 = new JMenu("put");
                item3.addActionListener(new Put(selected));
                Inventory inventory = player.getFarmItem();
                JMenuItem subItem;
                for (int i = 0; i < inventory.getSize(); i++) {
                    subItem = new JMenuItem(inventory.getItem(i).getName());
                    subItem.addActionListener(new Put(selected,inventory.getItem(i)));
                    menu.add(subItem);
                }
                menu.add(item3);
                if (status == PLOWED) {
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
    
    class Move extends RunnableListener {
        public Move(Selectable selected){
            super(selected);
        }
        public void run() {
            Player player = (Player) selected;
            int gx = (int)Point2D.this.X();
            int gy = (int)Point2D.this.Y();
            Object lock = new Object();
            player.move(gx, gy, lock);
            synchronized(lock){
                try {
                    lock.wait();
                } catch (InterruptedException e){}
            }
        }
    }

    class Put extends RunnableListener {
        public Put(Selectable selected,Item item){
            super(selected);
        }
        public void run() {
            Player player = (Player) selected;
            int gx = (int)Point2D.this.X();
            int gy = (int)Point2D.this.Y();
            Object lock = new Object();
            player.move(gx, gy, lock);
            synchronized(lock){
                try {
                    lock.wait();
                } catch (InterruptedException e){}
            }
            player.put(item);
        }
    }

    class Plow extends RunnableListener {
        public Plow(Selectable selected){
            super(selected);
        }
        public void run() {
            Player player = (Player) selected;
            int gx = (int)Point2D.this.X();
            int gy = (int)Point2D.this.Y();
            Object lock = new Object();
            player.move(gx, gy, lock);
            synchronized(lock){
                try {
                    lock.wait();
                } catch (InterruptedException e){}
            }
            player.plow();
        }
    }

    class Water extends RunnableListener {
        public Water(Selectable selected){
            super(selected);
        }
        public void run() {
            Player player = (Player) selected;
            int gx = (int)Point2D.this.X();
            int gy = (int)Point2D.this.Y();
            Object lock = new Object();
            player.move(gx, gy, lock);
            synchronized(lock){
                try {
                    lock.wait();
                } catch (InterruptedException e){}
            }
            player.water();
        }
    }
}