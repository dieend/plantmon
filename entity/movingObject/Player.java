package plantmon.entity.movingObject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
//import javax.swing.FarmState;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import plantmon.system.Actionable;
import plantmon.system.Selectable;
import plantmon.entity.*;
import plantmon.game.GridMap;
import plantmon.game.Point2D;
import plantmon.game.TalkPanel;
import plantmon.states.ParentState;
import plantmon.states.Game;
import plantmon.system.Cancellable;
import plantmon.system.Jobable;


public class Player extends MovingObject implements Actionable, Cancellable,
                                                    Selectable,Jobable{
    private int work;

    //bisa ngapain aja
    public Player(GridMap map, JPanel panel, Graphics2D g2d){
        super(map,panel,g2d);
        init();
        work=3;
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
        image1 = new ImageIcon(this.getClass().getResource("icon player.png"));
        label1 = new JLabel(image1);
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
        label1.setBounds(0,0,100,100);
        panel.add(label1);
        label2 = new JLabel("Nama : " + Game.instance().getName());
        label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label2.setBounds(400,7,150,30);
        panel.add(label2);
        label3 = new JLabel("Uang : "+ Game.instance().getMoney());
        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label3.setBounds(400,25,150,30);
        panel.add(label3);
        
        return panel;
    }

    @Override public JPopupMenu getMenu(Selectable selected){
        if (selected instanceof Player){
            final Player player = (Player) selected;
            JPopupMenu menu = new JPopupMenu();
            JMenuItem ite;
            ite = new JMenuItem("inventory");
            ite.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Object[] args = new Object[1];
                    args[0] = Player.this;
                    Game.instance().seek(ParentState.INVENTORY,args);
                }
            });
            menu.add(ite);
            menu.pack();
            return menu;
        }
        return null;
    }
    @Override protected void init() {
        //inisiasi semua variable disini.
        type = 0;
        load("picture/anim", 4,1,66,70);
        creature.setImageName("picture/anim");
        creature.setVelocity(new Point2D(0,0));
        creature.setFrameDelay(5);
    }
    public void move(int gx,int gy,Object lock,Boolean[] cancel){
        addAction(lock,new Point2D(gx,gy));
        Canceller ca = new Canceller(creature.panel(),creature.graphics(),
        gx, gy, cancel,lock,(Cancellable)this,numAction-1);
        map.push(gx, gy, ca);
    }
    
    @Override
    public void cancel(Object lock){
        map.popCancel(destination.get(lock).IntX(), destination.get(lock).IntY());
        destination.remove(lock);
        this.lock.remove(lock);
        this.numAction--;
        creature.setFinalPosition(this.position().IntX(),this.position().IntY());
        inAction = false;
    }

    public void eat(Item item){
        Inventory inventory =  new Inventory();
        if(item.getIDitem()==Item.BuKentang){
            inventory.delete(item,1);
            item.setEfek(Item.Normal);
        }
        if(item.getIDitem()==Item.BuLobak){
            inventory.delete(item,1);
            item.setEfek(Item.Poison);
        }
        if(item.getIDitem()==Item.BuTimun){
            inventory.delete(item,1);
            item.setEfek(Item.Paralyze);
        }
        if(item.getIDitem()==Item.BuKubis){
            inventory.delete(item,1);
            item.setEfek(Item.Dead);
        }
        if(item.getIDitem()==Item.BuKubis){
            inventory.delete(item,1);
            item.setEfek(Item.Normal);
        }
        if(item.getIDitem()==Item.BuJagung){
            inventory.delete(item,1);
            item.setEfek(Item.Paralyze);
        }
        if(item.getIDitem()==Item.BuTomat){
            inventory.delete(item,1);
            item.setEfek(Item.Poison);
        }
        if(item.getIDitem()==Item.BuBawang){
            inventory.delete(item,1);
            item.setEfek(Item.Normal);
        }
        if(item.getIDitem()==Item.BuNanas){
            inventory.delete(item,1);
            item.setEfek(Item.Normal);
        }
        if(item.getIDitem()==Item.BuWortel){
            inventory.delete(item,1);
            item.setEfek(Item.Normal);
        }
        if(item.getIDitem()==Item.BuTerong){
            inventory.delete(item,1);
            item.setEfek(Item.Normal);
        }
        if(item.getIDitem()==Item.BuUbi){
            inventory.delete(item,1);
            item.setEfek(Item.Normal);
        }
        if(item.getIDitem()==Item.BuPaprika){
            inventory.delete(item,1);
            item.setEfek(Item.Normal);
        }
        if(item.getIDitem()==Item.BuStroberi){
            inventory.delete(item,1);
            item.setEfek(Item.Normal);
        }
        if(item.getIDitem()==Item.BuLabu){
            inventory.delete(item,1);
            item.setEfek(Item.Normal);
        }
        if(item.getIDitem()==Item.BuBayam){
            inventory.delete(item,1);
            item.setEfek(Item.Normal);
        }
    }
        public static void main(String[] str){
        JFrame frame=new JFrame();
        BufferedImage bf = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Player a = new Player(new GridMap(), new JPanel(), bf.createGraphics());
        frame.getContentPane().add(a.get_Info());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }

    /**
     * @return the work
     */
    public int getWork() {
        return work;
    }

    /**
     * @param work the work to set
     */
    public void setWork(int work) {
        this.work = work;
        this.getCreature().setAnimated(true);

        this.getCreature().setFrameDelay(5);
        if (this.getWork() == 0) {
        this.getCreature().load("picture/cangkulanim"+this.getCreature().getFace()+".png",4,1,66,70);
        } else if (this.getWork() == 1) {
        this.getCreature().load("picture/siramanim"+this.getCreature().getFace()+".png",4,1,66,70);
        } if (this.getWork() == 2) {
        this.getCreature().load("picture/sabitanim"+this.getCreature().getFace()+".png",4,1,66,70);
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
        }


        this.getCreature().load("picture/anim"+this.getCreature().getFace()+".png",4,1,66,70);
        this.getCreature().setAnimated(false);
        this.work = 3;
    }
}