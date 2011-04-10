package plantmon.states;

import java.awt.Graphics;
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
    public ParentState(int gridRow, int gridColumn, String backgroundFile){
        background = new ImageEntity(this);
        background.load(backgroundFile);
        backbuffer = new BufferedImage(gridRow*80, gridColumn*80, BufferedImage.TYPE_INT_ARGB);
        g2d = backbuffer.createGraphics();
    }
}
