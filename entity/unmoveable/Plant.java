package plantmon.entity.unmoveable;

import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import plantmon.entity.Unmoveable;
import plantmon.entity.movingObject.Player;
import plantmon.game.GridMap;
import plantmon.game.Point2D;
import plantmon.states.FarmState;
import plantmon.system.Actionable;
import plantmon.system.RunnableListener;
import plantmon.system.Selectable;

public class Plant extends Unmoveable implements Actionable,
                                                Selectable{
    //bisa diapain aja?
    final public static int BIBITNOSIRAM    = 0;
    final public static int BIBITSIRAM      = 1;
    final public static int REMAJANOSIRAM   = 2;
    final public static int REMAJASIRAM     = 3;
    final public static int DEWASANOSIRAM   = 4;
    final public static int DEWASASIRAM     = 5;
    final public static int BIBITMATI       = 6;
    final public static int TANAMANMATI     = 7;

    int posisi;
    int fase;

    int	typeTanaman;
    int season;
    int	happyMeter;
    int	titikDewasa;
    int	titikPanen;
    int	umur;
    boolean panenBerulang;
    // konstruktor default
    //konstruktor dari item.
    public Plant(GridMap map, JPanel panel, Graphics2D g2d,int gx,int gy){
        super(map, panel, g2d);
        init();
        entity.setPosition(new Point2D(gx,gy));
        entity.setFinalPosition(gx, gy);
    }
    public void drawBounds(){
        entity.drawBounds(Color.GREEN);
    }
    public int getFase(){
        return fase;
    }
    public String getInfo(){
        return null;
    }
    public JPopupMenu getMenu(Selectable selected){
        if (selected instanceof Player){
            final Player player = (Player) selected;
            JPopupMenu menu = new JPopupMenu();
            if ((fase == BIBITNOSIRAM) || (fase == BIBITSIRAM) || (fase == BIBITMATI)) {
                JMenuItem itemplant2;
                itemplant2 = new JMenuItem("plow");
                itemplant2.addActionListener(new Plow(selected));
                menu.add(itemplant2);

                JMenuItem itemplant3;
                if (fase == BIBITNOSIRAM) {
                    itemplant3 = new JMenuItem("water");
                    itemplant3.addActionListener(new Water(selected));
                    menu.add(itemplant3);
                }
            }
            else if ((fase == REMAJANOSIRAM) || (fase == REMAJASIRAM) || (fase == DEWASANOSIRAM) || (fase == DEWASASIRAM) || (fase == TANAMANMATI)) {
                JMenuItem itemplant4;
                itemplant4 = new JMenuItem("slash");
                itemplant4.addActionListener(new Slash(selected));
                menu.add(itemplant4);
                if ((fase == REMAJANOSIRAM) || (fase == DEWASANOSIRAM)) {
                    JMenuItem itemplant5;
                    itemplant5 = new JMenuItem("water");
                    itemplant5.addActionListener(new Water(selected));
                    menu.add(itemplant5);
                }
                if ((fase == DEWASANOSIRAM)||(fase == DEWASASIRAM)) {
                    JMenuItem itemplant6;
                    itemplant6 = new JMenuItem("harvest");
                    itemplant6.addActionListener(new Harvest(selected));
                    menu.add(itemplant6);
                }
        }
            menu.pack();
            return menu;
        }
        return null;
    }

    class Water extends RunnableListener {
        Water(Selectable selected){
            super(selected);
        }
        public void run() {
            // membuat player berjalan ke posisi tumbuhan
            Player player = (Player) selected;
            // gx dan gy adalah posisi tumbuhan saat ini
            int gx = (int)Plant.this.getPosition().X();
            int gy = (int)Plant.this.getPosition().Y();
            Object lock = new Object();
            Boolean[] cancel = new Boolean[1];
            player.move(gx, gy, lock,cancel);
            synchronized(lock){
                try {
                    lock.wait(); // tunggu player sampai ke posisi tumbuhan
                } catch (InterruptedException e){}
            }
            // setelah player sampai, siram tanaman.
            if (!cancel[0]){
                map.pop(gx, gy);
                fase = fase + 1;
            }
        }
    }
    /**
     * TODO: Buat item berdasarkan tanaman yang diharvest
     */
    class Harvest extends RunnableListener {
        Harvest(Selectable selected){
            super(selected);
        }
        public void run() {
        // membuat player berjalan ke posisi tumbuhan
            Player player = (Player) selected;
            // gx dan gy adalah posisi tumbuhan saat ini
            int gx = (int)Plant.this.getPosition().X();
            int gy = (int)Plant.this.getPosition().Y();
            Object lock = new Object();
            Boolean[] cancel = new Boolean[1];
            player.move(gx, gy, lock,cancel);
            synchronized(lock){
                try {
                    lock.wait(); // tunggu player sampai ke posisi tumbuhan
                } catch (InterruptedException e){}
            }
            // setelah player sampai, siram tanaman.
            if (!cancel[0]){
                map.pop(gx, gy);
                if (panenBerulang) {
                    fase = REMAJANOSIRAM;
                }
                else {
                    fase = TANAMANMATI;
                }
                FarmState.text.append("harvesting at ("+(gx/80)+","+(gy/80)+")\n");
            }
        }
    }

    class Plow extends RunnableListener {
        Plow(Selectable selected){
            super(selected);
        }
        public void run() {
            // membuat player berjalan ke posisi tumbuhan
            Player player = (Player) selected;
            // gx dan gy adalah posisi tumbuhan saat ini
            int gx = (int)Plant.this.getPosition().X();
            int gy = (int)Plant.this.getPosition().Y();
            Object lock = new Object();
            Boolean[] cancel = new Boolean[1];
            player.move(gx, gy, lock,cancel);
            synchronized(lock){
                try {
                    lock.wait(); // tunggu player sampai ke posisi tumbuhan
                } catch (InterruptedException e){}
            }
            if (!cancel[0]){
                map.pop(gx, gy);
                map.pop(gx, gy);
                FarmState.text.append("plowing  ("+(gx/80)+","+(gy/80)+")\n");
            }
        }
    }
    class Slash extends RunnableListener {
        Slash(Selectable selected){
            super(selected);
        }
        public void run() {
        // membuat player berjalan ke posisi tumbuhan
            Player player = (Player) selected;
            // gx dan gy adalah posisi tumbuhan saat ini
            int gx = (int)Plant.this.getPosition().X();
            int gy = (int)Plant.this.getPosition().Y();
            Object lock = new Object();
            Boolean[] cancel = new Boolean[1];
            player.move(gx, gy, lock,cancel);
            synchronized(lock){
                try {
                    lock.wait(); // tunggu player sampai ke posisi tumbuhan
                } catch (InterruptedException e){}
            }
            if (!cancel[0]){
                map.pop(gx, gy);
                map.pop(gx, gy);
                FarmState.text.append("slashing at ("+(gx/80)+","+(gy/80)+")\n");
            }
        }
    }
    public void init(){
        entity.load("picture/bibit.png", 1, 1, 80, 80);
        entity.setFrameDelay(5);
    }
}