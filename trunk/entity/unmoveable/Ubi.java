package plantmon.entity.unmoveable;

import java.awt.Graphics2D;
import javax.swing.JPanel;
import plantmon.game.GridMap;

public class Ubi extends Plant{
    public Ubi(GridMap map, JPanel panel, Graphics2D g2d,int gx, int gy,int status) {
        super(map,panel,g2d,gx,gy);
        fase = status - 1;
        typeTanaman = 11;
        season = 2;
        happyMeter = 0;
        titikDewasa = 3;
        titikPanen = 6;
        umur = 20;
        panenBerulang = true;
    }

}