package plantmon.entity.unmoveable;

import java.awt.Graphics2D;
import javax.swing.JPanel;
import plantmon.game.GridMap;

public class Wortel extends Plant{
    public Wortel(GridMap map, JPanel panel, Graphics2D g2d,int gx, int gy,int status) {
        super(map,panel,g2d,gx,gy);
        fase = status - 1;
        typeTanaman = 9;
        season = 2;
        happyMeter = 0;
        titikDewasa = 5;
        titikPanen = 8;
        umur = 12;
        panenBerulang = true;
    }

}
