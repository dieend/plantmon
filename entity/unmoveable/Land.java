package plantmon.entity.unmoveable;

import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import plantmon.states.Game;
import plantmon.system.Actionable;
import plantmon.system.Jobable;
import plantmon.system.RunnableListener;
import plantmon.system.Selectable;
import plantmon.system.Utilities;

public class Land extends Unmoveable implements Actionable{

    final public static int NORMAL = 0;
    final public static int PLOWED = 1;
    final public static int WATERED = 2;
    
    int status;
    @Override public void draw(){
        if (status != NORMAL)
            super.draw();
    }

    public Land(GridMap map, JPanel panel, Graphics2D g2d,int gridX,int gridY){
        super(map, panel, g2d);
        entity.setPosition(new Point2D(gridX*Utilities.GRIDSIZE, gridY*Utilities.GRIDSIZE));
        init();
    }
    public void init(){
        status = NORMAL;
        //entity.load("picture/land.png", 1, 1, Utilities.GRIDSIZE, Utilities.GRIDSIZE);
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
                Inventory inventory = Game.instance().getInventory().getFarmItem();
                JMenuItem subItem;
                if (inventory.isEmpty()) {
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

    public void setStatus(int status){
        this.status = status;
        int gx = (int)this.getPosition().X();
        int gy = (int)this.getPosition().Y();
        Game.instance().farmstatus()[gx/Utilities.GRIDSIZE][gy/Utilities.GRIDSIZE] = status;
        if (status == WATERED){
            entity.load("picture/water.png");//, 1, 1, Utilities.GRIDSIZE, Utilities.GRIDSIZE);
        } else if (status == PLOWED){
            entity.load("picture/plow.png");//, 1, 1, Utilities.GRIDSIZE, Utilities.GRIDSIZE);
        } else if (status == NORMAL){
            entity.load("picture/land.png");//, 1, 1, Utilities.GRIDSIZE, Utilities.GRIDSIZE);
        }
    }
    
    class Move extends RunnableListener {
        public Move(Selectable selected){
            super(selected);
        }
        public void run() {
            Player player = (Player) selected;
            int gx = (int)Land.this.getPosition().X();
            int gy = (int)Land.this.getPosition().Y();
            Object lock = new String("exact");
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
            }
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
            Boolean[] cancel = new Boolean[1];
            player.move(gx, gy, lock,cancel);
            synchronized(lock){
                try {
                    lock.wait();
                } catch (InterruptedException e){
                    return;
                }
            }
            // buat plant baru berdasarkan item
            if ((cancel==null) || (!cancel[0])){
                map.pop(gx, gy);
                if (temp.getName().equals("Lobak")) {
                    Lobak lobak = new Lobak(map, panel(),graphics(),gx,gy,status);
                    map.push(gx, gy, lobak);
                    Game.instance().addPlant(lobak);
                } else if (temp.getName().equals("Timun")) {
                    Timun timun = new Timun(map, panel(),graphics(),gx,gy,status);
                    map.push(gx, gy, timun);
                    Game.instance().addPlant(timun);
                }
                Game.instance().getInventory().delete(temp, 1);
                Game.instance().log().append("put "+temp.getName()+" at ("+(gx/Utilities.GRIDSIZE)+","+(gy/Utilities.GRIDSIZE)+")\n");
            }
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
            //Object lock = new String("plow");
            Object lock = new Object();
            System.out.println(lock.hashCode());

            Boolean[] cancel = new Boolean[1];
            player.move(gx, gy, lock,cancel);
            synchronized(lock){
                try {
                    lock.wait();
//                    Thread.sleep(1000);
                    //lock = "plow";
//                    lock.notify();
                } catch (InterruptedException e){
                    return;
                }
            }
            
            if (!cancel[0]){
                map.popCancel(gx, gy);
                if (Game.instance().getWeather() == Game.RAINY){
                    setStatus(WATERED);
                } else {
                    setStatus(PLOWED);
                }
                player.setWork(0);
            }
            
        }
    }

    class Water extends RunnableListener {
        public Water(Selectable selected){
            super(selected);
        }
        public void run() {
            Player player1 = (Player) selected;
            Jobable player = (Jobable) selected;
            int gx = (int)Land.this.getPosition().X();
            int gy = (int)Land.this.getPosition().Y();
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
                setStatus(WATERED);
                player1.setWork(1);
            }
        }
    }
}
