package plantmon.states;

import java.awt.Font;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import plantmon.entity.Time;
import plantmon.entity.movingObject.PulmosisBattle;
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
    private ArrayList<Integer> sold;
    private int day;
    private int season;
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
    PulmosisLand bawang;
    PulmosisLand labu;
    PulmosisLand ubi;
    PulmosisLand terong;
    PulmosisLand paprika;
    PulmosisLand bayam;
    PulmosisLand wortel;
    private boolean active;
    private Boolean[] belum;
    transient Thread storyloop;
    boolean adaLobak;
    boolean adaTimun;
    boolean adaKubis;
    boolean adaKentang;
    private boolean doneKentang;
    boolean doneTomat;
    boolean tomatDapat;
    private boolean doneNanas1;
    boolean labuHere;
    private boolean bayamMuncul;
    private boolean alreadyBawang;
    private boolean doneBayam;
    private boolean donePaprika;
    private boolean labuDone;
    private Boolean[] winBattle;
    private ArrayList<PulmosisLand> pulmosis;
    final Object lock = new String("exact");

    
    public StoryLine () {
        belum = new Boolean[15];
        pulmosis = new ArrayList<PulmosisLand>(15);
        sold = new ArrayList<Integer>(15);
        for (int i = 0; i < 15; i++) {
            sold.add(i, 0);
        }
        winBattle = new Boolean[4];
        for (int i = 0; i <4; i++) {
            winBattle[i]=false;
        }
        for (int i = 0; i <15; i++) {
            belum[i]=true;
        }
        doneKentang = false;
        doneNanas1 = false;
        alreadyBawang = false;
        bayamMuncul = false;
        kentang = new PulmosisLand(map,panel,null,2);
        lobak = new PulmosisLand(map,panel,null,0);
        timun =  new PulmosisLand(map,panel,null,1);
        stroberi = new PulmosisLand(map,panel,null,4);
        kubis = new PulmosisLand(map,panel,null,3);
        jagung = new PulmosisLand(map,panel,null,5);
        tomat = new PulmosisLand(map,panel,null,6);
        nanas = new PulmosisLand(map,panel,null,7);
        bawang = new PulmosisLand(map, panel, null, 8);
        labu = new PulmosisLand(map, panel, null, 9);
        ubi = new PulmosisLand(map, panel, null, 10);
        terong = new PulmosisLand(map, panel, null, 11);
        paprika = new PulmosisLand(map, panel, null, 12);
        bayam = new PulmosisLand(map, panel, null, 13);
        wortel = new PulmosisLand(map, panel, null, 14);
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
        if (!getBelum()[2]) map.push(kentang.position().IntX(),kentang.position().IntY(), kentang);
        lobak.reinit(map, g2d, panel);
        if (!getBelum()[0]) map.push(lobak.position().IntX(),lobak.position().IntY(), lobak);
        timun.reinit(map,g2d,panel);
        if (!getBelum()[1]) map.push(timun.position().IntX(),timun.position().IntY(), timun);
        stroberi.reinit(map, g2d, panel);
        if (!getBelum()[3]) map.push(stroberi.position().IntX(), stroberi.position().IntY(), stroberi);
        kubis.reinit(map, g2d, panel);
        if (!getBelum()[4]) map.push(kubis.position().IntX(), kubis.position().IntY(), kubis);
        jagung.reinit(map, g2d, panel);
        if (!getBelum()[5]) map.push(jagung.position().IntX(), jagung.position().IntY(), jagung);
        tomat.reinit(map, g2d, panel);
        if (!getBelum()[6]) map.push(tomat.position().IntX(), tomat.position().IntY(), tomat);
        nanas.reinit(map, g2d, panel);
        if (!getBelum()[7]) map.push(nanas.position().IntX(), nanas.position().IntY(), nanas);
        bawang.reinit(map, g2d, panel);
        if (!getBelum()[8]) map.push(bawang.position().IntX(), bawang.position().IntY(), bawang);
        labu.reinit(map, g2d, panel);
        if (!getBelum()[9]) map.push(labu.position().IntX(), labu.position().IntY(), labu);
        ubi.reinit(map, g2d, panel);
        if (!getBelum()[10]) map.push(ubi.position().IntX(), ubi.position().IntY(), ubi);
        terong.reinit(map, g2d, panel);
        if (!getBelum()[11]) map.push(terong.position().IntX(), terong.position().IntY(), terong);
        paprika.reinit(map, g2d, panel);
        if (!getBelum()[12]) map.push(paprika.position().IntX(), paprika.position().IntY(), paprika);
        bayam.reinit(map, g2d, panel);
        if (!getBelum()[13]) map.push(bayam.position().IntX(), bayam.position().IntY(), bayam);
        wortel.reinit(map, g2d, panel);
        if (!getBelum()[14]) map.push(wortel.position().IntX(), wortel.position().IntY(), wortel);
    }

    public void Story () {
        Boolean[] cancel = new Boolean[1];
        cancel[0] = false;
        //lobak muncul pada hari pertama dan bergerak
        if (map.getTop(5, 9) instanceof PulmosisLand) {
            lobak.move(3*Utilities.GRIDSIZE, 9*Utilities.GRIDSIZE, lock, cancel);
            synchronized(lock){
                try {
                    lock.wait();
                } catch (InterruptedException e){
                }
            }
            if (!cancel[0]){
                lobak.getCreature().setFinalPosition(3*Utilities.GRIDSIZE+5, 9*Utilities.GRIDSIZE+5);
            }
        } else if (map.getTop(3, 9) instanceof PulmosisLand) {
            lobak.move(5*Utilities.GRIDSIZE, 9*Utilities.GRIDSIZE, lock, cancel);
            synchronized(lock){
                try {
                    lock.wait();
                } catch (InterruptedException e){
                }
            }
            if (!cancel[0]){
                lobak.getCreature().setFinalPosition(5*Utilities.GRIDSIZE+5, 9*Utilities.GRIDSIZE+5);
            }
        } else if (getBelum()[0]){
            getBelum()[0] = false;
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
                }
            }
            if (!cancel[0]){
                lobak.getCreature().setFinalPosition(5*Utilities.GRIDSIZE+5, 9*Utilities.GRIDSIZE+5);
            }
        }

        //timun otomatis muncul pada hari pertama dengan tetap diam ditempat
        if (getBelum()[1]){
            getBelum()[1] = false;
            timun.getCreature().setPosition(new Point2D(Utilities.GRIDSIZE*2,Utilities.GRIDSIZE*6));
            timun.getCreature().setFinalPosition(Utilities.GRIDSIZE*2,Utilities.GRIDSIZE*6);
            getPulmosis().add(timun);
            Point2D pos = timun.getCreature().position();
            map.push(pos.X(), pos.Y(), timun);
            pos = new Point2D(4*Utilities.GRIDSIZE,6*Utilities.GRIDSIZE);
            timun.getCreature().setArah(pos);
        }

        //kentang akan muncul dan bergabung ketika sudah disiram 7 kali
        if (day >= 5 && season >= Time.SPRING) {
            if (kentang.isFullWatered() && !doneKentang) {
                pulmosis.add(kentang);
                Game.instance().seek(ParentState.KENTANGSTATE, null);
            }

            if (doneKentang) {
                if (map.getTop(3, 6) instanceof PulmosisLand) {
                    kentang.move(3*Utilities.GRIDSIZE, 4*Utilities.GRIDSIZE, lock, cancel);
                    synchronized(lock){
                        try {
                            lock.wait();
                        } catch (InterruptedException e){
                        }
                    }
                    if (!cancel[0]){
                        kentang.getCreature().setFinalPosition(3*Utilities.GRIDSIZE+5, 4*Utilities.GRIDSIZE+5);
                    }
                } else if (map.getTop(3, 4) instanceof PulmosisLand) {
                    kentang.move(3*Utilities.GRIDSIZE, 6*Utilities.GRIDSIZE, lock, cancel);
                    synchronized(lock){
                        try {
                            lock.wait();
                        } catch (InterruptedException e){
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
                        }
                    }
                    if (!cancel[0]){
                        kentang.getCreature().setFinalPosition(3*Utilities.GRIDSIZE+5, 6*Utilities.GRIDSIZE+5);
                    }
                }
            } else {
                if (getBelum()[2]) {
                    getBelum()[2] = false;
                    kentang.getCreature().setPosition(new Point2D(Utilities.GRIDSIZE*2,Utilities.GRIDSIZE*5));
                    kentang.getCreature().setFinalPosition(Utilities.GRIDSIZE*2,Utilities.GRIDSIZE*5);
                    Point2D pos = kentang.getCreature().position();
                    map.push(pos.X(), pos.Y(), kentang);
                    pos = new Point2D(Utilities.GRIDSIZE*3,Utilities.GRIDSIZE*5);
                    kentang.getCreature().setArah(pos);
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

        //stroberi akan bergabung jika dilahan adan empat jenis tanaman berbeda
        if (adaLobak && adaTimun && adaKentang && adaKubis && getBelum()[3]) {
            getBelum()[3] = false;
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

        //kubis akan bergabung jika telah mengalahkannya di battle
        if (winBattle[0] && getBelum()[4]) {
            getBelum()[4] = false;
            kubis.getCreature().setPosition(new Point2D(Utilities.GRIDSIZE*7,Utilities.GRIDSIZE*9));
            kubis.getCreature().setFinalPosition(Utilities.GRIDSIZE*7,Utilities.GRIDSIZE*9);
            pulmosis.add(kubis);
            Point2D pos = kubis.getCreature().position();
            map.push(pos.X(), pos.Y(), kubis);
            pos = new Point2D(7*Utilities.GRIDSIZE,8*Utilities.GRIDSIZE);
            kubis.getCreature().setArah(pos);
        }

        //jika belum memenangkan pertarungan melawan kubis sampai summer, player gameover
        if (day >= 1 && season>=Time.SUMMER && !winBattle[0]) {
            Game.instance().goTo(ParentState.GAMEOVER, null);
        }

        //jika telah menjual 5 kentang, 5 lobak, dan 5 timun, jagung akan bergabung
        if (sold.get(0) >= 5 && sold.get(1) >= 5 && sold.get(2) >= 5 && getBelum()[5]) {
            getBelum()[5] = false;
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
                }
            }
            if (!cancel[0]){
                jagung.getCreature().setFinalPosition(3*Utilities.GRIDSIZE+5, 4*Utilities.GRIDSIZE+5);
                pos = new Point2D(3*Utilities.GRIDSIZE+5,3*Utilities.GRIDSIZE+5);
                jagung.getCreature().setArah(pos);
            }
            
            JLabel label1;
            JLabel label2;
            JLabel label3;
            JPanel panelis;
            panelis = new TalkPanel();
            panelis.setLayout(null);
            label1 = new JLabel();
            label1.setBounds(0,0,100,100);
            panelis.add(label1);
            label2 = new JLabel("Nama : " + Game.instance().getName());
            label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label2.setBounds(400,7,150,30);
            panelis.add(label2);
            label3 = new JLabel ("Hello Guys... My name is Corno..");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,0,450,90);
            panelis.add(label3);
            label3 = new JLabel ("I think I have missed to coming to this land.");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,16,450,90);
            panelis.add(label3);
            label3 = new JLabel ("But I will do my hard to helping you");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            label3.setBounds(101,32,450,90);
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

        //tomat akan otomatis bergabung pada tanggal 27 Springs
        if (day >= 27 && season>=Time.SPRING && getBelum()[6]) {
            getBelum()[6] = false;
            Game.instance().seek(ParentState.TOMATSTATE, null);
        }
        
        if (!doneTomat) {
            if (tomatDapat) {
                tomat.getCreature().setPosition(new Point2D(Utilities.GRIDSIZE*5,Utilities.GRIDSIZE*3));
                tomat.getCreature().setFinalPosition(Utilities.GRIDSIZE*5,Utilities.GRIDSIZE*3);
                pulmosis.add(tomat);
                Point2D pos = tomat.getCreature().position();
                map.push(pos.X(), pos.Y(), tomat);   
            }
        }

        //nanas akan muncul pada hari pertama summer dan menculik lobak
        if (day >= 1 && season>=Time.SUMMER && !isDoneNanas1()) {
            Game.instance().seek(ParentState.NANASSTATE, null);
            setDoneNanas1(true);
            for (int i = 3; i <= 5; i++) {
                if (map.getTop(i, 9) instanceof PulmosisLand) {
                    map.pop(Utilities.GRIDSIZE*i,Utilities.GRIDSIZE*9);
                }
            }
        }

        //nanas akan bergabung jika sudah mengalahkannya sebelum 21 Summer
        if (map.getTop(16,4) instanceof PulmosisLand) {
            nanas.move(16*Utilities.GRIDSIZE, 6*Utilities.GRIDSIZE, lock, cancel);
            synchronized(lock){
                try {
                    lock.wait();
                } catch (InterruptedException e){
                }
            }
            if (!cancel[0]){
                nanas.getCreature().setFinalPosition(16*Utilities.GRIDSIZE+5, 6*Utilities.GRIDSIZE+5);
            }
        } else if (map.getTop(16,6) instanceof PulmosisLand) {
            nanas.move(16*Utilities.GRIDSIZE, 4*Utilities.GRIDSIZE, lock, cancel);
            synchronized(lock){
                try {
                    lock.wait();
                } catch (InterruptedException e){
                }
            }
            if (!cancel[0]){
                nanas.getCreature().setFinalPosition(16*Utilities.GRIDSIZE+5, 4*Utilities.GRIDSIZE+5);
            }
        } else if (day < 21 && season>=Time.SUMMER && winBattle[1] && getBelum()[7]) {
            getBelum()[7] = false;
            lobak.getCreature().setPosition(new Point2D(Utilities.GRIDSIZE*3,Utilities.GRIDSIZE*9));
            lobak.getCreature().setFinalPosition(Utilities.GRIDSIZE*3, Utilities.GRIDSIZE*9);
            Point2D pos = lobak.getCreature().position();
            map.push(pos.X(), pos.Y(), lobak);
            nanas.getCreature().setPosition(new Point2D(Utilities.GRIDSIZE*16,Utilities.GRIDSIZE*4));
            nanas.getCreature().setFinalPosition(Utilities.GRIDSIZE*16,Utilities.GRIDSIZE*4);
            pulmosis.add(nanas);
            pos = nanas.getCreature().position();
            map.push(pos.X(), pos.Y(), nanas);
            nanas.move(16*Utilities.GRIDSIZE, 6*Utilities.GRIDSIZE, lock, cancel);
            synchronized(lock){
                try {
                    lock.wait();
                } catch (InterruptedException e){
                }
            }
            if (!cancel[0]){
                nanas.getCreature().setFinalPosition(16*Utilities.GRIDSIZE+5, 6*Utilities.GRIDSIZE+5);
            }
        }

        if (day >=21 && season>=Time.SUMMER && !winBattle[1]) {
            Game.instance().goTo(ParentState.GAMEOVER, null);
        }

        //bawang akan muncul dari hasil perkawinan timun dan lobak
        if (day >= 22 && season>=Time.SUMMER && !isAlreadyBawang()) {
            Game.instance().seek(ParentState.BAWANGSTATE, null);
        }

        if (isAlreadyBawang() && getBelum()[8]) {
            getBelum()[8] = false;
            bawang.getCreature().setPosition(new Point2D(Utilities.GRIDSIZE*2,Utilities.GRIDSIZE*5));
            bawang.getCreature().setFinalPosition(Utilities.GRIDSIZE*2,Utilities.GRIDSIZE*5);
            pulmosis.add(bawang);
            Point2D pos = bawang.getCreature().position();
            map.push(pos.X(), pos.Y(), bawang);
            pos = new Point2D(Utilities.GRIDSIZE*3,Utilities.GRIDSIZE*5);
            bawang.getCreature().setArah(pos);
        }

        //labu akan berada dilahan dari tanggal 5 Summer sampai 15 Summer
        //jika berhasil mengalahkan labu dalam permainannya, labu akan bergabung
        if (day >=5 && day <= 15 && season == Time.SUMMER && !isLabuDone()) {
            labu.getCreature().setPosition(new Point2D(Utilities.GRIDSIZE*5,Utilities.GRIDSIZE*1));
            labu.getCreature().setFinalPosition(Utilities.GRIDSIZE*5,Utilities.GRIDSIZE*1);
            Point2D pos = labu.getCreature().position();
            map.push(pos.X(), pos.Y(), labu);
            pos = new Point2D(Utilities.GRIDSIZE*6,Utilities.GRIDSIZE*1);
            labu.getCreature().setArah(pos);
        }

        if (isLabuDone() && getBelum()[9]) {
            getBelum()[9] = false;
            pulmosis.add(labu);
        }

        if (day>=16 && !isLabuDone()) {
            map.pop(Utilities.GRIDSIZE*5, Utilities.GRIDSIZE*1);
        }

        //ubi akan otomatis bergabung pada hari pertama Fall
        if (day>=1 && season>=Time.FALL && getBelum()[10]) {
            getBelum()[10] = false;
            ubi.getCreature().setPosition(new Point2D(6*Utilities.GRIDSIZE,0*Utilities.GRIDSIZE));
            ubi.getCreature().setFinalPosition(6*Utilities.GRIDSIZE, 0*Utilities.GRIDSIZE);
            pulmosis.add(ubi);
            Point2D pos = ubi.getCreature().position();
            map.push(pos.X(), pos.Y(), ubi);
            ubi.move(3*Utilities.GRIDSIZE,8*Utilities.GRIDSIZE, lock, cancel);
            synchronized(lock){
                try {
                    lock.wait();
                } catch (InterruptedException e){
                }
            }
            if (!cancel[0]){
                ubi.getCreature().setFinalPosition(3*Utilities.GRIDSIZE+5, 8*Utilities.GRIDSIZE+5);
                pos = new Point2D(Utilities.GRIDSIZE*4,Utilities.GRIDSIZE*8);
                ubi.getCreature().setArah(pos);
            }
        }

        int countSell = 0;
        for (int i = 0; i < 15; i++) {
            if (sold.get(i) >= 1) {
                countSell += 1;
            }
        }

        //terong otomatis bergabung jika sudah menjual 9 tanaman
        if (countSell>=9 && getBelum()[11]) {
            getBelum()[11] = false;
            terong.getCreature().setPosition(new Point2D(16*Utilities.GRIDSIZE,1*Utilities.GRIDSIZE));
            terong.getCreature().setFinalPosition(16*Utilities.GRIDSIZE, 1*Utilities.GRIDSIZE);
            pulmosis.add(terong);
            Point2D pos = terong.getCreature().position();
            map.push(pos.X(), pos.Y(), terong);
            pos = new Point2D(Utilities.GRIDSIZE*15,Utilities.GRIDSIZE*1);
            terong.getCreature().setArah(pos);
        }

        //event bos paprika
        if (day>=15 && season>=Time.FALL && !isDonePaprika()) {
            Game.instance().seek(ParentState.PAPRIKASTATE, null);
        }

        //paprika bergabung setelah dikalahkan
        if (day <= 29 && season>=Time.FALL && isDonePaprika() && getBelum()[12] && winBattle[2]) {
            getBelum()[12] = false;
            paprika.getCreature().setPosition(new Point2D(10*Utilities.GRIDSIZE,9*Utilities.GRIDSIZE));
            paprika.getCreature().setFinalPosition(10*Utilities.GRIDSIZE, 9*Utilities.GRIDSIZE);
            pulmosis.add(paprika);
            Point2D pos = paprika.getCreature().position();
            map.push(pos.X(), pos.Y(), paprika);
        }

        if (day>=29 && season>=Time.FALL && !isDonePaprika()) {
            Game.instance().goTo(ParentState.GAMEOVER, null);
        }

        if (pulmosis.size() >= 12 && !isDoneBayam()) {
            Game.instance().seek(ParentState.BAYAMSTATE, null);
        }
        //bayam akan bergabung jika jumlah pulmosis di lahan sudah mencapai 12 dan menjawab 'yes' pada pertanyaannya
        if (isDoneBayam() && getBelum()[13] && isBayamMuncul()) {
            getBelum()[13] = false;
            bayam.getCreature().setPosition(new Point2D(13*Utilities.GRIDSIZE,1*Utilities.GRIDSIZE));
            bayam.getCreature().setFinalPosition(13*Utilities.GRIDSIZE, 1*Utilities.GRIDSIZE);
            pulmosis.add(bayam);
            Point2D pos = bayam.getCreature().position();
            map.push(pos.X(), pos.Y(), bayam);
        }

        for (PulmosisLand p:pulmosis) {
            if(p.getTypePul() == PulmosisBattle.Labu) {
                labuHere = true;
            }
        }

        if (labuHere && getBelum()[14]) {
            getBelum()[14] = false;
            wortel.getCreature().setPosition(new Point2D(Utilities.GRIDSIZE*6,Utilities.GRIDSIZE*0));
            wortel.getCreature().setFinalPosition(Utilities.GRIDSIZE*6,Utilities.GRIDSIZE*0);
            pulmosis.add(wortel);
            Point2D pos = wortel.getCreature().position();
            map.push(pos.X(), pos.Y(), wortel);
            wortel.move(9*Utilities.GRIDSIZE, 1*Utilities.GRIDSIZE, lock, cancel);
            synchronized(lock){
                try {
                    lock.wait();
                } catch (InterruptedException e){
                }
            }
            if (!cancel[0]){
                wortel.getCreature().setFinalPosition(9*Utilities.GRIDSIZE+5, 1*Utilities.GRIDSIZE+5);
                pos = new Point2D(9*Utilities.GRIDSIZE+5,2*Utilities.GRIDSIZE+5);
                wortel.getCreature().setArah(pos);
            }
        }

        if (day == 30 && season == Time.FALL) {
            Game.instance().goTo(ParentState.FINALSTATE, null);
        }

    }

    public void run() {
        active = true;
        while (active) {
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

    public Boolean getWinBattle(int i) {
        return winBattle[i];
    }

    public void setWinBattle(Boolean winBattle, int i) {
        this.winBattle[i] = winBattle;
    }

    public ArrayList<PulmosisLand> getPulmosis() {
        return pulmosis;
    }

    public void setPulmosis(ArrayList<PulmosisLand> pulmosis) {
        this.pulmosis = pulmosis;
    }

    public void isTomatDapat (boolean dapat) {
        this.tomatDapat = dapat;
        setDoneTomat(false);
    }

    public void setDoneTomat (boolean don) {
        doneTomat = don;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public boolean isLabuDone() {
        return labuDone;
    }

    public void setLabuDone(boolean labuDone) {
        this.labuDone = labuDone;
    }

    public boolean isDonePaprika() {
        return donePaprika;
    }

    public void setDonePaprika(boolean donePaprika) {
        this.donePaprika = donePaprika;
    }

    public boolean isDoneBayam() {
        return doneBayam;
    }

    public void setDoneBayam(boolean doneBayam) {
        this.doneBayam = doneBayam;
    }

    public boolean isAlreadyBawang() {
        return alreadyBawang;
    }

    public void setAlreadyBawang(boolean alreadyBawang) {
        this.alreadyBawang = alreadyBawang;
    }

    public boolean isDoneKentang() {
        return doneKentang;
    }

    public void setDoneKentang(boolean doneKentang) {
        this.doneKentang = doneKentang;
    }

    public boolean isBayamMuncul() {
        return bayamMuncul;
    }

    public void setBayamMuncul(boolean bayamMuncul) {
        this.bayamMuncul = bayamMuncul;
    }

    /**
     * @return the belum
     */
    public Boolean[] getBelum() {
        return belum;
    }

    /**
     * @param belum the belum to set
     */
    public void setBelum(Boolean belum, int i) {
        this.belum[i] = belum;
    }

    /**
     * @return the doneNanas1
     */
    public boolean isDoneNanas1() {
        return doneNanas1;
    }

    /**
     * @param doneNanas1 the doneNanas1 to set
     */
    public void setDoneNanas1(boolean doneNanas1) {
        this.doneNanas1 = doneNanas1;
    }
}
