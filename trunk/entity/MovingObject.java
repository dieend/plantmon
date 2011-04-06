package plantmon.entity;

import java.awt.Graphics2D;
import plantmon.game.Point2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import plantmon.game.AnimatedSprite;
import plantmon.game.GridMap;
import plantmon.system.Drawable;

public abstract class MovingObject implements Drawable{
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
    public void update(){
        creature.updateAnimation();
        creature.updatePosition();
        creature.transform();
    }
    protected abstract void init();
}