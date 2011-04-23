package plantmon.entity.unmoveable;

import java.awt.Graphics2D;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import plantmon.entity.Unmoveable;
import plantmon.entity.movingObject.Pulmosis;
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
    public boolean cekDistance(PulmosisBattle player){
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
//        i = player.getRange();
//        while (i > 0 && cek) {
//            if (lengthX == i) {
//                if (lengthY <= player.getRange() - i) {
//                    ite = new JMenuItem("move");
//                    ite.addActionListener(new Move(selected));
//                    menu.add(ite);
//                    if (map.getTop(posisi.IntX() / Utilities.GRIDSIZE, posisi.IntY() / Utilities.GRIDSIZE) instanceof Pulmosis) {
//                        Pulmosis pul = (Pulmosis) map.getTop(posisi.IntX() / Utilities.GRIDSIZE, posisi.IntY() / Utilities.GRIDSIZE);
//                        ite = new JMenuItem("attack");
//                        ite.addActionListener(new Attack(selected,pul));
//                        menu.add(ite);
//                    }
//                    cek = false;
//                } else {
//                    i--;
//                }
//            } else if (lengthY == i) {
//                if (lengthX <= player.getRange() - i) {
//                    cek = false;
//                } else {
//                    i--;
//                }
//            } else {
//                i--;
//            }
//        }
    }
    public JPopupMenu getMenu(Selectable selected) {
        final PulmosisBattle player = (PulmosisBattle) selected;
        int i;
        boolean cek = true;
        JPopupMenu menu = new JPopupMenu();
        JMenuItem ite;
        if (cekDistance(player)){
            ite = new JMenuItem("move");
            ite.addActionListener(new Move(selected));
            menu.add(ite);
            if (map.getTop(posisi.IntX() / Utilities.GRIDSIZE, posisi.IntY() / Utilities.GRIDSIZE) instanceof Pulmosis) {
                Pulmosis pul = (Pulmosis) map.getTop(posisi.IntX() / Utilities.GRIDSIZE, posisi.IntY() / Utilities.GRIDSIZE);
                ite = new JMenuItem("attack");
                ite.addActionListener(new Attack(selected,pul));
                menu.add(ite);
            }
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
            Object lock = new String("paused");
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
        Pulmosis enemy;
        public Attack(Selectable selected,Pulmosis pul){
            super(selected);
            enemy = pul;
        }
        public void run() {
            Pulmosis player = (Pulmosis) selected;
            int gx = BattleLand.this.getPosition().IntX();
            int gy = BattleLand.this.getPosition().IntY();
            System.out.format("%d %d\n",gx,gy);
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
            System.out.println("HP:"+enemy.getHP());
            player.doDamage(enemy);
            System.out.println("HP:"+enemy.getHP());
            if (enemy.getHP() <= 0) {
                map.pop(gx, gy);
                player.levelUp(12);
            }
        }
    }

}
