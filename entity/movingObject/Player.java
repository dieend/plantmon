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

public class Player extends MovingObject implements Actionable, 
                                                    Selectable{
    //bisa ngapain aja?
    public Player(GridMap map, JPanel panel, Graphics2D g2d){
        super(map,panel,g2d);
    }
    @Override public void drawBounds() {
        creature.drawBounds(Color.GREEN);
    }
    @Override public String getInfo() {
        return "player";
    }
    @Override public JPopupMenu getMenu(Selectable selected){
        return null;
    }
    @Override protected void init() {
        //inisiasi semua variable disini.
        creature.load("picture/anim0.png", 4,1,32,32);
        creature.setPosition(new Point2D(80,80));
        creature.setVelocity(new Point2D(0,0));
        creature.setFrameDelay(3);
    }
    public void move(int gx,int gy,Object lock){
        addAction(lock,new Point2D(gx,gy));
    }
    
}