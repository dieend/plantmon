package plantmon.entity.unmoveable;

import java.awt.Graphics2D;
import javax.swing.JPanel;
import plantmon.game.GridMap;

public class Stroberi extends Plant{
    public Stroberi(GridMap map, JPanel panel, Graphics2D g2d,int gx, int gy,int status) {
        super(map,panel,g2d,gx,gy);
        fase = status - 1;
        typeTanaman = 13;
        season = 1;
        happyMeter = 0;
        titikDewasa = 2;
        titikPanen = 8;
        umur = 25;
        panenBerulang = true;
    }

}