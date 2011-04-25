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

public class LabuState extends ParentState implements MouseListener,MouseMotionListener, KeyListener {
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
    PulmosisLand labulai;
    Boolean[] cancel = new Boolean[1];
    public LabuState(int gridRow, int gridColumn) {
        super(gridRow, gridColumn);
        map = new GridMap(gridRow,gridColumn);
        sudah = new Boolean[5];
        for (int j = 0; j < 5; j++) {
            sudah[j]=false;
        }
        ID = LABUSTATE;
        this.init();
        time = new JTextArea();
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setPreferredSize(new Dimension(640, 480));
        setLayout(null);

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
        background.load("picture/Lahan.png");
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
        labulai = new PulmosisLand(map,this,g2d,PulmosisBattle.Labu);
        labulai.getCreature().setPosition(new Point2D(5*Utilities.GRIDSIZE,1*Utilities.GRIDSIZE));
        labulai.getCreature().setFinalPosition(5*Utilities.GRIDSIZE,1*Utilities.GRIDSIZE);
        Point2D pos = labulai.getCreature().position();
        map.push(pos.X(), pos.Y(), labulai);
        pos = new Point2D(6*Utilities.GRIDSIZE,1*Utilities.GRIDSIZE);
        labulai.getCreature().setArah(pos);
        player = new Player(map,this,g2d);
        player.getCreature().setPosition(new Point2D(6*Utilities.GRIDSIZE,1*Utilities.GRIDSIZE));
        player.getCreature().setFinalPosition(6*Utilities.GRIDSIZE,1*Utilities.GRIDSIZE);
        pos = player.getCreature().position();
        map.push(pos.X(), pos.Y(), player);
        pos = new Point2D(5*Utilities.GRIDSIZE,1*Utilities.GRIDSIZE);
        player.getCreature().setArah(pos);
        cancel[0] = false;
        setDragged(true);
        addMouseListener(this);
    }

