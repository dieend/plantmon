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
    Integer moneyharvest;
    private static Point2D defpos;
    private int status = 0;
    private final int sleep=0;
    private final int wake_up=1;
    private String name;

    //type dwarf, 1 untuk water,0 untuk 
    private int type;
    public static final int water=1;
    public static final int harvest=2;
    public static final int slash=3;
    
    public Dwarf(GridMap map, JPanel panel, Graphics2D g2d,int t,Integer money){
        super(map,panel,g2d);
        System.out.println(t);
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
            moneyharvest=money;
        }
        else if (t==3)
        {
            name="destiny";
            defpos = new Point2D(80,320);
        }
        System.out.println("picture/dwarf"+type+"0");
        init();
    }
    @Override public void drawBounds() {
        creature.drawBounds(Color.GREEN);
    }
    @Override public String getInfo() {
        return name;
    }
    @Override protected void init() {
        //inisiasi semua variable disini.
        creature.setPosition(defpos);
        creature.setFinalPosition(defpos.IntX(),defpos.IntY());
        creature.setVelocity(new Point2D(0,0));
        creature.setFrameDelay(3);
    }
    public void reinit(){
        if (creature.panel()!=null){
            System.out.print("reinit\n");
            creature.load("picture/dwarf"+type+"0.png",4,1,32,32);
            creature.setImageName("picture/dwarf"+type);
        }
    }
    @Override public void run(){
        
        while (status==wake_up)
        {
            route=getRoute(0, 0, type);
            
        }
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