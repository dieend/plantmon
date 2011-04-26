/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plantmon.entity.movingObject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import plantmon.entity.MovingObject;
import plantmon.game.GridMap;
import plantmon.game.Point2D;
import plantmon.game.TalkPanel;
import plantmon.system.Cancellable;
import plantmon.system.Selectable;
import sun.font.Font2D;

/**
 *
 * @author novan hamonangan
 */
public class AIenemy extends MovingObject implements Cancellable,Selectable,Runnable{

    int level;
    int tipe;
    int HP;
    int atk;
    int def;
    int agi;
    int attacked;
    int dmg;
    int range;
    int missed;
    int chargeMeter;
    private boolean active;
    private boolean selected;
    private boolean move;
    private boolean attack;

    double miss;
    private int attackRange;
    public static final int easy=7;
    public static final int medium=8;
    public static final int hard=9;
    public static final int boss=10;

    //code 0-2 untuk easy enemy
    public static final int peach=0;    //tipe atk
    public static final int papaya=1;   //tipe int
    public static final int kiwifruit=2;//tipe agi

    //code 3-5 untuk medium enemy
    public static final int cranberry=3;//tipe atk
    public static final int blueberry=4;//tipe int
    public static final int barberry=5;//tipe agi

    //code 6-2 untuk hard enemy
    public static final int blacklobak=6;//tipe atk
    public static final int blackjagung=7;//tipe int
    public static final int blacknanas=8;//tipe agi

    //code 9 untuk boss
    public static final int megabadpumpkin=9;//all tipe

    //code untuk orang baik
    public static final int kubis=10;
    public static final int paprika=11;
    public static final int nanas=12;

    private String name;

    public boolean isAlreadyMove(){
        return move;
    }

    public void makeAttack(){
        attack=true;
    }

    public void miss(){
        miss=100;
    }
    
    public AIenemy(GridMap map,JPanel panel,Graphics2D g2d,int tipe,int level,int levelpb){
        super(map,panel,g2d);
        init();
        if (level==easy)
        {
            this.level=levelpb-2;
            if (tipe==peach)
            {
                range=level-1;
                attackRange=level-2;
                name="peach";
            }else if (tipe==papaya)
            {
                range=level-2;
                attackRange=level;
                name="papaya";
            }else if (tipe==kiwifruit)
            {
                range=level+1;
                attackRange=level-1;
                name="kiwifruit";
            }
        }else  if (level==medium)
        {
            this.level=levelpb-1;
            if (tipe==cranberry)
            {
                range=level-1;
                attackRange=level-2;
                name="cranberry";
            }else if (tipe==blueberry)
            {
                range=level-2;
                attackRange=level;
                name="blueberry";
            }else if (tipe==barberry)
            {
                range=level+1;
                attackRange=level-1;
                name="barberry";
            }
        }else if (level==hard)
        {
            this.level=levelpb;
            if (tipe==blacklobak)
            {
                range=level+1;
                attackRange=level-2;
                name="blacklobak";
            }else if (tipe==blackjagung)
            {
                range=level-2;
                attackRange=level;
                name="blackjagung";
            }else if (tipe==blacknanas)
            {
                range=level+1;
                attackRange=level-1;
                name="blacknanas";
            }
        }
        this.tipe=tipe;
        atk = 0;
        attacked = 0;
        miss = 0.05;
//        enemy = en;
        setDefend();
        setAgi();
        setAttack();
        setHP();
        chargeMeter = 0;
        move = false;
    }

    @Override
    public void update(){
        chargeMeter++;
        if ((!active) && (chargeMeter>1000/agi))
        {
            active=true;
            move=false;
            attack=false;
        }
        super.updateAction();
    }

    @Override
    public void draw(){
        super.draw();
        if (isActive()){
            creature.graphics().drawString("ACTIVE", position().IntX(), position().IntY());
        }
        if (attacked>0){//maksudnya telah menyerang
            if (dmg==-9999){
                creature.graphics().drawString("miss", position().IntX(), position().IntY());
            }else{
                creature.graphics().drawString(""+dmg, position().IntX(), position().IntY());
            }
            attacked--;
        }
        if (missed>0){
            creature.graphics().drawString("miss", position().IntX(), position().IntY());
            missed--;
        }
    }

    public void drawBounds() {
        if (isActive()){
            creature.drawBounds(Color.blue); 
        }
    }

    public JPanel get_Info() {

        ImageIcon image1=null;
        JLabel label1;
        JLabel label2;
        JLabel label3;

        JPanel panel = new TalkPanel();
        panel.setLayout(null);

        label1 = new JLabel(image1);
        label1.setBounds(0, 0, 100, 100);
        panel.add(label1);

        if (type!=megabadpumpkin)
            label2 = new JLabel("Name : "+ name);
        else
            label2 = new JLabel("Name : ??????????????");
        
        label2.setFont(new Font("Times New Roman",Font.BOLD,16));
        label2.setBounds(120, 0, 150, 30);
        panel.add(label2);

        return panel;
    }
    
    @Override
    protected void init() {
        load("picture/anim",4,1,32,32);
        creature.setImageName("picture/anim");
        creature.setVelocity(new Point2D(0,0));
        creature.setFrameDelay(1);
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void move(int gx,int gy,Object lock,Boolean[] cancel){
        move=true;
        addAction(lock, new Point2D(gx,gy)); 
    }

    public void doDamage(PulmosisBattle pulmo){
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

    public void setDamage(int i){
        dmg=i;
    }

    public void setAttacked(int i){
        attacked=i;
    }

    public int getHP(){
        return HP;
    }

    public int getRange(){
        return range;
    }

    public int getAttack(){
        return atk;
    }

    public int getDefend(){
        return def;
    }

    public int getAgi(){
        return agi;
    }

    public void setHP(){
        HP = level + atk * 2 + def;
    }

    public void setHP(int HP){
        this.HP=HP;
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

    
    public void cancel(Object lock) {
        destination.remove(lock);
        this.lock.remove(lock);
        this.numAction--;
        creature.setFinalPosition(this.position().IntX(),this.position().IntY());
        inAction = false;
        inAction = false;
    }

    public boolean isActive(){
        return active;
    }

    public void run() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
