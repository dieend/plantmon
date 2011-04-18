package plantmon.entity.unmoveable;

import java.awt.Graphics2D;
import javax.swing.JPanel;
import plantmon.game.GridMap;

public class Labu extends Plant{
    public Labu(GridMap map, JPanel panel, Graphics2D g2d,int gx, int gy,int status) {
        super(map,panel,g2d,gx,gy);
        fase = status - 1;
        typeTanaman = 14;
        season = 2;
        happyMeter = 0;
        titikDewasa = 10;
        titikPanen = 15;
        umur = 25;
        panenBerulang = true;
    }

}