    public void run() {
        active = true;
        i = 1;
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
            Game.instance().setDialogBox(boces(1), this);
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
            panelis = new TalkPanel();
            panelis.setLayout(null);
            //image1 = new ImageIcon(this.getClass().getResource("icon player.png"));
            label1 = new JLabel();
            //image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
            label1.setBounds(0,0,100,100);
            panelis.add(label1);
            label2 = new JLabel("Nama : Labulai");
            label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label2.setBounds(400,7,150,30);
            panelis.add(label2);
            label3 = new JLabel ("Do you want to play some games?");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,0,450,90);
            panelis.add(label3);
            but1 = new JButton ("Yes");
            but1.setBounds(375,70,75,16);
            but1.addActionListener(new Yes(selected,i,this));
            panelis.add(but1);
            but2 = new JButton ("No");
            but2.setBounds(500,70,75,16);
            but2.addActionListener(new No(selected,i,this));
            panelis.add(but2);
        } else if (i == 2) {
            panelis.setLayout(null);
            //image1 = new ImageIcon(this.getClass().getResource("icon player.png"));
            label1 = new JLabel();
            //image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
            label1.setBounds(0,0,100,100);
            panelis.add(label1);
            label2 = new JLabel("Nama : Labulai");
            label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label2.setBounds(400,7,150,30);
            panelis.add(label2);
            label3 = new JLabel ("Thank you");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,0,450,90);
            panelis.add(label3);
            label3 = new JLabel ("It's just a simple game");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,16,450,90);
            panelis.add(label3);
            label3 = new JLabel ("We play scissors,paper, and stone game");
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
            label2 = new JLabel("Nama : Labulai");
            label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label2.setBounds(400,7,150,30);
            panelis.add(label2);
            label3 = new JLabel ("If you can beat me in three turns,");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,0,450,90);
            panelis.add(label3);
            label3 = new JLabel ("I will help you");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,16,450,90);
            label3 = new JLabel ("Do you want to try?");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,32,450,90);
            but1 = new JButton ("Yes");
            but1.setBounds(375,70,75,16);
            but1.addActionListener(new Yes(selected,i,this));
            panelis.add(but1);
            but2 = new JButton ("No");
            but2.setBounds(500,70,75,16);
            but2.addActionListener(new No(selected,i,this));
            panelis.add(but2);
        } else if (i == 4) {
            panelis.setLayout(null);
            //image1 = new ImageIcon(this.getClass().getResource("icon player.png"));
            label1 = new JLabel();
            //image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
            label1.setBounds(0,0,100,100);
            panelis.add(label1);
            label2 = new JLabel("Nama : Labulai");
            label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label2.setBounds(400,7,150,30);
            panelis.add(label2);
            label3 = new JLabel ("Ok, choose what you want..");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,0,450,90);
            panelis.add(label3);
            but1 = new JButton ("Scissor");
            but1.setBounds(101,70,100,16);
            but1.addActionListener(new Sci(selected,i,this));
            panelis.add(but1);
            but2 = new JButton ("Paper");
            but2.setBounds(202,70,100,16);
            but2.addActionListener(new Pap(selected,i,this));
            panelis.add(but2);
            but2 = new JButton ("Stone");
            but2.setBounds(303,70,100,16);
            but2.addActionListener(new Sto(selected,i,this));
            panelis.add(but2);
        } else if (i == 5) {
            panelis.setLayout(null);
            //image1 = new ImageIcon(this.getClass().getResource("icon player.png"));
            label1 = new JLabel();
            //image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
            label1.setBounds(0,0,100,100);
            panelis.add(label1);
            label2 = new JLabel("Nama : Labulai");
            label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label2.setBounds(400,7,150,30);
            panelis.add(label2);
            label3 = new JLabel ("I choose Paper and You");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,0,450,90);
            panelis.add(label3);
            label3 = new JLabel ("choose Scissor");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,16,450,90);
            panelis.add(label3);
            label3 = new JLabel ("You Win the first turn");
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
            label2 = new JLabel("Nama : Labulai");
            label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label2.setBounds(400,7,150,30);
            panelis.add(label2);
            label3 = new JLabel ("Ok, choose the next what you want..");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,0,450,90);
            panelis.add(label3);
            but1 = new JButton ("Scissor");
            but1.setBounds(101,70,100,16);
            but1.addActionListener(new Sci(selected,i,this));
            panelis.add(but1);
            but2 = new JButton ("Paper");
            but2.setBounds(202,70,100,16);
            but2.addActionListener(new Pap(selected,i,this));
            panelis.add(but2);
            but2 = new JButton ("Stone");
            but2.setBounds(303,70,100,16);
            but2.addActionListener(new Sto(selected,i,this));
            panelis.add(but2);
        } else if (i == 7) {
            panelis.setLayout(null);
            //image1 = new ImageIcon(this.getClass().getResource("icon player.png"));
            label1 = new JLabel();
            //image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
            label1.setBounds(0,0,100,100);
            panelis.add(label1);
            label2 = new JLabel("Nama : Labulai");
            label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label2.setBounds(400,7,150,30);
            panelis.add(label2);
            label3 = new JLabel ("I choose Scissor and You");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,0,450,90);
            panelis.add(label3);
            label3 = new JLabel ("choose Stone");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,16,450,90);
            panelis.add(label3);
            label3 = new JLabel ("You Win the second turn");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,32,450,90);
            panelis.add(label3);
            but1 = new JButton ("Next");
            but1.setBounds(375,70,150,16);
            but1.addActionListener(new Yes(selected,i,this));
            panelis.add(but1);
        }  else if (i == 8) {
            panelis.setLayout(null);
            //image1 = new ImageIcon(this.getClass().getResource("icon player.png"));
            label1 = new JLabel();
            //image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
            label1.setBounds(0,0,100,100);
            panelis.add(label1);
            label2 = new JLabel("Nama : Labulai");
            label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label2.setBounds(400,7,150,30);
            panelis.add(label2);
            label3 = new JLabel ("Ok, for the last turn,");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,0,450,90);
            panelis.add(label3);
            label3 = new JLabel ("choose what you want..");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,16,450,90);
            panelis.add(label3);
            but1 = new JButton ("Scissor");
            but1.setBounds(101,70,100,16);
            but1.addActionListener(new Sci(selected,i,this));
            panelis.add(but1);
            but2 = new JButton ("Paper");
            but2.setBounds(202,70,100,16);
            but2.addActionListener(new Pap(selected,i,this));
            panelis.add(but2);
            but2 = new JButton ("Stone");
            but2.setBounds(303,70,100,16);
            but2.addActionListener(new Sto(selected,i,this));
            panelis.add(but2);
        } else if (i == 9) {
            panelis.setLayout(null);
            //image1 = new ImageIcon(this.getClass().getResource("icon player.png"));
            label1 = new JLabel();
            //image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
            label1.setBounds(0,0,100,100);
            panelis.add(label1);
            label2 = new JLabel("Nama : Labulai");
            label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label2.setBounds(400,7,150,30);
            panelis.add(label2);
            label3 = new JLabel ("I choose Scissor and You");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,0,450,90);
            panelis.add(label3);
            label3 = new JLabel ("choose Stone");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,16,450,90);
            panelis.add(label3);
            label3 = new JLabel ("You Win the last turn");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,32,450,90);
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
            label2 = new JLabel("Nama : Labulai");
            label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label2.setBounds(400,7,150,30);
            panelis.add(label2);
            label3 = new JLabel ("Awesome man");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,0,450,90);
            panelis.add(label3);
            label3 = new JLabel ("Now I will help you");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,16,450,90);
            panelis.add(label3);
            label3 = new JLabel ("Thank you for playing with me");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,32,450,90);
            panelis.add(label3);
            but1 = new JButton ("Return");
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
            label2 = new JLabel("Nama : Labulai");
            label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label2.setBounds(400,7,150,30);
            panelis.add(label2);
            label3 = new JLabel ("I'm sorry. You lose");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,0,450,90);
            panelis.add(label3);
            label3 = new JLabel ("Please try again next time");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,16,450,90);
            panelis.add(label3);
            but1 = new JButton ("Return");
            but1.setBounds(375,70,150,16);
            but1.addActionListener(new Yes(selected,i,this));
            panelis.add(but1);
        } else if (i == 12) {
            panelis.setLayout(null);
            //image1 = new ImageIcon(this.getClass().getResource("icon player.png"));
            label1 = new JLabel();
            //image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
            label1.setBounds(0,0,100,100);
            panelis.add(label1);
            label2 = new JLabel("Nama : Labulai");
            label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label2.setBounds(400,7,150,30);
            panelis.add(label2);
            label3 = new JLabel ("I feel sorry to disturb you");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,0,450,90);
            panelis.add(label3);
            label3 = new JLabel ("See ya when you have time to play");
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
                Game.instance().setDialogBox(boces(i), panel);
            } else if (state == 3) {
                i++;
                Game.instance().setDialogBox(boces(i), panel);
            } else if (state == 5) {
                i++;
                Game.instance().setDialogBox(boces(i), panel);
            } else if (state == 7) {
                i++;
                Game.instance().setDialogBox(boces(i), panel);
            } else if (state == 9) {
                i++;
                Game.instance().setDialogBox(boces(i), panel);
            } else if (state == 10) {
                Game.instance().getStory().setLabuDone(true);
                Game.instance().returnTo();
            } else if (state == 11) {
                Game.instance().returnTo();
            } else if (state == 12) {
                Game.instance().returnTo();
            }
        }
    }

    class No extends RunnableListener {
        int state;
        JPanel panel;
        public No(Selectable selected,int i,JPanel panel) {
            super(selected);
            state = i;
            this.panel=panel;
        }

        public void run() {
            if (state == 1) {
                i=12;
                Game.instance().setDialogBox(boces(i), panel);
            } else if (state == 3) {
                i=12;
                Game.instance().setDialogBox(boces(i), panel);
            }
        }
    }

    class Sci extends RunnableListener {
        int state;
        JPanel panel;
        public Sci(Selectable selected,int i,JPanel panel) {
            super(selected);
            state = i;
            this.panel=panel;
        }

        public void run() {
            if (state == 4) {
                i=5;
                Game.instance().setDialogBox(boces(i), panel);
            } else {
                i=11;
                Game.instance().setDialogBox(boces(i), panel);
            }
        }
    }

    class Pap extends RunnableListener {
        int state;
        JPanel panel;
        public Pap(Selectable selected,int i,JPanel panel) {
            super(selected);
            state = i;
            this.panel=panel;
        }

        public void run() {
            i=11;
            Game.instance().setDialogBox(boces(i), panel);
        }
    }

    class Sto extends RunnableListener {
        int state;
        JPanel panel;
        public Sto(Selectable selected,int i,JPanel panel) {
            super(selected);
            state = i;
            this.panel=panel;
        }

        public void run() {
            if (state == 8 || state == 6) {
                i++;
                Game.instance().setDialogBox(boces(i), panel);
            } else {
                i=11;
                Game.instance().setDialogBox(boces(i), panel);
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
        Game.instance().goTo(ParentState.TOMATSTATE,null);
    }
}
