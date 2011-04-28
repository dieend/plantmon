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

public class FinalState extends ParentState implements MouseListener,MouseMotionListener, KeyListener {
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
    Player paman;
    Boolean[] cancel = new Boolean[1];
    private boolean done;
    public FinalState(int gridRow, int gridColumn) {
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
        paman = new Player(map,this,g2d);
        paman.getCreature().setPosition(new Point2D(3*Utilities.GRIDSIZE,4*Utilities.GRIDSIZE));
        paman.getCreature().setFinalPosition(3*Utilities.GRIDSIZE,4*Utilities.GRIDSIZE);
        Point2D pos = paman.getCreature().position();
        map.push(pos.X(), pos.Y(), paman);
        pos = new Point2D(3*Utilities.GRIDSIZE,3*Utilities.GRIDSIZE);
        paman.getCreature().setArah(pos);
        player = new Player(map,this,g2d);
        player.getCreature().setPosition(new Point2D(3*Utilities.GRIDSIZE,3*Utilities.GRIDSIZE));
        player.getCreature().setFinalPosition(3*Utilities.GRIDSIZE,3*Utilities.GRIDSIZE);
        pos = player.getCreature().position();
        map.push(pos.X(), pos.Y(), player);
        pos = new Point2D(3*Utilities.GRIDSIZE,4*Utilities.GRIDSIZE);
        player.getCreature().setArah(pos);
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
            label2 = new JLabel("Nama : Uncle Mc Donald");
            label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label2.setBounds(400,7,150,30);
            panelis.add(label2);
            label3 = new JLabel ("Hello my niece");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,0,450,90);
            panelis.add(label3);
            label3 = new JLabel ("How are you today??");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,16,450,90);
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
            label2 = new JLabel("Nama : Uncle Mc Donald");
            label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label2.setBounds(400,7,150,30);
            panelis.add(label2);
            label3 = new JLabel ("I think you done your job very well");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,0,450,90);
            panelis.add(label3);
            label3 = new JLabel ("Hhahahhaha");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,16,450,90);
            panelis.add(label3);
            label3 = new JLabel ("But, I will give you the last test");
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
            label2 = new JLabel("Nama : Uncle Mc Donald");
            label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label2.setBounds(400,7,150,30);
            panelis.add(label2);
            label3 = new JLabel ("If you can beat me in battle,");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,0,450,90);
            panelis.add(label3);
            label3 = new JLabel ("The land is yours forever");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,16,450,90);
            panelis.add(label3);
            label3 = new JLabel ("Dare to try??");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,32,450,90);
            panelis.add(label3);
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
            label2 = new JLabel("Nama : Uncle Mc Donald");
            label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label2.setBounds(400,7,150,30);
            panelis.add(label2);
            label3 = new JLabel ("Ok, let's get it easy");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,0,450,90);
            panelis.add(label3);
            but1 = new JButton ("Next");
            but1.setBounds(375,70,150,16);
            but1.addActionListener(new Yes(selected,i,this));
            panelis.add(but1);
        } else if (i == 6) {
            panelis.setLayout(null);
            //image1 = new ImageIcon(this.getClass().getResource("icon player.png"));
            label1 = new JLabel();
            //image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
            label1.setBounds(0,0,100,100);
            panelis.add(label1);
            label2 = new JLabel("Nama : Uncle Mc Donald");
            label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label2.setBounds(400,7,150,30);
            panelis.add(label2);
            label3 = new JLabel ("Oh, you're such impression me");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,0,450,90);
            panelis.add(label3);
            label3 = new JLabel ("You must be trained your Pulmo everyday");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,16,450,90);
            panelis.add(label3);
            label3 = new JLabel ("As the ... I will give you the land");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,32,450,90);
            panelis.add(label3);
            but1 = new JButton ("Next");
            but1.setBounds(375,70,150,16);
            but1.addActionListener(new Yes(selected,i,this));
            panelis.add(but1);
        }  else if (i == 7) {
            panelis.setLayout(null);
            //image1 = new ImageIcon(this.getClass().getResource("icon player.png"));
            label1 = new JLabel();
            //image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
            label1.setBounds(0,0,100,100);
            panelis.add(label1);
            label2 = new JLabel("Nama : Uncle Mc Donald");
            label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label2.setBounds(400,7,150,30);
            panelis.add(label2);
            label3 = new JLabel ("See ya Guys, be a better Pulmo trainer");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,0,450,90);
            panelis.add(label3);
            but1 = new JButton ("Finish");
            but1.setBounds(375,70,150,16);
            but1.addActionListener(new Yes(selected,i,this));
            panelis.add(but1);
        } else if (i == 8) {
//            if (!sudah[0]) {
            panelis.setLayout(null);
            //image1 = new ImageIcon(this.getClass().getResource("icon player.png"));
            label1 = new JLabel();
            //image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
            label1.setBounds(0,0,100,100);
            panelis.add(label1);
            label2 = new JLabel("Nama : Uncle Mc Donald");
            label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label2.setBounds(400,7,150,30);
            panelis.add(label2);
            label3 = new JLabel ("You're such a loser man..");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,0,450,90);
            panelis.add(label3);
            but1 = new JButton ("Next");
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
                Game.instance().setDialogBox(boces(i), panel);
            } else if (state == 3) {
                i++;
                Game.instance().setDialogBox(boces(i), panel);
            } else if (state == 4) {
                i++;
                Object[] args = new Integer[1];
                args[0] = 4;
                Game.instance().seek(ParentState.SELECTPULMOSIS, args);
            } else if (state == 5) {
                if (Game.instance().getStory().getWinBattle(3)) {
                    i++;
                } else {
                    i = 8;
                }
                Game.instance().setDialogBox(boces(i), panel);
            } else if (state == 6) {
                i++;
                Game.instance().setDialogBox(boces(i), panel);
            } else if (state == 7) {
                Game.instance().goTo(ParentState.GAMEFINISH, null);
            } else if (state == 8) {
                Game.instance().goTo(ParentState.GAMEOVER, null);
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
            if (state == 3) {
                i=8;
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
        Game.instance().goTo(ParentState.FINALSTATE,null);
    }
}