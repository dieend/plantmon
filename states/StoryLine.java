package plantmon.states;

import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import plantmon.entity.movingObject.Pulmosis;
import plantmon.game.GridMap;
import plantmon.game.Point2D;
import plantmon.system.Utilities;

public class StoryLine implements Runnable {
    private int type;
    private ArrayList<Integer> sold;
    private int day;
    private GridMap map;
    private JPanel panel;
    private Graphics2D g2d;
    boolean active;
    Pulmosis kentang;
    boolean belum;

    public StoryLine (GridMap map, JPanel panel, Graphics2D g2d) {
        this.map = map;
        this.panel = panel;
        this.g2d = g2d;
        belum = true;
        kentang = new Pulmosis(map,panel,g2d,2,false);
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

    public GridMap getMap() {
        return map;
    }

    public void setMap(GridMap map) {
        this.map = map;
    }

    public Graphics2D getG2d() {
        return g2d;
    }

    public void setG2d(Graphics2D g2d) {
        this.g2d = g2d;
    }

    public void Story () {
        Boolean[] cancel = new Boolean[1];
        Object lock = new Object();
        if (day >= 1) {
            if (map.getTop(3, 6) instanceof Pulmosis) {
                kentang.getCreature().setFinalPosition(Utilities.GRIDSIZE*3,Utilities.GRIDSIZE*4);
                kentang.move(3, 4, lock, cancel);
            } else if (map.getTop(3, 5) instanceof Pulmosis) {
            } else if (map.getTop(3, 4) instanceof Pulmosis) {
                kentang.getCreature().setFinalPosition(Utilities.GRIDSIZE*3,Utilities.GRIDSIZE*6);
                kentang.move(3, 6, lock, cancel);
            } else if (belum){
                belum = false;
                kentang.getCreature().setPosition(new Point2D(Utilities.GRIDSIZE*3,Utilities.GRIDSIZE*6));
                Point2D pos = kentang.getCreature().position();
                map.push(pos.X(), pos.Y(), kentang);
                kentang.move(3, 4, lock, cancel);
                //kentang.getCreature().setFinalPosition(Utilities.GRIDSIZE*3,Utilities.GRIDSIZE*4);
            }
        }
    }

    public void run() {
       active = true;
       while (active) {
            System.out.format("There are currenty %d Thread running\n",Thread.activeCount());
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Story();
        }
    }

    public void turnOff () {
        active = false;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
}
