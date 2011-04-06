package plantmon.states;


import javax.swing.JPopupMenu;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.*;
import java.awt.image.*;
import java.net.URL;
import javax.swing.JPanel;
import plantmon.entity.movingObject.Player;
import plantmon.game.GridMap;
import plantmon.game.ImageEntity;
import plantmon.game.Point2D;
import plantmon.system.Actionable;
import plantmon.system.Drawable;
import plantmon.system.Selectable;


public class FarmState extends JPanel implements Runnable,MouseListener {
    Thread gameloop;
    GridMap map;
    JPopupMenu popup;
    static int SCREENHEIGHT = 480;
    static int SCREENWIDTH = 640;
    Graphics2D g2d;
    ImageEntity background;
    BufferedImage backbuffer;
    AffineTransform at;
    Player player;
    Selectable selected;
    Actionable actionated;
    boolean selectsomething;
    public FarmState(){
        super();
        init();
    }
    private URL getURL(String filename) {
        URL url = null;
        try {
            url = this.getClass().getResource(filename);
        }
        catch (Exception e) { }
        return url;
    }

    public void init() {
        System.out.println("Farm INIT");
        map = new GridMap();
        backbuffer = new BufferedImage(SCREENWIDTH, SCREENHEIGHT, BufferedImage.TYPE_INT_ARGB);
        g2d = backbuffer.createGraphics();
        background = new ImageEntity(this);
        background.load("picture/bg2.png");
        player = new Player(map, this, g2d);
        Point2D pos = player.getCreature().position();
        System.out.printf("%f %f\n",pos.X(),pos.Y());
        map.push(pos.X(), pos.Y(), player);
        addMouseListener(this);
    }
    public void run(){
        
        while (true) {
            System.out.println("run - CobaOpeh");
            try {
                Thread.sleep(20);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            gameUpdate();
            repaint();
        }
        
    }
    public void gameUpdate() {
        System.out.println("gameUpdate-CobaOpeh");
        Point2D pos = player.getCreature().position();
        System.out.format("%d %d ciiiiiiiiiih\n",(int)pos.X()/80, (int)pos.Y()/80);
        map.pop((int)pos.X()/80, (int)pos.Y()/80);
        player.update();
        map.push(pos.X(), pos.Y(), player);
    }

    public void updated(Graphics g){
        System.out.println("updated - CobaOpeh");
        g2d.setColor(Color.WHITE);
        g2d.drawImage(background.getImage(), 0, 0,SCREENWIDTH-1,SCREENHEIGHT-1, this);
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                if (map.getTop(i, j) instanceof Drawable) { // it should be instance of drawable
                    // it should be selectable.draw
                    ((Drawable) map.getTop(i, j)).draw();
//                //}else {// if (map.getTop(i, j) instanceof Object){
//                    System.out.format("It is something at %d %d\n",i,j);
                }
            }
        }
        if (selectsomething) {
            selected.drawBounds();
        }
      
        g2d.setColor(Color.GRAY);
        g2d.drawString("Position: " + player.position().X() + "," + player.position().Y(), 5, 10);
        g2d.drawString("Velocity: " + player.getCreature().velocity().X() + "," + player.getCreature().velocity().Y(), 5, 25);
        g2d.drawString("Animation: " + player.getCreature().currentFrame(), 5, 40);
    }
    @Override public void paintComponent(Graphics g) {
        System.out.println("paintComponent - CobaOpeh");
        updated(g);
        g.drawImage(backbuffer, 0, 0, this);
    }
    public void mouseExited(MouseEvent e){

    }
    public void mouseEntered(MouseEvent e){

    }
    public void mouseReleased(MouseEvent e){

    }
    public void mousePressed(MouseEvent e){

     }
   public void mouseClicked(MouseEvent e){
        System.out.println("mouseClicked");
        int fx = e.getX();
        int fy = e.getY();
        int gx = fx/80;
        int gy = fy/80;
        System.out.printf("%d(%d) %d(%d) | %d(%f) %d(%f)",gx/80,gx, gy/80,gy,(int)player.position().X()/80,player.position().X(),(int)player.position().Y()/80,player.position().Y());
        int clicked = e.getButton();
        switch(clicked){    
            case MouseEvent.BUTTON1:
                selectsomething = false;
                if (map.getTop(gx, gy) != null ) {
                    if (map.getTop(gx, gy) instanceof Selectable) {
                        // will be a Selectable object
                        selected = (Selectable) map.getTop(gx, gy);
                        // if (selected instanceof Actionable)
                        selectsomething = true;
                    }
                } else selectsomething = false;
                break;
            case MouseEvent.BUTTON3:
                if (selectsomething){
                    // get object from where it's clicked from map
                    if (map.getTop(gx, gy) instanceof Actionable){
                        actionated = (Actionable) map.getTop(gx, gy);
                    } else {
                        actionated = new Point2D(fx,fy);
                    }
                    
                    // it should be menu = selected.setPopMenu(actionated)
                    popup = actionated.getMenu(selected);
                    popup.show(e.getComponent(),
                           e.getX(), e.getY());
                }
                break;
            default: break;
        }
    }
}
   