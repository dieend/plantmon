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
import plantmon.game.TalkPanel;
import plantmon.states.Game;
import plantmon.system.Cancellable;
import plantmon.system.Selectable;
import plantmon.system.Utilities;


public class PulmosisBattle extends MovingObject implements Cancellable,
                                                    Selectable{
    boolean iscanbeattacked=false;
    int tipe;
    int HP;
    public int level;
    int atk;
    int def;
    int agi;
    public int tagi;;
    int exp;
    int attacked;
    int dmg;
    int range;
    int missed;
    int healed;
    int thundered;
    int speedup;
    int chargeMeter;
    String name;
    private boolean active;
    private boolean selected;
    private boolean move;
    private boolean attack;
    boolean enemy;
    double miss;
    private int attackRange;
    public static final int Lobak = 0;  //thunderpunch easy(3)
    public static final int Timun = 1;  //healing easy(3)
    public static final int Kentang = 2;//speedup easy(3)
    public static final int Kubis = 3;  //thunderpunch(3)
    public static final int Stroberi = 4;//healing easy(3)
    public static final int Jagung = 5;//speedup easy(3)
    public static final int Tomat = 6;  //thunderpunch advance(2)
    public static final int Nanas = 7; //healing advance(2)
    public static final int Bawang = 8; //speedup advance(2)
    public static final int Labu = 9;   //thunderpunch advance(2)
    public static final int Ubi = 10;  //healing advance(2)
    public static final int Terong = 11;//speedup advance(2)
    public static final int Paprika = 12;//thunderpunch mega(1)
    public static final int Bayam = 13;  //healing mega(1)
    public static final int Wortel = 14; //speedup mega(1)

    public static final int easy=-1;
    public static final int medium=-2;
    public static final int hard=-3;
    public static final int boss=-4;

    //code 0-2 untuk easy enemy
    public static final int peach=-1;    //tipe atk
    public static final int papaya=-2;   //tipe int
    public static final int kiwifruit=-3;//tipe agi

    //code 3-5 untuk medium enemy
    public static final int cranberry=-4;//tipe atk
    public static final int blueberry=-5;//tipe int
    public static final int barberry=-6;//tipe agi

    //code 6-2 untuk hard enemy
    public static final int blacklobak=-7;//tipe atk
    public static final int blackjagung=-8;//tipe int
    public static final int blacknanas=-9;//tipe agi

    //code 9 untuk boss
    public static final int megabadpumpkin=-10;//all tipe
    public  int specialtotal;
    public  int defaultspecialtotal=0;

    public int getTipe(){
        return tipe;
    }

    ArrayList<PulmosisBattle> alpb = new ArrayList<PulmosisBattle>();
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

    /**
     * 
     *levelpb, diperhitungkan jika en true*/
    
    public PulmosisBattle(GridMap map, JPanel panel, Graphics2D g2d, int tipe, boolean en,int levelpb) {
        super(map,panel,g2d);
        this.tipe = tipe;
        init();
        if (!en)
        {
            if (tipe == Lobak) {
                level = 3;
                range = 2;
                attackRange = 3;
                name = "Lobak";
                specialtotal=3;
                defaultspecialtotal=3;
            } else if (tipe == Timun) {
                level = 3;
                range = 4;
                attackRange = 1;
                name = "Timun";
                specialtotal=3;
                defaultspecialtotal=3;
            } else if (tipe == Kentang) {
                level = 3;
                range = 3;
                attackRange = 2;
                name = "Kentang";
                specialtotal=3;
                defaultspecialtotal=3;
            } else if (tipe == Kubis) {
                level = 3;
                range = 1;
                attackRange = 2;
                name = "Kubis";
                specialtotal=3;
                defaultspecialtotal=3;
            } else if (tipe == Stroberi) {
                level = 3;
                range = 5;
                attackRange = 1;
                name = "Stroberi";
                specialtotal=3;
                defaultspecialtotal=3;
            } else if (tipe == Jagung) {
                level = 3;
                range = 3;
                attackRange = 3;
                name = "Jagung";
                specialtotal=3;
                defaultspecialtotal=3;
            } else if (tipe == Tomat) {
                level = 4;
                range = 3;
                attackRange = 3;
                name = "Tomat";
                specialtotal=2;
                defaultspecialtotal=2;
            } else if (tipe == Nanas) {
                level = 4;
                range = 4;
                attackRange = 2;
                name = "Nanas";
                specialtotal=2;
                defaultspecialtotal=2;
            } else if (tipe == Bawang) {
                level = 4;
                range = 2;
                attackRange = 4;
                name = "Bawang";
                specialtotal=2;
                defaultspecialtotal=2;
            } else if (tipe == Labu) {
                level = 4;
                range = 4;
                attackRange = 3;
                name = "Labu";
                specialtotal=2;
                defaultspecialtotal=2;
            } else if (tipe == Ubi) {
                level = 4;
                range = 5;
                attackRange = 2;
                name = "Ubi";
                specialtotal=2;
                defaultspecialtotal=2;
            } else if (tipe == Terong) {
                level = 4;
                range = 2;
                attackRange = 5;
                name = "Terong";
                specialtotal=2;
                defaultspecialtotal=2;
            } else if (tipe == Paprika) {
                level = 5;
                range = 4;
                attackRange = 5;
                name = "Paprika";
                specialtotal=1;
                defaultspecialtotal=1;
            } else if (tipe == Bayam) {
                level = 5;
                range = 6;
                attackRange = 3;
                name = "Bayam";
                specialtotal=1;
                defaultspecialtotal=1;
            } else if (tipe == Wortel) {
                level = 5;
                range = 3;
                attackRange = 6;
                name = "Wortel";
                specialtotal=1;
                defaultspecialtotal=1;
            }
        }else
        {
            if (tipe==peach || tipe==papaya || tipe==kiwifruit)
            {
                this.level=levelpb-2;
                if (tipe==peach)
                {
                    range=2;
                    attackRange=3;
                    name="peach";
                }else if (tipe==papaya)
                {
                    range=4;
                    attackRange=1;
                    name="papaya";
                }else if (tipe==kiwifruit)
                {
                    range=1;
                    attackRange=4;
                    name="kiwifruit";
                }
            }else  if (tipe==cranberry || tipe==blueberry || tipe==barberry)
            {
                this.level=levelpb-1;
                if (tipe==cranberry)
                {
                    range=3;
                    attackRange=4;
                    name="cranberry";
                }else if (tipe==blueberry)
                {
                    range=5;
                    attackRange=1;
                    name="blueberry";
                }else if (tipe==barberry)
                {
                    range=1;
                    attackRange=5;
                    name="barberry";
                }
            }else if (tipe==blacklobak || tipe==blackjagung || tipe==blacknanas)
            {
                this.level=levelpb;
                if (tipe==blacklobak)
                {
                    range=4;
                    attackRange=4;
                    name="blacklobak";
                }else if (tipe==blackjagung)
                {
                    range=6;
                    attackRange=2;
                    name="blackjagung";
                }else if (tipe==blacknanas)
                {
                    range=2;
                    attackRange=6;
                    name="blacknanas";
                }
            }else if (tipe==megabadpumpkin){
                this.level=levelpb+1;
                range=5;
                attackRange=5;
                name="megabadpumpkin";
            }
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
        if (healed > 0){
            Color oldcolor = creature.graphics().getColor();
            creature.graphics().setColor(Color.green);
            creature.graphics().drawString(""+this.getHP(), position().IntX(), position().IntY());
            creature.graphics().setColor(oldcolor); 
            
            healed--;
        }
        if (thundered > 0){
            Color oldcolor = creature.graphics().getColor();
            creature.graphics().setColor(Color.blue);
            creature.graphics().drawString(""+this.getHP() + "/\\/\\/\\/\\", position().IntX(), position().IntY());
            creature.graphics().drawString("  /\\/\\/\\/\\", position().IntX(), position().IntY()+5);
            creature.graphics().drawString("  /\\/\\/\\/\\", position().IntX(), position().IntY()+15);
            creature.graphics().drawString("  /\\/\\/\\/\\", position().IntX(), position().IntY()+25);
            creature.graphics().drawString("  /\\/\\/\\/\\", position().IntX(), position().IntY()+35);
            creature.graphics().setColor(oldcolor);
            thundered--;
        }
        if (speedup > 0){
            Color oldcolor = creature.graphics().getColor();
            creature.graphics().setColor(Color.ORANGE);
            creature.graphics().drawString(" ~~", position().IntX()-15, position().IntY()+30);
            creature.graphics().drawString(" ~~", position().IntX()-15, position().IntY()+32);
            creature.graphics().drawString(" ~~", position().IntX()-15, position().IntY()+35);
            creature.graphics().drawString("~~~", position().IntX()-15, position().IntY()+38);
            creature.graphics().drawString("~~~", position().IntX()-15, position().IntY()+40);
            creature.graphics().drawString("~~~", position().IntX()-15, position().IntY()+42);
            creature.graphics().drawString("~~~", position().IntX()-15, position().IntY()+45);

            creature.graphics().drawString("~~", position().IntX()+38, position().IntY()+30);
            creature.graphics().drawString("~~", position().IntX()+38, position().IntY()+32);
            creature.graphics().drawString("~~", position().IntX()+38, position().IntY()+35);
            creature.graphics().drawString("~~~", position().IntX()+38, position().IntY()+38);
            creature.graphics().drawString("~~~", position().IntX()+38, position().IntY()+40);
            creature.graphics().drawString("~~~", position().IntX()+38, position().IntY()+42);
            creature.graphics().drawString("~~~", position().IntX()+38, position().IntY()+45);
            creature.graphics().setColor(oldcolor);
            speedup--;
            setAgi(tagi);
        }else{
            setAgi();
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
        panel = new TalkPanel();
        panel.setLayout(null);
//        image1 = new ImageIcon(this.getClass().getResource("player.jpg"));
        label1 = new JLabel(image1);
        label1.setBounds(0,0,100,100);
        panel.add(label1);
        label2 = new JLabel("Name : " + name);
        label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label2.setBounds(120,0,150,30);
        panel.add(label2);

        return panel;

    }
    @Override public void init() {
        //inisiasi semua variable disini.
        if (this.tipe == Lobak) {
            load("picture/pulmo_1_", 4,1,48,48);
            creature.setImageName("picture/pulmo_1_");
            creature.setVelocity(new Point2D(0,0));
            creature.setFrameDelay(1);
        } else if (this.tipe == Timun) {
            load("picture/pulmo_2_", 4,1,48,48);
            creature.setImageName("picture/pulmo_2_");
            creature.setVelocity(new Point2D(0,0));
            creature.setFrameDelay(1);
        } else if (this.tipe == Paprika) {
            load("picture/pulmo_3_", 4,1,48,48);
            creature.setImageName("picture/pulmo_3_");
            creature.setVelocity(new Point2D(0,0));
            creature.setFrameDelay(1);
        } else if (this.tipe == Tomat) {
            load("picture/pulmo_8_", 4,1,48,48);
            creature.setImageName("picture/pulmo_8_");
            creature.setVelocity(new Point2D(0,0));
            creature.setFrameDelay(1);
        } else if (this.tipe == Kentang) {
            load("picture/pulmo_10_", 4,1,48,48);
            creature.setImageName("picture/pulmo_10_");
            creature.setVelocity(new Point2D(0,0));
            creature.setFrameDelay(1);
        } else if (this.tipe == Kubis) {
            load("picture/pulmo_10_", 4,1,48,48);
            creature.setImageName("picture/pulmo_10_");
            creature.setVelocity(new Point2D(0,0));
            creature.setFrameDelay(1);
        } else if (this.tipe == Stroberi) {
            load("picture/pulmo_5_", 4,1,48,48);
            creature.setImageName("picture/pulmo_5_");
            creature.setVelocity(new Point2D(0,0));
            creature.setFrameDelay(1);
        } else if (this.tipe == Jagung) {
            load("picture/pulmo_7_", 4,1,48,48);
            creature.setImageName("picture/pulmo_7_");
            creature.setVelocity(new Point2D(0,0));
            creature.setFrameDelay(1);
        } else if (this.tipe == Bawang) {
            load("picture/pulmo_4_", 4,1,48,48);
            creature.setImageName("picture/pulmo_4_");
            creature.setVelocity(new Point2D(0,0));
            creature.setFrameDelay(1);
        } else if (this.tipe == Nanas) {
            load("picture/pulmo_10_", 4,1,48,48);
            creature.setImageName("picture/pulmo_10_");
            creature.setVelocity(new Point2D(0,0));
            creature.setFrameDelay(1);
        } else if (this.tipe == Ubi) {
            load("picture/pulmo_10_", 4,1,48,48);
            creature.setImageName("picture/pulmo_10_");
            creature.setVelocity(new Point2D(0,0));
            creature.setFrameDelay(1);
        } else if (this.tipe == Terong) {
            load("picture/pulmo_10_", 4,1,48,48);
            creature.setImageName("picture/pulmo_10_");
            creature.setVelocity(new Point2D(0,0));
            creature.setFrameDelay(1);
        } else if (this.tipe == Labu) {
            load("picture/pulmo_9_", 4,1,48,48);
            creature.setImageName("picture/pulmo_9_");
            creature.setVelocity(new Point2D(0,0));
            creature.setFrameDelay(1);
        } else if (this.tipe == Bayam) {
            load("picture/pulmo_10_", 4,1,48,48);
            creature.setImageName("picture/pulmo_10_");
            creature.setVelocity(new Point2D(0,0));
            creature.setFrameDelay(1);
        } else if (this.tipe == Wortel) {
            load("picture/pulmo_6_", 4,1,48,48);
            creature.setImageName("picture/pulmo_6_");
            creature.setVelocity(new Point2D(0,0));
            creature.setFrameDelay(1);
        } else if (this.tipe == peach) {
            load("picture/pulmo_16_", 4,1,48,48);
            creature.setImageName("picture/pulmo_16_");
            creature.setVelocity(new Point2D(0,0));
            creature.setFrameDelay(1);
        } else if (this.tipe == papaya) {
            load("picture/pulmo_18_", 4,1,48,48);
            creature.setImageName("picture/pulmo_18_");
            creature.setVelocity(new Point2D(0,0));
            creature.setFrameDelay(1);
        } else if (this.tipe == kiwifruit) {
            load("picture/pulmo_20_", 4,1,48,48);
            creature.setImageName("picture/pulmo_20_");
            creature.setVelocity(new Point2D(0,0));
            creature.setFrameDelay(1);
        } else if (this.tipe == cranberry) {
            load("picture/pulmo_17_", 4,1,48,48);
            creature.setImageName("picture/pulmo_19_");
            creature.setVelocity(new Point2D(0,0));
            creature.setFrameDelay(1);
        } else if (this.tipe == blueberry) {
            load("picture/pulmo_16_", 4,1,48,48);
            creature.setImageName("picture/pulmo_22_");
            creature.setVelocity(new Point2D(0,0));
            creature.setFrameDelay(1);
        } else if (this.tipe == barberry) {
            load("picture/pulmo_16_", 4,1,48,48);
            creature.setImageName("picture/pulmo_21_");
            creature.setVelocity(new Point2D(0,0));
            creature.setFrameDelay(1);
        } else if (this.tipe == blacklobak) {
            load("picture/pulmo_16_", 4,1,48,48);
            creature.setImageName("picture/pulmo_16_");
            creature.setVelocity(new Point2D(0,0));
            creature.setFrameDelay(1);
        } else if (this.tipe == blackjagung) {
            load("picture/pulmo_16_", 4,1,48,48);
            creature.setImageName("picture/pulmo_5_");
            creature.setVelocity(new Point2D(0,0));
            creature.setFrameDelay(1);
        } else if (this.tipe == blacknanas) {
            load("picture/pulmo_19_", 4,1,48,48);
            creature.setImageName("picture/pulmo_3_");
            creature.setVelocity(new Point2D(0,0));
            creature.setFrameDelay(1);
        } else if (this.tipe == megabadpumpkin) {
            load("picture/pulmo_16_", 4,1,48,48);
            creature.setImageName("picture/pulmo_9_");
            creature.setVelocity(new Point2D(0,0));
            creature.setFrameDelay(1);
        }
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

    public void doHealing(){
       healed=100;
    }

    public void doThunder(){
        thundered=100;
    }

    public void doSpeedup(int time){
        speedup=time;
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
        tagi = agi;
    }

    public void setAgi (int nagi){
        agi = nagi;
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

    public int getMaxHP(){
        return  level + atk * 2 + def;
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
        PulmosisBattle a = new PulmosisBattle(new GridMap(), new JPanel(),bf.createGraphics(),PulmosisBattle.Kentang,true,0);
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

    public void instantiateALPB(){
        alpb.clear();
        for(int i=0;i< map.getRow();++i){
            for(int j=0;j< map.getColumn();++j){
                if (map.getTop(i, j) instanceof PulmosisBattle){
                    if ( !((PulmosisBattle)map.getTop(i, j)).getStatusEnemy() ){
                        alpb.add((PulmosisBattle)map.getTop(i, j));
                    }
                }
            }
        }
    }

    public boolean isInRange(int xp,int yp,int range,int xdes,int ydes){
       return (Math.abs(xdes-xp)+Math.abs(ydes-yp)<=(range*Utilities.GRIDSIZE));
    }

    /**
     *
     * @returnnya adalah pixel dari posisi dari musuh yang terdekat
     */
    public Point2D closestEn(PulmosisBattle en){
        Point2D ce = alpb.get(0).position();
        en=alpb.get(0);
        int length=Math.abs(this.position().IntX()-ce.IntX()) + Math.abs(this.position().IntY()-ce.IntY());
        for(int i=1;i<alpb.size();++i){
            if (length> (Math.abs(this.position().IntX()-alpb.get(i).position().IntX()) + Math.abs(this.position().IntY()-alpb.get(i).position().IntY()))){
                length=(Math.abs(this.position().IntX()-alpb.get(i).position().IntX()) + Math.abs(this.position().IntY()-alpb.get(i).position().IntY()));
                ce = alpb.get(i).position();
                en=alpb.get(i);
            }
        }
//        System.out.println("CLOSEST ENEMY :" + ce.IntX() + " - " + ce.IntY());
        return ce;
    }

    /**
     *
     * @param x pixel
     * @param y pixel
     * @return true jika pixel(x,y) di map
     */
    public boolean isInMap(int x,int y){
        int gx=x/Utilities.GRIDSIZE;
        int gy=y/Utilities.GRIDSIZE;
        return ((gx>=0) && (gx<=map.getRow()) && (gy>=0) && (gy<=map.getColumn()));
    }

    public int minattackrange(int xp,int yp,int xdes,int ydes){
        return Math.abs(xdes-xp)+Math.abs(ydes-yp);
    }

    public Point2D closestMove(int xp,int yp,int range,int xdes,int ydes){
        Point2D cm=null;
        int length=map.getColumn()*Utilities.GRIDSIZE;
        int templength;
        int i,j;
        int x0=xp-(range*Utilities.GRIDSIZE);
        int y0=yp-(range*Utilities.GRIDSIZE);
        int xf=xp+(range*Utilities.GRIDSIZE);
        int yf=yp+(range*Utilities.GRIDSIZE);
//        System.out.println(x0 + "-" + y0 + "  :  " + xf + "-" + yf);
        iscanbeattacked=false;
        
        for(i=x0;i<=xf;i+=Utilities.GRIDSIZE){
            for(j=y0;j<=yf;j+=Utilities.GRIDSIZE){
                if (isInMap(i, j) && isInRange(i, j, range, xp, yp)){
//                    System.out.println("qualified : " + i + "," + j);
                    templength=Math.abs(xdes-i)+Math.abs(ydes-j);

                        if (tipe<=-4 && tipe>=-10){
//                            System.out.println("TYPE " + tipe);
                                if (templength==(attackRange*Utilities.GRIDSIZE)){
                                    length=templength;
                                    cm = new Point2D(i,j);
//                                    System.out.println("TEMUKAN MINIMUM : " + cm.IntX() + "," + cm.IntY());
                                    iscanbeattacked=true;
//                                    System.out.println("Foundmin = true");
                                    break;
                                }
                            }

                    
                    if (length>templength){
                        length=templength;
                        cm = new Point2D(i, j);
                    }
                }
            }
            if (iscanbeattacked){
                break;
            }
        }
        return cm;
    }

    public Point2D minPoint(int xp,int yp,int xdes,int ydes){
        Point2D mp=null;
        int length=0;
        int i,j;
        int row = map.getRow()*Utilities.GRIDSIZE;
        int col = map.getColumn()*Utilities.GRIDSIZE;
        for(i=0;i<row;i+=Utilities.GRIDSIZE){
            for(j=0;j<col;j+=Utilities.GRIDSIZE){
                if (isInMap(i, j) && isInRange(i, j, attackRange, xdes, ydes)){
                    int templength=Math.abs(xdes-i)+Math.abs(ydes-j);
                    if (length<templength){
//                        System.out.println("kemungkinan : " + i + "-" +j);
                        length=templength;
                        mp = new Point2D(i,j);
                    }
                }
            }
        }
        return mp;
    }
    
    public void nextMove(){
        instantiateALPB();
        if (!alpb.isEmpty()){
            
            System.out.println("This is it :");
            performALPB();
            Object lock = new String("stop");
            Boolean[] cancel = new Boolean[1];
            cancel[0] = false;
            PulmosisBattle en=alpb.get(0); 
            
            Point2D ce=closestEn(en);
            
//            System.out.println("COOR AWAL :"+ this.position().IntX() + " - " + this.position().IntY());
//            System.out.println("COOR AKHIR :"+ ce.IntX() + " - " + ce.IntY());
//            System.out.println("RANGE : " + range);
            
            if (isInRange(this.position().IntX(), this.position().IntY(),attackRange , ce.IntX(), ce.IntY())){
//                delaymove();
                attackAI(en);
//                System.out.println("IN RANGE//ATTACKKKKK!!!!");
                
                if (tipe<=-7 && tipe>=-10){
                    Point2D mp = minPoint(this.position().IntX(), this.position().IntY(), ce.IntX(), ce.IntY());
                    if (mp!=null){
//                        System.out.println("SAFE POINT : " + mp.IntX() + "-" + mp.IntY());
                        this.move(mp.IntX(), mp.IntY(), lock, cancel);
                    }
                }
                
                
            }else{//System.out.println("NOT IN ATTACK RANGE");
                Point2D cm = closestMove(this.position().IntX(), this.position().IntY(), range, ce.IntX(), ce.IntY());
//                System.out.println("ICBATTAK : " +iscanbeattacked);
                if (cm!=null)
                {
//                    System.out.println("CLOSEST MOVE : " + cm.IntX() + " - " + cm.IntY());
//                    System.out.println("POSISI attacker :" + this.position().IntX() + "-" + this.position().IntY());
//                    delaymove();
                    this.move(cm.IntX(), cm.IntY(), lock, cancel);
                    /*
                    synchronized(lock){
                        try{
                            lock.wait();
                        }catch(InterruptedException ie){
                        }
                    }
                    if(!cancel[0]) {
                        this.getCreature().setFinalPosition(cm.IntX(), cm.IntY());
                    }*/
//                    while (! (cm.IntX()==this.position().IntX() && cm.IntY()==this.position().IntY())){
//
//                    }
//                    System.out.println("MOVING TO ATTACK");
//                    System.out.println("POSISI attacker :" + this.getCreature().position().IntX() + "-" + this.getCreature().position().IntY());
//                    System.out.println("POSISI attacked :" + ce.IntX() + "-" + ce.IntY());
//                    System.out.println("ATKRANGE : " + attackRange);
//                    if (iscanbeattacked){
//                        System.out.println("BISA MENYERANG");
//                        attackAI(en);
//                        iscanbeattacked=false;
//                    }else{
//                        System.out.println("TIDAK BISA MENYERANG");
//                    }
                    
                    if (isInRange(this.position().IntX(), this.position().IntY(), attackRange, ce.IntX(), ce.IntY())){
//                        System.out.println("MOVE ATTACK");
                        attackAI(en);
//                        System.out.println("IN MOVE//ATTACKKKKK!!!!");
                    }else{
//                        System.out.println("FAILED MOVE ATTACK");
                    }
                }else{
//                    System.out.println("HIHI");
                    this.move(cm.IntX(), cm.IntY(), lock, cancel);
//                    System.out.println("HAHA");
                }
            }
        }
    }

    public void attackAI(PulmosisBattle enemy){
           this.doDamage(enemy);
           if (enemy.getHP() <= 0) {
               map.pop(enemy.position().IntX(), enemy.position().IntY());
               this.levelUp(12);
           }
           this.resetChargeMeter();
    }

    public void performALPB(){
        for(int i=0;i<alpb.size();++i){
//            System.out.println(alpb.get(i).position().IntX() + " - " + alpb.get(i).position().IntY());
        }
    }

    public String getName() {
        return name;
    }

    public void setEnemy(boolean ene) {
        enemy = ene;
    }

//    public void delaymove(){
//        int i,j=0;
//        if (tipe==peach || tipe==papaya || tipe==kiwifruit){
//            for(i=0;i<22000;++i){
//                j+=2;
//                System.out.println(j);
//            }
//        }else if (tipe==cranberry || tipe==blueberry || tipe==barberry){
//            for(i=0;i<10000;++i){
//                j+=2;
//                System.out.println(j);
//            }
//        }
//    }
}