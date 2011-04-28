package plantmon.states;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import plantmon.entity.movingObject.Player;
import plantmon.entity.movingObject.PulmosisBattle;
import plantmon.entity.movingObject.PulmosisLand;
import plantmon.entity.unmoveable.Land;
import plantmon.entity.unmoveable.Road;
import plantmon.game.GridMap;
import plantmon.game.ImageEntity;
import plantmon.game.Point2D;
import plantmon.game.TalkPanel;
import plantmon.system.Actionable;
import plantmon.system.RunnableListener;
import plantmon.system.Selectable;
import plantmon.system.Utilities;

public class BawangState extends ParentState implements MouseListener,MouseMotionListener, KeyListener {
    Thread gameloop;
    GridMap map;
    JPopupMenu popup;
    int startx;
    int starty;
    Player player;
    JTextArea time;
    Selectable selected;
    boolean selectsomething;
    Actionable actionated;
    int clickx,clicky,defx,defy;
    private boolean dragged;
    int i;
    StoryLine story;
    Boolean[] sudah;
    PulmosisLand timumun;
    PulmosisLand turunip;
    PulmosisLand onino;
    Boolean[] cancel = new Boolean[1];
    private boolean done;
    public BawangState(int gridRow, int gridColumn) {
        super(gridRow, gridColumn);
        map = new GridMap(gridRow,gridColumn);
        sudah = new Boolean[5];
        for (int j = 0; j < 5; j++) {
            sudah[j]=false;
        }
        ID = BAWANGSTATE;
        this.init();
        time = new JTextArea();
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setPreferredSize(new Dimension(640, 480));
        setLayout(null);
        Game.instance().playMusic("025-Town03.mid");
        time.setEditable(false);
        time.setBounds(0, 0, 200, 50);
        time.setBackground(Color.ORANGE);
        time.setForeground(Color.black);
        add(Game.instance().dialogBox());
//        Game.instance().setDialogBox(selected.get_Info(),this);
//        Game.instance().dialogOn();
//        add(time);
        //add(pane);
        active = true;
        addMouseMotionListener(this);
    }

    public void init() {
        startx=0; starty=0;
        backbuffer = new BufferedImage(864,537, BufferedImage.TYPE_INT_ARGB);
        g2d = backbuffer.createGraphics();
        background = new ImageEntity(this);
        background.load("picture/LahanDark.png");
                       //0 1 2 3 4 5 6 7 8 9 10
        int[][] what = {{0,0,0,0,0,0,0,0,0,0,0},//0
                        {0,0,0,2,2,2,2,0,0,0,0},//1
                        {0,0,0,2,2,2,2,0,0,0,0},//2
                        {0,0,0,2,2,2,2,2,2,2,0},//3
                        {0,0,0,2,2,2,2,2,2,2,0},//4
                        {0,2,2,2,2,2,2,2,2,2,0},//5
                        {0,2,2,2,2,2,2,2,2,2,0},//6
                        {0,2,2,2,2,2,2,2,2,2,0},//7
                        {0,1,1,1,1,1,1,1,1,1,0},//8
                        {0,1,1,1,1,1,1,1,1,1,0},//9
                        {0,1,1,1,1,1,1,1,1,1,0},//10
                        {0,1,1,1,1,1,1,1,1,1,0},//11
                        {0,1,1,1,1,1,1,1,1,1,0},//12
                        {0,1,1,1,1,1,1,1,1,1,0},//13
                        {0,1,1,1,1,1,1,1,1,1,0},//14
                        {0,1,1,1,1,1,1,1,1,1,0},//15
                        {0,1,1,1,1,1,1,1,1,1,0},//16
                        {0,0,0,0,0,0,0,0,0,0,0}};//17
        for (int i=0; i<map.getRow();i++){
            for (int j=0; j<map.getColumn(); j++){
                if (what[i][j] == 0){
                } else if (what[i][j] == 1){
                    Land l = new Land(map, this, g2d,i,j);
                    l.setStatus(Game.instance().farmstatus()[i][j]);
                    map.gpush(i, j, l);
                } else if (what[i][j] ==2){
                    Road r = new Road(map, this, g2d, i, j);
                    map.gpush(i, j, r);
                }
            }
        }
        turunip = new PulmosisLand(map,this,g2d,PulmosisBattle.Lobak);
        turunip.getCreature().setPosition(new Point2D(3*Utilities.GRIDSIZE,4*Utilities.GRIDSIZE));
        turunip.getCreature().setFinalPosition(3*Utilities.GRIDSIZE,4*Utilities.GRIDSIZE);
        Point2D pos = turunip.getCreature().position();
        map.push(pos.X(), pos.Y(), turunip);
        cancel[0] = false;
        setDragged(true);
        addMouseListener(this);
        setDone(false);
        i = 1;
    }

