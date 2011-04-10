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

    final public static int Bandanna = 0;
    final public static int HeadBand = 1;
    final public static int PointedHat = 2;
    final public static int Robe = 3;
    final public static int Tunic = 4;
    final public static int LeatherCoat = 5;
    final public static int BrassArmor = 6;
    final public static int WoodenShield = 7;
    final public static int WoodenShoes = 8;
    final public static int Leggings = 9;
    final public static int Gloves = 10;
    final public static int Cape = 11;
    final public static int Circlet = 12;

    public ArmorItem(int IDitem,JPanel panel) {
        gambar = new ImageEntity(panel);
        IDItem = IDitem;
        if (IDitem == Robe) {
            costBuy = 100;
            name = "Robe";
            defStatus = 1;
            atkStatus = 0;
            intStatus = 0;
            position = Body;
            gambar.load(null);
        } else if (IDitem == Tunic) {
            costBuy = 200;
            name = "Tunic";
            defStatus = 2;
            atkStatus = 0;
            intStatus = 0;
            position = Body;
            gambar.load(null);
        } else if (IDitem == LeatherCoat) {
            costBuy = 700;
            name = "Leather Coat";
            defStatus = 4;
            atkStatus = 0;
            intStatus = 0;
            position = Body;
            gambar.load(null);
        } else if (IDitem == BrassArmor) {
            costBuy = 1000;
            name = "Brass Armor";
            defStatus = 5;
            atkStatus = 0;
            intStatus = 0;
            position = Body;
            gambar.load(null);
        } else if (IDitem == Bandanna) {
            costBuy = 50;
            name = "Bandanna";
            defStatus = 1;
            atkStatus = 0;
            intStatus = 0;
            position = Head;
            gambar.load(null);
        } else if (IDitem == HeadBand) {
            costBuy = 300;
            name = "HeadBand";
            defStatus = 2;
            atkStatus = 0;
            intStatus = 0;
            position = Head;
            gambar.load(null);
        } else if (IDitem == PointedHat) {
            costBuy = 1200;
            name = "Pointed Hat";
            defStatus = 5;
            atkStatus = 0;
            intStatus = 0;
            position = Head;
            gambar.load(null);
        } else if (IDitem == WoodenShield) {
            costBuy = 300;
            name = "Wooden Shield";
            defStatus = 2;
            atkStatus = 0;
            intStatus = 0;
            position = Shield;
            gambar.load(null);
        } else if (IDitem == WoodenShoes) {
            costBuy = 100;
            name = "Wooden Shoes";
            defStatus = 1;
            atkStatus = 0;
            intStatus = 0;
            position = Foot;
            gambar.load(null);
        } else if (IDitem == Leggings) {
            costBuy = 200;
            name = "Leggings";
            defStatus = 2;
            atkStatus = 0;
            intStatus = 0;
            position = Pant;
            gambar.load(null);
        } else if (IDitem == Gloves) {
            costBuy = 300;
            name = "Gloves";
            defStatus = 2;
            atkStatus = 0;
            intStatus = 0;
            position = Hand;
            gambar.load(null);
        } else if (IDitem == Cape) {
            costBuy = 400;
            name = "Cape";
            defStatus = 2;
            atkStatus = 0;
            intStatus = 0;
            position = Head;
            gambar.load(null);
        } else if (IDitem == Circlet) {
            costBuy = 600;
            name = "Circlet";
            defStatus = 3;
            atkStatus = 0;
            intStatus = 0;
            position = Head;
            gambar.load(null);
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
