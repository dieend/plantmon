package plantmon.entity;

import java.awt.Graphics2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import plantmon.game.Point2D;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Scanner;
import javax.swing.JPanel;
import plantmon.entity.deadItem.Portal;
import plantmon.entity.movingObject.Dwarf;
import plantmon.entity.movingObject.Player;
import plantmon.entity.movingObject.Pulmosis;
import plantmon.entity.unmoveable.Land;
import plantmon.entity.unmoveable.Plant;
import plantmon.entity.unmoveable.Road;
import plantmon.game.AnimatedSprite;
import plantmon.game.GridMap;
import plantmon.system.Cancellable;
import plantmon.system.Drawable;
import plantmon.system.Utilities;

public abstract class MovingObject implements Drawable, Serializable{
    final public static int PLAYER = 0;
    protected int type;
    public static final int water=1;
    public static final int harvest=2;
    public static final int slash=3;
    transient protected ArrayList<Object> lock;
    transient protected IdentityHashMap<Object,Point2D> destination;
    transient protected GridMap map;
    transient protected boolean inAction;
    transient protected int numAction;
    
    protected AnimatedSprite creature;
    protected ArrayList<Point2D> route = new ArrayList<Point2D>();

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        in.defaultReadObject();
        lock = new ArrayList<Object>();
        destination = new IdentityHashMap<Object,Point2D>();
        map = new GridMap();
    }
    public MovingObject(GridMap map, JPanel panel, Graphics2D g2d){
        this.map = map;
        lock = new ArrayList<Object>();
        destination = new IdentityHashMap<Object,Point2D>();
        creature = new AnimatedSprite(panel, g2d);
        numAction = 0;
    //    init();
    }
    public void setPanel(JPanel panel){
        creature.setPanel(panel);
    }
    public void setGraphic(Graphics2D g2d){
        creature.setGraphics(g2d);
    }

    public int getID(){
        return type;
    }

    public void setID(int i){
        type=i;
    }
    
    public void reinit(GridMap map,Graphics2D g2d, JPanel panel) {
        this.map=map;
        setGraphic(g2d);
        setPanel(panel);
    }
    public void load(String filename, int columns, int rows,int width, int height){
        creature.setImageName(filename);
        filename = filename+"0.png";
        creature.load(filename, 4,1,width,height);
    }
    public AnimatedSprite getCreature()     {return creature;}
    public void draw()                      {creature.draw();}
    public Point2D position()               { return creature.position(); }
    public synchronized void update(){
        updateAction();
        int gx = (int) creature.position().X();
        gx/=Utilities.GRIDSIZE;
        int gy = (int) creature.position().Y();
        gy/=Utilities.GRIDSIZE;
        creature.updateAnimation();
        creature.updatePosition();
        creature.transform();
        
        int fx = (int) creature.position().X();
        fx/=Utilities.GRIDSIZE;
        int fy = (int) creature.position().Y();
        fy/=Utilities.GRIDSIZE;
        if (!(fx==gx && fy==gy)){
            map.gpop(gx, gy);
            map.gpush(fx,fy,this);
        }
    }
    protected synchronized void addAction(Object lock,Point2D dest){
        this.lock.add(lock);
        numAction++;
        int x = (int)dest.X()/Utilities.GRIDSIZE * Utilities.GRIDSIZE + 10;
        int y = (int)dest.Y()/Utilities.GRIDSIZE * Utilities.GRIDSIZE + 10;
        destination.put(lock, new Point2D(x, y));

    }
    protected abstract void init();
    public void updateAction(){
        if (inAction){
//            System.out.println("panjang rute "+route.size());
            if (destination.get(lock.get(0)) == null){
                destination.remove(lock.get(0));
                lock.remove(0);
                inAction = false;
                return;
            }
            if (!lock.get(0).equals("stop") && !lock.get(0).equals("paused")){
                route = getRoute(destination.get(lock.get(0)).IntX(), destination.get(lock.get(0)).IntY(), type);
            }
            if (route.size() == 0 ){
                if ((Math.abs(creature.position().X()-creature.finalPosition().X())  <= 1) &&
                    (Math.abs(creature.position().Y()-creature.finalPosition().Y())  <= 1)){
                    inAction = false;
                    if (lock.size()>0){
                        synchronized (lock.get(0)) {
    //                            Point2D dest = destination.get(lock.get(0));
                            lock.get(0).notify();
    //                          animate(lock);
                            destination.remove(lock.get(0));
                            lock.remove(0);
                            numAction--;
                        }
                    }
                }
            } else if (route.size()>1 || lock.get(0).equals("exact") || lock.get(0).equals("stop") || lock.get(0).equals("paused")){
                Point2D dest;//;
                dest = route.get(0);
                int gx = dest.IntX()*Utilities.GRIDSIZE+Utilities.GRIDGALAT;
                int gy = dest.IntY()*Utilities.GRIDSIZE+Utilities.GRIDGALAT;
                if ((lock.get(0).equals("paused") || lock.get(0).equals("stop")) &&
                    !map.getTop(dest.IntX(),dest.IntY()).equals(this)&&
                    !(map.getTop(dest.IntX(),dest.IntY()) instanceof Road ||
                      map.getTop(dest.IntX(),dest.IntY()) instanceof Land)){

                    if (lock.get(0).equals("paused")){
                        creature.setFinalPosition(creature.position().IntX(), creature.position().IntY());
                    } else if (lock.get(0).equals("stop")){
                        route.removeAll(route);
                    }
                } else {
                    creature.setFinalPosition(gx, gy);
                    if ((Math.abs(creature.position().X()-creature.finalPosition().X())  <= 1) &&
                        (Math.abs(creature.position().Y()-creature.finalPosition().Y())  <= 1)) {
                        route.remove(0);
                    }
                }
            } else if (route.size()==1){
                if ((Math.abs(creature.position().X()-creature.finalPosition().X())  <= 1) &&
                    (Math.abs(creature.position().Y()-creature.finalPosition().Y())  <= 1)) {
                    System.out.print("size == 1");
                    int gx = route.get(0).IntX()*Utilities.GRIDSIZE+Utilities.GRIDGALAT;
                    int gy = route.get(0).IntY()*Utilities.GRIDSIZE+Utilities.GRIDGALAT;
                    creature.setArah(new Point2D(gx,gy));
                    inAction = false;
                    if (lock.size()>0){
                        synchronized (lock.get(0)) {
//                            Point2D dest = destination.get(lock.get(0));
                            lock.get(0).notify();
//                          animate(lock);
                            destination.remove(lock.get(0));
                            lock.remove(0);
                            numAction--;
                            
                        }
                    }
                }
            }
        } else{//if not in action
            if (!(this instanceof Player) || ((Player)this).getWork()==3){
                creature.setAnimated(false);
                if (lock.size()>0){
                    if (lock.get(0).equals("stop") || lock.get(0).equals("paused")) {
                        route = getRoute(destination.get(lock.get(0)).IntX(), destination.get(lock.get(0)).IntY(), 0);
                    }
                    creature.setAnimated(true);
                    inAction = true;
                }
            }
        }
        
    }

    //for debugging
    public static void performarr(int[][] arr,int r,int c )
    {
        for(int i=0;i<r;++i)
        {
            for(int j=0;j<c;++j)
            {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }
    
    //for debugging
    public static void performarrlist(ArrayList<Point2D> p)
    {
        for(int i=0;i<p.size();++i)
        {
            System.out.println(p.get(i).IntX() + "," + p.get(i).IntY());
        }
    }
    /**
     *Mati lu
     *
     * @param x absis tujuan PLAYER
     * @param y ordinat tujuan PLAYER
     * @param caller, code pemanggil, 0 untuk player, 1 untuk dwarf water,
     * 2 untuk dwarf harvest, 3 untuk dwarf slash
     * @return
     */
    
    public ArrayList<Point2D> getRoute(int x,int y,int caller){


        final int land=0;
        final int block=1;
        final int destiny=2;
        int i,j;
        x/=Utilities.GRIDSIZE;
        y/=Utilities.GRIDSIZE;
        ArrayList<Point2D> TempBRoute = new ArrayList<Point2D>();
        ArrayList<Point2D> TrueBRoute = new ArrayList<Point2D>();

        //TempBRoute.add(new Point2D(2, 6));

        //TempBRoute.add(creature.position());
        TempBRoute.add(new Point2D(creature.position().IntX()/Utilities.GRIDSIZE, creature.position().IntY()/Utilities.GRIDSIZE));

        //copy dulu arraynya
        if(map==null)
        {
            System.out.println("NULLLLLLLLLLLLLLLLLLLLL");
        }
        int[][] tmap = new int[map.getRow()][map.getColumn()];



        //inisialisasi awal tmap(tanpa tujuan)

        for(i=0;i<map.getRow();++i)
        {
            for(j=0;j<map.getColumn();++j)
            {
               //check Land
                if (map.getTop(i, j) instanceof Land || map.getTop(i, j) instanceof Road || map.getTop(i,j) instanceof Portal || map.getTop(i,j) instanceof Canceller)
                {
                    tmap[i][j]= 0;
                }
                else if (map.getTop(i,j) instanceof MovingObject)
                {
                    tmap[i][j]=1;
                }
                //check non-land/non-destiny
                else if (map.getTop(i,j) instanceof Pulmosis)
                {
                    tmap[i][j]= 1;
                }
                else
                {
                    tmap[i][j]=1;
                }
            }
        }
        
        /*
        //hanya untuk debugging
        for(i=0;i<8;++i)
        {
            for(j=0;j<8;++j)
            {
                tmap[i][j]=0;
            }
        }
        */
        //inisialisasi tujuan tmap bernilai 2
        //z==0, artinya objek yang memakai adalah player

        if (caller==0)
        {
            tmap[x][y]=2;
        }

        //z==1, artinya objek yang memakai adalah dwarf water
        else if(caller==1)
        {
//            System.out.println("CALLER 1 MAP SEBELUM DESTINY");
//            performarr(tmap,map.getRow(),map.getColumn());
            
            // Scanner sin = new Scanner(System.in);
            //int n = sin.nextInt();
        //inisialisasi nilai map untuk tanaman yang bisa disiram
            for(i=0;i<map.getRow();++i)
            {
                for(j=0;j<map.getColumn();++j)
                {
//                    if (map.getTop(i,j)!=null) {
////                      System.out.println(map.getTop(i, j).getClass().getName() + " : " + i + " : " + j);
//                    }
                    if (map.getTop(i, j) instanceof  Plant)
                    {
                        if (!((Plant)map.getTop(i, j)).isWatered())
                        {
                            tmap[i][j]=2;
                        }
                    }
                }
            }
//            System.out.println("SETELAH");
//            performarr(tmap,map.getRow(),map.getColumn());
        }

        //caller==2, artinya yang memakai adalah dwarf harvest
        else if (caller==2)
        {
//          System.out.println("CALLER 2 MAP SEBELUM DESTINY");
//          performarr(tmap,map.getRow(),map.getColumn());
            
        //inisialisasi nilai map
          
          for(i=0;i<map.getRow();++i)
          {
              for(j=0;j<map.getColumn();++j)
              {
//                  if (map.getTop(i,j)!=null) {
//                      System.out.println(map.getTop(i, j).getClass().getName() + " : " + i + " : " + j);
//                    }
//                  if (map.getTop(i,j)!=null) {
//                      System.out.println(map.getTop(i, j).getClass().getName() + " : " + i + " : " + j);
//                  }
                  if (map.getTop(i, j) instanceof Plant)
                  {
//                      System.out.println("KETEMU LHO tanamannya!!!" + " : " + i + " : " + j);
                      if ( (((Plant)map.getTop(i, j)).getFase()==Plant.DEWASANOSIRAM) || (((Plant)map.getTop(i, j)).getFase()==Plant.DEWASASIRAM) )
                      {
//                          System.out.println("KETEMU LHO dewasanya!!!");
                          tmap[i][j]=2;
                      }
                  }
//                  System.out.println("SETELAH");
//                  performarr(tmap,map.getRow(),map.getColumn());
              }
          }
        }

        //caller=3, artinya yang memakai adalah dwarf slash
        else if (caller==3)
        {
//          System.out.println("CALLER 3 MAP SEBELUM DESTINY");
//          performarr(tmap,map.getRow(),map.getColumn());
        //inisialisasi nilai map
          for(i=0;i<map.getRow();++i)
          {
              for(j=0;j<map.getColumn();++j)
              {
                  if (map.getTop(i, j) instanceof Plant)
                  {
//                      if (map.getTop(i,j)!=null)
//                      {
//                          System.out.println(map.getTop(i,j).getClass().getName() + " : " + i + " : " + j);
//                      }
                      if ((((Plant)map.getTop(i, j)).getFase()==Plant.BIBITMATI) || (((Plant)map.getTop(i, j)).getFase()==Plant.TANAMANMATI))
                      {
                        tmap[i][j]=2;
                      }
                  }
              }
          }
//          System.out.println("SETELAH");
//          performarr(tmap,map.getRow(),map.getColumn());
        }
        else  if (caller==4)
        {
            tmap[1][4]=2;
//            System.out.println("INI MAPNYA : " + x + " -- " + y);
//            performarr(tmap, map.getRow(), map.getColumn());
        }else if (caller==5)
        {
            tmap[1][5]=2;
        }else if (caller==6)
        {
            tmap[1][6]=2;
        }

        /*hanya untuk debugging
        //inisialisasi nilai untuk posisi awal bernilai 3
        tmap[2][6]=3;

        tmap[3][3]=1;
        tmap[3][4]=1;
        tmap[3][5]=1;
        tmap[4][4]=1;
        tmap[4][5]=1;
        tmap[5][5]=1;


        //hanya untuk debugging
        performarr(tmap, 8, 8);
        */
        //tmap[0][0]=0;
//        if (caller!=0)
//        {
//            System.out.println("INI DIANYA, type pemanggil : " + caller);
//            performarr(tmap, map.getRow(),map.getColumn());
//        }
        Boolean found=false;

        //System.out.println(TempBRoute.get(0).IntX());
//        System.out.println("Nilai found pertama : " + found);
        while (!TempBRoute.isEmpty() && (!found))
        {
            //System.out.println("TESTESTES\n");
            //System.out.println("Nilai found  : " + found);
            Point2D cPoint = new Point2D(TempBRoute.get(0).IntX(), TempBRoute.get(0).IntY());
            //System.out.println(TempBRoute.get(0).IntX()+","+TempBRoute.get(0).IntY());
            //check grid di atas
            if ((!found) && (!cPoint.isXEqual(0)) && (tmap[cPoint.IntX()-1][cPoint.IntY()]  != 1) )
            {
                TempBRoute.add(cPoint.getUp());
                TrueBRoute.add(cPoint.getUp());
                tmap[cPoint.IntX()][cPoint.IntY()]=1;
                if (tmap[cPoint.IntX()-1][cPoint.IntY()]==2)
                {
                    found=true;
                    break;
                }
                tmap[cPoint.IntX()-1][cPoint.IntY()]=1;

            }
            //check grid di kanan
            if  ((!found) && (!cPoint.isYEqual(map.getColumn()-1)) && (tmap[cPoint.IntX()][cPoint.IntY()+1] != 1))
            {
                TempBRoute.add(cPoint.getRight());
                TrueBRoute.add(cPoint.getRight());
                tmap[cPoint.IntX()][cPoint.IntY()]=1;
                if (tmap[cPoint.IntX()][cPoint.IntY()+1]==2)
                {
                    found=true;
                    break;
                }
                tmap[cPoint.IntX()][cPoint.IntY()+1]=1;
            }
            //check grid di bawah
            if ((!found) && (!cPoint.isXEqual(map.getRow()-1)) && (tmap[cPoint.IntX()+1][cPoint.IntY()] !=1 ))
            {
                TempBRoute.add(cPoint.getDown());
                TrueBRoute.add(cPoint.getDown());
                tmap[cPoint.IntX()][cPoint.IntY()]=1;
                if (tmap[cPoint.IntX()+1][cPoint.IntY()]==2)
                {
                    found=true;
                    break;
                }
                tmap[cPoint.IntX()+1][cPoint.IntY()]=1;
            }
            //check grid di kiri
            if  ((!found) && (!cPoint.isYEqual(0)) && (tmap[cPoint.IntX()][cPoint.IntY()-1] != 1) )
            {
                TempBRoute.add(cPoint.getLeft());
                TrueBRoute.add(cPoint.getLeft());
                tmap[cPoint.IntX()][cPoint.IntY()]=1;
                if (tmap[cPoint.IntX()][cPoint.IntY()-1]==2)
                {
                    found=true;
                }
                tmap[cPoint.IntX()][cPoint.IntY()-1]=1;
            }

            TempBRoute.remove(0);
        }

        //for debugging
        /*for(i=0;i<TrueBRoute.size();++i)
        {
            System.out.println(TrueBRoute.get(i).IntX() + "," + TrueBRoute.get(i).IntY());
        }*/

        //System.out.println("TEWAS");
        ArrayList<Point2D> retroute = new ArrayList<Point2D>();
        Point2D cPoint;

        if ((TrueBRoute.size()>=1) && (found))
        {

            i=TrueBRoute.size()-1;

            j=0;
            retroute.add(j,TrueBRoute.get(i));
            ++j;
            //System.out.println("hasilnya " +i );

            cPoint = new Point2D(TrueBRoute.get(i));

            TrueBRoute.remove(i);
            --i;

            while (i>=0)
            {
                //System.out.println(1);
                if (i<TrueBRoute.size())
                if (TrueBRoute.get(i).same(cPoint.getUp()))
                {
                    //System.out.println("up");
                    cPoint.setUp();
                    //System.out.println(cPoint.IntX()+ "," + cPoint.IntY());
                    retroute.add(j,new Point2D(cPoint));
                    //System.out.println(retroute.get(j).IntX()+ "," + retroute.get(j).IntY());
                    ++j;
                    TrueBRoute.remove(i);

                }
                if (i<TrueBRoute.size())
                if (TrueBRoute.get(i).same(cPoint.getRight()))
                {
                    //System.out.println("right");
                    cPoint.setRight();
                    //System.out.println(cPoint.IntX()+ "," + cPoint.IntY());
                    retroute.add(j,new Point2D(cPoint));
                    //System.out.println(retroute.get(j).IntX()+ "," + retroute.get(j).IntY());
                    ++j;
                    TrueBRoute.remove(i);
                }
                if (i<TrueBRoute.size())
                if (TrueBRoute.get(i).same(cPoint.getDown()))
                {
                    //System.out.println("down");
                    cPoint.setDown();
                    //System.out.println(cPoint.IntX()+ "," + cPoint.IntY());
                    retroute.add(j,new Point2D(cPoint));
                    //System.out.println(retroute.get(j).IntX()+ "," + retroute.get(j).IntY());
                    ++j;
                    TrueBRoute.remove(i);

                }
                if (i<TrueBRoute.size())
                if(TrueBRoute.get(i).same(cPoint.getLeft()))
                {
                    cPoint.setLeft();
                    //System.out.println(cPoint.IntX()+ "," + cPoint.IntY());
                    retroute.add(j,new Point2D(cPoint));
                    //System.out.println(retroute.get(j).IntX()+ "," + retroute.get(j).IntY());
                    ++j;
                    TrueBRoute.remove(i);

                }
                --i;
            }

        }
        ArrayList<Point2D> rettrue = new ArrayList<Point2D>();
        for(i=retroute.size()-1;i>=0;--i)
        {
            rettrue.add(retroute.get(i));
        }


//        if (caller!=0)
//        {
////          if (!rettrue.isEmpty())
////              System.out.println("TIDAK KOSONG");
////          else
////              System.out.println("KOSONG");
////          System.out.println("rute");
//          for(i=0;i<rettrue.size()-1;++i)
//          {
//            System.out.println(rettrue.get(i).IntX() + "," + rettrue.get(i).IntY() );
//          }
//        }
//
//        System.out.println("Ini rutenya : ");
//        performarrlist(rettrue);
        return rettrue;
    }
    
    /*
    public static void main(String[] args)
    {
        ArrayList<Point2D> al = getRoute(5, 4,1);
        
    }*/
    
}