package plantmon.entity.deadItem;

import java.util.ArrayList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import plantmon.entity.Item;
import plantmon.entity.item.ArmorItem;
import plantmon.entity.item.FarmItem;
import plantmon.entity.item.WarItem;
import plantmon.entity.movingObject.Player;
import plantmon.system.Actionable;
import plantmon.system.Selectable;

public class Store implements Actionable {
    ArrayList<Item> item = new ArrayList<Item>();
    ArrayList<Boolean> lock = new ArrayList<Boolean>();

    public Store (JPanel panel) {
        System.out.print("lalala");
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

        lock.add(1,true);
        lock.add(2,true);
        lock.add(12,true);
        lock.add(14,true);
        lock.add(15,true);
        lock.add(16,true);
        lock.add(17,true);
        lock.add(20,true);
        lock.add(25,true);
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
                    subMenuFarm.add(subSubMenu);
                    }
                } else if (item.get(i) instanceof WarItem) {
                   if (lock.get(i) == true) {
                    JMenuItem subSubMenu;
                    subSubMenu = new JMenuItem(item.get(i).getName());
                    subMenuWar.add(subSubMenu);
                    }
                } else if (item.get(i) instanceof ArmorItem) {
                   if (lock.get(i) == true) {
                    JMenuItem subSubMenu;
                    subSubMenu = new JMenuItem(item.get(i).getName());
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

}
