package plantmon.game;
import java.awt.*;
import javax.swing.JPanel;
/**
 * Sprite class represent a sprite
 */
public class Sprite extends Object {
    
    private ImageEntity entity;
    protected Point2D pos;
    protected Point2D vel;
    protected double rotRate;
    protected int currentState;
    protected Point2D finalPosition;

    public Point2D finalPosition(){
        return finalPosition;
    }

    //constructor
    Sprite(JPanel a, Graphics2D g2d) {
        entity = new ImageEntity(a);
        entity.setGraphics(g2d);
        entity.setAlive(false);
        pos = new Point2D(0, 0);
        vel = new Point2D(0, 0);
        finalPosition = new Point2D(0,0);
        rotRate = 0.0;
        currentState = 0;
    }
    /**
     * load bitmap file
     * @param filename nama file, harus bertipe png
     */
    public void load(String filename) {
        entity.load(filename);
    }
    //perform affine transformations
    public void transform() {
        entity.setX(pos.X());
        entity.setY(pos.Y());
        entity.transform();
    }
    /**
     * draw the image
     */
    public void draw() {
        entity.draw();//g2d.drawImage(entity.getImage(),entity.at,entity.applet);
    }
    /**
     * draw bounding rectangle around sprite
     * @param c color
     */
    public void drawBounds(Color c) {
        entity.g2d.setColor(c);
        entity.g2d.draw(getBounds());
    }
    /**
     * update the position based on velocity
     */
    public void updatePosition() {
        pos.setX(pos.X() + vel.X());
        pos.setY(pos.Y() + vel.Y());
        if ((Math.abs((double)pos.X()-finalPosition.X())  <= 1) &&
            (Math.abs((double)pos.Y()-finalPosition.Y())  <= 1)) {
            setVelocity(new Point2D(0,0));
        } else {
            double vx = 1.5* Math.sin(Math.atan2(finalPosition.X()-this.position().X(),
                    finalPosition.Y()-this.position().Y()));
            double vy = 1.5* Math.cos(Math.atan2(finalPosition.X()-this.position().X(),
                    finalPosition.Y()-this.position().Y()));
            setVelocity(new Point2D(vx, vy));
        }
    }
    /**
     *
     * @return rotRate
     */
    public double rotationRate() { return rotRate; }
    /**
     * set rotation rate
     * @param rate
     */
    public void setRotationRate(double rate) { rotRate = rate; }
    /**
     * update arah hadap berdasarkan kecepatan berputar
     */
    public void updateRotation() {
        setFaceAngle(faceAngle() + rotRate);
        if (faceAngle() < 0)
            setFaceAngle(360 - rotRate);
        else if (faceAngle() > 360)
            setFaceAngle(rotRate);
    }
    /**
     * generic sprite state variable (alive, dead, collided, etc.)
     */
    public int state() { return currentState; }
    public void setState(int state) { currentState = state; }
    /**
     *
     * @return a bounding rectangle
     */
    public Rectangle getBounds() { return entity.getBounds(); }
    /**
     * 
     * @return sprite position
     */
    public Point2D position() { return pos; }
    /**
     * set sprite position
     * @param pos
     */
    public void setPosition(Point2D pos) { this.pos = pos; }
    /**
     * sprite movement velocity
     */
    public Point2D velocity() { return vel; }
    /**
     * set sprite movement velocity
     * @param vel
     */
    public void setVelocity(Point2D vel) { this.vel = vel; }
    /**
     * 
     * @return the center of the sprite as a Point2D
     */
    public Point2D center() {
        return(new Point2D(entity.getCenterX(),entity.getCenterY()));
    }
    /**
     * 
     * @return generic variable for selectively using sprites
     */
    public boolean alive() { return entity.isAlive(); }
    /**
     * set generic variable for selectively using sprites
     * @param alive
     */
    public void setAlive(boolean alive) { entity.setAlive(alive); }
    /**
     * 
     * @return face angle indicates which direction sprite is facing
     */
    public double faceAngle() { return entity.getFaceAngle(); }
    /*
     * set face angle indicates which direction sprite is facing
     */
    public void setFaceAngle(double angle) {
    entity.setFaceAngle(angle);
    }
    /*
     * set face angle indicates which direction sprite is facing
     */
    public void setFaceAngle(float angle) {
        entity.setFaceAngle((double) angle);
    }
    /*
     * set face angle indicates which direction sprite is facing
     */
    public void setFaceAngle(int angle) {
        entity.setFaceAngle((double) angle);
    }
    /**
     *
     * @return move angle indicates direction sprite is moving
     */
    public double moveAngle() { return entity.getMoveAngle(); }
    /**
     * set move angle indicates direction sprite is moving
     * @param angle
     */
    public void setMoveAngle(double angle) {
        entity.setMoveAngle(angle);
    }
    /**
     * set move angle indicates direction sprite is moving
     * @param angle
     */
    public void setMoveAngle(float angle) {
        entity.setMoveAngle((double) angle);
    }
    /**
     * set move angle indicates direction sprite is moving
     * @param angle
     */
    public void setMoveAngle(int angle) {
        entity.setMoveAngle((double) angle);
    }
    /**
     *
     * @return the source image width
     */
    public int imageWidth() { return entity.width(); }
    /**
     *
     * @return the source image height
     */
    public int imageHeight() { return entity.height(); }
    /**
     * check for collision with a rectangular shape
     * @param rect
     * @return is it collide?
     */
    public boolean collidesWith(Rectangle rect) {
        return (rect.intersects(getBounds()));
    }
    /**
     * check for collision with another sprite
     * @param sprite
     * @return is it collide?
     */
    public boolean collidesWith(Sprite sprite) {
        return (getBounds().intersects(sprite.getBounds()));
    }
    /**
     * check for collision with a point
     * @param point
     * @return is it collide?
     */
    public boolean collidesWith(Point2D point) {
        return (getBounds().contains(point.X(), point.Y()));
    }
    /**
     *
     * @return where is it drawed
     */
    public JPanel panel() { return entity.panel; }
    /**
     *
     * @return what is the buffer
     */
    public Graphics2D graphics() { return entity.g2d; }
    /**
     *
     * @return the image object
     */
    public Image image() { return entity.image; }
    /**
     * set the image object
     * @param image
     */
    public void setImage(Image image) { entity.setImage(image); }
    /**
     * set the final position
     * @param gx position in pixel
     * @param gy position in pixel
     */
    public void setFinalPosition(int gx,int gy){
        finalPosition = new Point2D(gx,gy);
    }
}