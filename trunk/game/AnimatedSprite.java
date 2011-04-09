package plantmon.game;

/*****************************************************
* AnimatedSprite class
*****************************************************/
import java.awt.*;
import java.awt.image.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
public class AnimatedSprite extends Sprite {
    //this image holds the large tiled bitmap
    private Image animimage;
    //temp image passed to parent draw method
    BufferedImage tempImage;
    Graphics2D tempSurface;
    //custom properties
    private int currFrame, totFrames;
    private int animDir;
    private int frCount, frDelay;
    private int frWidth, frHeight;
    private int cols;
    private int width;
    private int height;
    protected JPanel panel;

    public AnimatedSprite(JPanel applet, Graphics2D g2d) {
        super(applet, g2d);
        panel = applet;
        currFrame = 0;
        totFrames = 0;
        animDir = 1;
        frCount = 0;
        frDelay = 0;
        frWidth = 0;
        frHeight = 0;
        cols = 0;
    }
    
    public void load(String filename, int columns, int rows,int width, int height)
    {
        //load the tiled animation bitmap
        System.out.print("animated should be changed");
        try {
            System.out.print("animated is changed");
            animimage = ImageIO.read(this.getClass().getResource(filename));
        } catch(IOException e) {e.printStackTrace(); }
        setColumns(columns);
        setTotalFrames(columns * rows);
        setFrameWidth(width);
        setFrameHeight(height);
        this.width = width;
        this.height = height;
    }
    public JPanel panel() {return panel;}
    public int currentFrame() { return currFrame; }
    public void setCurrentFrame(int frame) { currFrame = frame; }public int frameWidth() { return frWidth; }
    public void setFrameWidth(int width) { frWidth = width; }
    public int frameHeight() { return frHeight; }
    public void setFrameHeight(int height) { frHeight = height; }
    public int totalFrames() { return totFrames; }
    public void setTotalFrames(int total) { totFrames = total; }
    public int animationDirection() { return animDir; }
    public void setAnimationDirection(int dir) { animDir = dir; }
    public int frameCount() { return frCount; }
    public void setFrameCount(int count) { frCount = count; }
    public int frameDelay() { return frDelay; }
    public void setFrameDelay(int delay) { frDelay = delay; }
    public int columns() { return cols; }
    public void setColumns(int num) { cols = num; }
    
    public void updateAnimation() {
        frCount++;
        if (frameCount() > frameDelay()) {
            setFrameCount(0);
            //update the animation frame
            setCurrentFrame(currentFrame() + animationDirection());
            if (currentFrame() > totalFrames() - 1) {
                setCurrentFrame(0);
            }
            else if (currentFrame() < 0) {
                setCurrentFrame(totalFrames() - 1);
            }
        }
    }

    @Override public void draw() {
        //calculate the current frame’s X and Y position
        System.out.print("I'm drawing animated\n");
        tempImage = new BufferedImage(width, height,BufferedImage.TYPE_INT_ARGB);
        tempSurface = tempImage.createGraphics();
        int frameX = (currentFrame() % columns()) * frameWidth();
        int frameY = (currentFrame() / columns()) * frameHeight();
        //copy the frame onto the temp image
        tempSurface.drawImage(animimage, 0, 0, frameWidth()-1,
                frameHeight()-1, frameX, frameY, frameX+frameWidth(),
                frameY+frameHeight(), panel());
        //pass the temp image on to the parent class and draw it
        //frame image is passed to parent class for drawing
        
        //super.setImage(tempImage);
        super.setImage(tempImage);
        super.transform();
        super.draw();
    }
}

