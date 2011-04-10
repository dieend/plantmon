package plantmon.states;


import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.*;
import java.net.URL;
import javax.swing.JButton;
import plantmon.entity.movingObject.Player;
import plantmon.entity.unmoveable.Land;
import plantmon.game.GridMap;
import plantmon.game.ImageEntity;
import plantmon.game.Point2D;
import plantmon.system.Actionable;
import plantmon.system.Selectable;


public class FarmState extends ParentState implements Runnable,MouseListener,MouseMotionListener {
    Thread gameloop;
    GridMap map;
    JPopupMenu popup;
    JButton text = new JButton("mungkin percakapan disini?");
    public static int SCREENHEIGHT = 480;
    public static int SCREENWIDTH = 640;
    public int startx;
    public int starty;
    int gridRow, gridColumn;
    

    Player player;
    Selectable selected;
    Actionable actionated;
    boolean selectsomething;
    int clickx,clicky,defx,defy;
    boolean dragged;
    public FarmState(int gridRow, int gridColumn,String bg){
        super(gridRow, gridColumn, bg);
        init();
        setLayout(new GridLayout(5, 1));
        add(new Component() {});
        add(new Component() {});
        add(new Component() {});
        add(new Component() {});
        //test.setVisible(true);
        text.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //text.setVisible(false);
//                startx += 50;
//                starty += 50;
            }
        });
        add(text);
        addMouseMotionListener(this);
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
        startx=0; starty=0;
        int x = 10; int y = 10;
        map = new GridMap(x,y);
        backbuffer = new BufferedImage(x*80, y*80, BufferedImage.TYPE_INT_ARGB);
        g2d = backbuffer.createGraphics();
        background = new ImageEntity(this);
        background.load("picture/bg2.png");
        for (int i=0; i<map.getRow();i++){
            for (int j=0; j<map.getColumn(); j++){
                map.gpush(i, j, new Land(map, this, g2d,i,j));
            }
        }
        player = new Player(map, this, g2d, 10);// maxslot = 10
        Point2D pos = player.getCreature().position();
        map.push(pos.X(), pos.Y(), player);
        addMouseListener(this);
    }
    public void run(){
        
        while (true) {
//            System.out.format("There are currenty %d Thread running\n",Thread.activeCount());
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
//        Point2D pos = player.getCreature().position();
//        for (int i=0; i<8;i++){
//            for (int j=0; j<8; j++){
//                if (map.getTop(i, j) instanceof Drawable){
//                    ((Drawable)map.getTop(i, j)).update();
//                }
//            }
//        }
    }

    public void updated(Graphics g){

        g2d.setColor(Color.WHITE);
        g2d.drawImage(background.getImage(), 0, 0,SCREENWIDTH-1,SCREENHEIGHT-1, this);
        map.draw(startx,starty);
        if (selectsomething) {
            selected.drawBounds();
        }
      
        g2d.setColor(Color.GRAY);
        g2d.drawString("Position: " + player.position().X() + "," + player.position().Y(), 5, 10);
        g2d.drawString("Velocity: " + player.getCreature().velocity().X() + "," + player.getCreature().velocity().Y(), 5, 25);
        g2d.drawString("Animation: " + player.getCreature().currentFrame(), 5, 40);
    }
    @Override public void paintComponent(Graphics g) {
//        System.out.println("paintComponent - CobaOpeh");
        updated(g);
        g.drawImage(backbuffer,startx, starty, this);
    }
    public void mouseExited(MouseEvent e){

    }
    public void mouseEntered(MouseEvent e){

    }
    public void mouseReleased(MouseEvent e){
        updateDiff(e);
        clickx = 0;
        clicky = 0;
        dragged = false;
    }
    public void mousePressed(MouseEvent e){
        clickx = e.getX();
        clicky = e.getY();
        defx = startx;
        defy = starty;
        dragged = true;
     }
   public void mouseClicked(MouseEvent e){
        final MouseEvent tmp = e;
//        System.out.println("mouseClicked");
        int fx = e.getX()-startx;
        int fy = e.getY()-starty;
        int gx = fx/80;
        int gy = fy/80;
//        System.out.printf("%d(%d) %d(%d) | %d(%f) %d(%f)",gx/80,gx, gy/80,gy,(int)player.position().X()/80,player.position().X(),(int)player.position().Y()/80,player.position().Y());
        int clicked = e.getButton();
        switch(clicked){    
            case MouseEvent.BUTTON1:
                selectsomething = false;
                if (map.getTop(gx, gy) != null ) {
                    if (map.getTop(gx, gy) instanceof Selectable) {
                        selected = (Selectable) map.getTop(gx, gy);
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
                    
                    //get menu from selected
                    popup = actionated.getMenu(selected);
                    if (popup != null) {
                        popup.show(tmp.getComponent(),tmp.getX(), tmp.getY());
                    }
                }
                break;
            default: break;
        }
    }

        public void mouseDragged(MouseEvent e) {
            updateDiff(e);
        }

        

        void updateDiff(MouseEvent e) {
            if (dragged){
                int x = e.getX();
                int y = e.getY();
                int ULx =10,ULy=10, LRx=-10+FarmState.SCREENWIDTH-map.getRow()*80,
                        LRy = FarmState.SCREENHEIGHT-(map.getColumn()*80+100);
                int newx = defx +((x-clickx));
                int newy = defy +((y-clicky));
//                System.out.println("oooo" + newx+" "+ULx+" "+LRx);
                if ((ULx >= newx && newx >=LRx)&&(ULy >= newy && newy >=LRy)){
                    startx =newx;
                    starty =newy;
                }
            }
        }

    public void mouseMoved(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }
}
   