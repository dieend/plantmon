package plantmon.entity;

import java.awt.Graphics2D;
import plantmon.game.Point2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import plantmon.game.AnimatedSprite;
import plantmon.game.GridMap;
import plantmon.system.Drawable;

public abstract class MovingObject implements Drawable{
    protected ArrayList<Object> lock = new ArrayList<Object>();
    protected ArrayList<Point2D> destination = new ArrayList<Point2D>();
    protected GridMap map;
    protected AnimatedSprite creature;
    protected ArrayList<Point2D> route = new ArrayList<Point2D>();
    public MovingObject(GridMap map, JPanel panel, Graphics2D g2d){
        this.map = map;
        creature = new AnimatedSprite(panel, g2d);
        init();
    }
    public ArrayList<Point2D> getRoute(Point2D destination){
        //TODO
        return null;
    }
    public Point2D getNextDest(){
        //TODO
        return null;
    }
    public AnimatedSprite getCreature()     {return creature;}
    public void draw()                      {creature.draw();}
    public Point2D position()               { return creature.position(); }
    public synchronized void update(){
        updateAction();
        int gx = (int) creature.position().X();
        gx/=80;
        int gy = (int) creature.position().Y();
        gy/=80;
        creature.updateAnimation();
        creature.updatePosition();
        creature.transform();
        int fx = (int) creature.position().X();
        fx/=80;
        int fy = (int) creature.position().Y();
        fy/=80;
        if (!(fx==gx && fy==gy)){
            map.gpop(gx, gy);
            map.gpush(fx,fy,this);
        }
    }
    protected synchronized void addAction(Object lock,Point2D dest){
        this.lock.add(lock);
        int x = (int)dest.X()/80 * 80 + 10;
        int y = (int)dest.Y()/80 * 80 + 10;
        this.destination.add(new Point2D(x, y));

    }
    protected abstract void init();
    public void updateAction(){
        if (lock.size()>0){
            synchronized (lock.get(0)) {
                Point2D dest = destination.get(0);
                if (Math.abs(creature.position().X()-dest.X())<10 &&
                    Math.abs(creature.position().Y()-dest.Y())<10) {

                    lock.get(0).notify();
                    lock.remove(0);
                    destination.remove(0);
                }
            }
        }
        if (destination.size()>0){
            creature.setVelocity((int)destination.get(0).X()
                    , (int)destination.get(0).Y());
        }
    }
}