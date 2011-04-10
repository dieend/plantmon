package plantmon.entity.unmoveable;

import java.awt.Graphics2D;
import javax.swing.JPanel;
import plantmon.game.GridMap;

public class Timun extends Plant{
    public Timun(GridMap map, JPanel panel, Graphics2D g2d) {
        super(map,panel,g2d);
        fase = 1;
        typeTanaman = 3;
        season = 0;
        happyMeter = 0;
        titikDewasa = 5;
        titikPanen = 10;
        umur = 25;
        panenBerulang = true;
    }

}
