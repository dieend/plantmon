package plantmon.states;


import javax.swing.JPopupMenu;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.*;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import plantmon.entity.Canceller;
import plantmon.entity.Time;
import plantmon.entity.deadItem.Store;
import plantmon.entity.movingObject.Dwarf;
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
    public static JTextArea text;
    int startx;
    int starty;
    Player player;
    JTextArea time;
    Selectable selected;
    Actionable actionated;
    boolean selectsomething;
    int clickx,clicky,defx,defy;
    boolean dragged;
    public FarmState(int gridRow, int gridColumn){
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
        backbuffer = new BufferedImage(x*80, y*80, BufferedImage.TYPE_INT_ARGB);
        g2d = backbuffer.createGraphics();
        background = new ImageEntity(this);
        background.load("picture/bg2.png");
        for (int i=0; i<map.getRow();i++){
            for (int j=0; j<map.getColumn(); j++){
                map.gpush(i, j, new Land(map, this, g2d,i,j));
            }
        }
        Integer money = new Integer(2000);
        player = new Player(map, this, g2d, 10,money);// maxslot = 10
        Point2D pos = player.getCreature().position();
        map.push(pos.X(), pos.Y(), player);
        Store st = new Store(map, this, g2d);
        map.push(st.getEntity().position().IntX(), st.getEntity().position().IntY(), st);
        Dwarf dw = new Dwarf(map, this, g2d, 2, money);
        map.push(dw.position().IntX(), dw.position().IntY(), dw);
        dw = new Dwarf(map, this, g2d, 1, money);
        map.push(dw.position().IntX(), dw.position().IntY(), dw);
        dw = new Dwarf(map, this, g2d, 3, money);
        map.push(dw.position().IntX(), dw.position().IntY(), dw);
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
        Time.instance().update();
        time.setText(Time.instance().getTime());
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
        int gx = fx/80;
        int gy = fy/80;
//        System.out.printf("%d(%d) %d(%d) | %d(%f) %d(%f)",gx/80,gx, gy/80,gy,(int)player.position().X()/80,player.position().X(),(int)player.position().Y()/80,player.position().Y());
        int clicked = e.getButton();
        switch(clicked){    
            case MouseEvent.BUTTON1:
                if (map.getTop(gx, gy) != null ) {
                    if (map.getTop(gx, gy) instanceof Selectable) {
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
                    if (map.getTop(gx, gy) instanceof Actionable){
                        actionated = (Actionable) map.getTop(gx, gy);
                        popup = actionated.getMenu(selected);
                    } else {
                        popup = new JPopupMenu();
                        JMenuItem menu = new JMenuItem("no available action");
                        menu.setEnabled(false);
                        popup.add(menu);
                    }
                    
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
                int ULx =10,ULy=10, LRx=-10+FarmState.SCREENWIDTH-map.getRow()*80,
                        LRy = FarmState.SCREENHEIGHT-(map.getColumn()*80+150);
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
}
   