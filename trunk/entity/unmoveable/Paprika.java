package plantmon.entity.unmoveable;

import java.awt.Graphics2D;
import javax.swing.JPanel;
import plantmon.game.GridMap;

public class Paprika extends Plant{
    public Paprika(GridMap map, JPanel panel, Graphics2D g2d,int gx, int gy,int status) {
        super(map,panel,g2d,gx,gy);
        fase = status - 1;
        typeTanaman = 12;
        season = 2;
        happyMeter = 0;
        titikDewasa = 6;
        titikPanen = 8;
        umur = 20;
        panenBerulang = true;
    }

}