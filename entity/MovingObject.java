package plantmon.entity;

import java.awt.Graphics2D;
import java.io.IOException;
import plantmon.game.Point2D;
import java.util.ArrayList;
import javax.imageio.ImageIO;
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
    protected boolean inAction;
    protected String imageName;
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
    public void load(String filename, int columns, int rows,int width, int height){
        imageName = filename;
        filename = filename+"0.png";
        creature.load(filename, 4,1,32,32);
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
                    inAction = false;
                    lock.get(0).notify();
                    lock.remove(0);
                    destination.remove(0);

                }
            }
        }
        if (!inAction){
            if (destination.size()>0){
                int gx =(int)destination.get(0).X();
                int gy = (int)destination.get(0).Y();
                gx = (gx/80)*80+10;
                gy = (gy/80)*80+10;
                double vx = 1.5* Math.sin(Math.atan2(gx-this.position().X(),
                            gy-this.position().Y()));
                double vy = 1.5* Math.cos(Math.atan2(gx-this.position().X(),
                            gy-this.position().Y()));
                creature.setVelocity(new Point2D(vx, vy));
                
                    if ((Math.abs(vy/vx)>1.0) && vy>0) {
                        System.out.println("Hadap bawah");
        //                setFaceAngle(0);
                        creature.load(imageName+"0.png",4,1,32,32);
                    } else if ((Math.abs(vy/vx) < 1.0) && vx>0){
                        System.out.println("Hadap kanan");
        //                setFaceAngle(90);
                        creature.load(imageName+"3.png",4,1,32,32);
                    } else if ((Math.abs(vy/vx) > 1.0) && vy<0){
        //                setFaceAngle(180);
                        System.out.println("Hadap atas");
                        creature.load(imageName+"2.png",4,1,32,32);
                    } else if ((Math.abs(vy/vx) < 1.0) && vx<0){
        //                setFaceAngle(270);
                        System.out.println("Hadap ke kiri");
                        creature.load(imageName+"1.png",4,1,32,32);
                    }
                creature.setFinalPosition(gx, gy);
                inAction = true;
            }
        }

    }
}