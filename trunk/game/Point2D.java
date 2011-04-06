package plantmon.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import plantmon.entity.movingObject.Player;
import plantmon.system.Actionable;
import plantmon.system.Selectable;

/*********************************************************
* Point2D Class
**********************************************************/
public class Point2D extends Object implements Actionable {
    private double x, y;
    //int constructor
    public Point2D(int x, int y) {
        setX(x);
        setY(y);
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
    public void setX(double x) { this.x = x; }
    public void setX(float x) { this.x = (double) x; }
    public void setX(int x) { this.x = (double) x; }
    //Y property
    public double Y() { return y; }
    public void setY(double y) { this.y = y; }
    public void setY(float y) { this.y = (double) y; }
    public void setY(int y) { this.y = (double) y; }
    @Override public JPopupMenu getMenu(Selectable selected) {
        if (selected instanceof Player){
            final Player player = (Player) selected;
            JPopupMenu menu = new JPopupMenu();
            JMenuItem item;
            item = new JMenuItem("move");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int gx = (int)Point2D.this.X();
                    int gy = (int)Point2D.this.Y();
                    player.getCreature().setVelocity(gx,gy);
                }
            });
            menu.add(item);
            menu.pack();
            return menu;
        }
        return null;
    }
}