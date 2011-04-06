package plantmon.entity.unmoveable;

import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import plantmon.entity.Unmoveable;
import plantmon.game.GridMap;
import plantmon.system.Actionable;
import plantmon.system.Selectable;

public class SellBox extends Unmoveable implements Actionable{

    int status;
    public SellBox(GridMap map, JPanel panel, Graphics2D g2d){
        super(map, panel, g2d);
        init();
    }
    public void init(){
    }
    public JPopupMenu getMenu(Selectable selected){
        //TODO
        return null;
    }
}