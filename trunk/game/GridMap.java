/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plantmon.game;

import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class GridMap {
    ArrayList<Object>[][] map;
    int column;
    int row;
    public GridMap(){
        map = new ArrayList[100][100];
        column = 8;
        row = 8;
        init();
    }
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
    public synchronized Object getTop(int x,int y){
//        x /= 80
//        y /= 80;
        if (map[x][y].size()!=0) {
            Object result =  map[x][y].get(map[x][y].size()-1);
            return result;
        } else return null;
    }
    public synchronized void push(int x, int y, Object o){
        x /= 80;
        y /= 80;
        map[x][y].add(o);
    }
    public synchronized void push(double x, double y, Object o){
        x /= 80;
        y /= 80;
        int ix = (int) x; int iy = (int) y;
        map[ix][iy].add(o);
    }
    public synchronized void gpush(int x, int y, Object o){
        map[x][y].add(o);
    }
    public synchronized void pop(int x, int y){
        x /= 80;
        y /= 80;
        if (map[x][y].size()!=0) {
            map[x][y].remove(map[x][y].size()-1);
        }
    }
    public synchronized void gpop(int x, int y){
        if (map[x][y].size()!=0) {
            map[x][y].remove(map[x][y].size()-1);
        }
    }

    public synchronized void pop(double  x, double y){
        x /= 80;
        y /= 80;
        int ix=(int) x, iy = (int) y;
        if (map[ix][iy].size()!=0) {
            map[ix][iy].remove(map[ix][iy].size()-1);
        }
    }
}
