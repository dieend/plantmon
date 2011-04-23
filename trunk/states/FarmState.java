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
import javax.swing.JTextArea;
import plantmon.entity.Canceller;
import plantmon.entity.Time;
import plantmon.entity.deadItem.Portal;
import plantmon.entity.movingObject.Dwarf;
import plantmon.entity.movingObject.Player;
import plantmon.entity.unmoveable.Land;
import plantmon.entity.unmoveable.Plant;
import plantmon.entity.unmoveable.Road;
import plantmon.entity.unmoveable.SellBox;
import plantmon.game.GridMap;
import plantmon.game.ImageEntity;
import plantmon.game.Point2D;
import plantmon.system.Actionable;
import plantmon.system.Selectable;
import plantmon.system.Utilities;


public class FarmState extends ParentState implements MouseListener,MouseMotionListener {
    Thread gameloop;
    GridMap map;
    JPopupMenu popup;
    int startx;
    int starty;
    Player player;
    JTextArea time;
    Selectable selected;
    boolean selectsomething;
    Actionable actionated;
    int clickx,clicky,defx,defy;
    boolean dragged;
    StoryLine story;
    public FarmState(int gridRow, int gridColumn){
        super(gridRow, gridColumn);
        map = new GridMap(gridRow,gridColumn);
        ID = FARMSTATE;
        init();
        time = new JTextArea();
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setPreferredSize(new Dimension(640, 480));
        setLayout(null);
        
//        add(new Component() {});
//        add(new Component() {});
//        add(new Component() {});
//        add(new Component() {});
        //test.setVisible(true);
        time.setEditable(false);
        time.setBounds(0, 0, 200, 50);
        time.setBackground(Color.ORANGE);
        time.setForeground(Color.black);
        add(Game.instance().dialogBox());
        Game.instance().setDialogBox(selected.get_Info(),this);
        Game.instance().dialogOn();
//        add(time);
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
        backbuffer = new BufferedImage(864,537, BufferedImage.TYPE_INT_ARGB);
        g2d = backbuffer.createGraphics();
        background = new ImageEntity(this);
        background.load("picture/Lahan.png");
                       //0 1 2 3 4 5 6 7 8 9 10
        int[][] what = {{0,0,0,0,0,0,0,0,0,0,0},//0
                        {0,0,0,2,2,2,2,0,0,0,0},//1
                        {0,0,0,2,2,2,2,0,0,0,0},//2
                        {0,0,0,2,2,2,2,2,2,2,0},//3
                        {0,0,0,2,2,2,2,2,2,2,0},//4
                        {0,2,2,2,2,2,2,2,2,2,0},//5
                        {0,2,2,2,2,2,2,2,2,2,0},//6
                        {0,2,2,2,2,2,2,2,2,2,0},//7
                        {0,1,1,1,1,1,1,1,1,1,0},//8
                        {0,1,1,1,1,1,1,1,1,1,0},//9
                        {0,1,1,1,1,1,1,1,1,1,0},//10
                        {0,1,1,1,1,1,1,1,1,1,0},//11
                        {0,1,1,1,1,1,1,1,1,1,0},//12
                        {0,1,1,1,1,1,1,1,1,1,0},//13
                        {0,1,1,1,1,1,1,1,1,1,0},//14
                        {0,1,1,1,1,1,1,1,1,1,0},//15
                        {0,1,1,1,1,1,1,1,1,1,0},//16
                        {0,0,0,0,0,0,0,0,0,0,0}};//17
        for (int i=0; i<map.getRow();i++){
            for (int j=0; j<map.getColumn(); j++){
                if (what[i][j] == 0){
                } else if (what[i][j] == 1){
                    Land l = new Land(map, this, g2d,i,j);
                    l.setStatus(Game.instance().farmstatus()[i][j]);
                    map.gpush(i, j, l);
                } else if (what[i][j] ==2){
                    Road r = new Road(map, this, g2d, i, j);
                    map.gpush(i, j, r);
                }
            }
        }
        for (Plant p:Game.instance().plants){
            map.push(p.getPosition().IntX(), p.getPosition().IntY(), p);
            p.reinit(map, this, g2d);
        }
        player = new Player(map, this, g2d);
        map.gpush(3, 2, new Portal(map, this, g2d, 3, 2));
        player.getCreature().setPosition(new Point2D(3*Utilities.GRIDSIZE,3*Utilities.GRIDSIZE));
        player.getCreature().setFinalPosition(3*Utilities.GRIDSIZE,3*Utilities.GRIDSIZE);
        Point2D pos = player.getCreature().position();
        map.push(pos.X(), pos.Y(), player);
        map.gpush(4, 2, new SellBox(map, this, g2d, 4, 2));
        for(Dwarf d:Game.instance().dwarfs){
            d.reinit(map,g2d,this);
            Point2D dlocation = d.getCreature().position();
            map.push(dlocation.IntX(),dlocation.IntY(), d);
        }
        selected = player;
        selectsomething = true;
        story = Game.instance().getStory();
        story.reinit(map,this,g2d);
        addMouseListener(this);
    }
    @Override
    public void run(){
        story.begin();
        System.out.print("creating story\n");
        active = true;
        while (active) {
//            System.out.format("There are currenty %d Thread running\n",Thread.activeCount());
            try {
                Thread.sleep(20);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            gameUpdate(
                    );
            repaint();
        }
        
    }
    public void gameUpdate() {
//        System.out.format("I wonder why won't work\n");
        Time.instance().update();
        time.setText(Time.instance().getTime());
        Point2D pos = player.getCreature().position();
//        System.out.print("Player position: "+pos.IntX()/Utilities.GRIDSIZE+" "+pos.IntY()/Utilities.GRIDSIZE+"\n");
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
        g2d.drawImage(background.getImage(), 0, 0, this);
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
                        selected = (Selectable) map.getTop(gx, gy);
                        Game.instance().setDialogBox(selected.get_Info(),this);
                        Game.instance().dialogOn();
                        selectsomething = true;
                    } else if (map.getTop(gx, gy) instanceof Canceller){
                        System.out.print("fjhfhgf");
                        popup = ((Canceller)map.getTop(gx,gy)).getMenu();
                        popup.show(tmp.getComponent(),tmp.getX(), tmp.getY());
                    } else {
                        selectsomething = false;
                        Game.instance().dialogOff();
                    }
                } else {
                    selectsomething = false;
                    Game.instance().dialogOff();
                }
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

        @Override public void turnOff () {
            story.turnOff();
            System.out.print("turn off famrstate");
            super.turnOff();
        }

        void updateDiff(MouseEvent e) {
            if (dragged){
                int x = e.getX();
                int y = e.getY();
                int ULx =10,ULy=10, LRx=-10+FarmState.SCREENWIDTH-map.getRow()*Utilities.GRIDSIZE,
                        LRy = FarmState.SCREENHEIGHT-(map.getColumn()*Utilities.GRIDSIZE+48);
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
   