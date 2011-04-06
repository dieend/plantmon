package plantmon.entity.unmoveable;

import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import plantmon.entity.Unmoveable;
import plantmon.game.GridMap;
import plantmon.system.Actionable;
import plantmon.system.Selectable;

public class Land extends Unmoveable implements Actionable{
    // bisa disiram, dicangkul, diput?

    final public static int NORMAL = 0;
    final public static int PLOWED = 1;
    final public static int WATERED = 2;
    
    int status;
    public Land(GridMap map, JPanel panel, Graphics2D g2d){
        super(map, panel, g2d);
        init();
    }
    public void init(){
        status = Land.NORMAL;
    }
    public JPopupMenu getMenu(Selectable selected){
        //TODO
        return null;
    }
}