    public void run() {
        active = true;
        conversation();
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
    @Override public void paintComponent(Graphics g) {
//        System.out.println("paintComponent - CobaOpeh");
        updated();
        g.drawImage(backbuffer,startx, starty, this);
    }
    public void conversation () {
//        if (i <= 5 && dragged) {
            Game.instance().setDialogBox(boces(i), this);
            Game.instance().dialogOn();
//        }
    }
public void updated(){
        g2d.setColor(Color.WHITE);
        g2d.drawImage(background.getImage(), 0, 0, this);
        map.draw(startx,starty);
        if (selectsomething) {
            selected.drawBounds();
        }

        g2d.setColor(Color.GRAY);
    }
    public JPanel boces (int i) {
        JLabel label1;
        JLabel label2;
        JLabel label3;
        JButton but1;
        JButton but2;
        JPanel panelis;
        panelis = new TalkPanel();
        if (i == 1) {
//            if (!sudah[0]) {
            panelis.setLayout(null);
            //image1 = new ImageIcon(this.getClass().getResource("icon player.png"));
            label1 = new JLabel();
            //image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
            label1.setBounds(0,0,100,100);
            panelis.add(label1);
            label2 = new JLabel("Nama : ????");
            label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label2.setBounds(400,7,150,30);
            panelis.add(label2);
            label3 = new JLabel ("Good Night, Turunip");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,0,450,90);
            panelis.add(label3);
            but1 = new JButton ("Next");
            but1.setBounds(375,70,150,16);
            but1.addActionListener(new Yes(selected,i,this));
            panelis.add(but1);
        } else if (i == 2) {
            panelis.setLayout(null);
            //image1 = new ImageIcon(this.getClass().getResource("icon player.png"));
            label1 = new JLabel();
            //image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
            label1.setBounds(0,0,100,100);
            panelis.add(label1);
            label2 = new JLabel("Nama : Turunip");
            label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label2.setBounds(400,7,150,30);
            panelis.add(label2);
            label3 = new JLabel ("Hey, who's there??");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,0,450,90);
            panelis.add(label3);
            label3 = new JLabel ("...");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,16,450,90);
            panelis.add(label3);
            label3 = new JLabel ("Is it you Timumun??");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,32,450,90);
            panelis.add(label3);
            but1 = new JButton ("Next");
            but1.setBounds(375,70,150,16);
            but1.addActionListener(new Yes(selected,i,this));
            panelis.add(but1);
        } else if (i == 3) {
            panelis.setLayout(null);
            //image1 = new ImageIcon(this.getClass().getResource("icon player.png"));
            label1 = new JLabel();
            //image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
            label1.setBounds(0,0,100,100);
            panelis.add(label1);
            label2 = new JLabel("Nama : Timumun");
            label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label2.setBounds(400,7,150,30);
            panelis.add(label2);
            label3 = new JLabel ("Yeah, it's me");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,0,450,90);
            panelis.add(label3);
            label3 = new JLabel ("I've some word want's to tell you");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,16,450,90);
            panelis.add(label3);
            but1 = new JButton ("Next");
            but1.setBounds(375,70,150,16);
            but1.addActionListener(new Yes(selected,i,this));
            panelis.add(but1);
        } else if (i == 4) {
            panelis.setLayout(null);
            //image1 = new ImageIcon(this.getClass().getResource("icon player.png"));
            label1 = new JLabel();
            //image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
            label1.setBounds(0,0,100,100);
            panelis.add(label1);
            label2 = new JLabel("Nama : Timumun");
            label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label2.setBounds(400,7,150,30);
            panelis.add(label2);
            label3 = new JLabel ("I Love You, Turunip");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,0,450,90);
            panelis.add(label3);
            label3 = new JLabel ("For deep of my heart");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,16,450,90);
            panelis.add(label3);
            label3 = new JLabel ("Do you to be my wife??");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,32,450,90);
            panelis.add(label3);
            but1 = new JButton ("Next");
            but1.setBounds(375,70,150,16);
            but1.addActionListener(new Yes(selected,i,this));
            panelis.add(but1);
        } else if (i == 5) {
            panelis.setLayout(null);
            //image1 = new ImageIcon(this.getClass().getResource("icon player.png"));
            label1 = new JLabel();
            //image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
            label1.setBounds(0,0,100,100);
            panelis.add(label1);
            label2 = new JLabel("Nama : Turunip");
            label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label2.setBounds(400,7,150,30);
            panelis.add(label2);
            label3 = new JLabel ("xxxx(0.0)xxxxx");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,0,450,90);
            panelis.add(label3);
            label3 = new JLabel ("..............");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,16,450,90);
            panelis.add(label3);
            label3 = new JLabel ("I want too, Timumun..");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,32,450,90);
            panelis.add(label3);
            but1 = new JButton ("Next");
            but1.setBounds(375,70,150,16);
            but1.addActionListener(new Yes(selected,i,this));
            panelis.add(but1);
        }  else if (i == 6) {
            panelis.setLayout(null);
            //image1 = new ImageIcon(this.getClass().getResource("icon player.png"));
            label1 = new JLabel();
            //image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
            label1.setBounds(0,0,100,100);
            panelis.add(label1);
            label2 = new JLabel("Nama : Timumun");
            label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label2.setBounds(400,7,150,30);
            panelis.add(label2);
            label3 = new JLabel ("Come to my side Turunip");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,0,450,90);
            panelis.add(label3);
            but1 = new JButton ("Next");
            but1.setBounds(375,70,150,16);
            but1.addActionListener(new Yes(selected,i,this));
            panelis.add(but1);
        } else if (i == 7) {
//            if (!sudah[0]) {
            panelis.setLayout(null);
            //image1 = new ImageIcon(this.getClass().getResource("icon player.png"));
            label1 = new JLabel();
            //image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
            label1.setBounds(0,0,100,100);
            panelis.add(label1);
            label2 = new JLabel("Nama : Turunip");
            label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label2.setBounds(400,7,150,30);
            panelis.add(label2);
            label3 = new JLabel ("I Love You very much Timumun");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,0,450,90);
            panelis.add(label3);
            but1 = new JButton ("Next");
            but1.setBounds(375,70,150,16);
            but1.addActionListener(new Yes(selected,i,this));
            panelis.add(but1);
        } else if (i == 8) {
            panelis.setLayout(null);
            //image1 = new ImageIcon(this.getClass().getResource("icon player.png"));
            label1 = new JLabel();
            //image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
            label1.setBounds(0,0,100,100);
            panelis.add(label1);
            label2 = new JLabel("Nama : Timumun");
            label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label2.setBounds(400,7,150,30);
            panelis.add(label2);
            label3 = new JLabel ("I Love You, too Turunip");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,0,450,90);
            panelis.add(label3);
            label3 = new JLabel ("You see the sky");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,16,450,90);
            panelis.add(label3);
            label3 = new JLabel ("There is so many star");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,32,450,90);
            panelis.add(label3);
            but1 = new JButton ("Next");
            but1.setBounds(375,70,150,16);
            but1.addActionListener(new Yes(selected,i,this));
            panelis.add(but1);
        } else if (i == 9) {
            panelis.setLayout(null);
            //image1 = new ImageIcon(this.getClass().getResource("icon player.png"));
            label1 = new JLabel();
            //image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
            label1.setBounds(0,0,100,100);
            panelis.add(label1);
            label2 = new JLabel("Nama : Timumun");
            label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label2.setBounds(400,7,150,30);
            panelis.add(label2);
            label3 = new JLabel ("But you are the only");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,0,450,90);
            panelis.add(label3);
            label3 = new JLabel ("The only star in my heart");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,16,450,90);
            panelis.add(label3);
            but1 = new JButton ("Next");
            but1.setBounds(375,70,150,16);
            but1.addActionListener(new Yes(selected,i,this));
            panelis.add(but1);
        } else if (i == 10) {
            panelis.setLayout(null);
            //image1 = new ImageIcon(this.getClass().getResource("icon player.png"));
            label1 = new JLabel();
            //image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
            label1.setBounds(0,0,100,100);
            panelis.add(label1);
            label2 = new JLabel("Nama : Turunip");
            label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label2.setBounds(400,7,150,30);
            panelis.add(label2);
            label3 = new JLabel ("Let's sleep together");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,0,450,90);
            panelis.add(label3);
            but1 = new JButton ("Next");
            but1.setBounds(375,70,150,16);
            but1.addActionListener(new Yes(selected,i,this));
            panelis.add(but1);
        } else if (i == 11) {
            panelis.setLayout(null);
            //image1 = new ImageIcon(this.getClass().getResource("icon player.png"));
            label1 = new JLabel();
            //image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
            label1.setBounds(0,0,100,100);
            panelis.add(label1);
            label2 = new JLabel("Nama : ?????");
            label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label2.setBounds(400,7,150,30);
            panelis.add(label2);
            label3 = new JLabel ("Oeeeoeeeoeee");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,0,450,90);
            panelis.add(label3);
            label3 = new JLabel ("Oeeeoeeeoeee");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,16,450,90);
            panelis.add(label3);
            but1 = new JButton ("return");
            but1.setBounds(375,70,150,16);
            but1.addActionListener(new Yes(selected,i,this));
            panelis.add(but1);
        }else if (i == 12) {
            panelis.setLayout(null);
            //image1 = new ImageIcon(this.getClass().getResource("icon player.png"));
            label1 = new JLabel();
            //image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
            label1.setBounds(0,0,100,100);
            panelis.add(label1);
            label2 = new JLabel("Nama : Timumun");
            label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label2.setBounds(400,7,150,30);
            panelis.add(label2);
            label3 = new JLabel ("Oh my.. He is my child");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,0,450,90);
            panelis.add(label3);
            but1 = new JButton ("Next");
            but1.setBounds(375,70,150,16);
            but1.addActionListener(new Yes(selected,i,this));
            panelis.add(but1);
        }  else if (i == 13) {
            panelis.setLayout(null);
            //image1 = new ImageIcon(this.getClass().getResource("icon player.png"));
            label1 = new JLabel();
            //image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
            label1.setBounds(0,0,100,100);
            panelis.add(label1);
            label2 = new JLabel("Nama : Turunip");
            label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label2.setBounds(400,7,150,30);
            panelis.add(label2);
            label3 = new JLabel ("He's our chile my honey");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,0,450,90);
            panelis.add(label3);
            label3 = new JLabel ("Let's hope he can be as good as us");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,16,450,90);
            panelis.add(label3);
            but1 = new JButton ("Return");
            but1.setBounds(375,70,150,16);
            but1.addActionListener(new Yes(selected,i,this));
            panelis.add(but1);
        }
        return panelis;
    }

    /**
     * @return the dragged
     */
    public boolean isDragged() {
        return dragged;
    }

    /**
     * @param dragged the dragged to set
     */
    public void setDragged(boolean dragged) {
        this.dragged = dragged;
    }

    /**
     * @return the done
     */
    public boolean isDone() {
        return done;
    }

    /**
     * @param done the done to set
     */
    public void setDone(boolean done) {
        this.done = done;
    }

    class Yes extends RunnableListener {
        int state;
        JPanel panel;
        Object lock = new Object();
        public Yes(Selectable selected,int i,JPanel panel) {
            super(selected);
            state = i;
            this.panel=panel;
        }

        public void run() {
            if (state == 1) {
                i++;
                Game.instance().setDialogBox(boces(i), panel);
            } else if (state == 2) {
                i++;
                timumun = new PulmosisLand(map,panel,g2d,PulmosisBattle.Timun);
                timumun.getCreature().setPosition(new Point2D(11*Utilities.GRIDSIZE,4*Utilities.GRIDSIZE));
                timumun.getCreature().setFinalPosition(11*Utilities.GRIDSIZE,4*Utilities.GRIDSIZE);
                Point2D pos = timumun.getCreature().position();
                map.push(pos.X(), pos.Y(), timumun);
                pos = new Point2D(6*Utilities.GRIDSIZE,4*Utilities.GRIDSIZE);
                timumun.getCreature().setArah(pos);
                pos = new Point2D(6*Utilities.GRIDSIZE,4*Utilities.GRIDSIZE);
                turunip.getCreature().setArah(pos);
                Game.instance().setDialogBox(boces(i), panel);
            } else if (state == 3) {
                i++;
                timumun.move(5*Utilities.GRIDSIZE, 4*Utilities.GRIDSIZE, lock, cancel);
                synchronized(lock){
                    try {
                        lock.wait();
                    } catch (InterruptedException e){
                    }
                }
                if (!cancel[0]){
                    timumun.getCreature().setFinalPosition(5*Utilities.GRIDSIZE+5, 4*Utilities.GRIDSIZE+5);
                    Game.instance().setDialogBox(boces(i), panel);
                }
            } else if (state == 4) {
                i++;
                Game.instance().setDialogBox(boces(i), panel);
            } else if (state == 5) {
                i++;
                turunip.move(4*Utilities.GRIDSIZE, 4*Utilities.GRIDSIZE, lock, cancel);
                synchronized(lock){
                    try {
                        lock.wait();
                    } catch (InterruptedException e){
                    }
                }
                if (!cancel[0]){
                    turunip.getCreature().setFinalPosition(4*Utilities.GRIDSIZE+5, 4*Utilities.GRIDSIZE+5);
                    Game.instance().setDialogBox(boces(i), panel);
                }
            } else if (state == 6) {
                i++;
                background.load("picture/BlackOut.png");
                map.pop(4*Utilities.GRIDSIZE,4*Utilities.GRIDSIZE);
                map.pop(5*Utilities.GRIDSIZE,4*Utilities.GRIDSIZE);
                Game.instance().setDialogBox(boces(i), panel);
            } else if (state == 7) {
                i++;
                Game.instance().setDialogBox(boces(i), panel);
            } else if (state == 8) {
                i++;
                Game.instance().setDialogBox(boces(i), panel);
            } else if (state == 9) {
                i++;
                Game.instance().setDialogBox(boces(i), panel);
            } else if (state == 10) {
                i++;
                background.load("picture/Morning.png");
                Game.instance().setDialogBox(boces(i), panel);
            } else if (state == 11) {
                i++;
                background.load("picture/Lahan.png");
                onino = new PulmosisLand(map,panel,g2d,PulmosisBattle.Bawang);
                onino.getCreature().setPosition(new Point2D(4*Utilities.GRIDSIZE,3*Utilities.GRIDSIZE));
                onino.getCreature().setFinalPosition(4*Utilities.GRIDSIZE,3*Utilities.GRIDSIZE);
                Point2D pos = onino.getCreature().position();
                map.push(pos.X(), pos.Y(), onino);
                pos = new Point2D(4*Utilities.GRIDSIZE,4*Utilities.GRIDSIZE);
                onino.getCreature().setArah(pos);
                timumun = new PulmosisLand(map,panel,g2d,PulmosisBattle.Timun);
                timumun.getCreature().setPosition(new Point2D(5*Utilities.GRIDSIZE,4*Utilities.GRIDSIZE));
                timumun.getCreature().setFinalPosition(5*Utilities.GRIDSIZE,4*Utilities.GRIDSIZE);
                pos = timumun.getCreature().position();
                map.push(pos.X(), pos.Y(), timumun);
                pos = new Point2D(5*Utilities.GRIDSIZE,3*Utilities.GRIDSIZE);
                timumun.getCreature().setArah(pos);
                turunip = new PulmosisLand(map,panel,g2d,PulmosisBattle.Timun);
                turunip.getCreature().setPosition(new Point2D(4*Utilities.GRIDSIZE,4*Utilities.GRIDSIZE));
                turunip.getCreature().setFinalPosition(4*Utilities.GRIDSIZE,4*Utilities.GRIDSIZE);
                pos = turunip.getCreature().position();
                map.push(pos.X(), pos.Y(), turunip);
                pos = new Point2D(4*Utilities.GRIDSIZE,3*Utilities.GRIDSIZE);
                turunip.getCreature().setArah(pos);
                Game.instance().setDialogBox(boces(i), panel);
            } else if (state == 12) {
                i++;
                Game.instance().setDialogBox(boces(i), panel);
            } else if (state == 13) {
                Game.instance().StopMusic();
                Game.instance().getStory().setAlreadyBawang(true);
                Game.instance().returnTo();
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Plantmon");
        mainFrame.setSize(640, 480);
//        mainFrame.setResizable(false);
        //mainFrame.add(p, BorderLayout.CENTER);
//        JPanel panel = new JPanel();
//        mainFrame.add(panel);
        //mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        Game.instance().setFrame(mainFrame);
        Game.instance().goTo(ParentState.BAWANGSTATE,null);
    }
}
