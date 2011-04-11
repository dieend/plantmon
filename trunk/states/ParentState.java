package plantmon.states;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import plantmon.game.ImageEntity;

/**
 *
 * @author asus
 */
public class ParentState extends JPanel{
    Graphics2D g2d;
    AffineTransform at;
    BufferedImage backbuffer;
    ImageEntity background;
    int ID;
    public static final int FARMSTATE = 0;
    public static final int FRONTSTATE = 1;
    public ParentState(){
    }

    public ParentState(int gridRow, int gridColumn){
        background = new ImageEntity(this);
        backbuffer = new BufferedImage(gridRow*80, gridColumn*80, BufferedImage.TYPE_INT_ARGB);
        g2d = backbuffer.createGraphics();
    }
    public int getID(){
        return ID;
    }
    protected void setname(String name){
        this.setName(name);
    }

}
