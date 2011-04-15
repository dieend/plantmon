/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plantmon.states;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import plantmon.entity.Canceller;
import plantmon.entity.movingObject.Player;
import plantmon.entity.unmoveable.Road;
import plantmon.game.GridMap;
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
    public HomeState(Object[] args){
        super(6,6);
        ID = HOME;
        map = new GridMap(6, 6);
        for (int i=0; i<6; i++){
            for (int j=0 ;j<6; j++){
                map.gpush( i, j, new Road(map, this, g2d, i, j));
            }
        }
        Player player =  new Player(map, this, g2d);
        player.getCreature().setPosition(new Point2D(5*Utilities.GRIDSIZE,5*Utilities.GRIDSIZE));
        player.getCreature().setFinalPosition(5*Utilities.GRIDSIZE,5*Utilities.GRIDSIZE);
        map.gpush(5, 5,player);
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
            repaint();
        }

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
}
