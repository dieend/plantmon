package plantmon.entity.unmoveable;

import java.awt.Graphics2D;
import javax.swing.JPanel;
import plantmon.game.GridMap;

public class Bayam extends Plant{
    public Bayam(GridMap map, JPanel panel, Graphics2D g2d,int gx, int gy,int status) {
        super(map,panel,g2d,gx,gy);
        fase = status - 1;
        typeTanaman = 15;
        season = 3;
        happyMeter = 0;
        titikDewasa = 3;
        titikPanen = 6;
        umur = 10;
        panenBerulang = true;
    }

}
