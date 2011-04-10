/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plantmon.entity.item;

import javax.swing.JPanel;
import plantmon.entity.Item;
import plantmon.game.ImageEntity;

/**
 *
 * @author Nugraha Siburian
 */
public class ArmorItem extends Item {
    private int atkStatus;
    private int defStatus;
    private int intStatus;
    private int position;

    final public static int Head = 0;
    final public static int Body = 1;
    final public static int Shield = 2;
    final public static int Foot = 3;
    final public static int Hand = 4;
    final public static int Pant = 5;

    public ArmorItem(int IDitem,JPanel panel) {
        gambar = new ImageEntity(panel);
        IDItem = IDitem;
        if (IDitem == Robe) {
            costBuy = 100;
            name = "Robe";
            deskripsi = "DEF +1";
            defStatus = 1;
            atkStatus = 0;
            intStatus = 0;
            position = Body;
            //gambar.load(null);
        } else if (IDitem == Tunic) {
            costBuy = 200;
            name = "Tunic";
            deskripsi = "DEF +2";
            defStatus = 2;
            atkStatus = 0;
            intStatus = 0;
            position = Body;
            //gambar.load(null);
        } else if (IDitem == LeatherCoat) {
            costBuy = 700;
            name = "Leather Coat";
            deskripsi = "DEF +4";
            defStatus = 4;
            atkStatus = 0;
            intStatus = 0;
            position = Body;
            //gambar.load(null);
        } else if (IDitem == BrassArmor) {
            costBuy = 1000;
            name = "Brass Armor";
            deskripsi = "DEF +5";
            defStatus = 5;
            atkStatus = 0;
            intStatus = 0;
            position = Body;
            //gambar.load(null);
        } else if (IDitem == Bandanna) {
            costBuy = 50;
            name = "Bandanna";
            deskripsi = "DEF +1";
            defStatus = 1;
            atkStatus = 0;
            intStatus = 0;
            position = Head;
            //gambar.load(null);
        } else if (IDitem == HeadBand) {
            costBuy = 300;
            name = "HeadBand";
            deskripsi = "DEF +2";
            defStatus = 2;
            atkStatus = 0;
            intStatus = 0;
            position = Head;
            //gambar.load(null);
        } else if (IDitem == PointedHat) {
            costBuy = 1200;
            name = "Pointed Hat";
            deskripsi = "DEF +5";
            defStatus = 5;
            atkStatus = 0;
            intStatus = 0;
            position = Head;
            //gambar.load(null);
        } else if (IDitem == WoodenShield) {
            costBuy = 300;
            name = "Wooden Shield";
            deskripsi = "DEF +2";
            defStatus = 2;
            atkStatus = 0;
            intStatus = 0;
            position = Shield;
            //gambar.load(null);
        } else if (IDitem == WoodenShoes) {
            costBuy = 100;
            name = "Wooden Shoes";
            deskripsi = "DEF +1";
            defStatus = 1;
            atkStatus = 0;
            intStatus = 0;
            position = Foot;
            //gambar.load(null);
        } else if (IDitem == Leggings) {
            costBuy = 200;
            name = "Leggings";
            deskripsi = "DEF +2";
            defStatus = 2;
            atkStatus = 0;
            intStatus = 0;
            position = Pant;
            //gambar.load(null);
        } else if (IDitem == Gloves) {
            costBuy = 300;
            name = "Gloves";
            deskripsi = "DEF +2";
            defStatus = 2;
            atkStatus = 0;
            intStatus = 0;
            position = Hand;
            //gambar.load(null);
        } else if (IDitem == Cape) {
            costBuy = 400;
            name = "Cape";
            deskripsi = "DEF +2";
            defStatus = 2;
            atkStatus = 0;
            intStatus = 0;
            position = Head;
            //gambar.load(null);
        } else if (IDitem == Circlet) {
            costBuy = 600;
            name = "Circlet";
            deskripsi = "DEF +3";
            defStatus = 3;
            atkStatus = 0;
            intStatus = 0;
            position = Head;
            //gambar.load(null);
        }
        costSell = costBuy / 2;
    }

    public int getAtkStatus() {
        return atkStatus;
    }

    public void setAtkStatus(int atkStatus) {
        this.atkStatus = atkStatus;
    }

    public int getDefStatus() {
        return defStatus;
    }

    public void setDefStatus(int defStatus) {
        this.defStatus = defStatus;
    }

    public int getIntStatus() {
        return intStatus;
    }

    public void setIntStatus(int intStatus) {
        this.intStatus = intStatus;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
