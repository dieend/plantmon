package plantmon.entity.unmoveable;

import java.awt.Graphics2D;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import plantmon.entity.Inventory;
import plantmon.entity.Item;
import plantmon.entity.Unmoveable;
import plantmon.entity.movingObject.Player;
import plantmon.game.GridMap;
import plantmon.game.Point2D;
import plantmon.system.Actionable;
import plantmon.system.RunnableListener;
import plantmon.system.Selectable;

public class Land extends Unmoveable implements Actionable{
    // bisa disiram, dicangkul, diput?

    final public static int NORMAL = 0;
    final public static int PLOWED = 1;
    final public static int WATERED = 2;
    
    int status;
    @Override public void draw(){
        super.draw();
    }

    public Land(GridMap map, JPanel panel, Graphics2D g2d,int gridX,int gridY){
        super(map, panel, g2d);
        entity.setPosition(new Point2D(gridX*80, gridY*80));
        init();
    }
    public void init(){
        status = NORMAL;
        entity.load("picture/land.png", 1, 1, 80, 80);
        entity.setFrameDelay(5);
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
                Inventory inventory = player.getFarmItem();
                JMenuItem subItem;
                if (inventory.getSize()==0) {
                    subItem = new JMenuItem("no crop item");
                    subItem.setEnabled(false);
                    item3.add(subItem);
                }
                for (int i = 0; i < inventory.getSize(); i++) {
                    subItem = new JMenuItem(inventory.getItem(i).getName());
                    subItem.addActionListener(new Put(selected,inventory.getItem(i)));
                    item3.add(subItem);
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
            int gx = (int)Land.this.getPosition().X();
            int gy = (int)Land.this.getPosition().Y();
            Object lock = new Object();
            player.move(gx, gy, lock);
            synchronized(lock){
                try {
                    lock.wait();
                } catch (InterruptedException e){
                    return;
                }
            }
            player.getCreature().setFinalPosition(gx+10, gy+10);
        }
    }

    class Put extends RunnableListener {
        Item temp;
        public Put(Selectable selected,Item item){
            super(selected);
            temp = item;
        }
        public void run() {
            Player player = (Player) selected;
            int gx = (int)Land.this.getPosition().X();
            int gy = (int)Land.this.getPosition().Y();
            Object lock = new Object();
            player.move(gx, gy, lock);
            synchronized(lock){
                try {
                    lock.wait();
                } catch (InterruptedException e){
                    return;
                }
            }
            // buat plant baru berdasarkan item

            if (temp.getName() == "Lobak") {
                Lobak lobak = new Lobak(map, panel(),graphics(),gx,gy,status);
                map.push(gx, gy, lobak);
            } else if (temp.getName() == "Timun") {
                Timun timun = new Timun(map, panel(),graphics(),gx,gy,status);
                map.push(gx, gy, timun);
            }
            player.getInventory().delete(temp, 1);
        }
    }

    class Plow extends RunnableListener {
        public Plow(Selectable selected){
            super(selected);
        }
        public void run() {
            Player player = (Player) selected;
            int gx = (int)Land.this.getPosition().X();
            int gy = (int)Land.this.getPosition().Y();
            Object lock = new Object();
            player.move(gx, gy, lock);
            synchronized(lock){
                try {
                    lock.wait();
                } catch (InterruptedException e){
                    return;
                }
            }
            status = PLOWED;
            entity.load("picture/plow.png", 1, 1, 80, 80);
        }
    }

    class Water extends RunnableListener {
        public Water(Selectable selected){
            super(selected);
        }
        public void run() {
            Player player = (Player) selected;
            int gx = (int)Land.this.getPosition().X();
            int gy = (int)Land.this.getPosition().Y();
            Object lock = new Object();
            player.move(gx, gy, lock);
            synchronized(lock){
                try {
                    lock.wait();
                } catch (InterruptedException e){
                    return;
                }
            }
            status = WATERED;
            entity.load("picture/water.png", 1, 1, 80, 80);
        }
    }
}