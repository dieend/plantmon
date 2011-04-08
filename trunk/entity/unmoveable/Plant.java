package plantmon.entity.unmoveable;

import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import plantmon.entity.Unmoveable;
import plantmon.entity.movingObject.Player;
import plantmon.entity.unmoveable.Land.Move;
import plantmon.entity.unmoveable.Land.Plow;
import plantmon.entity.unmoveable.Land.Water;
import plantmon.game.GridMap;
import plantmon.system.Actionable;
import plantmon.system.Selectable;

public class Plant extends Unmoveable implements Actionable,
                                                Selectable{
    //bisa diapain aja?
    final public static int BIBITNOSIRAM    = 0;
    final public static int BIBITSIRAM      = 1;
    final public static int REMAJANOSIRAM   = 2;
    final public static int REMAJASIRAM     = 3;
    final public static int DEWASANOSIRAM   = 4;
    final public static int DEWASASIRAM     = 5;
    final public static int BIBITMATI       = 7;
    final public static int TANAMANMATI     = 7;

    int posisi;
    int fase;

    int	typeTanaman;
    int	cost;
    int season;
    int	happyMeter;
    int	titikDewasa;
    int	titikPanen;
    int	umur;
    boolean panenBerulang;

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
        if (selected instanceof Player){
            final Player player = (Player) selected;
            JPopupMenu menu = new JPopupMenu();
            menu.add(item);
            if ((fase == BIBITNOSIRAM) || (fase == BIBITSIRAM) || (fase == BIBITMATI)) {
                JMenuItem itemplant2;
                itemplant2 = new JMenuItem("plow");
                itemplant2.addActionListener(new Plow(selected));
                menu.add(itemplant2);
                JMenuItem itemplant3;
                if (fase == BIBITNOSIRAM){
                    JMenuItem itemplant3;
                    itemplant3 = new JMenuItem("water");
                    itemplant3.addActionListener(new Water(selected));
                    menu.add(itemplant3);
                }
            }
            else if ((fase == REMAJANOSIRAM) || (fase == REMAJASIRAM) || (fase == DEWASANOSIRAM) || (fase == DEWASASIRAM) || (fase == TANAMANMATI)) {
                JMenuItem itemplant4;
                itemplant4 = new JMenuItem("slash");
                itemplant4.addActionListener(new Slash(selected));
                menu.add(itemplant4);
                if ((fase == REMAJANOSIRAM) || (fase == DEWASANOSIRAM)) {
                    JMenuItem itemplant5;
                    itemplant5 = new JMenuItem("water");
                    itemplant5.addActionListener(new Water(selected));
                    menu.add(itemplant5);
                }
                if ((fase == DEWASANOSIRAM)||(fase == DEWASASIRAM)) {
                    JMenuItem itemplant6;
                    itemplant6 = new JMenuItem("harvest");
                    itemplant6.addActionListener(new Harvest(selected));
                    menu.add(itemplant6);
                }
        }
            menu.pack();
            return menu;
        }
        return null;
    }

    public void init(){

    }
}