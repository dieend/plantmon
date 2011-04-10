package plantmon.entity.movingObject;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import plantmon.system.Actionable;
import plantmon.system.Selectable;
import plantmon.entity.*;
import plantmon.game.GridMap;
import plantmon.game.Point2D;

public class Dwarf extends MovingObject implements Actionable,
                                                    Selectable,
                                                    Runnable{
    //status dwarf, 0 untuk sleep, 1 untuk wake_up
    private static Point2D defpos;
    private int status = 0;
    private final int sleep=0;
    private final int wake_up=1;
    private String name;
    private int type;
    
    public Dwarf(GridMap map, JPanel panel, Graphics2D g2d,int t){
        super(map,panel,g2d);
        type=t;
        if (t==1)
        {
            name="justice";
            //set default position
            defpos = new Point2D(80,160);
        }
        else if (t==2)
        {
            name="freedom";
            defpos = new Point2D(80,240);
        }
        else if (t==3)
        {
            name="destiny";
            defpos = new Point2D(80,320);
        }
    }
    @Override public void drawBounds() {
        creature.drawBounds(Color.GREEN);
    }
    @Override public String getInfo() {
        return name;
    }
    @Override protected void init() {
        //inisiasi semua variable disini.
        creature.load("picture/dwarf"+type,4,1,32,32);
        creature.setPosition(defpos);
        creature.setFinalPosition(80, 80);
        creature.setVelocity(new Point2D(0,0));
        creature.setFrameDelay(3);
    }
    @Override public void run(){
        
    }

    ActionListener setstatus()
    {
        ActionListener sl = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if ("sleep".equals(e.getActionCommand()))
                {
                    status=sleep;
                }
                else if ("wake_up".equals(e.getActionCommand()))
                {
                    status=wake_up;
                }
                else
                    throw new UnsupportedOperationException("Not supported yet.");
            }
        };
        return sl;
    }
    
    @Override public JPopupMenu getMenu(Selectable selected){
        if (selected instanceof Player)
        {
            final Player player = (Player) selected;
            JPopupMenu menu = new JPopupMenu();
            JMenuItem item;
            item = new JMenuItem("wake_up");
            item.addActionListener(setstatus());
            menu.add(item);
            
            JMenuItem item2;
            item2 = new JMenuItem("sleep");
            item2.addActionListener(setstatus());
            menu.add(item2);
            menu.pack();
            return menu;
        }
        return null;
    }
}