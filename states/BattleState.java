package plantmon.states;

import javax.swing.JPopupMenu;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.*;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import plantmon.entity.Canceller;
import plantmon.entity.Time;
import plantmon.entity.deadItem.Portal;
import plantmon.entity.movingObject.Pulmosis;
import plantmon.entity.unmoveable.BattleLand;
import plantmon.entity.unmoveable.Land;
import plantmon.game.GridMap;
import plantmon.game.ImageEntity;
import plantmon.game.Point2D;
import plantmon.system.Actionable;
import plantmon.system.Selectable;
import plantmon.system.Utilities;


public class BattleState extends ParentState implements MouseListener,MouseMotionListener {
    Thread gameloop;
    GridMap map;
    JPopupMenu popup;
    public static JTextArea text;
    int startx;
    int starty;
    JTextArea time;
    Selectable selected;
    boolean selectsomething;
    Actionable actionated;
    int clickx,clicky,defx,defy;
    boolean dragged;
    public BattleState(int gridRow, int gridColumn){
        super(gridRow, gridColumn);
        ID = FARMSTATE;
        init();
        time = new JTextArea();
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setPreferredSize(new Dimension(640, 480));
        setLayout(null);
        text = new JTextArea();
        text.setBounds(0, 350, 650, 100);
        JScrollPane pane = new JScrollPane(text);
        pane.setBounds(0, 350, 650, 100);
//        add(new Component() {});
//        add(new Component() {});
//        add(new Component() {});
//        add(new Component() {});
        //test.setVisible(true);
        text.setEditable(false);
        time.setEditable(false);
        time.setBounds(0, 0, 200, 50);
        time.setBackground(Color.ORANGE);
        time.setForeground(Color.black);
        add(pane);
        add(time);
        //add(pane);
        addMouseMotionListener(this);
        active = true;

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
        backbuffer = new BufferedImage(x*Utilities.GRIDSIZE, y*Utilities.GRIDSIZE, BufferedImage.TYPE_INT_ARGB);
        g2d = backbuffer.createGraphics();
        background = new ImageEntity(this);
        background.load("picture/bg2.png");
       
        Pulmosis player = new Pulmosis(map, this, g2d,1,false);
        player.getCreature().setPosition(new Point2D(Utilities.GRIDSIZE + Utilities.GRIDGALAT,Utilities.GRIDSIZE + Utilities.GRIDGALAT));
        player.getCreature().setFinalPosition(Utilities.GRIDSIZE + Utilities.GRIDGALAT,Utilities.GRIDSIZE + Utilities.GRIDGALAT);
        Pulmosis player2 = new Pulmosis(map, this, g2d,0,true);
        player2.getCreature().setPosition(new Point2D(2*Utilities.GRIDSIZE + Utilities.GRIDGALAT,2*Utilities.GRIDSIZE + Utilities.GRIDGALAT));
        player2.getCreature().setFinalPosition(2*Utilities.GRIDSIZE + Utilities.GRIDGALAT,2*Utilities.GRIDSIZE + Utilities.GRIDGALAT);
        for (int i=0; i<map.getRow();i++){
            for (int j=0; j<map.getColumn(); j++){
                map.gpush(i, j, new Land(map, this, g2d,i,j));
            }
        }
        Point2D pos = player.getCreature().position();
        //map.gpush(1, 1, new Pulmosis(map,this,g2d,1,false));
        map.push(pos.X(), pos.Y(), player);
        pos = player2.getCreature().position();
        //map.gpush(2, 2, new Pulmosis(map,this,g2d,0,true));
        map.push(pos.X(), pos.Y(), player2);
        addMouseListener(this);
    }
    @Override
    public void run(){
        active = true;
        while (active) {
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
//        System.out.format("I wonder why won't work\n");
        boolean found = true;
        int i = 0;
        int j = 0;
        Time.instance().update();
        time.setText(Time.instance().getTime());
        for (i = 0;i < 10 && found; i++) {
            for (j = 0; j < 10 && found;j++) {
                if (map.getTop(i,j) instanceof Pulmosis) {
                    Pulmosis pul = (Pulmosis) map.getTop(i,j);
                    System.out.println("adada");
                    if (pul.getStatusEnemy()) {
                        found = false;
                        break;
                    }
                } else if (map.getTop(i,j) instanceof Land) {
                    System.out.println("APAAN SIH"+i+j);
                }
            }
        }
        if (found) {
            Game.instance().goTo(ParentState.MAPSTATE,new Object[0]);
        }
//        Point2D pos = player.getCreature().position();
//        for (int i=0; i<8;i++){
//            for (int j=0; j<8; j++){
//                if (map.getTop(i, j) instanceof Drawable){
//                    ((Drawable)map.getTop(i, j)).update();
//                }
//            }
//        }
    }

    public void updated(){
        g2d.setColor(Color.WHITE);
        g2d.drawImage(background.getImage(), 0, 0,SCREENWIDTH-1,SCREENHEIGHT-1, this);
        map.draw(startx,starty);
        if (selectsomething) {
            selected.drawBounds();
        }

        g2d.setColor(Color.GRAY);
    }
    @Override public void paintComponent(Graphics g) {
//        System.out.println("paintComponent - CobaOpeh");
        updated();
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
        int gx = fx/Utilities.GRIDSIZE;
        int gy = fy/Utilities.GRIDSIZE;
//        System.out.printf("%d(%d) %d(%d) | %d(%f) %d(%f)",gx/Utilities.GRIDSIZE,gx, gy/Utilities.GRIDSIZE,gy,(int)player.position().X()/Utilities.GRIDSIZE,player.position().X(),(int)player.position().Y()/Utilities.GRIDSIZE,player.position().Y());
        int clicked = e.getButton();
        switch(clicked){
            case MouseEvent.BUTTON1:
                if (map.getTop(gx, gy) != null ) {
                    if (map.getTop(gx, gy) instanceof Selectable) {
                        //actionated = (Actionable) map.getTop(gx, gy);
                        selected = (Selectable) map.getTop(gx, gy);
                        selectsomething = true;
                    } else if (map.getTop(gx, gy) instanceof Canceller){
                        System.out.print("fjhfhgf");
                        popup = ((Canceller)map.getTop(gx,gy)).getMenu();
                        popup.show(tmp.getComponent(),tmp.getX(), tmp.getY());
                        selectsomething = false;
                    }
                } else selectsomething = false;
                break;
            case MouseEvent.BUTTON3:
                if (selectsomething){
                    // get object from where it's clicked from map
                    //actionated = (Actionable) map.getTop(gx, gy);
                    actionated = new BattleLand(gx * Utilities.GRIDSIZE + Utilities.GRIDGALAT,gy * Utilities.GRIDSIZE + Utilities.GRIDGALAT,map,this,g2d);
                    popup = actionated.getMenu(selected);
                    //get menu from selected
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
                int ULx =10,ULy=10, LRx=-10+FarmState.SCREENWIDTH-map.getRow()*Utilities.GRIDSIZE,
                        LRy = FarmState.SCREENHEIGHT-(map.getColumn()*Utilities.GRIDSIZE+150);
                int newx = defx +((x-clickx));
                int newy = defy +((y-clicky));
//                System.out.println("oooo" + newx+" "+ULx+" "+LRx);
                if ((ULx >= newx && newx >=LRx)&&(ULy >= newy && newy >=LRy)){
                    startx =newx;
                    starty =newy;
                }
            }
        }

    public void mouseMoved(MouseEvent e) {}
    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setSize(640, 480);
        frame.setVisible(true);
        Game.instance().setFrame(frame);
        Game.instance().goTo(BATTLESTATE, args);
    }
}
