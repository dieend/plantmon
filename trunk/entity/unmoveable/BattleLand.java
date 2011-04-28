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
        if (player.position().IntX()==posisi.IntX() && player.position().IntY()==posisi.IntY()){
            ite = new JMenuItem("");
            if (player.getTipe()==PulmosisBattle.Kentang || player.getTipe()==PulmosisBattle.Jagung || player.getTipe()==PulmosisBattle.Bawang || player.getTipe()==PulmosisBattle.Terong || player.getTipe()==PulmosisBattle.Wortel){
                if (player.getTipe()==PulmosisBattle.Kentang || player.getTipe()==PulmosisBattle.Jagung){
                    ite.setText("CLOCK UP");
                }else if (player.getTipe()==PulmosisBattle.Bawang || player.getTipe()==PulmosisBattle.Terong){
                    ite.setText("FLASH MOTION");
                }else if (player.getTipe()==PulmosisBattle.Wortel){
                    ite.setText("TENZA ZANGETSU"); 
                }
                ite.addActionListener(new SpeedUp(selected, new Point2D(posisi.IntX() / Utilities.GRIDSIZE , posisi.IntY() / Utilities.GRIDSIZE)));
                menu.add(ite);
                if (player.specialtotal<1){
                    ite.setEnabled(false); 
                }
            }
            if (player.specialtotal<1){
                ite.setEnabled(false); 
            }
        }
        if (!player.getStatusEnemy()) {
            if (pulE instanceof PulmosisBattle) {
                if (((PulmosisBattle) pulE).getStatusEnemy()) {
                    if (inAttackRange(player) && !player.isAlreadyAttack()){
                        ite = new JMenuItem("attack");
                        ite.addActionListener(new Attack(selected,new Point2D(posisi.IntX() / Utilities.GRIDSIZE, posisi.IntY() / Utilities.GRIDSIZE)));
                        menu.add(ite);
                    }

                    //TAMBAHKAN FUNGSI THUNDERPUNCH
                    if (inAttackRange(player) && !player.isAlreadyAttack()){
                        if (player.getTipe()==PulmosisBattle.Lobak || player.getTipe()==PulmosisBattle.Kubis || player.getTipe()==PulmosisBattle.Tomat || player.getTipe()==PulmosisBattle.Labu || player.getTipe()==PulmosisBattle.Paprika){
                            ite = new JMenuItem("");
                            if (player.getTipe()==PulmosisBattle.Lobak || player.getTipe()==PulmosisBattle.Kubis){
                                ite.setText("THUNDER PUNCH");
                            }else if (player.getTipe()==PulmosisBattle.Tomat || player.getTipe()==PulmosisBattle.Labu){
                                ite.setText("LIGHTNING SCISSOR");
                            }else if (player.getTipe()==PulmosisBattle.Paprika){
                                ite.setText("MAGNA STORM");
                            }
                            ite.addActionListener(new Thunder(selected, new Point2D(posisi.IntX()/Utilities.GRIDSIZE,posisi.IntY()/Utilities.GRIDSIZE))); 
                            menu.add(ite);
                            if (player.specialtotal<1){
                                ite.setEnabled(false); 
                            }
                        }
                    }
               }

               //TAMBAHKAN FUNGSI HEALING
               if (!((PulmosisBattle)pulE).getStatusEnemy()) {
                    if (player.getTipe()==PulmosisBattle.Timun || player.getTipe()==PulmosisBattle.Stroberi || player.getTipe()==PulmosisBattle.Nanas || player.getTipe()==PulmosisBattle.Ubi || player.getTipe()==PulmosisBattle.Bawang || player.getTipe()==PulmosisBattle.Bayam){
//                        System.out.println("YA IYALAH");
                        ite = new JMenuItem("");
                        if (player.getTipe()==PulmosisBattle.Timun || player.getTipe()==PulmosisBattle.Stroberi){
                            ite.setText("HEALING");
                        }else if(player.getTipe()==PulmosisBattle.Ubi || player.getTipe()==PulmosisBattle.Nanas){
                            ite.setText("MEGA HEALING");
                        }else if(player.getTipe()==PulmosisBattle.Bayam){
//                            System.out.println("INI BAYAM");
                            ite.setText("COLOSAL LIFE");
                        }
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
//            System.out.format("%d %d\n",gx,gy);
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

//            Object lock = new String("stop");
//            Boolean[] cancel = new Boolean[1];
//            player.makeAttack();
//            player.move(gx, gy, lock,cancel);
//            synchronized(lock){
//                try {
//                    lock.wait();
//                } catch (InterruptedException e){
//                    return;
//                }
//            }
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
                PulmosisBattle pbh = (PulmosisBattle)pbhealed;
                if (player.defaultspecialtotal==3){
                    if (pbh.getHP()+20>pbh.getMaxHP()){
                        pbh.setHP(pbh.getMaxHP());
                    }else{
                        pbh.setHP(pbh.getHP()+20);
                    }
                    pbh.doHealing();
                }
                if (player.defaultspecialtotal==2){
                    pbh.setHP(pbh.getMaxHP());
                    pbh.doHealing();
                }
                if (player.defaultspecialtotal==1){
                    int i,j;
                    for(i=0;i<map.getRow();++i){
                        for(j=0;j<map.getColumn();++j){
                            if (map.getTop(i, j) instanceof PulmosisBattle){
                                PulmosisBattle pulFriend = (PulmosisBattle)map.getTop(i, j);
                                if (!pulFriend.getStatusEnemy()){
                                    pulFriend.setHP(pulFriend.getMaxHP());
                                    pulFriend.doHealing();
                                }
                            }
                        }
                    }
                }
                
//                ((PulmosisBattle)pbhealed).setHP(((PulmosisBattle)pbhealed).getMaxHP());
//                ((PulmosisBattle)pbhealed).doHealing();

                player.specialtotal--;
//                System.out.println("HEALED, new HP : " + ((PulmosisBattle)pbhealed).getHP());
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

class Thunder extends RunnableListener {
        Point2D posisi;
        public Thunder(Selectable selected,Point2D posisi){
            super(selected);
            this.posisi = posisi;
        }
        public void run() {
            PulmosisBattle player = (PulmosisBattle) selected;
            int gx = BattleLand.this.getPosition().IntX();
            int gy = BattleLand.this.getPosition().IntY();

//            Object lock = new String("stop");
//            Boolean[] cancel = new Boolean[1];
//            player.makeAttack();
//            player.move(gx, gy, lock,cancel);
//            synchronized(lock){
//                try {
//                    lock.wait();
//                } catch (InterruptedException e){
//                    return;
//                }
//            }
//            System.out.println("HP:"+enemy.getHP());

//            System.out.println("HP:"+enemy.getHP());
            Object enemy = map.getTop(posisi.IntX(), posisi.IntY());
            if (enemy instanceof PulmosisBattle){
                PulmosisBattle pulEn = (PulmosisBattle)map.getTop(posisi.IntX(), posisi.IntY());
                if (player.defaultspecialtotal==3){
                    player.setHP(player.getHP()-20);
                    pulEn.doThunder();
                    if (pulEn.getHP()<=0){
                        map.pop(gx, gy); 
                    }
                }
                if (player.defaultspecialtotal==2){
                    if (pulEn.getHP()>(pulEn.getMaxHP()*3/4)){
                        pulEn.setHP(pulEn.getHP()-(pulEn.getMaxHP()/3));
                    }else{
                        pulEn.setHP(pulEn.getHP()/2);
                    }
//                    System.out.println("L SCISSOR");
                    pulEn.doThunder();
                }
                if (player.defaultspecialtotal==1){
                    int i,j;
                    for(i=0;i<map.getRow();++i){
                        for(j=0;j<map.getColumn();++j){
                            if (map.getTop(i, j) instanceof PulmosisBattle){
                                pulEn = (PulmosisBattle)map.getTop(i, j);
                                if (pulEn.getStatusEnemy()){
                                    if (pulEn.getHP()>(pulEn.getMaxHP()*3/4)){
                                        pulEn.setHP(pulEn.getHP()-(pulEn.getMaxHP()/3));
                                    }else{
                                        pulEn.setHP(pulEn.getHP()/3);
                                    }
                                    pulEn.doThunder();
                                }
                            }
                        }
                    }
                }
//                player.doDamage((PulmosisBattle)enemy);
                if (((PulmosisBattle)enemy).getHP() <= 0) {
                    map.pop(gx, gy);
                    player.levelUp(12);
                }
                player.specialtotal--;
            } else {
                player.miss();
            }
            player.resetChargeMeter();
        }
    }


    class SpeedUp extends RunnableListener {
        Point2D posisi;
        public SpeedUp(Selectable selected,Point2D posisi){
            super(selected);
            this.posisi = posisi;
        }
        public void run() {
            PulmosisBattle player = (PulmosisBattle) selected;
            int gx = BattleLand.this.getPosition().IntX();
            int gy = BattleLand.this.getPosition().IntY();

            if (player.defaultspecialtotal==3){
                player.tagi=player.getAgi()+3;
                player.doSpeedup(600);
            }else if (player.defaultspecialtotal==2){
                player.tagi=player.getAgi()+5;
                player.doSpeedup(700);
            }else if (player.defaultspecialtotal==1){
                player.tagi=player.getAgi()+8;
                player.doSpeedup(900);
            }
            player.specialtotal--;
//            Object lock = new String("stop");
//            Boolean[] cancel = new Boolean[1];
//            player.makeAttack();
//            player.move(gx, gy, lock,cancel);
//            synchronized(lock){
//                try {
//                    lock.wait();
//                } catch (InterruptedException e){
//                    return;
//                }
//            }
//            System.out.println("HP:"+enemy.getHP());

//            System.out.println("HP:"+enemy.getHP());

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
