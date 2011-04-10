package plantmon.entity.unmoveable;

import java.awt.Graphics2D;
import javax.swing.JPanel;
import plantmon.game.GridMap;

public class Timun extends Plant{
    public Timun(GridMap map, JPanel panel, Graphics2D g2d,int gx, int gy,int status) {
        super(map,panel,g2d,gx,gy);
        fase = status - 1;
        typeTanaman = 3;
        season = 0;
        happyMeter = 0;
        titikDewasa = 5;
        titikPanen = 10;
        umur = 25;
        panenBerulang = true;
    }

}
