package plantmon.states;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import plantmon.game.ImageEntity;
import plantmon.system.Utilities;

/**
 *
 * @author asus
 */
public class ParentState extends JPanel implements Runnable{
    public static int SCREENHEIGHT = 480;
    public static int SCREENWIDTH = 640;
    Graphics2D g2d;
    AffineTransform at;
    BufferedImage backbuffer;
    ImageEntity background;
    protected boolean active;
    int ID;
    public static final int FARMSTATE = 0;
    public static final int FRONTSTATE = 1;
    public static final int INVENTORY = 2;
    public static final int HOME = 3;
    public static final int BATTLEGURUN = 4;
    public static final int STORE = 5;
    public static final int MAPSTATE = 6;
    public static final int TOMATSTATE = 7;
    public static final int NANASSTATE = 8;
    public static final int BAWANGSTATE = 9;
    public static final int LABUSTATE = 10;
    public static final int GAMEOVER = 11;
    public static final int PAPRIKASTATE = 12;
    public static final int BAYAMSTATE = 13;
    public static final int KENTANGSTATE = 14;
    public ParentState(){
    }

    public ParentState(int gridRow, int gridColumn){
        background = new ImageEntity(this);
        backbuffer = new BufferedImage(gridRow*Utilities.GRIDSIZE, gridColumn*Utilities.GRIDSIZE, BufferedImage.TYPE_INT_ARGB);
        g2d = backbuffer.createGraphics();
    }
    public int getID(){
        return ID;
    }
    protected void setname(String name){
        this.setName(name);
    }
    public void run(){}
    public void turnOff(){
        active = false;
    }
}
