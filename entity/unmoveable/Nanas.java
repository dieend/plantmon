package plantmon.entity.unmoveable;

import java.awt.Graphics2D;
import javax.swing.JPanel;
import plantmon.game.GridMap;

public class Nanas extends Plant{
    public Nanas(GridMap map, JPanel panel, Graphics2D g2d) {
        super(map,panel,g2d);
        fase = 1;
        typeTanaman = 8;
        season = 1;
        happyMeter = 0;
        titikDewasa = 16;
        titikPanen = 21;
        umur = 30;
        panenBerulang = true;
    }

}
