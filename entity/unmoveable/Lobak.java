package plantmon.entity.unmoveable;

import java.awt.Graphics2D;
import javax.swing.JPanel;
import plantmon.game.GridMap;

public class Lobak extends Plant{
    public Lobak(GridMap map, JPanel panel, Graphics2D g2d,int gx, int gy,int status) {
        super(map,panel,g2d,gx,gy);
        fase = status - 1;
        typeTanaman = 2;
        season = 0;
        happyMeter = 0;
        titikDewasa = 2;
        titikPanen = 5;
        umur = 8;
        panenBerulang = false;
    }

}
