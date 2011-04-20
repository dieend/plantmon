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


    public StoryLine () {
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
        if (day >= 5) {
            if (map.getTop(4, 4) instanceof Pulmosis) {
            } else {
                Pulmosis kentang = new Pulmosis(map,panel,g2d,2,false);
                kentang.getCreature().setPosition(new Point2D(Utilities.GRIDSIZE*2,Utilities.GRIDSIZE*6));
                kentang.getCreature().setFinalPosition(Utilities.GRIDSIZE*3,Utilities.GRIDSIZE*4);
                kentang.getCreature().setPosition(new Point2D(Utilities.GRIDSIZE*3,Utilities.GRIDSIZE*4));
                Point2D pos = kentang.getCreature().position();
                map.push(pos.X(), pos.Y(), kentang);
            }
        }
    }

    public void run() {
        System.out.format("There are currenty %d Thread running\n",Thread.activeCount());
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Story();
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
}
