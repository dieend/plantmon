/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plantmon.entity;

import java.awt.Graphics2D;
import javax.swing.JPanel;
import plantmon.game.AnimatedSprite;
import plantmon.game.GridMap;
import plantmon.game.Point2D;
import plantmon.system.Drawable;

/**
 *
 * @author asus
 */
public abstract class Unmoveable implements Drawable{
    protected AnimatedSprite entity;
    protected GridMap map;
    protected Unmoveable(GridMap map, JPanel panel,Graphics2D g2d){
        entity = new AnimatedSprite(panel,g2d);
        this.map = map;
    }
    public void draw(){
        entity.draw();
    }
    public JPanel panel() {return entity.panel();}
    public Graphics2D graphics() {return entity.graphics();}
    public AnimatedSprite getEntity()   {return entity;}
    public Point2D getPosition() {return entity.position();}
    public abstract void init();
    public void update(){
        entity.updateAnimation();
    }
    public void reinit(GridMap map, JPanel panel, Graphics2D g2d){
        this.map = map;
        entity.setPanel(panel);
        entity.setGraphics(g2d);
    }
}
