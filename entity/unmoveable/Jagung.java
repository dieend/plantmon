package plantmon.entity.unmoveable;

import java.awt.Graphics2D;
import javax.swing.JPanel;
import plantmon.game.GridMap;

public class Jagung extends Plant{
    public Jagung(GridMap map, JPanel panel, Graphics2D g2d,int gx, int gy,int status) {
        super(map,panel,g2d,gx,gy);
        fase = status - 1;
        typeTanaman = 5;
        season = 1;
        happyMeter = 0;
        titikDewasa = 12;
        titikPanen = 15;
        umur = 25;
        panenBerulang = true;
    }

}
