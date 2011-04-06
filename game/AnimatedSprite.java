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
    

    public AnimatedSprite(JPanel applet, Graphics2D g2d) {
        super(applet, g2d);
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
        try {
            animimage = ImageIO.read(this.getClass().getResource(filename));
        } catch(IOException e) {}
        setColumns(columns);
        setTotalFrames(columns * rows);
        setFrameWidth(width);
        setFrameHeight(height);
        this.width = width;
        this.height = height;
    }
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
    public void setVelocity(int gx, int gy) {
        gx = (gx/80)*80+10;
        gy = (gy/80)*80+10;
        double vx = 1.5* Math.sin(Math.atan2(gx-this.position().X(),
                    gy-this.position().Y()));
        double vy = 1.5* Math.cos(Math.atan2(gx-this.position().X(),
                    gy-this.position().Y()));
        try {
            if ((Math.abs(vy/vx)>1.0) && vy>0) {
                setFaceAngle(0);
                setImage(ImageIO.read(this.getClass().getResource("picture/anim2.png")));
            } else if ((Math.abs(vy/vx) < 1.0) && vx>0){
                setFaceAngle(90);
                setImage(ImageIO.read(this.getClass().getResource("picture/anim3.png")));
            } else if ((Math.abs(vy/vx) > 1.0) && vy<0){
                setFaceAngle(180);
                setImage(ImageIO.read(this.getClass().getResource("picture/anim0.png")));
            } else if ((Math.abs(vy/vx) < 1.0) && vx<0){
                setFaceAngle(270);
                setImage(ImageIO.read(this.getClass().getResource("picture/anim1.png")));
            }
        } catch (IOException e) {}
        super.setVelocity(new Point2D(vx,vy));
        this.setFinalPosition(gx,gy);
    }
    
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
        tempImage = new BufferedImage(width, height,BufferedImage.TYPE_INT_ARGB);
        tempSurface = tempImage.createGraphics();
        int frameX = (currentFrame() % columns()) * frameWidth();
        int frameY = (currentFrame() / columns()) * frameHeight();
        //copy the frame onto the temp image
        tempSurface.drawImage(animimage, 0, 0, frameWidth()-1,
                frameHeight()-1, frameX, frameY, frameX+frameWidth(),
                frameY+frameHeight(), applet());
        //pass the temp image on to the parent class and draw it
        //frame image is passed to parent class for drawing
        
        //super.setImage(tempImage);
        super.setImage(tempImage);
        super.transform();
        super.draw();
    }
}

