package plantmon.states;

import java.awt.Font;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import plantmon.entity.movingObject.PulmosisLand;
import plantmon.entity.unmoveable.Kentang;
import plantmon.entity.unmoveable.Kubis;
import plantmon.entity.unmoveable.Lobak;
import plantmon.entity.unmoveable.Plant;
import plantmon.entity.unmoveable.Timun;
import plantmon.game.GridMap;
import plantmon.game.Point2D;
import plantmon.game.TalkPanel;
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
    PulmosisLand stroberi;
    PulmosisLand kubis;
    PulmosisLand jagung;
    PulmosisLand tomat;
    PulmosisLand nanas;
    private boolean active;
    Boolean[] belum;
    Thread storyloop;
    boolean adaLobak;
    boolean adaTimun;
    boolean adaKubis;
    boolean adaKentang;
    boolean done;
    private Boolean[] winBattle;
    private ArrayList<PulmosisLand> pulmosis;
    final Object lock = new String("exact");

    
    public StoryLine () {
        belum = new Boolean[7];
        pulmosis = new ArrayList<PulmosisLand>(15);
        sold = new ArrayList<Integer>(15);
        for (int i = 0; i < 15; i++) {
            sold.add(i, 0);
        }
        winBattle = new Boolean[4];
        for (int i = 0; i <4; i++) {
            winBattle[i]=false;
        }
        for (int i = 0; i <7; i++) {
            belum[i]=true;
        }
        done = false;
        kentang = new PulmosisLand(map,panel,null,2);
        lobak = new PulmosisLand(map,panel,null,0);
        timun =  new PulmosisLand(map,panel,null,1);
        stroberi = new PulmosisLand(map,panel,null,4);
        kubis = new PulmosisLand(map,panel,null,3);
        jagung = new PulmosisLand(map,panel,null,5);
        tomat = new PulmosisLand(map,panel,null,6);
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
        int i = sold.get(ID);
        i = i + amm;
        sold.set(ID,i);
        System.out.println("Barang terjual:"+sold.get(ID));
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
        stroberi.reinit(map, g2d, panel);
        if (!belum[3]) map.push(stroberi.position().IntX(), stroberi.position().IntY(), stroberi);
        kubis.reinit(map, g2d, panel);
        if (!belum[4]) map.push(kubis.position().IntX(), kubis.position().IntY(), kubis);
        jagung.reinit(map, g2d, panel);
        if (!belum[5]) map.push(jagung.position().IntX(), jagung.position().IntY(), jagung);
        tomat.reinit(map, g2d, panel);
        if (!belum[6]) map.push(tomat.position().IntX(), tomat.position().IntY(), tomat);
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
            getPulmosis().add(lobak);
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
//        System.out.println("posisi lobak : "+lobak.position().IntX()+"  "+lobak.position().IntY());
        
        if (belum[1]){
            belum[1] = false;
            timun.getCreature().setPosition(new Point2D(Utilities.GRIDSIZE*2,Utilities.GRIDSIZE*6));
            timun.getCreature().setFinalPosition(Utilities.GRIDSIZE*2,Utilities.GRIDSIZE*6);
            getPulmosis().add(timun);
            Point2D pos = timun.getCreature().position();
            map.push(pos.X(), pos.Y(), timun);
            pos = new Point2D(4*Utilities.GRIDSIZE,6*Utilities.GRIDSIZE);
            timun.getCreature().setArah(pos);
            //kentang.getCreature().setFinalPosition(Utilities.GRIDSIZE*3,Utilities.GRIDSIZE*4);
        }
        
        if (day >= 5) {
            if (kentang.isFullWatered()) {
                if (!done) {
                    done = true;
                    JLabel label1;
                    JLabel label2;
                    JLabel label3;
                    JPanel panelis;
                    panelis = new TalkPanel();
                    panelis.setLayout(null);
                    //image1 = new ImageIcon(this.getClass().getResource("icon player.png"));
                    label1 = new JLabel();
                    //image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
                    label1.setBounds(0,0,100,100);
                    panelis.add(label1);
                    label2 = new JLabel("Nama : " + Game.instance().getName());
                    label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label2.setBounds(400,7,150,30);
                    panelis.add(label2);
                    label3 = new JLabel ("Hello Guys... My Name ");
                    label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label3.setBounds(101,9,450,90);
                    panelis.add(label3);
                    label3 = new JLabel ("Please Help Me!! My Land was attacked");
                    label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
                    label3.setBounds(101,26,450,90);
                    panelis.add(label3);
                    
                    Game.instance().setDialogBox(panelis, panel);
                }
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
                }  else {
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
                }
            } else {
                if (belum[2]) {
                    belum[2] = false;
                    kentang.getCreature().setPosition(new Point2D(Utilities.GRIDSIZE*2,Utilities.GRIDSIZE*5));
                    kentang.getCreature().setFinalPosition(Utilities.GRIDSIZE*2,Utilities.GRIDSIZE*5);
                    getPulmosis().add(kentang);
                    Point2D pos = kentang.getCreature().position();
                    map.push(pos.X(), pos.Y(), kentang);
                }
            }
        }

        for(Plant p:Game.instance().getPlants()) {
            if (p instanceof Lobak) {
                adaLobak = true;
            } else if (p instanceof Timun) {
                adaTimun = true;
            } else if (p instanceof Kentang) {
                adaKentang = true;
            } else if (p instanceof Kubis) {
                adaKubis = true;
            } 
        }

        if (adaLobak && adaTimun && adaKentang && adaKubis && belum[3]) {
            belum[3] = false;
            stroberi.getCreature().setPosition(new Point2D(Utilities.GRIDSIZE*6,Utilities.GRIDSIZE*0));
            stroberi.getCreature().setFinalPosition(Utilities.GRIDSIZE*6,Utilities.GRIDSIZE*0);
            pulmosis.add(stroberi);
            Point2D pos = stroberi.getCreature().position();
            map.push(pos.X(), pos.Y(), stroberi);
            stroberi.move(7*Utilities.GRIDSIZE, 1*Utilities.GRIDSIZE, lock, cancel);
            synchronized(lock){
                try {
                    lock.wait();
                } catch (InterruptedException e){
                }
            }
            if (!cancel[0]){
                stroberi.getCreature().setFinalPosition(7*Utilities.GRIDSIZE+5, 1*Utilities.GRIDSIZE+5);
                pos = new Point2D(7*Utilities.GRIDSIZE+5,2*Utilities.GRIDSIZE+5);
                stroberi.getCreature().setArah(pos);
            }   
        }

        if (winBattle[0] && belum[4]) {
            belum[4] = false;
            kubis.getCreature().setPosition(new Point2D(Utilities.GRIDSIZE*7,Utilities.GRIDSIZE*9));
            kubis.getCreature().setFinalPosition(Utilities.GRIDSIZE*7,Utilities.GRIDSIZE*9);
            pulmosis.add(kubis);
            Point2D pos = kubis.getCreature().position();
            map.push(pos.X(), pos.Y(), kubis);
            pos = new Point2D(7*Utilities.GRIDSIZE,8*Utilities.GRIDSIZE);
            kubis.getCreature().setArah(pos);
        }

        if (sold.get(0) >= 5 && sold.get(1) >= 5 && sold.get(2) >= 5 && belum[5]) {
            belum[5] = false;
            jagung.getCreature().setPosition(new Point2D(Utilities.GRIDSIZE*6,Utilities.GRIDSIZE*4));
            jagung.getCreature().setFinalPosition(Utilities.GRIDSIZE*6,Utilities.GRIDSIZE*4);
            pulmosis.add(jagung);
            Point2D pos = jagung.getCreature().position();
            map.push(pos.X(), pos.Y(), jagung);
            jagung.move(3*Utilities.GRIDSIZE, 4*Utilities.GRIDSIZE, lock, cancel);
            synchronized(lock){
                try {
                    lock.wait();
                } catch (InterruptedException e){
//                        return;
                }
            }
            if (!cancel[0]){
//                    map.pop(3*Utilities.GRIDSIZE, 4*Utilities.GRIDSIZE);
                jagung.getCreature().setFinalPosition(3*Utilities.GRIDSIZE+5, 4*Utilities.GRIDSIZE+5);
                pos = new Point2D(3*Utilities.GRIDSIZE+5,3*Utilities.GRIDSIZE+5);
                jagung.getCreature().setArah(pos);
            }
            
            //ImageIcon image1;
            JLabel label1;
            JLabel label2;
            JLabel label3;
            JPanel panelis;
            panelis = new TalkPanel();
            panelis.setLayout(null);
            //image1 = new ImageIcon(this.getClass().getResource("icon player.png"));
            label1 = new JLabel();
            //image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
            label1.setBounds(0,0,100,100);
            panelis.add(label1);
            label2 = new JLabel("Nama : " + Game.instance().getName());
            label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label2.setBounds(400,7,150,30);
            panelis.add(label2);
            label3 = new JLabel ("Hello Guys... I think I have missed to coming to this land.\nBut I will do my hard to helping you\n"
                    + "Oh yeah, my name is");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,9,450,90);
            panelis.add(label3);
            
            Game.instance().setDialogBox(panelis, panel);
            
            jagung.move(16*Utilities.GRIDSIZE, 9*Utilities.GRIDSIZE, lock, cancel);
            synchronized(lock){
                try {
                    lock.wait();
                } catch (InterruptedException e){
//                        return;
                }
            }
            if (!cancel[0]){
//                    map.pop(3*Utilities.GRIDSIZE, 4*Utilities.GRIDSIZE);
                jagung.getCreature().setFinalPosition(16*Utilities.GRIDSIZE+5, 9*Utilities.GRIDSIZE+5);
                pos = new Point2D(16*Utilities.GRIDSIZE+5,8*Utilities.GRIDSIZE+5);
                jagung.getCreature().setArah(pos);
            }
        }

        if (day >= 27 && belum[6]) {
            belum[6] = false;
            tomat.getCreature().setPosition(new Point2D(Utilities.GRIDSIZE*6,Utilities.GRIDSIZE*3));
            tomat.getCreature().setFinalPosition(Utilities.GRIDSIZE*6,Utilities.GRIDSIZE*3);
            pulmosis.add(tomat);
            Point2D pos = tomat.getCreature().position();
            map.push(pos.X(), pos.Y(), tomat);
        }
    }

    public void run() {
        active = true;
        while (active) {
//            System.out.format("Story running:There are currenty %d Thread running\n",Thread.activeCount());
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

    /**
     * @return the winBattle
     */
    public Boolean[] getWinBattle() {
        return winBattle;
    }

    /**
     * @param winBattle the winBattle to set
     */
    public void setWinBattle(Boolean[] winBattle) {
        this.winBattle = winBattle;
    }

    /**
     * @return the pulmosis
     */
    public ArrayList<PulmosisLand> getPulmosis() {
        return pulmosis;
    }

    /**
     * @param pulmosis the pulmosis to set
     */
    public void setPulmosis(ArrayList<PulmosisLand> pulmosis) {
        this.pulmosis = pulmosis;
    }
}
