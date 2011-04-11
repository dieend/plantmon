package plantmon.entity.movingObject;

import java.awt.Color;
import java.awt.Graphics2D;
//import javax.swing.FarmState;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import plantmon.system.Actionable;
import plantmon.system.Selectable;
import plantmon.entity.*;
import plantmon.game.GridMap;
import plantmon.game.Point2D;
import plantmon.states.FarmState;
import plantmon.system.Cancellable;

public class Player extends MovingObject implements Actionable, Cancellable,
                                                    Selectable{

    
    Inventory inventory;
    Integer money;
    //bisa ngapain aja
    public Player(GridMap map, FarmState panel, Graphics2D g2d, int maxItemSlot,Integer money){
        super(map,panel,g2d);
        inventory = new Inventory();
        this.money = money;
        init();
    }
    @Override public void drawBounds() {
        creature.drawBounds(Color.GREEN);
    }
    @Override public String getInfo() {
        return "player";
    }
    @Override public JPopupMenu getMenu(Selectable selected){
        if (selected instanceof Player){
            final Player player = (Player) selected;
            JPopupMenu menu = new JPopupMenu();
            JMenu ite;
            ite = new JMenu("inventory");

            JMenu subMenuFarm;
            subMenuFarm = new JMenu("Farm Item");

            JMenu subMenuFood;
            subMenuFood = new JMenu("Food Item");

            JMenu subMenuWar;
            subMenuWar = new JMenu("War Item");

            JMenu subMenuArmor;
            subMenuArmor = new JMenu("Armor Item");

            if (!inventory.getFarmItem().isEmpty()) {
                for (int i = 0; i <inventory.getFarmItem().getSize(); i++) {
                    JMenuItem subSubFarm = new JMenuItem(inventory.getFarmItem().getSlot(i).getName());
                    subMenuFarm.add(subSubFarm);
                }
            } else {
                JMenuItem subSubFarm2 = new JMenuItem("no Farm Item");
                subSubFarm2.setEnabled(false);
                subMenuFarm.add(subSubFarm2);
            }
            
            if (!inventory.getFoodItem().isEmpty()) {
                for (int i = 0; i <inventory.getFoodItem().getSize(); i++) {
                    JMenuItem subSubFood = new JMenuItem(inventory.getFoodItem().getSlot(i).getName());
                    subMenuFood.add(subSubFood);
                }
            } else {
                JMenuItem subSubFood2 = new JMenuItem("no Food Item");
                subSubFood2.setEnabled(false);
                subMenuFood.add(subSubFood2);
            }

            if (!inventory.getWarItem().isEmpty()) {
                for (int i = 0; i <inventory.getWarItem().getSize(); i++) {
                    JMenuItem subSubWar = new JMenuItem(inventory.getWarItem().getSlot(i).getName());
                    subMenuWar.add(subSubWar);
                }
            } else {
                JMenuItem subSubWar2 = new JMenuItem("no War Item");
                subSubWar2.setEnabled(false);
                subMenuWar.add(subSubWar2);
            }
            
            if (!inventory.getArmorItem().isEmpty()) {
                for (int i = 0; i <inventory.getArmorItem().getSize(); i++) {
                    JMenuItem subSubArmor = new JMenuItem(inventory.getArmorItem().getSlot(i).getName());
                    subMenuArmor.add(subSubArmor);
                }
            } else {
                JMenuItem subSubArmor2 = new JMenuItem("no Armor Item");
                subSubArmor2.setEnabled(false);
                subMenuArmor.add(subSubArmor2);
            }

            ite.add(subMenuFarm);
            ite.add(subMenuFood);
            ite.add(subMenuWar);
            ite.add(subMenuArmor);

            menu.add(ite);

            menu.pack();
            return menu;
        }
        return null;
    }
    @Override protected void init() {
        //inisiasi semua variable disini.
        
        load("picture/anim", 4,1,32,32);
        creature.setImageName("picture/anim");
        creature.setPosition(new Point2D(80,80));
        creature.setFinalPosition(80, 80);
        creature.setVelocity(new Point2D(0,0));
        creature.setFrameDelay(3);
    }
    public void move(int gx,int gy,Object lock,Boolean[] cancel){
        addAction(lock,new Point2D(gx,gy));
        Canceller ca = new Canceller(creature.panel(),creature.graphics(),
                                    gx, gy, cancel,lock,(Cancellable)this,numAction-1);
        map.push(gx, gy, ca);
    }
    public Inventory getFarmItem(){
        return inventory.getFarmItem();
    }
    public Inventory getInventory() {
        return inventory;
    }
    public void setInventory (Item i,int Jumlah) {
        inventory.add(i, Jumlah);
    }
    @Override
    public void cancel(Object lock){
        map.pop(destination.get(lock).X(), destination.get(lock).Y());
        System.out.println(destination.size());
        destination.remove(lock);
        this.lock.remove(lock);
        this.numAction--;
        creature.setFinalPosition(this.position().IntX(),this.position().IntY());
        inAction = false;
    }
}