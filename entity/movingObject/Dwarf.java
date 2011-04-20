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
import plantmon.entity.unmoveable.Plant;
import plantmon.game.GridMap;
import plantmon.game.Point2D;
import plantmon.system.Cancellable;
//import plantmon.system.Jobable;
import plantmon.system.Utilities;

public class Dwarf extends MovingObject implements Actionable,
                                                    Selectable,
                                                    Runnable,/*Jobable,*/Cancellable{
    //status dwarf, 0 untuk sleep, 1 untuk wake_up
    Integer moneyharvest;
    private static Point2D defpos;
    private int status = 0;
    private final int sleep=0;
    private final int wake_up=1;
    private String name;

    //type dwarf, 1 untuk water,0 untuk 
    

    
    
    public Dwarf(GridMap map, JPanel panel, Graphics2D g2d,int t,Integer money){
        super(map,panel,g2d);
        System.out.println(t);
        type=t;
        status=sleep;
        if (t==1)
        {
            name="justice";
            //set default position
            defpos = new Point2D(Utilities.GRIDSIZE,4*Utilities.GRIDSIZE);
        }
        else if (t==2)
        {
            name="freedom";
            defpos = new Point2D(Utilities.GRIDSIZE,5*Utilities.GRIDSIZE);
            moneyharvest=money;
        }
        else if (t==3)
        {
            name="destiny";
            defpos = new Point2D(Utilities.GRIDSIZE,6*Utilities.GRIDSIZE);
        }
        creature.setPosition(defpos);
        System.out.println("picture/dwarf"+type+"0");
        init();
    }
    @Override public void drawBounds() {
        creature.drawBounds(Color.GREEN);
    }
    @Override public JPanel get_Info() {
        return null;
    }
    @Override protected void init() {
        //inisiasi semua variable disini.
        creature.setPosition(defpos);
        creature.setFinalPosition(defpos.IntX(),defpos.IntY());
        creature.setVelocity(new Point2D(0,0));
        creature.setFrameDelay(3);
    }
    @Override
    public void reinit(GridMap map,Graphics2D g2d, JPanel panel){
        super.reinit(map,g2d, panel);
        if (creature.panel()!=null){
            creature.load("picture/dwarf"+type+"0.png",4,1,32,32);
            creature.setImageName("picture/dwarf"+type);
        }
    }

    public void dojob()
    {
        if (type==1)
        {
           Point2D where = route.get(0);
           ((Plant)(map.getTop(where.IntX(), where.IntY()))).doWater(this);
        }
        else if (type==2)
        {
            Point2D where = route.get(0);
            ((Plant)(map.getTop(where.IntX()*Utilities.GRIDSIZE, where.IntY()*Utilities.GRIDSIZE))).doHarvest(this);
        }
        else if (type==3)
        {
            Point2D where = route.get(0);
            ((Plant)(map.getTop(where.IntX()*Utilities.GRIDSIZE, where.IntY()))).doSlash(this);
        }
    }
    @Override public void run(){
        while (status==wake_up)
        {
            route=getRoute(creature.position().IntX(), creature.position().IntY(), type);
            if (!route.isEmpty())
            {
                System.out.println("HIIIIIIIIIIIIIIIIIH");
                if (route.size()==1)
                {
                    dojob();
                }
                else 
                {
                    Boolean[] cancel = new Boolean[1];
                    Object key = new Object();
                    move(route.get(route.size()-1).IntX()*Utilities.GRIDSIZE, route.get(route.size()-1).IntY()*Utilities.GRIDSIZE, key, cancel);
                    synchronized(key){
                        try {
                            key.wait(); // tunggu player sampai ke posisi tumbuhan
                        } catch (InterruptedException e){}
                    }
//                    System.out.println(route.size());
//                    if (!route.isEmpty())
//                    creature.setFinalPosition(route.get(0).IntX()*Utilities.GRIDSIZE,route.get(0).IntY()*Utilities.GRIDSIZE);
                }
            }
            else
            {
                System.out.println("HAHAHAHHAHAHAHAH"+creature.position().IntX()+creature.position().IntY());
                //creature.setFinalPosition(30,30);
                creature.setFinalPosition(creature.position().IntX(), creature.position().IntY());
            }
        }
    }

    public void move(int gx,int gy,Object lock,Boolean[] cancel){
        addAction(lock,new Point2D(gx,gy));
        /*Canceller ca = new Canceller(creature.panel(),creature.graphics(),
                                    gx, gy, cancel,lock,(Cancellable)this,numAction-1);
        map.push(gx, gy, ca);*/
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
                    (new Thread (Dwarf.this)).start();
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

    public void cancel(Object lock) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}