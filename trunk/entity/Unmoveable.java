/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plantmon.entity;

import java.awt.Graphics2D;
import javax.swing.JPanel;
import plantmon.game.AnimatedSprite;
import plantmon.game.GridMap;
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
    public AnimatedSprite getEntity()   {return entity;}
    public abstract void init();
    public void update(){
        entity.updateAnimation();
    }
}
