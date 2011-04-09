/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plantmon.entity.item;

// maaf yang item masih belum -.-
public class Item{
    String name;
    int costBuy;
    int costSell;
    int IDitem;

    public String getName(){
        return name;
    }
    public int getCostBuy(){
        return costBuy;
    }
    public int getCostSell(){
        return costSell;
    }
    public int getIDitem(){
        return IDitem;
    }
    public void setName(String e){
        name = e;
    }
    public void setCostBuy(int e){
        costBuy=e;
    }
    public void setCostSell(int e){
        costSell=e;
    }
    public void setIDitem(int e){
        IDitem=e;
    }
}