/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plantmon.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import plantmon.entity.Canceller;
import plantmon.entity.Time;
import plantmon.entity.deadItem.Portal;
import plantmon.entity.deadItem.Store;
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
public class StoreState extends ParentState implements MouseListener{
    Selectable selected;
    boolean selectsomething;
    GridMap map;
    JTextArea time;
    public StoreState(Object[] args){
        super(6,6);
        time = new JTextArea();
        ID = HOME;
        map = new GridMap(6, 6);
        for (int i=0; i<6; i++){
            for (int j=0 ;j<6; j++){
                map.gpush( i, j, new Road(map, this, g2d, i, j));
            }
        }
        background = new ImageEntity(this);
        background.load("picture/bg2.png");
        map.gpush(4,4, new Portal(map, this, g2d, 4, 4));
        Player player =  new Player(map, this, g2d);
        player.getCreature().setPosition(new Point2D(5*Utilities.GRIDSIZE,5*Utilities.GRIDSIZE));
        player.getCreature().setFinalPosition(5*Utilities.GRIDSIZE,5*Utilities.GRIDSIZE);
        map.gpush(5, 5,player);
        addMouseListener(this);
        time.setEditable(false);
        time.setBounds(0, 0, 200, 50);
        time.setBackground(Color.ORANGE);
        time.setForeground(Color.GRAY);
        add(time);
        map.gpush(2, 2, new Portal(map, this, g2d, 2, 2));
        Store stitem = new Store(map, this, g2d, 1);
        stitem.getEntity().setPosition(new Point2D(5*Utilities.GRIDSIZE, 1*Utilities.GRIDSIZE));
        stitem.getEntity().setFinalPosition(5*Utilities.GRIDSIZE, 1*Utilities.GRIDSIZE);
        map.push(stitem.getEntity().position().IntX(), stitem.getEntity().position().IntY(), stitem);
        Store stwaritem = new Store(map, this, g2d, 2);
        stwaritem.getEntity().setPosition(new Point2D(5*Utilities.GRIDSIZE, 2*Utilities.GRIDSIZE));
        stwaritem.getEntity().setFinalPosition(5*Utilities.GRIDSIZE, 1*Utilities.GRIDSIZE);
        map.push(stwaritem.getEntity().position().IntX(), stwaritem.getEntity().position().IntY(), stwaritem);
        Store starmoritem = new Store(map, this, g2d, 3);
        starmoritem.getEntity().setPosition(new Point2D(5*Utilities.GRIDSIZE, 3*Utilities.GRIDSIZE));
        starmoritem.getEntity().setFinalPosition(5*Utilities.GRIDSIZE, 1*Utilities.GRIDSIZE);
        map.push(starmoritem.getEntity().position().IntX(), starmoritem.getEntity().position().IntY(), starmoritem);
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
        final MouseEvent tmp = e;
        int fx = e.getX();
        int fy = e.getY();
        int gx = fx/Utilities.GRIDSIZE;
        int gy = fy/Utilities.GRIDSIZE;
        JPopupMenu popup;
        switch(clicked){
            case MouseEvent.BUTTON1:
                System.out.format("aduh %d %d\n",e.getX(),e.getY());
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
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public static void main(String[] args){
        JFrame mainFrame = new JFrame();
        mainFrame.setSize(640, 480);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        Game.instance().setFrame(mainFrame);
        Game.instance().goTo(ParentState.STORE, null);
    }
}