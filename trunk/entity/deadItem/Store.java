package plantmon.entity.deadItem;

import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import plantmon.entity.Item;
import plantmon.entity.Unmoveable;
import plantmon.entity.item.ArmorItem;
import plantmon.entity.item.FarmItem;
import plantmon.entity.item.WarItem;
import plantmon.entity.movingObject.Player;
import plantmon.game.GridMap;
import plantmon.game.Point2D;
import plantmon.system.Actionable;
import plantmon.system.RunnableListener;
import plantmon.system.Selectable;

public class Store extends Unmoveable implements Actionable {
    ArrayList<Item> item = new ArrayList<Item>();
    ArrayList<Boolean> lock = new ArrayList<Boolean>();

    public Store (GridMap map, JPanel panel, Graphics2D g2d) {
        super(map,panel,g2d);
        init();
        item.add(0, new FarmItem(0,panel));
        item.add(1, new FarmItem(1,panel));
        item.add(2, new FarmItem(2,panel));
        item.add(3, new FarmItem(3,panel));
        item.add(4, new FarmItem(4,panel));
        item.add(5, new FarmItem(5,panel));
        item.add(6, new FarmItem(6,panel));
        item.add(7, new FarmItem(7,panel));
        item.add(8, new FarmItem(8,panel));
        item.add(9, new FarmItem(9,panel));
        item.add(10, new FarmItem(10,panel));
        item.add(11, new FarmItem(11,panel));
        item.add(12, new WarItem(50,panel));
        item.add(13, new WarItem(51,panel));
        item.add(14, new WarItem(52,panel));
        item.add(15, new WarItem(53,panel));
        item.add(16, new WarItem(54,panel));
        item.add(17, new ArmorItem(70,panel));
        item.add(18, new ArmorItem(71,panel));
        item.add(19, new ArmorItem(72,panel));
        item.add(20, new ArmorItem(73,panel));
        item.add(21, new ArmorItem(74,panel));
        item.add(22, new ArmorItem(75,panel));
        item.add(23, new ArmorItem(76,panel));
        item.add(24, new ArmorItem(77,panel));
        item.add(25, new ArmorItem(78,panel));
        item.add(26, new ArmorItem(79,panel));
        item.add(27, new ArmorItem(80,panel));
        item.add(28, new ArmorItem(81,panel));
        item.add(29, new ArmorItem(82,panel));
        for (int i = 0; i <= 29; i++) {
            lock.add(i,false);
        }

        lock.set(1,true);
        lock.set(2,true);
        lock.set(12,true);
        lock.set(14,true);
        lock.set(15,true);
        lock.set(16,true);
        lock.set(17,true);
        lock.set(20,true);
        lock.set(25,true);
    }

    public JPopupMenu getMenu(Selectable selected) {
        if (selected instanceof Player){
            final Player player = (Player) selected;
            JPopupMenu menu = new JPopupMenu();
            JMenu ite;
            ite = new JMenu("buy");
            
            JMenu subMenuFarm;
            subMenuFarm = new JMenu("Farm Item");

            JMenu subMenuWar;
            subMenuWar = new JMenu("War Item");

            JMenu subMenuArmor;
            subMenuArmor = new JMenu("Armor Item");

            for (int i = 0; i <=29; i++) {
                if (item.get(i) instanceof FarmItem) {
                   if (lock.get(i) == true) {
                    JMenuItem subSubMenu;
                    subSubMenu = new JMenuItem(item.get(i).getName());
                    subSubMenu.addActionListener(new Buy(selected,item.get(i)));
                    subMenuFarm.add(subSubMenu);
                    }
                } else if (item.get(i) instanceof WarItem) {
                   if (lock.get(i) == true) {
                    JMenuItem subSubMenu;
                    subSubMenu = new JMenuItem(item.get(i).getName());
                    subSubMenu.addActionListener(new Buy(selected,item.get(i)));
                    subMenuWar.add(subSubMenu);
                    }
                } else if (item.get(i) instanceof ArmorItem) {
                   if (lock.get(i) == true) {
                    JMenuItem subSubMenu;
                    subSubMenu = new JMenuItem(item.get(i).getName());
                    subSubMenu.addActionListener(new Buy(selected,item.get(i)));
                    subMenuArmor.add(subSubMenu);
                    }
                }
            }
            ite.add(subMenuFarm);
            ite.add(subMenuWar);
            ite.add(subMenuArmor);

            menu.add(ite);
            menu.pack();
            return menu;
        }

        
        return null;
    }

    class Buy extends RunnableListener {
        Item temp;
        public Buy(Selectable selected,Item it){
            super(selected);
            temp = it;
        }
        public void run() {
            Player player = (Player) selected;
            int gx = (int)Store.this.getPosition().X();
            int gy = (int)Store.this.getPosition().Y();
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
                player.setInventory(temp,1);
            }
        }
    }
    
    @Override
    public void init() {
        entity.load("picture/toko.png", 1, 1, 80, 80);
        entity.setFrameDelay(5);
        entity.setPosition(new Point2D(320,320));
        entity.setFinalPosition(320, 320);
    }

}
