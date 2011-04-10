package plantmon.entity.unmoveable;

import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.Spring;
import plantmon.game.GridMap;

public class Kentang extends Plant{
    public Kentang(GridMap map, JPanel panel, Graphics2D g2d,int gx, int gy,int status) {
        super(map,panel,g2d,gx,gy);
        fase = status - 1;
        typeTanaman = 1;
        season = 0;
        happyMeter = 0;
        titikDewasa = 5;
        titikPanen = 8;
        umur = 10;
        panenBerulang = false;
    }

}
