package plantmon.entity.unmoveable;

import java.awt.Graphics2D;
import javax.swing.JPanel;
import plantmon.game.GridMap;

public class Kubis extends Plant{
    public Kubis(GridMap map, JPanel panel, Graphics2D g2d) {
        super(map,panel,g2d);
        fase = 1;
        typeTanaman = 4;
        season = 0;
        happyMeter = 0;
        titikDewasa = 10;
        titikPanen = 15;
        umur = 20;
        panenBerulang = false;
    }

}
