package plantmon.game;

import java.awt.*;
import java.awt.Graphics2D;
import java.awt.geom.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImageEntity extends BaseGameEntity {
//variables
    protected Image image;
    protected JPanel panel;
    protected AffineTransform at;
    protected Graphics2D g2d;
    //default constructor
    public ImageEntity(JPanel a) {
        panel = a;
        setImage(null);
        setAlive(true);
    }
    public Image getImage() { return image; }

    public void setImage(Image image) {
        this.image = image;
        double x = panel.getSize().width/2 - width()/2;
        double y = panel.getSize().height/2 - height()/2;
        at = AffineTransform.getTranslateInstance(x, y);
    }
    public int width() {
        if (image != null)
            return image.getWidth(panel);
        else
            return 0;
    }
    public int height() {
        if (image != null)
            return image.getHeight(panel);
        else
            return 0;
    }
    public double getCenterX() {
        return getX() + width() / 2;
    }
    public double getCenterY() {
        return getY() + height() / 2;
    }
    public void setGraphics(Graphics2D g) {
        g2d = g;
    }
    public void load(String filename) {
        try {
            image = ImageIO.read(this.getClass().getResource(filename));
        } catch(IOException e){}
        while(getImage().getWidth(panel) <= 0);
        double x = panel.getSize().width/2 - width()/2;
        double y = panel.getSize().height/2 - height()/2;
        at = AffineTransform.getTranslateInstance(x, y);
    }
    public void transform() {
        at.setToIdentity();
        at.translate((int)getX() + width()/2, (int)getY() + height()/2);
        at.rotate(Math.toRadians(getFaceAngle()));
        at.translate(-width()/2, -height()/2);
    }
    public void draw() {
        g2d.drawImage(getImage(), at, panel);
    }
    //bounding rectangle
    public Rectangle getBounds() {
        Rectangle r;
        r = new Rectangle((int)getX(), (int)getY(), width(), height());
        return r;
    }
}