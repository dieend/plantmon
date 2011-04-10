package plantmon.entity;

<<<<<<< .mine
package plantmon.entity;
=======
import plantmon.game.ImageEntity;
>>>>>>> .r20

public class Item{
    protected String name;
    protected int costBuy;
    protected int costSell;
    protected int IDItem;
    protected ImageEntity gambar;

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
        return IDItem;
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
        IDItem=e;
    }
}