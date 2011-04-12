package plantmon.entity.movingObject;

import java.awt.Color;
import java.awt.Graphics2D;
//import javax.swing.FarmState;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import plantmon.system.Actionable;
import plantmon.system.Selectable;
import plantmon.entity.*;
import plantmon.game.GridMap;
import plantmon.game.Point2D;
import plantmon.states.FarmState;
import plantmon.states.ParentState;
import plantmon.states.StateManager;
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
            JMenuItem ite;
            ite = new JMenuItem("inventory");
            ite.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Object[] args = new Object[1];
                    args[0] = Player.this;
                    StateManager.instance().goTo(ParentState.INVENTORY,args);
                }
            });

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

    public void setMoney(int uang) {
        money = uang;
    }

    public int getMoney() {
        return money;
    }
}