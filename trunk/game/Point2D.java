package plantmon.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import plantmon.entity.movingObject.Player;
import plantmon.system.Actionable;
import plantmon.system.RunnableListener;
import plantmon.system.Selectable;

/*********************************************************
* Point2D Class
**********************************************************/
public class Point2D extends Object implements Actionable {
    private double x, y;
    Object lock = new Object();
    //int constructor

    
    public Point2D(int x, int y) {
        setX(x);
        setY(y);
    }

    public Point2D(Point2D p)
    {
        setX(p.IntX());
        setY(p.IntY());
    }
    
    //float constructor
    public Point2D(float x, float y) {
        setX(x);
        setY(y);
    }
    //double constructor
    public Point2D(double x, double y) {
        setX(x);
        setY(y);
    }
    //X property
    public double X() { return x; }
    public int IntX() {return (int)x;}
    public void setX(double x) { this.x = x; }
    public void setX(float x) { this.x = (double) x; }
    public void setX(int x) { this.x = (double) x; }
    public boolean isXZero(){ return this.x==0;}
    public boolean isXEqual(int x){ return this.x==x;}
    
    //Y property
    public double Y() { return y; }
    public int IntY() {return (int)y;}
    public void setY(double y) { this.y = y; }
    public void setY(float y) { this.y = (double) y; }
    public void setY(int y) { this.y = (double) y; }
    public boolean isYZero(){ return this.y==0;}
    public boolean isYEqual(int y){ return this.y==y;}

    public boolean  same(Point2D p)
    {
        return (x==p.IntX()) && (y==p.IntY());
    }
    public void setUp()
    {
        setX(x-1);
    }
    public void setRight()
    {
        setY(y+1);
    }
    public void setDown()
    {
        setX(x+1);
    }
    public void setLeft()
    {
        setY(y-1);
    }
    public Point2D getUp()
    {
        return new Point2D((int)x-1, (int)y);
    }
    public Point2D getRight()
    {
        return new Point2D((int)x, (int)y+1);
    }
    public Point2D getDown()
    {
        return new Point2D((int)x+1, (int)y);
    }
    public Point2D getLeft()
    {
        return new Point2D((int)x, (int)y-1);
    }
    
    @Override public JPopupMenu getMenu(Selectable selected) {
        if (selected instanceof Player){
            final Player player = (Player) selected;
            JPopupMenu menu = new JPopupMenu();
            JMenuItem item;
            item = new JMenuItem("move");
            item.addActionListener(new Move(selected));
            menu.add(item);
            menu.pack();
            return menu;
        }
        return null;
    }
    class Move extends RunnableListener {
        public Move(Selectable selected){
            super(selected);
        }
        public void run() {
            Player player = (Player) selected;
            int gx = (int)Point2D.this.X();
            int gy = (int)Point2D.this.Y();
            Object lock = new Object();
            player.move(gx, gy, lock);
            synchronized(lock){
                try {
                    lock.wait();
                } catch (InterruptedException e){}
            }
        }
    }

}