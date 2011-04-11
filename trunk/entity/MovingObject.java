package plantmon.entity;

import java.awt.Graphics2D;
import plantmon.game.Point2D;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import javax.swing.JPanel;
import plantmon.entity.unmoveable.Land;
import plantmon.entity.unmoveable.Plant;
import plantmon.game.AnimatedSprite;
import plantmon.game.GridMap;
import plantmon.system.Drawable;

public abstract class MovingObject implements Drawable{
    final public static int PLAYER = 0;
    protected ArrayList<Object> lock = new ArrayList<Object>();
    protected IdentityHashMap<Object,Point2D> destination = new IdentityHashMap<Object,Point2D>();
    protected GridMap map;
    protected AnimatedSprite creature;
    protected ArrayList<Point2D> route = new ArrayList<Point2D>();
    protected boolean inAction;
    protected int numAction;
    public MovingObject(GridMap map, JPanel panel, Graphics2D g2d){
        this.map = map;
        creature = new AnimatedSprite(panel, g2d);
        numAction = 0;
    //    init();
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
    
    
    public void load(String filename, int columns, int rows,int width, int height){
        creature.setImageName(filename);
        filename = filename+"0.png";
        creature.load(filename, 4,1,32,32);
    }
    public AnimatedSprite getCreature()     {return creature;}
    public void draw()                      {creature.draw();}
    public Point2D position()               { return creature.position(); }
    public synchronized void update(){
        updateAction();
        int gx = (int) creature.position().X();
        gx/=80;
        int gy = (int) creature.position().Y();
        gy/=80;
        creature.updateAnimation();
        creature.updatePosition();
        creature.transform();
        
        int fx = (int) creature.position().X();
        fx/=80;
        int fy = (int) creature.position().Y();
        fy/=80;
        if (!(fx==gx && fy==gy)){
            map.gpop(gx, gy);
            map.gpush(fx,fy,this);
        }
    }
    protected synchronized void addAction(Object lock,Point2D dest){
        this.lock.add(lock);
        numAction++;
        int x = (int)dest.X()/80 * 80 + 10;
        int y = (int)dest.Y()/80 * 80 + 10;
        destination.put(lock, new Point2D(x, y));

    }
    protected abstract void init();
    public void updateAction(){
        if (inAction){
            route = getRoute(destination.get(lock.get(0)).IntX(), destination.get(lock.get(0)).IntY(), PLAYER);
            if (route.size()>1){
                Point2D dest;//;
    //            if (!(map.getTop(dest.IntX(),dest.IntY()) instanceof Land) &&
    //                !(map.getTop(dest.IntX(),dest.IntY()).equals(this))){
                    dest = route.get(0);
    //            }
                int gx = dest.IntX()*80+10;
                int gy = dest.IntY()*80+10;
                creature.setFinalPosition(gx, gy);
                if ((Math.abs(creature.position().X()-creature.finalPosition().X())  <= 1) &&
                    (Math.abs(creature.position().Y()-creature.finalPosition().Y())  <= 1)) {
                    route.remove(0);
                }
            }
            if (route.size()==1){
                if ((Math.abs(creature.position().X()-creature.finalPosition().X())  <= 1) &&
                    (Math.abs(creature.position().Y()-creature.finalPosition().Y())  <= 1)) {

                    int gx = route.get(0).IntX()*80+10;
                    int gy = route.get(0).IntY()*80+10;
                    System.out.println(gx+" "+gy);
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
            if (lock.size()>0){
                lock.get(0);
                int gx = destination.get(lock.get(0)).IntX();
                int gy = destination.get(lock.get(0)).IntY();
                gx = (gx/80)*80+10;
                gy = (gy/80)*80+10;
                inAction = true;
            }
        }
        
    }

    //for debugging
    public static void performarr(int[][] arr,int c,int r)
    {
        for(int i=0;i<r;++i)
        {
            for(int j=0;j<r;++j)
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

    public  ArrayList<Point2D> getRoute(int x,int y,int caller){
        final int land=0;
        final int block=1;
        final int destiny=2;
        int i,j;
        x/=80;
        y/=80;
        ArrayList<Point2D> TempBRoute = new ArrayList<Point2D>();
        ArrayList<Point2D> TrueBRoute = new ArrayList<Point2D>();

        //TempBRoute.add(new Point2D(2, 6));

        //TempBRoute.add(creature.position());
        TempBRoute.add(new Point2D(creature.position().IntX()/80, creature.position().IntY()/80));

        //copy dulu arraynya
        int[][] tmap = new int[map.getRow()][map.getColumn()];



        //inisialisasi awal tmap(tanpa tujuan)

        for(i=0;i<map.getRow();++i)
        {
            for(j=0;j<map.getColumn();++j)
            {
               //check Land
                if (map.getTop(i, j) instanceof Land)
                {
                    tmap[i][j]= 0;
                }
                //check non-land/non-destiny
                else
                {
                    tmap[i][j]= 1;
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
        //inisialisasi nilai map untuk tanaman yang bisa disiram
            for(i=0;i<map.getRow();++i)
            {
                for(j=0;j<map.getColumn();++j)
                {
                    if (map.getTop(i, j) instanceof  Plant)
                    {
                        if (((Plant)map.getTop(i, j)).getFase()%2==0)
                        {
                            tmap[i][j]=2;
                        }
                    }
                }
            }
        }

        //caller==2, artinya yang memakai adalah dwarf harvest
        else if (caller==2)
        {
        //inisialisasi nilai map
          for(i=0;i<map.getRow();++i)
          {
              for(j=0;j<map.getColumn();++j)
              {
                  if (map.getTop(i, j) instanceof Plant)
                  {
                      if ( (((Plant)map.getTop(i, j)).getFase()==Plant.DEWASANOSIRAM) || (((Plant)map.getTop(i, j)).getFase()==Plant.DEWASASIRAM) )
                      {
                          tmap[i][j]=2;
                      }
                  }
              }
          }
        }

        //caller=3, artinya yang memakai adalah dwarf slash
        else if (caller==3)
        {
        //inisialisasi nilai map
          for(i=0;i<map.getRow();++i)
          {
              for(j=0;j<map.getColumn();++j)
              {
                  if (map.getTop(i, j) instanceof Plant)
                  {
                      if ((((Plant)map.getTop(i, j)).getFase()==Plant.BIBITMATI) || (((Plant)map.getTop(i, j)).getFase()==Plant.TANAMANMATI))
                      {
                        tmap[i][j]=2;
                      }
                  }
              }
          }
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
        Boolean found=false;

        //System.out.println(TempBRoute.get(0).IntX());

        while (!TempBRoute.isEmpty() && (!found))
        {

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

        if (TrueBRoute.size()>=1)
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
        /*System.out.println("TEWAS");
        for(i=0;i<rettrue.size()-1;++i)
        {
            System.out.println(rettrue.get(i).IntX() + "," + rettrue.get(i).IntY() );
        }*/
        return rettrue;
    }
    /*
    public static void main(String[] args)
    {
        ArrayList<Point2D> al = getRoute(5, 4,0);
        
    }*/

}