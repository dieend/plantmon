package plantmon.entity.item;

import javax.swing.JPanel;
import plantmon.entity.Item;
import plantmon.game.ImageEntity;

public class WarItem extends Item{
    int HPStatus;
	
    public WarItem(int IDitem, JPanel panel) {
        gambar = new ImageEntity(panel);
        IDItem = IDitem;
        if (IDitem == Water) {
            name = "Water";
            costBuy = 100;
            HPStatus = 100;
            gambar.load(null);
        } else if (IDitem == HolyWater) {
            name = "Holy Water";
            costBuy = 500;
            HPStatus = 500;
            gambar.load(null);
        } else if (IDitem == Antitoxin) {
            name = "Antitoxin";
            costBuy = 200;
            HPStatus = 0;
            gambar.load(null);
        } else if (IDitem == EyeDrop) {
            name = "Eye Drop";
            costBuy = 250;
            HPStatus = 0;
            gambar.load(null);
        } else if (IDitem == HotWater) {
            name = "Hot Water";
            costBuy = 250;
            HPStatus = 0;
            gambar.load(null);
        }
        costSell = costSell / 2;
    }
}
