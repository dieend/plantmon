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
        

        map.gpush(5,1, new Portal(map, this, g2d, 5, 1));
        map.gpush(6,4, new Portal(map, this, g2d, 6, 4));
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
                System.out.format("aduh %d %d\n",e.getX(),e.getY());
                if (map.getTop(gx, gy) != null ) {
                    if (gx == 6 && gy == 4) {
                        Game.instance().goTo(ParentState.FARMSTATE,new Object[0]);
                        selectsomething = true;
                    } else if (gx == 5 && gy == 1) {
                        Game.instance().goTo(ParentState.SELECTPULMOSIS,new Object[0]);
                        selectsomething = true;
                    } else {
                        selectsomething = true;
                    }
                } else selectsomething = false;
                break;
           case MouseEvent.BUTTON3:
                System.out.format("aduh %d %d\n",e.getX(),e.getY());
                if (map.getTop(gx, gy) != null ) {
                    if (gx == 6 && gy == 4) {
                        Game.instance().goTo(ParentState.FARMSTATE,new Object[0]);
                        selectsomething = true;
                    } else if (gx == 5 && gy == 1) {
                        Game.instance().goTo(ParentState.BATTLEGURUN,new Object[0]);
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
