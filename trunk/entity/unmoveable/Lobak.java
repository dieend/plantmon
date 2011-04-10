package plantmon.entity.unmoveable;

import java.awt.Graphics2D;
import javax.swing.JPanel;
import plantmon.game.GridMap;

public class Lobak extends Plant{
    public Lobak(GridMap map, JPanel panel, Graphics2D g2d) {
        super(map,panel,g2d);
        fase = 1;
        typeTanaman = 2;
        season = 0;
        happyMeter = 0;
        titikDewasa = 2;
        titikPanen = 5;
        umur = 8;
        panenBerulang = false;
    }

}
