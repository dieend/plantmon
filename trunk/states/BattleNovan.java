package plantmon.states;
import javax.swing.JPopupMenu;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.*;
import java.net.URL;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import plantmon.entity.Time;
import plantmon.entity.movingObject.PulmosisBattle;
import plantmon.entity.unmoveable.BattleLand;
import plantmon.entity.unmoveable.Land;
import plantmon.game.GridMap;
import plantmon.game.ImageEntity;
import plantmon.game.Point2D;
import plantmon.system.Actionable;
import plantmon.system.Utilities;


public class BattleNovan extends ParentState implements MouseListener,MouseMotionListener {
    Thread gameloop;
    GridMap map;
    JPopupMenu popup;
    public static JTextArea text;
    int startx;
    int starty;
    JTextArea time;
    PulmosisBattle selected;
    boolean selectsomething;
    Actionable actionated;
    int clickx,clicky,defx,defy;
    boolean dragged;
    Object[] pulmos;
    int counta;
    public BattleNovan(int gridRow, int gridColumn, Object[] args){
        super(gridRow, gridColumn);
        this.pulmos = args;
        ID = BATTLEGURUN;
        init();
        time = new JTextArea();
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setPreferredSize(new Dimension(640, 480));
        setLayout(null);
//        add(new Component() {});
//        add(new Component() {});
//        add(new Component() {});
//        add(new Component() {});
        //add(pane);
        addMouseMotionListener(this);
        active = true;
        counta = 10;
    }
    private URL getURL(String filename) {
        URL url = null;
        try {
            url = this.getClass().getResource(filename);
        }
        catch (Exception e) { }
        return url;
    }

