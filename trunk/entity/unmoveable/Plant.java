package plantmon.entity.unmoveable;

import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import plantmon.entity.Unmoveable;
import plantmon.game.GridMap;
import plantmon.system.Actionable;
import plantmon.system.Selectable;

public class Plant extends Unmoveable implements Actionable,
                                                Selectable{
    //bisa diapain aja?
    public Plant(GridMap map, JPanel panel, Graphics2D g2d){
        super(map, panel, g2d);
        init();
    }

    public void drawBounds(){
        entity.drawBounds(Color.GREEN);
    }

    public String getInfo(){
        return null;
    }
    public JPopupMenu getMenu(Selectable selected){
        return null;
    }
    public void init(){

    }
}