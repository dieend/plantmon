package plantmon.game;

/**
 * Kelas untuk membuat gambar animasi
 */
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
    protected String imageName;
    protected JPanel panel;
    @Override
    public void setPanel(JPanel panel){
        super.setPanel(panel);
        super.reinit();
        this.panel = panel;
    }
    @Override
    public void setGraphics(Graphics2D g2d){
        super.setGraphics(g2d);

    }
    /**
     * imageName nama gambar beranimasi. harus bertipe png dan tidak memakai extensi.
     */
    public void setImageName(String name){
        imageName = name;
    }
    /**
     * Konstruktor gambar animasi.
     * @param applet tempat menggambar animasi
     * @param g2d buffer animasi
     */
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
    /**
     * Meload data file gambar yang akan digunakan sebagai animasi
     * @param filename lokasi gambar, harus bertipe .png
     * @param columns jumlah kolom frame gambar animasi
     * @param rows jumlah baris frame gambar animasi
     * @param width lebar 1 frame(dalam pixel)
     * @param height tinggi 1 frame(dalam pixel)
     */
    public void load(String filename, int columns, int rows,int width, int height)
    {
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
    /**
     *
     * @return tempat menggambar yang digunakan animasi ini
     */
    @Override public JPanel panel() {return panel;}
    /**
     *
     * @return nomor frame saat ini
     */
    public int currentFrame() { return currFrame; }
    /**
     * mengeset frame saat ini dengan frame yang baru
     * @param nomor frame yang baru
     */
    public void setCurrentFrame(int frame) { currFrame = frame; }public int frameWidth() { return frWidth; }
    /**
     * mengeset lebar frame saat ini dengan width
     * @param width
     */
    public void setFrameWidth(int width) { frWidth = width; }
    /**
     *
     * @return tinggi frame
     */
    public int frameHeight() { return frHeight; }
    /**
     * mengeset tinggi frame saat ini dengan height
     * @return
     */
    public void setFrameHeight(int height) { frHeight = height; }
    /**
     *
     * @return total frame yang ada
     */
    public int totalFrames() { return totFrames; }
    /**
     * mengeset total frame
     * @param total
     */
    public void setTotalFrames(int total) { totFrames = total; }
    /**
     *
     * @return arah animasi
     */
    public int animationDirection() { return animDir; }
    /**
     * mengeset arah animasi
     * @param dir arah animasi
     */
    public void setAnimationDirection(int dir) { animDir = dir; }
    /**
     * frame counter digunakan untuk menunda pindah frame, apabila
     * frame counter > frame delay, maka akan pindah frame
     * @return frame counter
     */
    public int frameCount() { return frCount; }
    /**
     * mengeset frame counter
     * @param count
     */
    public void setFrameCount(int count) { frCount = count; }
    /**
     *
     * @return delay yang digunakan untuk frame
     */
    public int frameDelay() { return frDelay; }
    /**
     * mengeset frame delay
     * @param delay
     */
    public void setFrameDelay(int delay) { frDelay = delay; }
    /**
     *
     * @return jumlah kolom
     */
    public int columns() { return cols; }
    /**
     * mengeset jumlah kolom
     * @param num jumlah kolom
     */
    public void setColumns(int num) { cols = num; }
    /**
     * mengupdate animasi
     */
    public void updateAnimation() {
        if ((Math.abs(velocity().Y()/velocity().X())>1.0) && velocity().Y()>0) {
//            System.out.println("Hadap bawah");
//                setFaceAngle(0);
            load(imageName+"0.png",4,1,32,32);
        } else if ((Math.abs(velocity().Y()/velocity().X())<1.0) && velocity().X()>0){
//            System.out.println("Hadap kanan");
//                setFaceAngle(90);
            load(imageName+"3.png",4,1,32,32);
        } else if ((Math.abs(velocity().Y()/velocity().X())>1.0) && velocity().Y()<0){
//                setFaceAngle(180);
//            System.out.println("Hadap atas");
            load(imageName+"2.png",4,1,32,32);
        } else if ((Math.abs(velocity().Y()/velocity().X())<1.0) && velocity().X()<0){
//                setFaceAngle(270);
//            System.out.println("Hadap ke kiri");
            load(imageName+"1.png",4,1,32,32);
        }
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
    /**
     * menggambar animasi ke buffer
     */
    @Override public void draw() {
        //calculate the current frame’s X and Y position
//        System.out.print("I'm drawing animated\n");
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
    public void setArah(Point2D dest){
        System.out.println(dest.X()+" "+dest.Y()+" "+position().X()+" "+position().Y());
        double vx = Math.sin(Math.atan2(dest.X()-this.position().X(),
                    dest.Y()-this.position().Y()));
        double vy = Math.cos(Math.atan2(dest.X()-this.position().X(),
                dest.Y()-this.position().Y()));
        System.out.println(vx + " " + vy + " " + (vy/vx));
        if ((Math.abs(vy/vx)>1.0) && vy>0) {
//                setFaceAngle(0);
            load(imageName+"0.png",cols,totFrames/cols,width,height);
        } else if ((Math.abs(vy/vx)<1.0) && vx>0){
//            System.out.println("Hadap kanan");
//                setFaceAngle(90);
            load(imageName+"3.png",cols,totFrames/cols,width,height);
        } else if ((Math.abs(vy/vx)>1.0) && vy<0){
//                setFaceAngle(180);
//            System.out.println("Hadap atas");
            load(imageName+"2.png",cols,totFrames/cols,width,height);
        } else if ((Math.abs(vy/vx)<1.0) && vx<0){
//                setFaceAngle(270);
//            System.out.println("Hadap ke kiri");
            load(imageName+"1.png",cols,totFrames/cols,width,height);
        }

    }
}

