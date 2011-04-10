package plantmon.entity.unmoveable;

import java.awt.Graphics2D;
import javax.swing.JPanel;
import plantmon.entity.Item;
import plantmon.game.GridMap;

public class Kentang extends Plant{
    public Kentang(GridMap map, JPanel panel, Graphics2D g2d) {
        super(map,panel,g2d);
        fase = 1;
        typeTanaman = 1;
        season = 0;
        happyMeter = 0;
        titikDewasa = 5;
        titikPanen = 8;
        umur = 10;
        panenBerulang = false;
    }

}
