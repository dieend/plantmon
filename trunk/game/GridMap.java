/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plantmon.game;

import java.io.Serializable;
import java.util.ArrayList;
import plantmon.entity.unmoveable.Land;
import plantmon.states.ParentState;
import plantmon.system.Drawable;
import plantmon.system.Utilities;

/**
 *
 * @author asus
 */
public class GridMap implements Serializable{
    /**
     * Peta yang menggambarkan keadaan area. Merupakan array of stack 2 dimensi
     */
    transient ArrayList<Object>[][] map;
    /**
     * jumlah kolom
     */
    int column;
    /**
     * jumlah baris
     */
    int row;
    /**
     * Konstruktor default, akan menginisiasi area dengan ukuran 8x8
     */
    public int getRow() {return row;}
    public int getColumn() {return column;}
    public GridMap(){
        map = new ArrayList[100][100];
        column = 8;
        row = 8;
        init();
    }
    /**
     * Konstruktor dengan ukuran 8x8
     * @param x
     * @param y
     */
    public GridMap(int x, int y) {
        map = new ArrayList[x][y];
        row = x;
        column = y;
        init();
    }

    private void init() {
        for (int i=0; i<row; i++){
            for (int j=0;j<column;j++){
                map[i][j] = new ArrayList<Object>();
            }
        }
    }
    /**
     * Mengembalikan object yang berada di stack paling atas dari stack di
     * posisi (x,y). x,y merupakan posisi grid aktual, bukan posisi pixel
     * @param x
     * @param y
     * @return
     */
    public Object getTop(int x,int y){
//        x /= 80
//        y /= 80;
        synchronized(map[x][y]){
            if (map[x][y].size()!=0) {
                Object result =  map[x][y].get(map[x][y].size()-1);
                return result;
            } else return null;
        }
    }
    public Land getLand(int x, int y){
        for (int i=0; i<map[x][y].size(); i++){
            if (map[x][y].get(i) instanceof Land){
                return (Land)map[x][y].get(i);
            }
        }
        return null;
    }
    /**
     * push sebuah Object o ke dalam stack yang ada di posisi x,y. x,y merupakan
     * posisi pixel dari Object o.
     * @param x
     * @param y
     * @param o
     */
    public void push(int x, int y, Object o){
        x /= Utilities.GRIDSIZE;
        y /= Utilities.GRIDSIZE;
        synchronized(map[x][y]){
            map[x][y].add(o);
        }
    }
    /**
     * push sebuah Object o ke dalam stack yang ada di posisi x,y. x,y merupakan
     * posisi pixel dari Object o.
     * @param x
     * @param y
     * @param o
     */
    public void push(double x, double y, Object o){
        x /= Utilities.GRIDSIZE;
        y /= Utilities.GRIDSIZE;
        int ix = (int) x; int iy = (int) y;
        synchronized(map[ix][iy]){
            map[ix][iy].add(o);
        }
    }
    /**
     * push sebuah Object o ke dalam stack yang ada di posisi gx,gy. 
     * gx,gy merupakan posisi grid aktual, bukan posisi pixel
     * @param x
     * @param y
     * @param o
     */
    public void gpush(int x, int y, Object o){
        synchronized(map[x][y]){
            map[x][y].add(o);
        }
    }
    /**
     * pop sebuah Object o dari stack yang ada di posisi x,y. x,y merupakan
     * posisi pixel dari Object o.
     * @param x
     * @param y
     */
    public void pop(int x, int y){
        x /= Utilities.GRIDSIZE;
        y /= Utilities.GRIDSIZE;
        synchronized(map[x][y]){
            if (map[x][y].size()!=0) {
                map[x][y].remove(map[x][y].size()-1);
            }
        }
    }
    /**
     * pop sebuah Object o dari stack yang ada di posisi x,y.
     * x,y merupakan posisi grid aktual, bukan posisi pixel
     * @param x
     * @param y
     */
    public void gpop(int x, int y){
        synchronized(map[x][y]){
            if (map[x][y].size()!=0) {
                map[x][y].remove(map[x][y].size()-1);
            }
        }
    }
    /**
     * pop sebuah Object o dari stack yang ada di posisi x,y. x,y merupakan
     * posisi pixel dari Object o.
     * @param x
     * @param y
     */
    public void pop(double  x, double y){
        x /= Utilities.GRIDSIZE;
        y /= Utilities.GRIDSIZE;
        int ix=(int) x, iy = (int) y;
        synchronized(map[ix][iy]){
            if (map[ix][iy].size()!=0) {
                map[ix][iy].remove(map[ix][iy].size()-1);
            }
        }
    }
    /**
     * menggambar seluruh objek yang ada didalam map, mulai digambar dari tumpukan
     * terbawah, sehingga akan tergambar seluruh objek yang ada.
     */
    public void draw(int x,int y){
        x*=-1; y*=-1;
        x/=Utilities.GRIDSIZE; y/=Utilities.GRIDSIZE;
        int highest=1; // k is highest stack
//        System.out.println(x+" "+(2+x+FarmState.SCREENHEIGHT/80)+" "+y+" "+(2+y+FarmState.SCREENWIDTH/80));
        for (int k=0; k<highest; k++){
            for (int i=0; i<getRow();i++){
                for (int j=0; j<getColumn();j++){
                    if (k<map[i][j].size()){
                        if (highest < map[i][j].size()) highest = map[i][j].size();

                        if (map[i][j].get(k) instanceof Drawable){
                            ((Drawable)map[i][j].get(k)).update();
                            if((x<=i && i<48+x+ParentState.SCREENHEIGHT/Utilities.GRIDSIZE)&&
                               (y<=j && j<48+y+ParentState.SCREENWIDTH/Utilities.GRIDSIZE)){
                               if (k<map[i][j].size()) ((Drawable)map[i][j].get(k)).draw();
                            }
                        }
                }
            }

//            for (int i=x; i<2+x+FarmState.SCREENHEIGHT/80; i++) if (i<getRow()){
//                for (int j=y; j<2+y+FarmState.SCREENWIDTH/80; j++) if (j<getColumn()){
//                    synchronized(map[i][j]){
//                        if (k<map[i][j].size()){
//                            if (highest < map[i][j].size()) highest = map[i][j].size();
//                            if (map[i][j].get(k) instanceof Drawable){
//                                ((Drawable)map[i][j].get(k)).update();
//                                if (k<map[i][j].size())
//                                    ((Drawable)map[i][j].get(k)).draw();
//                            }
//                        }
//                    }
//                }
            }
        }
    }
}