    protected void init() {
        startx=0; starty=0;
        int x = 16; int y = 16;
        map = new GridMap(x,y);
        backbuffer = new BufferedImage(x*Utilities.GRIDSIZE, y*Utilities.GRIDSIZE, BufferedImage.TYPE_INT_ARGB);
        g2d = backbuffer.createGraphics();
        background = new ImageEntity(this);
        background.load("picture/Gurun Island.png");
        Random rand = new Random();
        int z;

        if (pulmos != null) {
            for (int i=0; i<map.getRow();i++){
                for (int j=0; j<map.getColumn(); j++){
                    map.gpush(i, j, new Land(map, this, g2d,i,j));
                }
            }
            PulmosisBattle player1 = new PulmosisBattle(map,this, g2d,((PulmosisBattle) pulmos[0]).getTipe(),false,0);
            player1.getCreature().setPosition(new Point2D(Utilities.GRIDSIZE + Utilities.GRIDGALAT,Utilities.GRIDSIZE + Utilities.GRIDGALAT));
            player1.getCreature().setFinalPosition(Utilities.GRIDSIZE + Utilities.GRIDGALAT,Utilities.GRIDSIZE + Utilities.GRIDGALAT);
            player1.reinit(map, g2d, this);
            Point2D pos = player1.getCreature().position();
            //map.gpush(1, 1, new Pulmosis(map,this,g2d,1,false));
            map.push(pos.X(), pos.Y(), player1);

            z = rand.nextInt(6) + 1;
            z = -1*z;
            PulmosisBattle player2 = new PulmosisBattle(map,this, g2d,-6,true,player1.level);
            player2.getCreature().setPosition(new Point2D(9*Utilities.GRIDSIZE + Utilities.GRIDGALAT,9*Utilities.GRIDSIZE + Utilities.GRIDGALAT));
            player2.getCreature().setFinalPosition(9*Utilities.GRIDSIZE + Utilities.GRIDGALAT,9*Utilities.GRIDSIZE + Utilities.GRIDGALAT);

            pos = player2.getCreature().position();
            //map.gpush(2, 2, new Pulmosis(map,this,g2d,0,true));
            map.push(pos.X(), pos.Y(), player2);

            PulmosisBattle player3 = new PulmosisBattle(map,this, g2d,((PulmosisBattle) pulmos[1]).getTipe(),false,0);
            player3.getCreature().setPosition(new Point2D(2*Utilities.GRIDSIZE + Utilities.GRIDGALAT,2*Utilities.GRIDSIZE + Utilities.GRIDGALAT));
            player3.getCreature().setFinalPosition(2*Utilities.GRIDSIZE + Utilities.GRIDGALAT,2*Utilities.GRIDSIZE + Utilities.GRIDGALAT);
            pos=player3.getCreature().position();
            map.push(pos.X(), pos.Y(), player3);

            PulmosisBattle player4 = new PulmosisBattle(map,this, g2d,((PulmosisBattle) pulmos[2]).getTipe(),false,0);
            player4.getCreature().setPosition(new Point2D(1*Utilities.GRIDSIZE + Utilities.GRIDGALAT,2*Utilities.GRIDSIZE + Utilities.GRIDGALAT));
            player4.getCreature().setFinalPosition(1*Utilities.GRIDSIZE + Utilities.GRIDGALAT,2*Utilities.GRIDSIZE + Utilities.GRIDGALAT);
            pos=player4.getCreature().position();
            map.push(pos.X(), pos.Y(), player4);

            z = rand.nextInt(6) + 1;
            z = -1*z;
            PulmosisBattle player5 = new PulmosisBattle(map,this, g2d,-8,true,player3.level);
            player5.getCreature().setPosition(new Point2D(9*Utilities.GRIDSIZE + Utilities.GRIDGALAT,10*Utilities.GRIDSIZE + Utilities.GRIDGALAT));
            player5.getCreature().setFinalPosition(9*Utilities.GRIDSIZE + Utilities.GRIDGALAT,10*Utilities.GRIDSIZE + Utilities.GRIDGALAT);

            pos = player5.getCreature().position();
            //map.gpush(2, 2, new Pulmosis(map,this,g2d,0,true));
            map.push(pos.X(), pos.Y(), player5);

            z = rand.nextInt(6) + 1;
            z = -1*z;
            PulmosisBattle player6 = new PulmosisBattle(map,this, g2d,-10,true,player4.level);
            player6.getCreature().setPosition(new Point2D(10*Utilities.GRIDSIZE + Utilities.GRIDGALAT,10*Utilities.GRIDSIZE + Utilities.GRIDGALAT));
            player6.getCreature().setFinalPosition(10*Utilities.GRIDSIZE + Utilities.GRIDGALAT,10*Utilities.GRIDSIZE + Utilities.GRIDGALAT);

            pos = player6.getCreature().position();
            //map.gpush(2, 2, new Pulmosis(map,this,g2d,0,true));
            map.push(pos.X(), pos.Y(), player6);

            addMouseListener(this);
        }

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
        if ((selected!=null) && !selected.isActive()){
            selectsomething = false;
        }
        boolean found = true;
        boolean founden = true;
        int i = 0;
        int j = 0;
        Time.instance().update();
        time.setText(Time.instance().getTime());
        Object lock = new String("stop");
        Boolean[] cancel = new Boolean[1];
        cancel[0] = true;

        for (i = 0;i < map.getRow() && (found || founden); i++) {
            for (j = 0; j < map.getColumn() && (found || founden); j++) {
                if (map.getTop(i,j) instanceof PulmosisBattle) {
                    PulmosisBattle pul = (PulmosisBattle) map.getTop(i,j);
                    if (pul.getStatusEnemy()) {
                        found = false;
                        if(pul.isActive()) {
//                            pul.move((i+1)*Utilities.GRIDSIZE, j*Utilities.GRIDSIZE, lock,cancel);
//                            pul.resetChargeMeter();
                            pul.nextMove();
                            pul.resetChargeMeter();
                        }
                        break;
                    }else{
                        founden=false;
                    }
                }
            }
        }

        if (found) {
            if (counta > 0) {
                i--;
            } else {
                Game.instance().goTo(ParentState.MAPSTATE,new Object[0]);
            }
        }
        if (founden){
            Game.instance().goTo(ParentState.GAMEOVER,new Object[0]);
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
                    if (map.getTop(gx, gy) instanceof PulmosisBattle) {
                        //actionated = (Actionable) map.getTop(gx, gy);
                        selected = (PulmosisBattle) map.getTop(gx, gy);
                        if (selected.isActive()){
                            selectsomething = true;
                        }
                    } else {
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
                    if (gx < map.getRow() && gy < map.getColumn() && popup != null) {
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Game.instance().setFrame(frame);
        Game.instance().goTo(ParentState.BATTLEGURUN, args);
    }
}
