package plantmon.entity.movingObject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import plantmon.system.Actionable;
import plantmon.system.Selectable;
import plantmon.entity.*;
import plantmon.entity.unmoveable.Plant;
import plantmon.game.GridMap;
import plantmon.game.Point2D;
import plantmon.game.TalkPanel;
import plantmon.states.Game;
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
    
    public String getName()
    {
        return name;
    }
    
    
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
            System.out.println("TYPE : " + type);
            System.out.println("Posisi X : " +defpos.IntX()/Utilities.GRIDSIZE);
            System.out.println("Posisi X : " +defpos.IntY()/Utilities.GRIDSIZE);
            System.out.println("TYPE : " + type + " -- " + defpos.IntX()/Utilities.GRIDSIZE + " -- " + defpos.IntY()/Utilities.GRIDSIZE);
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
        ImageIcon image1;
        JLabel label1;
        JLabel label2;
        JLabel label3;

        JPanel panel;
        panel = new TalkPanel();
        panel.setLayout(null);
        if(this.getID()==water){
        image1 = new ImageIcon(this.getClass().getResource("icon dwarf10.png"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label1 = new JLabel(image1);
        label1.setBounds(0,0,100,100);
        panel.add(label1);}
        else if(this.getID() == harvest){
        image1 = new ImageIcon(this.getClass().getResource("icon dwarf20.png"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label1 = new JLabel(image1);
        label1.setBounds(0,0,100,100);
        panel.add(label1);}
        else if(this.getID() == slash){
        image1 = new ImageIcon(this.getClass().getResource("icon dwarf30.png"));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label1 = new JLabel(image1);
        label1.setBounds(0,0,100,100);
        panel.add(label1);}
        
        label2 = new JLabel("Nama : " + this.getName());
        label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label2.setBounds(400,13,150,30);
        panel.add(label2);
        return panel;
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
            System.out.println("picture/dwarf"+((type>3)?type-3:type)+"0.png");
            creature.load("picture/dwarf"+((type>3)?type-3:type)+"0.png",4,1,32,32);
            creature.setImageName("picture/dwarf"+((type>3)?type-3:type));
        }
    }

    public void dojob()
    {
        if (type==1)
        {
           Point2D where = route.get(0);
           ((Plant)(map.getTop(where.IntX(), where.IntY()))).doWater();
        }
        else if (type==2)
        {
            Point2D where = route.get(0);
            ((Plant)(map.getTop(where.IntX(), where.IntY()))).doHarvest();
        }
        else if (type==3)
        {
            Point2D where = route.get(0);
            ((Plant)(map.getTop(where.IntX(), where.IntY()))).doSlash();
        }
    }
    @Override public void run(){
        while (status==wake_up)
        {
            update();
            route=getRoute(creature.position().IntX(), creature.position().IntY(), type);
            if (!route.isEmpty())
            {
//                System.out.println("HIIIIIIIIIIIIIIIIIH");
                if (route.size()==1)
                {
                    dojob();
                }
                else 
                {
                    Boolean[] cancel = new Boolean[1];
                    Object key = new Object();
                    move(route.get(0).IntX()*Utilities.GRIDSIZE, route.get(0).IntY()*Utilities.GRIDSIZE, key, cancel);
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
                if (type==1)
                    type=4;
                else if (type==2)
                    type=5;
                else if (type==3)
                    type=6;
//                System.out.println("HAHAHAHHAHAHAHAH"+creature.position().IntX()+creature.position().IntY());
                //creature.setFinalPosition(30,30);
                ArrayList<Point2D> backroute= new ArrayList<Point2D>();
//                System.out.println("TYPE KURCACI : " + type + " -- " + defpos.IntX()/Utilities.GRIDSIZE + " -- " + defpos.IntY()/Utilities.GRIDSIZE);
//                System.out.println("COORDINATE : " + defpos.IntX()/Utilities.GRIDSIZE+ "--" +defpos.IntY()/Utilities.GRIDSIZE);
                backroute = getRoute(defpos.IntX()/Utilities.GRIDSIZE,defpos.IntY()/Utilities.GRIDSIZE, type);
//                System.out.println("Ini rutenya :: ");
//                performarrlist(backroute);
                Boolean[] cancel = new Boolean[1];
                Object key = new String("exact");
                if (!backroute.isEmpty())
                {
                    move(backroute.get(0).IntX()*Utilities.GRIDSIZE,backroute.get(0).IntY()*Utilities.GRIDSIZE,key,cancel);
                    synchronized(key){
                        try{
                            key.wait();
                        }catch (InterruptedException e){}
                    }
                }
                if (type==4)
                    type=1;
                else if (type==5)
                    type=2;
                else if (type==6)
                    type=3;
                //creature.setFinalPosition(creature.position().IntX(), creature.position().IntY());
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
                else if ("wake_up".equals(e.getActionCommand()) && (status==sleep))
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
    public static void main(String[] str){
        JFrame frame=new JFrame();
        BufferedImage bf = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Dwarf a = new Dwarf(new GridMap(), new JPanel(),bf.createGraphics(),2, 200);
        frame.getContentPane().add(a.get_Info());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setVisible(true);
    }
}