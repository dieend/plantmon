package plantmon.entity.unmoveable;

import java.awt.Graphics2D;
import javax.swing.JPanel;
import plantmon.game.GridMap;

public class Bawang extends Plant{
    public Bawang(GridMap map, JPanel panel, Graphics2D g2d) {
        super(map,panel,g2d);
        fase = 1;
        typeTanaman = 7;
        season = 1;
        happyMeter = 0;
        titikDewasa = 5;
        titikPanen = 8;
        umur = 12;
        panenBerulang = true;
    }

}