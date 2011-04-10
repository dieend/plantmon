package plantmon.entity.unmoveable;

import java.awt.Graphics2D;
import javax.swing.JPanel;
import plantmon.game.GridMap;

public class Paprika extends Plant{
    public Paprika(GridMap map, JPanel panel, Graphics2D g2d) {
        super(map,panel,g2d);
        fase = 1;
        typeTanaman = 12;
        season = 2;
        happyMeter = 0;
        titikDewasa = 6;
        titikPanen = 8;
        umur = 20;
        panenBerulang = true;
    }

}