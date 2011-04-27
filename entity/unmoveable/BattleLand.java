package plantmon.entity.unmoveable;

import java.awt.Graphics2D;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import plantmon.entity.Unmoveable;
import plantmon.entity.movingObject.PulmosisBattle;
import plantmon.game.GridMap;
import plantmon.game.Point2D;
import plantmon.system.Actionable;
import plantmon.system.RunnableListener;
import plantmon.system.Selectable;
import plantmon.system.Utilities;

public class BattleLand extends Unmoveable implements Actionable {
    Point2D posisi;

    public BattleLand (int x, int y, GridMap map, JPanel panel, Graphics2D g2d) {
        super(map,panel,g2d);
        posisi = new Point2D(x,y);
        entity.setPosition(posisi);
    }
    
    public Point2D getPosition () {
        return posisi;
    }
    public boolean inDistance(PulmosisBattle player){
        int lengthX, lengthY;
        lengthX = posisi.IntX() / Utilities.GRIDSIZE - player.position().IntX() / Utilities.GRIDSIZE;
        if (lengthX < 0) {
            lengthX = -1 * lengthX;
        }

        lengthY = posisi.IntY() / Utilities.GRIDSIZE - player.position().IntY() / Utilities.GRIDSIZE;
        if (lengthY < 0) {
            lengthY = -1 * lengthY;
        }

        if (lengthY+lengthX <= player.getRange()) return true;
        return false;
    }
    public boolean inAttackRange(PulmosisBattle player){
        int lengthX, lengthY;
        lengthX = posisi.IntX() / Utilities.GRIDSIZE - player.position().IntX() / Utilities.GRIDSIZE;
        if (lengthX < 0) {
            lengthX = -1 * lengthX;
        }

        lengthY = posisi.IntY() / Utilities.GRIDSIZE - player.position().IntY() / Utilities.GRIDSIZE;
        if (lengthY < 0) {
            lengthY = -1 * lengthY;
        }
        if (!player.isAlreadyMove() && lengthY+lengthX <= player.getRange()+player.getAttackRange()) return true;
        else if (lengthY+lengthX <= player.getAttackRange()) return true;
        return false;
    }
    
    public JPopupMenu getMenu(Selectable selected) {
        final PulmosisBattle player = (PulmosisBattle) selected;
        int i;
        boolean cek = true;
        JPopupMenu menu = new JPopupMenu();
        JMenuItem ite;
        Object pulE = map.getTop(posisi.IntX()/Utilities.GRIDSIZE, posisi.IntY()/Utilities.GRIDSIZE);
        if (!player.getStatusEnemy()) {
            if (pulE instanceof PulmosisBattle) {
                if (((PulmosisBattle) pulE).getStatusEnemy()) {
                    if (inAttackRange(player) && !player.isAlreadyAttack()){
                        ite = new JMenuItem("attack");
                        ite.addActionListener(new Attack(selected,new Point2D(posisi.IntX() / Utilities.GRIDSIZE, posisi.IntY() / Utilities.GRIDSIZE)));
                        menu.add(ite);
                    }
               }

               //TAMBAHKAN FUNGSI HEALING
               if (!((PulmosisBattle)pulE).getStatusEnemy()) {
                    if (player.getTipe()==PulmosisBattle.Timun){
                        System.out.println("YA IYALAH");
                        ite = new JMenuItem("HEALING");
                        ite.addActionListener(new Healing(selected, new Point2D(posisi.IntX() / Utilities.GRIDSIZE, posisi.IntY() / Utilities.GRIDSIZE)));
                        menu.add(ite);
                        if (player.specialtotal<1){
                            ite.setEnabled(false); 
                        }
                    }
               }
               //////////////////////////
            }
            if (inDistance(player) && !player.isAlreadyMove()){
                ite = new JMenuItem("move");
                ite.addActionListener(new Move(selected));
                menu.add(ite);
            }
            ite = new JMenuItem("skip");
            ite.addActionListener(new Skip(selected));
            menu.add(ite);
        }
        
        menu.pack();
        return menu;
    }

    @Override
    public void init() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    class Move extends RunnableListener {
        public Move(Selectable selected){
            super(selected);
        }
        public void run() {
            PulmosisBattle player = (PulmosisBattle) selected;
            int gx = BattleLand.this.getPosition().IntX();
            int gy = BattleLand.this.getPosition().IntY();
            System.out.format("%d %d\n",gx,gy);
            Object lock = new String("stop");
            Boolean[] cancel = new Boolean[1];
            player.move(gx, gy, lock,cancel);
            synchronized(lock){
                try {
                    lock.wait();
                } catch (InterruptedException e){
                    return;
                }
            }
            
        }
    }

    class Attack extends RunnableListener {
        Point2D posisi;
        public Attack(Selectable selected,Point2D posisi){
            super(selected);
            this.posisi = posisi;
        }
        public void run() {
            PulmosisBattle player = (PulmosisBattle) selected;
            int gx = BattleLand.this.getPosition().IntX();
            int gy = BattleLand.this.getPosition().IntY();
            Object lock = new String("stop");
            Boolean[] cancel = new Boolean[1];
            player.makeAttack();
            player.move(gx, gy, lock,cancel);
            synchronized(lock){
                try {
                    lock.wait();
                } catch (InterruptedException e){
                    return;
                }
            }
//            System.out.println("HP:"+enemy.getHP());
            
//            System.out.println("HP:"+enemy.getHP());
            Object enemy = map.getTop(posisi.IntX(), posisi.IntY());
            if (enemy instanceof PulmosisBattle){
                player.doDamage((PulmosisBattle)enemy);
                if (((PulmosisBattle)enemy).getHP() <= 0) {
                    map.pop(gx, gy);
                    player.levelUp(12);
                }
            } else {
                player.miss();
            }
            player.resetChargeMeter();
        }
    }
    
    class Healing extends RunnableListener {
        Point2D posisi;
        public Healing(Selectable selected,Point2D posisi){
            super(selected);
            this.posisi = posisi;
        }
        public void run() {
            PulmosisBattle player = (PulmosisBattle) selected;
            int gx = BattleLand.this.getPosition().IntX();
            int gy = BattleLand.this.getPosition().IntY();
//            Object lock = new String("stop");
//            Boolean[] cancel = new Boolean[1];
            if (player.specialtotal>0){
                Object pbhealed = map.getTop(posisi.IntX() , posisi.IntY());
                ((PulmosisBattle)pbhealed).setHP(((PulmosisBattle)pbhealed).getMaxHP());
                ((PulmosisBattle)pbhealed).doHealing();
                player.specialtotal--;
                System.out.println("HEALED, new HP : " + ((PulmosisBattle)pbhealed).getHP());
            }

            /*
            player.makeAttack();
            player.move(gx, gy, lock,cancel);
            synchronized(lock){
                try {
                    lock.wait();
                } catch (InterruptedException e){
                    return;
                }
            }
//            System.out.println("HP:"+enemy.getHP());

//            System.out.println("HP:"+enemy.getHP());
            Object enemy = map.getTop(posisi.IntX(), posisi.IntY());
            if (enemy instanceof PulmosisBattle){
                player.doDamage((PulmosisBattle)enemy);
                if (((PulmosisBattle)enemy).getHP() <= 0) {
                    map.pop(gx, gy);
                    player.levelUp(12);
                }
            } else {
                player.miss();
            }*/
            player.resetChargeMeter();
        }
    }

    class Skip extends RunnableListener {
        public Skip(Selectable selected){
            super(selected);
        }
        public void run() {
            PulmosisBattle player = (PulmosisBattle) selected;
            player.resetChargeMeter();
        }
    }

}
