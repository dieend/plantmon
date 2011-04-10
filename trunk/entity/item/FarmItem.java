package plantmon.entity.item;

<<<<<<< .mine
import plantmon.entity.Item;

=======
import javax.swing.JPanel;
import plantmon.entity.Item;
import plantmon.game.ImageEntity;

>>>>>>> .r20
public class FarmItem extends Item {
    int efek;//efek dari buah yang dimakan player
    int season;

    final public static int Kentang = 0;
    final public static int Lobak = 1;
    final public static int Timun = 2;
    final public static int Kubis = 3;
    final public static int Jagung = 4;
    final public static int Tomat = 5;
    final public static int Bawang = 6;
    final public static int Nanas = 7;
    final public static int Wortel = 8;
    final public static int Terong = 9;
    final public static int Ubi = 10;
    final public static int Paprika = 11;

    final public static int Spring = 0;
    final public static int Summer = 1;
    final public static int Fall = 2;

    public int getEfek(){
        return efek;
    }
    public void setEfek(int e){
        efek=e;
    }
  
    public FarmItem(int IDitem, JPanel panel) {
        gambar = new ImageEntity(panel);
        IDItem = IDitem;
        if (IDitem == Kentang) {
            name = "Kentang";
            costBuy = 150;
            efek = 1;
            gambar.load(null);
        } else if (IDitem == Lobak) {
            name = "Lobak";
            costBuy = 120;
            efek = 2;
            gambar.load(null);
        } else if (IDitem == Timun) {
            name = "Timun";
            costBuy = 200;
            efek = 1;
            gambar.load(null);
        } else if (IDitem == Kubis) {
            name = "Kubis";
            costBuy = 500;
            efek = 4;
            gambar.load(null);
        } else if (IDitem == Nanas) {
            name = "Nanas";
            costBuy = 1000;
            efek = 4;
            gambar.load(null);
        } else if (IDitem == Jagung) {
            name = "Jagung";
            costBuy = 300;
            efek = 1;
            gambar.load(null);
        } else if (IDitem == Tomat) {
            name = "Tomat";
            costBuy = 200;
            efek = 1;
            gambar.load(null);
        } else if (IDitem == Bawang) {
            name = "Bawang";
            costBuy = 150;
            efek = 2;
            gambar.load(null);
        } else if (IDitem == Wortel) {
            name = "Wortel";
            costBuy = 300;
            efek = 1;
            gambar.load(null);
        } else if (IDitem == Terong) {
            name = "Terong";
            costBuy = 120;
            efek = 4;
            gambar.load(null);
        } else if (IDitem == Ubi) {
            name = "Ubi";
            costBuy = 300;
            efek = 1;
            gambar.load(null);
        } else if (IDitem == Paprika) {
            name = "Paprika";
            costBuy = 150;
            efek = 4;
            gambar.load(null);
        }
        costSell = costBuy / 2;
    }
}
