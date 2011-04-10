package plantmon.entity.unmoveable;

import java.awt.Graphics2D;
import javax.swing.JPanel;
import plantmon.game.GridMap;

public class Wortel extends Plant{
    public Wortel(GridMap map, JPanel panel, Graphics2D g2d) {
        super(map,panel,g2d);
        fase = 1;
        typeTanaman = 9;
        season = 2;
        happyMeter = 0;
        titikDewasa = 5;
        titikPanen = 8;
        umur = 12;
        panenBerulang = true;
    }

}
