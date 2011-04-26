/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plantmon.entity.movingObject;

import java.awt.Graphics2D;
import javax.swing.JPanel;
import plantmon.entity.MovingObject;
import plantmon.game.GridMap;
import plantmon.system.Cancellable;
import plantmon.system.Selectable;

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
                
            }else if (tipe==papaya)
            {
                range=level-2;
                attackRange=level;
            }else if (tipe==kiwifruit)
            {
                range=level+1;
                attackRange=level-1;
            }
        }else  if (level==medium)
        {
            this.level=levelpb-1;
            if (tipe==cranberry)
            {
                range=level-1;
                attackRange=level-2;
            }else if (tipe==blueberry)
            {
                range=level-2;
                attackRange=level;
            }else if (tipe==barberry)
            {
                range=level+1;
                attackRange=level-1;
            }
        }else if (level==hard)
        {
            this.level=levelpb;
            if (tipe==blacklobak)
            {
                range=level-1;
                attackRange=level-2;
            }else if (tipe==blackjagung)
            {
                range=level-2;
                attackRange=level;
            }else if (tipe==blacknanas)
            {
                range=level+1;
                attackRange=level-1;
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
            
        }
    }
    
    @Override
    protected void init() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void cancel(Object lock) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void drawBounds() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public JPanel get_Info() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void setDefend() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void setAgi() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void setAttack() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void setHP() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public boolean isActive(){
        return active;
    }

    public void run() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
