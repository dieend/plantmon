package plantmon.entity.movingObject;

import java.awt.Font;
import java.awt.Graphics2D;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import plantmon.entity.Time;
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
        super.reinit(map, g2d, panel);
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
                if (!Game.instance().getStory().isLabuDone()) {
                    Game.instance().seek(ParentState.LABUSTATE,null);
                } else {
                    if (Game.instance().getStory().getDay() >= 15
                            && Game.instance().getStory().getDay() < 30
                            && Game.instance().getStory().getSeason()==Time.FALL
                            && !Game.instance().getStory().isDonePaprika()) {
                        JLabel label1;
                        JLabel label2;
                        JLabel label3;
                        JPanel panelis;
                        panelis = new TalkPanel();
                        panelis.setLayout(null);
                        label1 = new JLabel();
                        label1.setBounds(0,0,100,100);
                        panelis.add(label1);
                        label2 = new JLabel("Nama : Labulai");
                        label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
                        label2.setBounds(400,7,150,30);
                        panelis.add(label2);
                        label3 = new JLabel ("Dry!");
                        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
                        label3.setBounds(101,0,450,90);
                        panelis.add(label3);

                        Game.instance().setDialogBox(panelis, pan);
                    } else {
                        JLabel label1;
                        JLabel label2;
                        JLabel label3;
                        JPanel panelis;
                        panelis = new TalkPanel();
                        panelis.setLayout(null);
                        label1 = new JLabel();
                        label1.setBounds(0,0,100,100);
                        panelis.add(label1);
                        label2 = new JLabel("Nama : Labulai");
                        label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
                        label2.setBounds(400,7,150,30);
                        panelis.add(label2);
                        label3 = new JLabel ("Thanks, Sir");
                        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
                        label3.setBounds(101,0,450,90);
                        panelis.add(label3);

                        Game.instance().setDialogBox(panelis, pan);
                    }
                    
                }
            } else if (tipe == PulmosisBattle.Lobak) {
                if (Game.instance().getStory().getDay() >= 15
                            && Game.instance().getStory().getDay() < 30
                            && Game.instance().getStory().getSeason()==Time.FALL) {
                    JLabel label1;
                    JLabel label2;
                    JLabel label3;
                    JPanel panelis;
                    panelis = new TalkPanel();
                    panelis.setLayout(null);
                    label1 = new JLabel();
                    label1.setBounds(0,0,100,100);
                    panelis.add(label1);
                    label2 = new JLabel("Nama : Turunip");
                    label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label2.setBounds(400,7,150,30);
                    panelis.add(label2);
                    label3 = new JLabel ("Dry!");
                    label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label3.setBounds(101,0,450,90);
                    panelis.add(label3);
                    Game.instance().setDialogBox(panelis, pan);
                } else {
                    JLabel label1;
                    JLabel label2;
                    JLabel label3;
                    JPanel panelis;
                    panelis = new TalkPanel();
                    panelis.setLayout(null);
                    label1 = new JLabel();
                    label1.setBounds(0,0,100,100);
                    panelis.add(label1);
                    label2 = new JLabel("Nama : Turunip");
                    label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label2.setBounds(400,7,150,30);
                    panelis.add(label2);
                    label3 = new JLabel ("Good Day Sir!");
                    label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label3.setBounds(101,0,450,90);
                    panelis.add(label3);
                    Game.instance().setDialogBox(panelis, pan);
                }
            } else if (tipe == PulmosisBattle.Timun) {
                if (Game.instance().getStory().getDay() >= 15
                            && Game.instance().getStory().getDay() < 30
                            && Game.instance().getStory().getSeason()==Time.FALL) {
                    JLabel label1;
                    JLabel label2;
                    JLabel label3;
                    JPanel panelis;
                    panelis = new TalkPanel();
                    panelis.setLayout(null);
                    label1 = new JLabel();
                    label1.setBounds(0,0,100,100);
                    panelis.add(label1);
                    label2 = new JLabel("Nama : Timumun");
                    label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label2.setBounds(400,7,150,30);
                    panelis.add(label2);
                    label3 = new JLabel ("Oh, thirsty.");
                    label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label3.setBounds(101,0,450,90);
                    panelis.add(label3);
                    Game.instance().setDialogBox(panelis, pan);
                } else {
                    JLabel label1;
                    JLabel label2;
                    JLabel label3;
                    JPanel panelis;
                    panelis = new TalkPanel();
                    panelis.setLayout(null);
                    label1 = new JLabel();
                    label1.setBounds(0,0,100,100);
                    panelis.add(label1);
                    label2 = new JLabel("Nama : Timumun");
                    label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label2.setBounds(400,7,150,30);
                    panelis.add(label2);
                    label3 = new JLabel ("How are you today, my Lord?");
                    label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label3.setBounds(101,0,450,90);
                    panelis.add(label3);
                    Game.instance().setDialogBox(panelis, pan);
                }
            } else if (tipe == PulmosisBattle.Kentang) {
                if (!isFullWatered()) {
                    JLabel label1;
                    JLabel label2;
                    JLabel label3;
                    JPanel panelis;
                    panelis = new TalkPanel();
                    panelis.setLayout(null);
                    label1 = new JLabel();
                    label1.setBounds(0,0,100,100);
                    panelis.add(label1);
                    label2 = new JLabel("Nama : ?????");
                    label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label2.setBounds(400,7,150,30);
                    panelis.add(label2);
                    label3 = new JLabel ("Please water me...");
                    label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label3.setBounds(101,0,450,90);
                    panelis.add(label3);

                    Game.instance().setDialogBox(panelis, pan);
                } else {
                    if (Game.instance().getStory().getDay() >= 15
                            && Game.instance().getStory().getDay() < 30
                            && Game.instance().getStory().getSeason()==Time.FALL) {
                        JLabel label1;
                        JLabel label2;
                        JLabel label3;
                        JPanel panelis;
                        panelis = new TalkPanel();
                        panelis.setLayout(null);
                        label1 = new JLabel();
                        label1.setBounds(0,0,100,100);
                        panelis.add(label1);
                        label2 = new JLabel("Nama : Pottatoes");
                        label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
                        label2.setBounds(400,7,150,30);
                        panelis.add(label2);
                        label3 = new JLabel ("We need more water, Sir.");
                        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
                        label3.setBounds(101,0,450,90);
                        panelis.add(label3);

                        Game.instance().setDialogBox(panelis, pan);
                    } else {
                        JLabel label1;
                        JLabel label2;
                        JLabel label3;
                        JPanel panelis;
                        panelis = new TalkPanel();
                        panelis.setLayout(null);
                        label1 = new JLabel();
                        label1.setBounds(0,0,100,100);
                        panelis.add(label1);
                        label2 = new JLabel("Nama : Pottatoes");
                        label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
                        label2.setBounds(400,7,150,30);
                        panelis.add(label2);
                        label3 = new JLabel ("Sunshine");
                        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
                        label3.setBounds(101,0,450,90);
                        panelis.add(label3);

                        Game.instance().setDialogBox(panelis, pan);
                    }
                }
            } else if (tipe == PulmosisBattle.Kubis) {
                if (Game.instance().getStory().getDay() >= 15
                            && Game.instance().getStory().getDay() < 30
                            && Game.instance().getStory().getSeason()==Time.FALL) {
                    JLabel label1;
                    JLabel label2;
                    JLabel label3;
                    JPanel panelis;
                    panelis = new TalkPanel();
                    panelis.setLayout(null);
                    label1 = new JLabel();
                    label1.setBounds(0,0,100,100);
                    panelis.add(label1);
                    label2 = new JLabel("Nama : Kububis");
                    label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label2.setBounds(400,7,150,30);
                    panelis.add(label2);
                    label3 = new JLabel ("Water.. Water..");
                    label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label3.setBounds(101,0,450,90);
                    panelis.add(label3);

                    Game.instance().setDialogBox(panelis, pan);
                } else {
                    JLabel label1;
                    JLabel label2;
                    JLabel label3;
                    JPanel panelis;
                    panelis = new TalkPanel();
                    panelis.setLayout(null);
                    label1 = new JLabel();
                    label1.setBounds(0,0,100,100);
                    panelis.add(label1);
                    label2 = new JLabel("Nama : Kububis");
                    label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label2.setBounds(400,7,150,30);
                    panelis.add(label2);
                    label3 = new JLabel ("Nice Farm! I love living here");
                    label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label3.setBounds(101,0,450,90);
                    panelis.add(label3);

                    Game.instance().setDialogBox(panelis, pan);
                }
                
            } else if (tipe == PulmosisBattle.Stroberi) {
                if (Game.instance().getStory().getDay() >= 15
                            && Game.instance().getStory().getDay() < 30
                            && Game.instance().getStory().getSeason()==Time.FALL) {
                    JLabel label1;
                    JLabel label2;
                    JLabel label3;
                    JPanel panelis;
                    panelis = new TalkPanel();
                    panelis.setLayout(null);
                    label1 = new JLabel();
                    label1.setBounds(0,0,100,100);
                    panelis.add(label1);
                    label2 = new JLabel("Nama : Strotoburi");
                    label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label2.setBounds(400,7,150,30);
                    panelis.add(label2);
                    label3 = new JLabel ("Help me.. I need water");
                    label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label3.setBounds(101,0,450,90);
                    panelis.add(label3);

                    Game.instance().setDialogBox(panelis, pan);
                } else {
                    JLabel label1;
                    JLabel label2;
                    JLabel label3;
                    JPanel panelis;
                    panelis = new TalkPanel();
                    panelis.setLayout(null);
                    label1 = new JLabel();
                    label1.setBounds(0,0,100,100);
                    panelis.add(label1);
                    label2 = new JLabel("Nama : Strotoburi");
                    label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label2.setBounds(400,7,150,30);
                    panelis.add(label2);
                    label3 = new JLabel ("Happy Day as always!");
                    label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label3.setBounds(101,0,450,90);
                    panelis.add(label3);

                    Game.instance().setDialogBox(panelis, pan);
                }
                
            } else if (tipe == PulmosisBattle.Jagung) {
                if (Game.instance().getStory().getDay() >= 15
                            && Game.instance().getStory().getDay() < 30
                            && Game.instance().getStory().getSeason()==Time.FALL) {
                    JLabel label1;
                    JLabel label2;
                    JLabel label3;
                    JPanel panelis;
                    panelis = new TalkPanel();
                    panelis.setLayout(null);
                    label1 = new JLabel();
                    label1.setBounds(0,0,100,100);
                    panelis.add(label1);
                    label2 = new JLabel("Nama : Corno");
                    label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label2.setBounds(400,7,150,30);
                    panelis.add(label2);
                    label3 = new JLabel ("Help me.. I need water");
                    label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label3.setBounds(101,0,450,90);
                    panelis.add(label3);

                    Game.instance().setDialogBox(panelis, pan);
                } else {
                    JLabel label1;
                    JLabel label2;
                    JLabel label3;
                    JPanel panelis;
                    panelis = new TalkPanel();
                    panelis.setLayout(null);
                    label1 = new JLabel();
                    label1.setBounds(0,0,100,100);
                    panelis.add(label1);
                    label2 = new JLabel("Nama : Corno");
                    label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label2.setBounds(400,7,150,30);
                    panelis.add(label2);
                    label3 = new JLabel ("My pleasure for being with you, Sir.");
                    label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label3.setBounds(101,0,450,90);
                    panelis.add(label3);

                    Game.instance().setDialogBox(panelis, pan);
                }
                
            } else if (tipe == PulmosisBattle.Tomat) {
                if (Game.instance().getStory().getDay() >= 15
                            && Game.instance().getStory().getDay() < 30
                            && Game.instance().getStory().getSeason()==Time.FALL) {
                    JLabel label1;
                    JLabel label2;
                    JLabel label3;
                    JPanel panelis;
                    panelis = new TalkPanel();
                    panelis.setLayout(null);
                    label1 = new JLabel();
                    label1.setBounds(0,0,100,100);
                    panelis.add(label1);
                    label2 = new JLabel("Nama : Tommy");
                    label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label2.setBounds(400,7,150,30);
                    panelis.add(label2);
                    label3 = new JLabel ("Water.. Water..");
                    label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label3.setBounds(101,0,450,90);
                    panelis.add(label3);

                    Game.instance().setDialogBox(panelis, pan);
                } else {
                    JLabel label1;
                    JLabel label2;
                    JLabel label3;
                    JPanel panelis;
                    panelis = new TalkPanel();
                    panelis.setLayout(null);
                    label1 = new JLabel();
                    label1.setBounds(0,0,100,100);
                    panelis.add(label1);
                    label2 = new JLabel("Nama : Tommy");
                    label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label2.setBounds(400,7,150,30);
                    panelis.add(label2);
                    label3 = new JLabel ("Do you need my service, Sir?");
                    label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label3.setBounds(101,0,450,90);
                    panelis.add(label3);

                    Game.instance().setDialogBox(panelis, pan);
                }
                
            } else if (tipe == PulmosisBattle.Bawang) {
                if (Game.instance().getStory().getDay() >= 15
                            && Game.instance().getStory().getDay() < 30
                            && Game.instance().getStory().getSeason()==Time.FALL) {
                    JLabel label1;
                    JLabel label2;
                    JLabel label3;
                    JPanel panelis;
                    panelis = new TalkPanel();
                    panelis.setLayout(null);
                    label1 = new JLabel();
                    label1.setBounds(0,0,100,100);
                    panelis.add(label1);
                    label2 = new JLabel("Nama : Onino");
                    label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label2.setBounds(400,7,150,30);
                    panelis.add(label2);
                    label3 = new JLabel ("We need more water, Sir.");
                    label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label3.setBounds(101,0,450,90);
                    panelis.add(label3);

                    Game.instance().setDialogBox(panelis, pan);
                } else {
                    JLabel label1;
                    JLabel label2;
                    JLabel label3;
                    JPanel panelis;
                    panelis = new TalkPanel();
                    panelis.setLayout(null);
                    label1 = new JLabel();
                    label1.setBounds(0,0,100,100);
                    panelis.add(label1);
                    label2 = new JLabel("Nama : Onino");
                    label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label2.setBounds(400,7,150,30);
                    panelis.add(label2);
                    label3 = new JLabel ("Hello, Sir. I love your farm.");
                    label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label3.setBounds(101,0,450,90);
                    panelis.add(label3);

                    Game.instance().setDialogBox(panelis, pan);
                }
                
            } else if (tipe == PulmosisBattle.Nanas) {
                if (Game.instance().getStory().getDay() >= 15
                            && Game.instance().getStory().getDay() < 30
                            && Game.instance().getStory().getSeason()==Time.FALL) {
                    JLabel label1;
                    JLabel label2;
                    JLabel label3;
                    JPanel panelis;
                    panelis = new TalkPanel();
                    panelis.setLayout(null);
                    label1 = new JLabel();
                    label1.setBounds(0,0,100,100);
                    panelis.add(label1);
                    label2 = new JLabel("Nama : Nanaso");
                    label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label2.setBounds(400,7,150,30);
                    panelis.add(label2);
                    label3 = new JLabel ("Oh, thirsty.");
                    label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label3.setBounds(101,0,450,90);
                    panelis.add(label3);

                    Game.instance().setDialogBox(panelis, pan);
                } else {
                    JLabel label1;
                    JLabel label2;
                    JLabel label3;
                    JPanel panelis;
                    panelis = new TalkPanel();
                    panelis.setLayout(null);
                    label1 = new JLabel();
                    label1.setBounds(0,0,100,100);
                    panelis.add(label1);
                    label2 = new JLabel("Nama : Nanaso");
                    label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label2.setBounds(400,7,150,30);
                    panelis.add(label2);
                    label3 = new JLabel ("Hohoho, how kind of you, My Lord.");
                    label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label3.setBounds(101,0,450,90);
                    panelis.add(label3);

                    Game.instance().setDialogBox(panelis, pan);
                }
            } else if (tipe == PulmosisBattle.Wortel) {
                if (Game.instance().getStory().getDay() >= 15
                            && Game.instance().getStory().getDay() < 30
                            && Game.instance().getStory().getSeason()==Time.FALL) {
                    JLabel label1;
                    JLabel label2;
                    JLabel label3;
                    JPanel panelis;
                    panelis = new TalkPanel();
                    panelis.setLayout(null);
                    label1 = new JLabel();
                    label1.setBounds(0,0,100,100);
                    panelis.add(label1);
                    label2 = new JLabel("Nama : Wortello");
                    label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label2.setBounds(400,7,150,30);
                    panelis.add(label2);
                    label3 = new JLabel ("Oh, thirsty.");
                    label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label3.setBounds(101,0,450,90);
                    panelis.add(label3);

                    Game.instance().setDialogBox(panelis, pan);
                } else {
                    JLabel label1;
                    JLabel label2;
                    JLabel label3;
                    JPanel panelis;
                    panelis = new TalkPanel();
                    panelis.setLayout(null);
                    label1 = new JLabel();
                    label1.setBounds(0,0,100,100);
                    panelis.add(label1);
                    label2 = new JLabel("Nama : Wortello");
                    label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label2.setBounds(400,7,150,30);
                    panelis.add(label2);
                    label3 = new JLabel ("How Wide your farm MyLord, love it");
                    label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label3.setBounds(101,0,450,90);
                    panelis.add(label3);

                    Game.instance().setDialogBox(panelis, pan);
                }
            } else if (tipe == PulmosisBattle.Terong) {
                if (Game.instance().getStory().getDay() >= 15
                            && Game.instance().getStory().getDay() < 30
                            && Game.instance().getStory().getSeason()==Time.FALL) {
                    JLabel label1;
                    JLabel label2;
                    JLabel label3;
                    JPanel panelis;
                    panelis = new TalkPanel();
                    panelis.setLayout(null);
                    label1 = new JLabel();
                    label1.setBounds(0,0,100,100);
                    panelis.add(label1);
                    label2 = new JLabel("Nama : Terongrong");
                    label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label2.setBounds(400,7,150,30);
                    panelis.add(label2);
                    label3 = new JLabel ("Water.. Water..");
                    label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label3.setBounds(101,0,450,90);
                    panelis.add(label3);

                    Game.instance().setDialogBox(panelis, pan);
                } else {
                    JLabel label1;
                    JLabel label2;
                    JLabel label3;
                    JPanel panelis;
                    panelis = new TalkPanel();
                    panelis.setLayout(null);
                    label1 = new JLabel();
                    label1.setBounds(0,0,100,100);
                    panelis.add(label1);
                    label2 = new JLabel("Nama : Terongrong");
                    label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label2.setBounds(400,7,150,30);
                    panelis.add(label2);
                    label3 = new JLabel ("I got many friends here, thank you very much, Sir");
                    label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label3.setBounds(101,0,450,90);
                    panelis.add(label3);

                    Game.instance().setDialogBox(panelis, pan);
                }
            } else if (tipe == PulmosisBattle.Ubi) {
                if (Game.instance().getStory().getDay() >= 15
                            && Game.instance().getStory().getDay() < 30
                            && Game.instance().getStory().getSeason()==Time.FALL) {
                    JLabel label1;
                    JLabel label2;
                    JLabel label3;
                    JPanel panelis;
                    panelis = new TalkPanel();
                    panelis.setLayout(null);
                    label1 = new JLabel();
                    label1.setBounds(0,0,100,100);
                    panelis.add(label1);
                    label2 = new JLabel("Nama : Ubibubi");
                    label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label2.setBounds(400,7,150,30);
                    panelis.add(label2);
                    label3 = new JLabel ("We need more water, Sir.");
                    label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label3.setBounds(101,0,450,90);
                    panelis.add(label3);

                    Game.instance().setDialogBox(panelis, pan);
                } else {
                    JLabel label1;
                    JLabel label2;
                    JLabel label3;
                    JPanel panelis;
                    panelis = new TalkPanel();
                    panelis.setLayout(null);
                    label1 = new JLabel();
                    label1.setBounds(0,0,100,100);
                    panelis.add(label1);
                    label2 = new JLabel("Nama : Ubibubi");
                    label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label2.setBounds(400,7,150,30);
                    panelis.add(label2);
                    label3 = new JLabel ("My Lord!");
                    label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label3.setBounds(101,0,450,90);
                    panelis.add(label3);

                    Game.instance().setDialogBox(panelis, pan);
                }
            } else if (tipe == PulmosisBattle.Paprika) {
                JLabel label1;
                JLabel label2;
                JLabel label3;
                JPanel panelis;
                panelis = new TalkPanel();
                panelis.setLayout(null);
                label1 = new JLabel();
                label1.setBounds(0,0,100,100);
                panelis.add(label1);
                label2 = new JLabel("Nama : Paprikas");
                label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
                label2.setBounds(400,7,150,30);
                panelis.add(label2);
                label3 = new JLabel ("Always energetic MyLord");
                label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
                label3.setBounds(101,0,450,90);
                panelis.add(label3);

                Game.instance().setDialogBox(panelis, pan);
            } else if (tipe == PulmosisBattle.Bayam) {
                if (Game.instance().getStory().getDay() >= 15
                            && Game.instance().getStory().getDay() < 30
                            && Game.instance().getStory().getSeason()==Time.FALL) {
                    JLabel label1;
                    JLabel label2;
                    JLabel label3;
                    JPanel panelis;
                    panelis = new TalkPanel();
                    panelis.setLayout(null);
                    label1 = new JLabel();
                    label1.setBounds(0,0,100,100);
                    panelis.add(label1);
                    label2 = new JLabel("Nama : Bayamus");
                    label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label2.setBounds(400,7,150,30);
                    panelis.add(label2);
                    label3 = new JLabel ("Dry!");
                    label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label3.setBounds(101,0,450,90);
                    panelis.add(label3);

                    Game.instance().setDialogBox(panelis, pan);
                } else {
                    JLabel label1;
                    JLabel label2;
                    JLabel label3;
                    JPanel panelis;
                    panelis = new TalkPanel();
                    panelis.setLayout(null);
                    label1 = new JLabel();
                    label1.setBounds(0,0,100,100);
                    panelis.add(label1);
                    label2 = new JLabel("Nama : Bayamus");
                    label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label2.setBounds(400,7,150,30);
                    panelis.add(label2);
                    label3 = new JLabel ("Green Life Sir");
                    label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label3.setBounds(101,0,450,90);
                    panelis.add(label3);

                    Game.instance().setDialogBox(panelis, pan);
                }
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
