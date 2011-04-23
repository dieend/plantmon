package plantmon.states;

import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JPanel;
import plantmon.entity.movingObject.PulmosisLand;
import plantmon.game.GridMap;
import plantmon.game.Point2D;
import plantmon.system.Utilities;

public class StoryLine implements Runnable,Serializable {
    private int type;
    private ArrayList<Integer> sold;
    private int day;
    transient private GridMap map;
    transient private JPanel panel;
    PulmosisLand kentang;
    PulmosisLand lobak;
    PulmosisLand timun;
    private boolean active;
    Boolean[] belum;
    Thread storyloop;
    final Object lock = new String("exact");
    public StoryLine () {
        belum = new Boolean[3];
        belum[0] = true;
        belum[1] = true;
        belum[2] = true;
        kentang = new PulmosisLand(map,panel,null,2);
        lobak = new PulmosisLand(map,panel,null,0);
        timun =  new PulmosisLand(map,panel,null,1);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ArrayList<Integer> getSold() {
        return sold;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setSold(ArrayList<Integer> sold) {
        this.sold = sold;
    }

    public void setSold(int ID, int amm) {
        sold.set(ID,amm);
    }

    public GridMap getMap() {
        return map;
    }

    public void reinit (GridMap map, JPanel panel, Graphics2D g2d) {
        this.map = map;
        this.panel = panel;
//        this.g2d = g2d;
        kentang.reinit(map, g2d, panel);
        if (!belum[2]) map.push(kentang.position().IntX(),kentang.position().IntY(), kentang);
        lobak.reinit(map, g2d, panel);
        if (!belum[0]) map.push(lobak.position().IntX(),lobak.position().IntY(), lobak);
        timun.reinit(map,g2d,panel);
        if (!belum[1]) map.push(timun.position().IntX(),timun.position().IntY(), timun);
    }

    public void Story () {
        Boolean[] cancel = new Boolean[1];
        boolean arahKanan = false;
        if (map.getTop(5, 9) instanceof PulmosisLand) {
            //lobak.getCreature().setFinalPosition(Utilities.GRIDSIZE*3,Utilities.GRIDSIZE*9);
            lobak.move(3*Utilities.GRIDSIZE, 9*Utilities.GRIDSIZE, lock, cancel);
            synchronized(lock){
                try {
                    lock.wait();
                } catch (InterruptedException e){
//                    return;
                }
            }
            if (!cancel[0]){
                lobak.getCreature().setFinalPosition(3*Utilities.GRIDSIZE+5, 9*Utilities.GRIDSIZE+5);
            }
        } else if (map.getTop(3, 9) instanceof PulmosisLand) {
            //kentang.getCreature().setFinalPosition(Utilities.GRIDSIZE*3,Utilities.GRIDSIZE*6);
            lobak.move(5*Utilities.GRIDSIZE, 9*Utilities.GRIDSIZE, lock, cancel);
            synchronized(lock){
                try {
                    lock.wait();
                } catch (InterruptedException e){
//                    return;
                }
            }
            if (!cancel[0]){
//                map.pop(5*Utilities.GRIDSIZE, 9*Utilities.GRIDSIZE);
                lobak.getCreature().setFinalPosition(5*Utilities.GRIDSIZE+5, 9*Utilities.GRIDSIZE+5);
            }
        } else if (belum[0]){
            belum[0] = false;
            lobak.getCreature().setPosition(new Point2D(Utilities.GRIDSIZE*3,Utilities.GRIDSIZE*9));
            lobak.getCreature().setFinalPosition(Utilities.GRIDSIZE*3,Utilities.GRIDSIZE*9);
            Point2D pos = lobak.getCreature().position();
            map.push(pos.X(), pos.Y(), lobak);
            lobak.move(5*Utilities.GRIDSIZE, 9*Utilities.GRIDSIZE, lock, cancel);
            synchronized(lock){
                try {
                    lock.wait();
                } catch (InterruptedException e){
//                    return;
                }
            }
            if (!cancel[0]){
//                map.pop(5*Utilities.GRIDSIZE, 9*Utilities.GRIDSIZE);
                lobak.getCreature().setFinalPosition(5*Utilities.GRIDSIZE+5, 9*Utilities.GRIDSIZE+5);
            }
            //kentang.getCreature().setFinalPosition(Utilities.GRIDSIZE*3,Utilities.GRIDSIZE*4);
        }
        System.out.println("posisi lobak : "+lobak.position().IntX()+"  "+lobak.position().IntY());
        
        if (belum[1]){
            belum[1] = false;
            timun.getCreature().setPosition(new Point2D(Utilities.GRIDSIZE*2,Utilities.GRIDSIZE*6));
            timun.getCreature().setFinalPosition(Utilities.GRIDSIZE*2,Utilities.GRIDSIZE*6);
            Point2D pos = timun.getCreature().position();
            map.push(pos.X(), pos.Y(), timun);
            //kentang.getCreature().setFinalPosition(Utilities.GRIDSIZE*3,Utilities.GRIDSIZE*4);
        }
        
        if (day >= 5) {
            if (map.getTop(3, 6) instanceof PulmosisLand) {
                //kentang.getCreature().setFinalPosition(Utilities.GRIDSIZE*3,Utilities.GRIDSIZE*4);
                kentang.move(3*Utilities.GRIDSIZE, 4*Utilities.GRIDSIZE, lock, cancel);
                synchronized(lock){
                    try {
                        lock.wait();
                    } catch (InterruptedException e){
//                        return;
                    }
                }
                if (!cancel[0]){
//                    map.pop(3*Utilities.GRIDSIZE, 4*Utilities.GRIDSIZE);
                    kentang.getCreature().setFinalPosition(3*Utilities.GRIDSIZE+5, 4*Utilities.GRIDSIZE+5);
                }
            } else if (map.getTop(3, 4) instanceof PulmosisLand) {
                //kentang.getCreature().setFinalPosition(Utilities.GRIDSIZE*3,Utilities.GRIDSIZE*6);
                kentang.move(3*Utilities.GRIDSIZE, 6*Utilities.GRIDSIZE, lock, cancel);
                synchronized(lock){
                    try {
                        lock.wait();
                    } catch (InterruptedException e){
//                        return;
                    }
                }
                if (!cancel[0]){
                    kentang.getCreature().setFinalPosition(3*Utilities.GRIDSIZE+5, 6*Utilities.GRIDSIZE+5);
                }
            }  else if (belum[2]) {
                belum[2] = false;
                kentang.getCreature().setPosition(new Point2D(Utilities.GRIDSIZE*3,Utilities.GRIDSIZE*6));
                kentang.getCreature().setFinalPosition(Utilities.GRIDSIZE*3,Utilities.GRIDSIZE*6);
                Point2D pos = kentang.getCreature().position();
                map.push(pos.X(), pos.Y(), kentang);
                kentang.move(3*Utilities.GRIDSIZE, 4*Utilities.GRIDSIZE, lock, cancel);
                synchronized(lock){
                    try {
                        lock.wait();
                    } catch (InterruptedException e){
//                        return;
                    }
                }
                if (!cancel[0]){
//                    map.pop(3*Utilities.GRIDSIZE, 4*Utilities.GRIDSIZE);
                    kentang.getCreature().setFinalPosition(3*Utilities.GRIDSIZE+5, 4*Utilities.GRIDSIZE+5);
                }
                //kentang.getCreature().setFinalPosition(Utilities.GRIDSIZE*3,Utilities.GRIDSIZE*4);
            }
        }
    }

    public void run() {
        active = true;
        while (active) {
            System.out.format("Story running:There are currenty %d Thread running\n",Thread.activeCount());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Story();
            synchronized(this){
                notifyAll();
            }
        }
        System.out.print("ending story\n");
    }

    public void turnOff () {
        boolean fail;
        do{
            fail = false;
            try{
                System.out.print("waiting . . .\n");
                synchronized(this){
                    wait(10);
                }
            }catch(InterruptedException ex){
                synchronized(lock){
                    lock.notifyAll();
                    fail = true;
                }
            }
        }while (fail);
        System.out.print("end of wait\n");
        active = false;
    }

    public JPanel getPanel() {
        return panel;
    }
    public void begin(){
        (new Thread(new Runnable() {
            public void run() {
                if (storyloop != null){
                    if (storyloop.isAlive()){
                        storyloop.interrupt();
                    }
                }
                storyloop = new Thread(StoryLine.this);
                storyloop.start();
            }
        })).start();
    }

    public PulmosisLand getKentang () {
        return kentang;
    }
}
