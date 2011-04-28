package plantmon.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import plantmon.entity.Time;
import plantmon.entity.deadItem.Portal;
import plantmon.game.GridMap;
import plantmon.game.ImageEntity;
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
    Image a = null;
    public MapState(Object[] args){
        super(17,13);
        time = new JTextArea();
        ID = MAPSTATE;
        map = new GridMap(17, 13);
        background = new ImageEntity(this);
        background.load("picture/worldmap.png");
        
        if (Game.instance().getStory().isDoneKentang() && Game.instance().getStory().getDay() <= 30 && Game.instance().getStory().getSeason() == Time.SPRING) {
            map.gpush(5,1, new Portal(map, this, g2d, 5, 1));
        }
        if (Game.instance().getStory().isDoneNanas1() && Game.instance().getStory().getDay() < 21 && Game.instance().getStory().getSeason() == Time.SUMMER) {
            map.gpush(2,7, new Portal(map, this, g2d, 2, 7));
        }
        if (Game.instance().getStory().isDonePaprika() && Game.instance().getStory().getDay() < 30 && Game.instance().getStory().getSeason() == Time.FALL) {
            map.gpush(11,4, new Portal(map, this, g2d, 11, 4));
        }
        map.gpush(9, 7, new Portal(map, this, g2d, 9, 7));
        map.gpush(6, 4, new Portal(map, this, g2d, 6, 4));
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
        g2d.drawImage(a,80,80,80,80, this);
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
                if (map.getTop(gx, gy) != null ) {
                    if (gx == 6 && gy == 4) {
                        Game.instance().goTo(ParentState.FARMSTATE,new Object[0]);
                        selectsomething = true;
                    } else if (map.getTop(gx, gy) instanceof Portal && gx == 5 && gy == 1) {
                        Object[] as= new Integer[1];
                        as[0] = 0;
                        Game.instance().goTo(ParentState.SELECTPULMOSIS,as);
                        selectsomething = true;
                    } else if (map.getTop(gx, gy) instanceof Portal && gx == 2 && gy == 7) {
                        Object[] as= new Integer[1];
                        as[0] = 1;
                        Game.instance().goTo(ParentState.SELECTPULMOSIS,as);
                        selectsomething = true;
                    } else if (map.getTop(gx, gy) instanceof Portal && gx == 11 && gy == 4) {
                        Object[] as= new Integer[1];
                        as[0] = 2;
                        Game.instance().goTo(ParentState.SELECTPULMOSIS,as);
                        selectsomething = true;
                    } else if (map.getTop(gx, gy) instanceof Portal && gx == 9 && gy == 7) {
                        Object[] as= new Integer[1];
                        as[0] = 3;
                        Game.instance().goTo(ParentState.SELECTPULMOSIS,as);
                        selectsomething = true;
                    } else {
                        selectsomething = true;
                    }
                } else selectsomething = false;
                break;
           case MouseEvent.BUTTON3:
                if (map.getTop(gx, gy) != null ) {
                    if (gx == 6 && gy == 4) {
                        Game.instance().goTo(ParentState.FARMSTATE,new Object[0]);
                        selectsomething = true;
                    } else if (map.getTop(gx, gy) instanceof Portal && gx == 5 && gy == 1) {
                        Object[] as= new Integer[1];
                        as[0] = 0;
                        Game.instance().goTo(ParentState.SELECTPULMOSIS,as);
                        selectsomething = true;
                    } else if (map.getTop(gx, gy) instanceof Portal && gx == 2 && gy == 7) {
                        Object[] as= new Integer[1];
                        as[0] = 1;
                        Game.instance().goTo(ParentState.SELECTPULMOSIS,as);
                        selectsomething = true;
                    } else if (map.getTop(gx, gy) instanceof Portal && gx == 11 && gy == 4) {
                        Object[] as= new Integer[1];
                        as[0] = 2;
                        Game.instance().goTo(ParentState.SELECTPULMOSIS,as);
                        selectsomething = true;
                    } else if (map.getTop(gx, gy) instanceof Portal && gx == 9 && gy == 7) {
                        Object[] as= new Integer[1];
                        as[0] = 3;
                        Game.instance().goTo(ParentState.SELECTPULMOSIS,as);
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
    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setSize(640, 480);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Game.instance().setFrame(frame);
        Game.instance().goTo(ParentState.MAPSTATE, args);
    }
}
