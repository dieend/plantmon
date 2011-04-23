package plantmon.entity.movingObject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import plantmon.entity.MovingObject;
import plantmon.game.GridMap;
import plantmon.game.Point2D;
import plantmon.states.Game;
import plantmon.system.Cancellable;
import plantmon.system.Selectable;
import plantmon.system.Utilities;


public class PulmosisBattle extends MovingObject implements Cancellable,
                                                    Selectable{
    int HP;
    int level;
    int atk;
    int def;
    int agi;
    int exp;
    int attacked;
    int dmg;
    int range;
    int missed;
    int chargeMeter;
    private boolean active;
    private boolean selected;
    private boolean move;
    private boolean attack;
    boolean enemy;
    double miss;
    private int attackRange;
    public static final int Lobak = 0;
    public static final int Timun = 1;
    public static final int Kentang = 2;
    public static final int Kubis = 3;
    public static final int Stroberi = 4;
    public static final int Jagung = 5;
    public static final int Tomat = 6;
    public boolean isAlreadyMove(){
        return move;
    }
    public boolean isAlreadyAttack(){
        return attack;
    }
    public void makeAttack(){
        attack = true;
    }
    public void miss(){
        missed = 100;
    }
    public PulmosisBattle(GridMap map, JPanel panel, Graphics2D g2d, int tipe, boolean en) {
        super(map,panel,g2d);
        init();
        if (tipe == Lobak) {
            level = 3;
            range = 2;
            attackRange = 1;
        } else if (tipe == Timun) {
            level = 3;
            range = 4;
            attackRange = 1;
        } else if (tipe == Kentang) {
            level = 5;
            range = 3;
            attackRange = 1;
        } else if (tipe == Kubis) {
            level = 6;
            range = 2;
        } else if (tipe == Stroberi) {
            level = 8;
            range = 1;
        } else if (tipe == Jagung) {
            level = 9;
            range = 3;
        } else if (tipe == Tomat) {
            level = 10;
            range = 2;
        }
        atk = 0;
        attacked = 0;
        miss = 0.05;
        enemy = en;
        setDefend();
        setAgi();
        setAttack();
        setHP();
        chargeMeter = 0;
        move = false;
    }
    @Override public void update(){
        chargeMeter++;
//        System.out.println(""+chargeMeter+"/"+(10000/agi)+" move:"+move+" attack"+attack);
        if (!active && chargeMeter >= 1000/agi){
            active = true;
            move = false;
            attack = false;
        }
        super.update();
    }
    @Override public void draw() {
        super.draw();
        if (isActive()){
            creature.graphics().drawString("ACTIVE", position().IntX(), position().IntY());
        }
        if (attacked > 0) {
             if (dmg == -99999) {
                creature.graphics().drawString("miss", position().IntX(), position().IntY());
             } else {
                creature.graphics().drawString(""+dmg, position().IntX(), position().IntY());
             }
            attacked--;
        }
        if (missed >0) {
            creature.graphics().drawString("miss", position().IntX(), position().IntY());
            missed--;
        }
    }
    
    @Override public void drawBounds() {
        if (isActive()){
            creature.drawBounds(Color.RED);
            drawArea();
        }
    }
    @Override public JPanel get_Info(){
        ImageIcon image1 = null;
        JLabel label1;
        JLabel label2;
        JLabel label3;
        JPanel panel;
        panel = new JPanel();
        panel.setLayout(null);
//        image1 = new ImageIcon(this.getClass().getResource("player.jpg"));
        label1 = new JLabel(image1);
        label1.setBounds(0,0,100,100);
        panel.add(label1);
        label2 = new JLabel("Nama : " + Game.instance().getName());
        label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label2.setBounds(120,0,150,30);
        panel.add(label2);

        return panel;

    }
    @Override public void init() {
        //inisiasi semua variable disini.

        load("picture/anim", 4,1,32,32);
        creature.setImageName("picture/anim");
        creature.setVelocity(new Point2D(0,0));
        creature.setFrameDelay(1);
    }
    public void move(int gx,int gy,Object lock,Boolean[] cancel){
        move = true;
        addAction(lock,new Point2D(gx,gy));
//        Canceller ca = new Canceller(creature.panel(),creature.graphics(),
//                                    gx, gy, cancel,lock,(Cancellable)this,numAction-1);
//        map.push(gx, gy, ca);
    }
    
    public void doDamage (PulmosisBattle pulmo) {
        int HP = pulmo.getHP();
        int minus = 0;
        Random gene = new Random();
        int x = gene.nextInt(100);
        if (x < 94) {
            minus = (atk + level + gene.nextInt(5) + 1) - pulmo.getDefend();
            if (minus<=0) {
                 HP = HP - 1;
                 minus = 1;
            } else {
                HP = HP - minus;
            }
        } else {
            minus = -99999;
        }
        pulmo.setAttacked(100);
        pulmo.setDamage(minus);
        pulmo.setHP(HP);
    }

    public void setDamage (int i) {
        dmg = i;
    }

    public void setAttacked(int i) {
        attacked = i;
    }

    public int getHP () {
        return HP;
    }

    public int getRange () {
        return range;
    }

    public int getAttack () {
        return atk;
    }

    public int getDefend () {
        return def;
    }

    public int getAgi () {
        return agi;
    }

    public void setHP () {
        HP = level + atk * 2 + def;
    }

    public void setHP (int HP) {
        this.HP = HP;
    }

    public void setAttack () {
        atk = level * 3;
    }

    public void setDefend () {
        def = level * 2;
    }

    public void setAgi () {
        agi = (level * 4 + def + 10) * 12/100;
    }

    public void attackUp () {
        atk = atk + atk/10 + 1;
    }

    public void defendUp () {
        def = def + def/6 + 1;
    }

    public void agiUp () {
        agi = agi + agi/5 + 1;
    }

    public void levelUp (int plus) {
        int x;
        int i;
        exp = exp + plus;
        if ((exp/30) > 0) {
            x = exp/30;
            level = level + x;
            for (i = 1; i <= x; i++) {
                attackUp();
                setHP();
            }
        }
        exp = exp % 30;
    }

    public boolean getStatusEnemy () {
        return enemy;
    }

    @Override
    public void cancel(Object lock){
        destination.remove(lock);
        this.lock.remove(lock);
        this.numAction--;
        creature.setFinalPosition(this.position().IntX(),this.position().IntY());
        inAction = false;
        inAction = false;
    }

    public static void main(String[] str){
        JFrame frame=new JFrame();
        BufferedImage bf = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        PulmosisBattle a = new PulmosisBattle(new GridMap(), new JPanel(),bf.createGraphics(),PulmosisBattle.Kentang,true);
        frame.getContentPane().add(a.get_Info());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setVisible(true);
    }
    public void drawArea(){
        Graphics2D g2d = creature.graphics();
        int x = position().IntX()/Utilities.GRIDSIZE;
        int y = position().IntY()/Utilities.GRIDSIZE;
        int[] dx = {1,0,-1,0};
        int[] dy = {0,1,0,-1};
        boolean[][] udah = new boolean[map.getRow()][map.getColumn()];
        udah[x][y] = true;
        int dist = 0;
        ArrayList<Point2D> queue = new ArrayList<Point2D>();
        queue.add(new Point2D(x,y));
        while (!queue.isEmpty()){
            Point2D now = queue.get(0);
            queue.remove(0);
            for (int i=0; i<dx.length; i++){
                int cekx = now.IntX()+dx[i];
                int ceky = now.IntY()+dy[i];
                if (0<=cekx && cekx<map.getRow() && 0<=ceky && ceky<map.getColumn()){
                    dist = ((cekx-x)<0)?(cekx-x)*-1:(cekx-x);
                    dist += ((ceky-y)<0?(ceky-y)*-1:(ceky-y));
                    if (!isAlreadyMove() && dist<=getRange() && !udah[cekx][ceky]){
                        g2d.setColor(Color.BLUE);
                        g2d.drawRect(cekx*Utilities.GRIDSIZE, ceky*Utilities.GRIDSIZE, Utilities.GRIDSIZE, Utilities.GRIDSIZE);
                        queue.add(new Point2D(cekx,ceky));
                        udah[cekx][ceky] = true;
                    } else if (!isAlreadyMove() && dist<=getRange()+getAttackRange() && !udah[cekx][ceky]){
                        g2d.setColor(Color.RED);
                        g2d.drawRect(cekx*Utilities.GRIDSIZE, ceky*Utilities.GRIDSIZE, Utilities.GRIDSIZE, Utilities.GRIDSIZE);
                        queue.add(new Point2D(cekx,ceky));
                        udah[cekx][ceky] = true;
                    } else if (isAlreadyMove() && !isAlreadyAttack() && dist<=getAttackRange()&& !udah[cekx][ceky]){
                        g2d.setColor(Color.RED);
                        g2d.drawRect(cekx*Utilities.GRIDSIZE, ceky*Utilities.GRIDSIZE, Utilities.GRIDSIZE, Utilities.GRIDSIZE);
                        queue.add(new Point2D(cekx,ceky));
                        udah[cekx][ceky] = true;
                    }
                }
            }
        }
    }

    /**
     * @return the selected
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * @param selected the selected to set
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     * @return the attackRange
     */
    public int getAttackRange() {
        return attackRange;
    }

    /**
     * @param attackRange the attackRange to set
     */
    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }
    public void resetChargeMeter(){
        chargeMeter = 0;
        active = false;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }
}