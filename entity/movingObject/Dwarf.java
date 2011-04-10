package plantmon.entity.movingObject;

import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import plantmon.system.Actionable;
import plantmon.system.Selectable;
import plantmon.entity.*;
import plantmon.game.GridMap;
import plantmon.game.Point2D;

public class Dwarf extends MovingObject implements Actionable,
                                                    Selectable,
                                                    Runnable{

    private int type;
    private String name;
    //bisa di wake, di sleep
    public Dwarf(GridMap map, JPanel panel, Graphics2D g2d,int t){
        super(map,panel,g2d);
        type=t;
        if (type==1)
        {
            name="justice";
        }
        else if (type==2)
        {
            name="freedom";
        }
        else if (type==3)
        {
            name="destiny";
        }
        
    }
    @Override public void drawBounds() {
        creature.drawBounds(Color.GREEN);
    }
    @Override public String getInfo() {
        return name;
    }
    @Override protected void init() {
        //inisiasi semua variable disini.
        creature.load("picture/anim0.png", 4,1,32,32);
        creature.setPosition(new Point2D(80,80));
        creature.setVelocity(new Point2D(0,0));
        creature.setFrameDelay(3);
    }
    @Override public void run(){
        
    }
    @Override public JPopupMenu getMenu(Selectable selected){
        return null;
    }
}