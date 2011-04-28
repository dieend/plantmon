package plantmon.entity.movingObject;

import java.awt.Font;
import java.awt.Graphics2D;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import plantmon.game.GridMap;
import plantmon.game.TalkPanel;
import plantmon.states.Game;
import plantmon.states.ParentState;
import plantmon.system.Actionable;
import plantmon.system.Cancellable;
import plantmon.system.Jobable;
import plantmon.system.RunnableListener;
import plantmon.system.Selectable;

public class PulmosisLand extends PulmosisBattle implements Cancellable,
                                                    Selectable,Actionable {
    private int sumWater;
    private boolean watered;
    private int typePul;
    transient private JPanel pan;
    
    public PulmosisLand (GridMap map, JPanel panel, Graphics2D g2d, int type) {
        super(map,panel,g2d,type,false,0);
        this.pan = panel;
        this.typePul = type;
        if (type == PulmosisBattle.Kentang) {
            sumWater  = 0;
            watered = false;
        }
    }
    @Override public void drawArea(){}

    @Override
    public void reinit (GridMap map, Graphics2D g2d, JPanel panel) {
        super.map = map;
        super.setGraphic(g2d);
        super.setPanel(panel);
        this.pan = panel;
    }
    
    public JPopupMenu getMenu(Selectable selected){
        if (selected instanceof Player){
            final Player player = (Player) selected;
            JPopupMenu menu = new JPopupMenu();
            JMenuItem item;
            item = new JMenuItem("talk");
            item.addActionListener(new Talk(selected));
            menu.add(item);
            if (getTypePul() == 2) {
                if (!watered && !isFullWatered()) {
                    JMenuItem item4;
                    item4 = new JMenuItem("water");
                    item4.addActionListener(new Water(selected));
                    menu.add(item4);
                }
            }
            menu.pack();
            return menu;
        }
        return null;
    }

    public int getTypePul() {
        return typePul;
    }

    public void setTypePul(int typePul) {
        this.typePul = typePul;
    }

    class Water extends RunnableListener {
        public Water(Selectable selected){
            super(selected);
        }
        public void run() {
            Jobable player = (Jobable) selected;
            int gx = (int)PulmosisLand.this.position().IntX();
            int gy = (int)PulmosisLand.this.position().IntY();
            Object lock = new Object();
            Boolean[] cancel = new Boolean[1];
            player.move(gx, gy, lock,cancel);
            synchronized(lock){
                try {
                    lock.wait();
                } catch (InterruptedException e){
                    return;
                }
            }
            if (!cancel[0]){
                map.popCancel(gx, gy);
                sumWater += 1;
                watered = true;
            }
        }
    }

    class Talk extends RunnableListener {
        public Talk(Selectable selected){
            super(selected);
        }
        public void run() {
            Jobable player = (Jobable) selected;
            int gx = (int)PulmosisLand.this.position().IntX();
            int gy = (int)PulmosisLand.this.position().IntY();
            Object lock = new Object();
            Boolean[] cancel = new Boolean[1];
            player.move(gx, gy, lock,cancel);
            synchronized(lock){
                try {
                    lock.wait();
                } catch (InterruptedException e){
                    return;
                }
            }
            if (!cancel[0]){
                map.popCancel(gx, gy);
            }
            if (tipe == PulmosisBattle.Labu) {
                Game.instance().seek(ParentState.LABUSTATE,null);
            } else if (tipe == PulmosisBattle.Lobak) {
                JLabel label1;
                JLabel label2;
                JLabel label3;
                JPanel panelis;
                panelis = new TalkPanel();
                panelis.setLayout(null);
                label1 = new JLabel();
                label1.setBounds(0,0,100,100);
                panelis.add(label1);
                label2 = new JLabel("Nama : " + Game.instance().getName());
                label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
                label2.setBounds(400,7,150,30);
                panelis.add(label2);
                label3 = new JLabel ("Hello Guys... My name is Corno..");
                label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
                label3.setBounds(101,0,450,90);
                panelis.add(label3);
                label3 = new JLabel ("I think I have missed to coming to this land.");
                label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
                label3.setBounds(101,16,450,90);
                panelis.add(label3);
                label3 = new JLabel ("But I will do my hard to helping you");
                label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
                label3.setBounds(101,32,450,90);
                panelis.add(label3);

                Game.instance().setDialogBox(panelis, pan);
            } else if (tipe == PulmosisBattle.Timun) {
                JLabel label1;
                JLabel label2;
                JLabel label3;
                JPanel panelis;
                panelis = new TalkPanel();
                panelis.setLayout(null);
                label1 = new JLabel();
                label1.setBounds(0,0,100,100);
                panelis.add(label1);
                label2 = new JLabel("Nama : " + Game.instance().getName());
                label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
                label2.setBounds(400,7,150,30);
                panelis.add(label2);
                label3 = new JLabel ("Hello Guys... My name is Corno..");
                label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
                label3.setBounds(101,0,450,90);
                panelis.add(label3);
                label3 = new JLabel ("I think I have missed to coming to this land.");
                label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
                label3.setBounds(101,16,450,90);
                panelis.add(label3);
                label3 = new JLabel ("But I will do my hard to helping you");
                label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
                label3.setBounds(101,32,450,90);
                panelis.add(label3);

                Game.instance().setDialogBox(panelis, pan);
            }
        }
    }

    public boolean isFullWatered () {
        if (sumWater >= 7) {
            return true;
        } else {
            return false;
        }
    }

    public void setWatered (boolean water) {
        this.watered = water;
    }

}
