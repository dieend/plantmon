/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plantmon.states;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import plantmon.entity.Canceller;
import plantmon.entity.Time;
import plantmon.entity.deadItem.Bed;
import plantmon.entity.deadItem.Portal;
import plantmon.entity.movingObject.Player;
import plantmon.entity.unmoveable.Road;
import plantmon.game.GridMap;
import plantmon.game.ImageEntity;
import plantmon.game.Point2D;
import plantmon.system.Actionable;
import plantmon.system.Selectable;
import plantmon.system.Utilities;

/**
 *
 * @author asus
 */
public class HomeState extends ParentState implements MouseListener{
    Selectable selected;
    boolean selectsomething;
    GridMap map;
    JTextArea time;
    Player player;
        int startx;
    int starty;
    int clickx,clicky,defx,defy;
    boolean dragged;

    public HomeState(Object[] args){
        super(13,10);
        int x = 13, y = 10;
        ID = HOME;
        map = new GridMap(x, y);
        startx = 0;
        starty = 0;
        backbuffer = new BufferedImage(640,480, BufferedImage.TYPE_INT_ARGB);
        g2d = backbuffer.createGraphics();
        background = new ImageEntity(this);
        background.load("picture/Rumah.png");
                       //0 1 2 3 4 5 6 7 8 9 10
        int[][] what = {{0,0,0,0,0,0,0,0,0,0,0},//0
                        {0,0,0,0,0,0,0,0,0,0,0},//1
                        {0,0,0,0,0,0,0,0,0,0,0},//2
                        {0,0,2,2,2,2,2,2,2,0,0},//3
                        {0,0,2,2,2,2,2,2,2,0,0},//4
                        {0,0,2,2,2,2,2,2,2,0,0},//5
                        {0,0,2,2,0,0,2,2,2,0,0},//6
                        {0,0,2,2,0,0,2,2,2,0,0},//7
                        {0,0,2,2,2,2,2,2,2,0,0},//8
                        {0,0,2,2,2,2,2,2,2,0,0},//9
                        {0,0,0,2,2,2,2,2,0,0,0},//10
                        {0,0,0,0,0,0,0,0,0,0,0},//11
                        {0,0,0,0,0,0,0,0,0,0,0}};//12
                        
        for (int i=0; i<map.getRow();i++){
            for (int j=0; j<map.getColumn(); j++){
                if (what[i][j] == 0){
                } else if (what[i][j] ==2){
                    Road r = new Road(map, this, g2d, i, j);
                    map.gpush(i, j, r);
                }
            }
        }
        map.gpush(8,8, new Portal(map, this, g2d, 8, 8));
        map.gpush(9,8, new Portal(map, this, g2d, 9, 8));
        player =  new Player(map, this, g2d);
        player.getCreature().setPosition(new Point2D(5*Utilities.GRIDSIZE,5*Utilities.GRIDSIZE));
        player.getCreature().setFinalPosition(5*Utilities.GRIDSIZE,5*Utilities.GRIDSIZE);
        map.gpush(5, 5,player);
        map.gpush(10, 2, new Bed(map, this, g2d, 10, 2));
        addMouseListener(this);
        
        time = new JTextArea();
        time.setEditable(false);
        time.setBounds(0, 0, 200, 50);
        time.setBackground(Color.ORANGE);
        time.setForeground(Color.GRAY);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setPreferredSize(new Dimension(640, 480));
        setLayout(null);
        selected = player;
        selectsomething = true;
        add(time);
    }
        @Override
    public void run(){
        active = true;
        while (active) {
            System.out.format("There are currenty %d Thread running\n",Thread.activeCount());
            try {
                Thread.sleep(20);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            repaint();
            gameUpdate();
//            System.out.print(""+player.position().IntX()/Utilities.GRIDSIZE+""+player.position().IntY()/Utilities.GRIDSIZE+"\n");
        }

    }
    public void gameUpdate() {
        time.setText(Time.instance().getTime()+"\n(PAUSED) ");
    }
    public void updated(){
        g2d.drawImage(background.getImage(), 0, 0, this);
        map.draw(0,0);
        if (selectsomething) {
            selected.drawBounds();
        }
    }
    @Override public void paintComponent(Graphics g) {
//        System.out.println("paintComponent - CobaOpeh");
        updated();
        g.drawImage(backbuffer,0, 0, this);
    }


    public void mouseClicked(MouseEvent e) {
        int clicked = e.getButton();
        final MouseEvent tmp = e;
        int fx = e.getX();
        int fy = e.getY();
        int gx = fx/Utilities.GRIDSIZE;
        int gy = fy/Utilities.GRIDSIZE;
        JPopupMenu popup;
        switch(clicked){    
            case MouseEvent.BUTTON1:
//                System.out.format("aduh %d %d\n",e.getX(),e.getY());
                if (map.getTop(gx, gy) != null ) {
                    if (map.getTop(gx, gy) instanceof Selectable) {
                        selected = (Selectable) map.getTop(gx, gy);
                        selectsomething = true;
                    } else if (map.getTop(gx, gy) instanceof Canceller){
                        popup = ((Canceller)map.getTop(gx,gy)).getMenu();
                        popup.show(tmp.getComponent(),tmp.getX(), tmp.getY());
                        selectsomething = false;
                    }
                } else selectsomething = false;
                break;
           case MouseEvent.BUTTON3:
                if (selectsomething){
                    // get object from where it's clicked from map
                    Actionable actionated;
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
                int ULx =10,ULy=10, LRx=-10+FarmState.SCREENWIDTH-map.getRow()*Utilities.GRIDSIZE,
                        LRy = FarmState.SCREENHEIGHT-(map.getColumn()*Utilities.GRIDSIZE+150);
                int newx = defx +((x-clickx));
                int newy = defy +((y-clicky));
                System.out.println("oooo" + newx+" "+ULx+" "+LRx);
                if ((ULx >= newx && newx >=LRx)&&(ULy >= newy && newy >=LRy)){
                    startx =newx;
                    starty =newy;
                }
            }
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
}
