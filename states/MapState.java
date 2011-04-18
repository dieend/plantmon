package plantmon.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
public class MapState extends ParentState implements MouseListener{
    Selectable selected;
    boolean selectsomething;
    GridMap map;
    JTextArea time;
    public MapState(Object[] args){
        super(6,6);
        time = new JTextArea();
        ID = MAPSTATE;
        map = new GridMap(6, 6);
        background = new ImageEntity(this);
        background.load("picture/bg2.png");
        map.gpush(4,4, new Portal(map, this, g2d, 4, 4));
        map.gpush(1,1, new Portal(map, this, g2d, 1, 1));
        addMouseListener(this);
        time.setEditable(false);
        time.setBounds(0, 0, 200, 50);
        time.setBackground(Color.ORANGE);
        time.setForeground(Color.GRAY);
        add(time);
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
            repaint();
            gameUpdate();
        }

    }
    public void gameUpdate() {
        time.setText(Time.instance().getTime()+"\n(PAUSED) ");
    }
    public void updated(){
        g2d.drawImage(background.getImage(), 0, 0,SCREENWIDTH-1,SCREENHEIGHT-1, this);
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
        int fx = e.getX();
        int fy = e.getY();
        int gx = fx/Utilities.GRIDSIZE;
        int gy = fy/Utilities.GRIDSIZE;
        switch(clicked){
            case MouseEvent.BUTTON1:
                System.out.format("aduh %d %d\n",e.getX(),e.getY());
                if (map.getTop(gx, gy) != null ) {
                    if (gx == 1 && gy == 1) {
                        Game.instance().goTo(ParentState.FARMSTATE,new Object[0]);
                        selectsomething = true;
                    } else if (gx == 4 && gy == 4) {
                        Game.instance().goTo(ParentState.BATTLESTATE,new Object[0]);
                        selectsomething = true;
                    } else {
                        selectsomething = true;
                    }
                } else selectsomething = false;
                break;
           case MouseEvent.BUTTON3:
                System.out.format("aduh %d %d\n",e.getX(),e.getY());
                if (map.getTop(gx, gy) != null ) {
                    if (gx == 1 && gy == 1) {
                        Game.instance().goTo(ParentState.FARMSTATE,new Object[0]);
                        selectsomething = true;
                    } else if (gx == 4 && gy == 4) {
                        Game.instance().goTo(ParentState.BATTLESTATE,new Object[0]);
                        selectsomething = true;
                    } else {
                        selectsomething = true;
                    }
                } else selectsomething = false;
                break;
            default: break;
        }
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
