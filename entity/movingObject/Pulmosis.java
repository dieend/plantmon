package plantmon.entity.movingObject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import plantmon.entity.MovingObject;
import plantmon.game.GridMap;
import plantmon.game.Point2D;
import plantmon.game.TalkPanel;
import plantmon.states.Game;
import plantmon.system.Cancellable;
import plantmon.system.Selectable;
import plantmon.system.Utilities;


public class Pulmosis extends MovingObject implements Cancellable,
                                                    Selectable{
    private ImageIcon image1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    int HP;
    int level;
    int atk;
    int def;
    int agi;
    int exp;
    int attacked;
    int dmg;
    int range;
    boolean enemy;
    double miss;
    public static final int Lobak = 0;
    public static final int Timun = 1;
    public static final int Kentang = 2;
    
    public Pulmosis(GridMap map, JPanel panel, Graphics2D g2d, int tipe, boolean en) {
        super(map,panel,g2d);
        init();
        if (tipe == Lobak) {
            level = 1;
            range = 2;
        } else if (tipe == Timun) {
            level = 3;
            range = 3;
        } else if (tipe == Kentang) {
            level = 5;
            range = 2;
        }
        atk = 0;
        attacked = 0;
        miss = 0.05;
        enemy = en;
        setDefend();
        setAgi();
        setAttack();
        setHP();
    }
    @Override public void draw() {
        super.draw();
        
         if (attacked > 0) {
             if (dmg == -99999) {
                creature.graphics().drawString("miss", 100, 100);
             } else {
                System.out.print("drawing pulmo");
                creature.graphics().drawString(""+dmg, 100, 100);
             }
            attacked--;
        }
    }
    
    @Override public void drawBounds() {
        creature.drawBounds(Color.RED);
       
    }
    @Override public JPanel get_Info(){
        JPanel panel;
        panel = new TalkPanel();
        panel.setLayout(null);
//        image1 = new ImageIcon(this.getClass().getResource("player.jpg"));
        label1 = new JLabel(image1);
        label1.setBounds(0,0,100,100);
        panel.add(label1);
        label2 = new JLabel("Nama : " + Game.instance().getName());
        label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label2.setBounds(120,0,150,30);
        panel.add(label2);

        return panel;

    }
    @Override public void init() {
        //inisiasi semua variable disini.

        load("picture/anim", 4,1,32,32);
        creature.setImageName("picture/anim");
        creature.setVelocity(new Point2D(0,0));
        creature.setFrameDelay(1);
    }
    public void move(int gx,int gy,Object lock,Boolean[] cancel){
        addAction(lock,new Point2D(gx,gy));
        if (map.getTop(gx / Utilities.GRIDSIZE, gy / Utilities.GRIDSIZE) instanceof Pulmosis) {
            cancel[0] = true;
        } else {
            cancel[0] = false;
        }
//        Canceller ca = new Canceller(creature.panel(),creature.graphics(),
//                                    gx, gy, cancel,lock,(Cancellable)this,numAction-1);
//        map.push(gx, gy, ca);
    }

    public void doDamage (Pulmosis pulmo) {
        int HP = pulmo.getHP();
        int minus = 0;
        Random gene = new Random();
        int x = gene.nextInt(100);
        if (x < 94) {
            minus = (atk + level + gene.nextInt(5) + 1) - pulmo.getDefend();
            if (minus<=0) {
                 HP = HP - 1;
                 minus = 1;
            } else {
                HP = HP - minus;
            }
        } else {
            minus = -99999;
        }
        pulmo.setAttacked(100);
        pulmo.setDamage(minus);
        pulmo.setHP(HP);
    }

    public void setDamage (int i) {
        dmg = i;
    }

    public void setAttacked(int i) {
        attacked = i;
    }

    public int getHP () {
        return HP;
    }

    public int getRange () {
        return range;
    }

    public int getAttack () {
        return atk;
    }

    public int getDefend () {
        return def;
    }

    public int getAgi () {
        return agi;
    }

    public void setHP () {
        HP = level + atk * 2 + def;
    }

    public void setHP (int HP) {
        this.HP = HP;
    }

    public void setAttack () {
        atk = level * 3;
    }

    public void setDefend () {
        def = level * 2;
    }

    public void setAgi () {
        agi = (level * 4 + def + 10) * 12/100;
    }

    public void attackUp () {
        atk = atk + atk/10 + 1;
    }

    public void defendUp () {
        def = def + def/6 + 1;
    }

    public void agiUp () {
        agi = agi + agi/5 + 1;
    }

    public void levelUp (int plus) {
        int x;
        int i;
        exp = exp + plus;
        if ((exp/30) > 0) {
            x = exp/30;
            level = level + x;
            for (i = 1; i <= x; i++) {
                attackUp();
                setHP();
            }
        }
        exp = exp % 30;
    }

    public boolean getStatusEnemy () {
        return enemy;
    }

    @Override
    public void cancel(Object lock){
        map.pop(destination.get(lock).X(), destination.get(lock).Y());
        System.out.println(destination.size());
        destination.remove(lock);
        this.lock.remove(lock);
        this.numAction--;
        creature.setFinalPosition(this.position().IntX(),this.position().IntY());
        inAction = false;
    }

    public static void main(String[] str){
        JFrame frame=new JFrame();
        BufferedImage bf = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Pulmosis a = new Pulmosis(new GridMap(), new JPanel(),bf.createGraphics(),Pulmosis.Kentang,true);
        frame.getContentPane().add(a.get_Info());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setVisible(true);
    }
}