package plantmon.entity.unmoveable;

import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import plantmon.entity.Unmoveable;
import plantmon.entity.movingObject.Player;
import plantmon.game.GridMap;
import plantmon.game.Point2D;
import plantmon.states.FarmState;
import plantmon.system.Actionable;
import plantmon.system.RunnableListener;
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
    final public static int BIBITMATI       = 6;
    final public static int TANAMANMATI     = 7;

    int posisi;
    int fase;

    int	typeTanaman;
    int season;
    int	happyMeter;
    int	titikDewasa;
    int	titikPanen;
    int	umur;
    boolean panenBerulang;
    // konstruktor default
    //konstruktor dari item.
    public Plant(GridMap map, JPanel panel, Graphics2D g2d,int gx,int gy){
        super(map, panel, g2d);
        init();
        entity.setPosition(new Point2D(gx,gy));
        entity.setFinalPosition(gx, gy);
    }
    public void drawBounds(){
        entity.drawBounds(Color.GREEN);
    }
    public int getFase(){
        return fase;
    }
    boolean isWatered() 
    // mengeluarkan TRUE jika tanaman sudah disiram.
    {
            if ((getFase() % 2 == 1) && (getFase() != 7)) {
                    return true;
            } else {
                    return false;
            }
    }

    boolean isBibit() 
    // mengeluarkan TRUE jika tanaman berupa bibit.
    {
            if (getFase() == 0 || getFase() == 1)
                    return true;
            else
                    return false;
    }

    boolean isRemaja() 
    {
            if (getFase() == 2 || getFase() == 3)
                    return true;
            else
                    return false;
    }

    boolean isDewasa() 
    {
            if (getFase() == 4 || getFase() == 5)
                    return true;
            else
                    return false;
    }

    boolean isPanenBerulang() 
    // mengeluarkan TRUE jika tanaman yang dapat berbuah lagi
    {
            return panenBerulang;
    }
    public String getInfo(){
        return null;
    }
    public JPopupMenu getMenu(Selectable selected){
        if (selected instanceof Player){
            final Player player = (Player) selected;
            JPopupMenu menu = new JPopupMenu();
            if ((fase == BIBITNOSIRAM) || (fase == BIBITSIRAM) || (fase == BIBITMATI)) {
                JMenuItem itemplant2;
                itemplant2 = new JMenuItem("plow");
                itemplant2.addActionListener(new Plow(selected));
                menu.add(itemplant2);

                JMenuItem itemplant3;
                if (fase == BIBITNOSIRAM) {
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

    private void setFase(int i) {
        fase = i;
    }

    private void setHappyMeter(int i) {
        happyMeter = i;
    }

    private void setUmur(int i) {
        umur = i;
    }

    private void setTitikPanen(int i) {
        titikPanen = i;
    }

    class Water extends RunnableListener {
        Water(Selectable selected){
            super(selected);
        }
        public void run() {
            // membuat player berjalan ke posisi tumbuhan
            Player player = (Player) selected;
            // gx dan gy adalah posisi tumbuhan saat ini
            int gx = (int)Plant.this.getPosition().X();
            int gy = (int)Plant.this.getPosition().Y();
            Object lock = new Object();
            Boolean[] cancel = new Boolean[1];
            player.move(gx, gy, lock,cancel);
            synchronized(lock){
                try {
                    lock.wait(); // tunggu player sampai ke posisi tumbuhan
                } catch (InterruptedException e){}
            }
            // setelah player sampai, siram tanaman.
            if (!cancel[0]){
                map.pop(gx, gy);
                fase = fase + 1;
            }
        }
    }
    /**
     * TODO: Buat item berdasarkan tanaman yang diharvest
     */
    class Harvest extends RunnableListener {
        Harvest(Selectable selected){
            super(selected);
        }
        public void run() {
        // membuat player berjalan ke posisi tumbuhan
            Player player = (Player) selected;
            // gx dan gy adalah posisi tumbuhan saat ini
            int gx = (int)Plant.this.getPosition().X();
            int gy = (int)Plant.this.getPosition().Y();
            Object lock = new Object();
            Boolean[] cancel = new Boolean[1];
            player.move(gx, gy, lock,cancel);
            synchronized(lock){
                try {
                    lock.wait(); // tunggu player sampai ke posisi tumbuhan
                } catch (InterruptedException e){}
            }
            // setelah player sampai, siram tanaman.
            if (!cancel[0]){
                map.pop(gx, gy);
                if (panenBerulang) {
                    fase = REMAJANOSIRAM;
                }
                else {
                    fase = TANAMANMATI;
                }
                FarmState.text.append("harvesting at ("+(gx/80)+","+(gy/80)+")\n");
            }
        }
    }

    class Plow extends RunnableListener {
        Plow(Selectable selected){
            super(selected);
        }
        public void run() {
            // membuat player berjalan ke posisi tumbuhan
            Player player = (Player) selected;
            // gx dan gy adalah posisi tumbuhan saat ini
            int gx = (int)Plant.this.getPosition().X();
            int gy = (int)Plant.this.getPosition().Y();
            Object lock = new Object();
            Boolean[] cancel = new Boolean[1];
            player.move(gx, gy, lock,cancel);
            synchronized(lock){
                try {
                    lock.wait(); // tunggu player sampai ke posisi tumbuhan
                } catch (InterruptedException e){}
            }
            if (!cancel[0]){
                map.pop(gx, gy);
                map.pop(gx, gy);
                FarmState.text.append("plowing  ("+(gx/80)+","+(gy/80)+")\n");
            }
        }
    }
    class Slash extends RunnableListener {
        Slash(Selectable selected){
            super(selected);
        }
        public void run() {
        // membuat player berjalan ke posisi tumbuhan
            Player player = (Player) selected;
            // gx dan gy adalah posisi tumbuhan saat ini
            int gx = (int)Plant.this.getPosition().X();
            int gy = (int)Plant.this.getPosition().Y();
            Object lock = new Object();
            Boolean[] cancel = new Boolean[1];
            player.move(gx, gy, lock,cancel);
            synchronized(lock){
                try {
                    lock.wait(); // tunggu player sampai ke posisi tumbuhan
                } catch (InterruptedException e){}
            }
            if (!cancel[0]){
                map.pop(gx, gy);
                map.pop(gx, gy);
                FarmState.text.append("slashing at ("+(gx/80)+","+(gy/80)+")\n");
            }
        }
    }
    void setSiram()
    // pengubahan fase tanaman ketika disiram
    // instant change
    {
            if (((getFase() != BIBITMATI) || (getFase() != TANAMANMATI)) && (!isWatered()))
                    setFase(getFase()+1);
    }

    void setPanen()
    // pengubahan fase tanaman ketika dipanen
    // instant change
    {
            if (isPanenBerulang()) {
                    if (getFase() == DEWASASIRAM) {
                            setFase(REMAJASIRAM);
                    } else {
                            setFase(REMAJANOSIRAM);
                    }
                    setHappyMeter(titikDewasa);
                    if (happyMeter + 1 != titikPanen) {
                            setTitikPanen(titikPanen-1);
                    }
            } else {
                    setFase(6);
        }
    }
    public void grow(int newCurrentSeason)
// mengubah fase pada pergantian hari
// not instant change
{
	setUmur(umur-1);
	if (isWatered())
		setHappyMeter(happyMeter+1);
	else
		setHappyMeter(happyMeter-1);
	if (umur == 0)
		{
		if (isBibit())
			setFase(BIBITMATI);
		else
			setFase(TANAMANMATI);
		}
	else if (newCurrentSeason != season)
		{
		if (isBibit())
			{
			setFase(BIBITMATI);
			setUmur(0);
			}
		else
			{
			setFase(TANAMANMATI);
			setUmur(0);
			}
		}
	else
		{
		if (isBibit())
			{
			if (happyMeter == titikDewasa)
				setFase(REMAJANOSIRAM);
			else
				setFase(BIBITNOSIRAM);
			}
		else if (isDewasa())
			{
			if (happyMeter == titikPanen)
				setFase(DEWASANOSIRAM);
			else
				setFase(REMAJANOSIRAM);
			}
		}
    }
    public void init(){
        entity.load("picture/bibit.png", 1, 1, 80, 80);
        entity.setFrameDelay(5);
    }
}