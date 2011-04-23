package plantmon.entity.unmoveable;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import plantmon.entity.Inventory;
import plantmon.entity.Item;
import plantmon.entity.Unmoveable;
import plantmon.entity.item.ArmorItem;
import plantmon.entity.item.FarmItem;
import plantmon.entity.item.FoodItem;
import plantmon.entity.item.WarItem;
import plantmon.entity.movingObject.Player;
import plantmon.game.GridMap;
import plantmon.game.Point2D;
import plantmon.states.Game;
import plantmon.system.Actionable;
import plantmon.system.Selectable;
import plantmon.system.Utilities;

public class SellBox extends Unmoveable implements Actionable{

    int status;
    public SellBox(GridMap map, JPanel panel, Graphics2D g2d, int gx, int gy){
        super(map, panel, g2d);
        init();
        entity.setPosition(new Point2D(gx*Utilities.GRIDSIZE,gy*Utilities.GRIDSIZE));
        entity.setFinalPosition(gx*Utilities.GRIDSIZE,gy*Utilities.GRIDSIZE);
    }
    public void init(){
    }
    public JPopupMenu getMenu(Selectable selected) {
        if (selected instanceof Player) {
            final Player player = (Player) selected;
            JPopupMenu menu = new JPopupMenu();
            JMenu subSubMenu;

            JMenu it = new JMenu("sell");

            Inventory inventoryFarm = Game.instance().getInventory().getFarmItem();

            if (!inventoryFarm.isEmpty()) {
                JMenu subSellFarm;
                subSellFarm = new JMenu("Farm Item");
                for (int i = 0; i < inventoryFarm.getSize(); i++) {
                    subSubMenu = new JMenu(inventoryFarm.getItem(i).getName()+" (Rp."+inventoryFarm.getItem(i).getCostSell()+")");
                    subSubMenu.setLayout(null);
                    InitComponentSell comp = new InitComponentSell(selected,i,inventoryFarm.getItem(i),menu);
                    subSubMenu.add(comp);
                    subSellFarm.add(subSubMenu);
                }
                it.add(subSellFarm);
            }

            Inventory inventoryFood = Game.instance().getInventory().getFoodItem();

            if (!inventoryFood.isEmpty()) {
                JMenu subSellFood;
                subSellFood = new JMenu("Food Item");
                for (int i = 0; i < inventoryFood.getSize(); i++) {
                    subSubMenu = new JMenu(inventoryFood.getItem(i).getName()+" (Rp."+inventoryFood.getItem(i).getCostSell()+")");
                    subSubMenu.setLayout(null);
                    InitComponentSell comp = new InitComponentSell(selected,i,inventoryFood.getItem(i),menu);
                    subSubMenu.add(comp);
                    subSellFood.add(subSubMenu);
                }
                it.add(subSellFood);
            }

            Inventory inventoryWar = Game.instance().getInventory().getWarItem();

            if (!inventoryWar.isEmpty()) {
                JMenu subSellWar;
                subSellWar = new JMenu("War Item");
                for (int i = 0; i < inventoryWar.getSize(); i++) {
                    subSubMenu = new JMenu(inventoryWar.getItem(i).getName()+" (Rp."+inventoryWar.getItem(i).getCostSell()+")");
                    subSubMenu.setLayout(null);
                    InitComponentSell comp = new InitComponentSell(selected,i,inventoryWar.getItem(i),menu);
                    subSubMenu.add(comp);
                    subSellWar.add(subSubMenu);
                }
                it.add(subSellWar);
            }

            Inventory inventoryArmor = Game.instance().getInventory().getArmorItem();

            if (!inventoryArmor.isEmpty()) {
                JMenu subSellArmor;
                subSellArmor = new JMenu("Armor Item");
                for (int i = 0; i < inventoryArmor.getSize(); i++) {
                    subSubMenu = new JMenu(inventoryArmor.getItem(i).getName()+" (Rp."+inventoryArmor.getItem(i).getCostSell()+")");
                    subSubMenu.setLayout(null);
                    InitComponentSell comp = new InitComponentSell(selected,i,inventoryArmor.getItem(i),menu);
                    subSubMenu.add(comp);
                    subSellArmor.add(subSubMenu);
                }
                it.add(subSellArmor);
            }

            if (Game.instance().getInventory().isEmpty()) {
                JMenuItem notFound;
                notFound = new JMenuItem("No Inventory");
                notFound.setEnabled(false);
                it.add(notFound);
            }

            menu.add(it);
            menu.pack();
            menu.setVisible(false);
            return menu;
        }
        return null;
    }
        class InitComponentSell extends JMenuItem implements ActionListener,Runnable {
        int x;
        JTextField text;
        JButton buttonbes;
        JButton buttonkec;
        JButton buttonBuy;
        JPopupMenu men;
        Selectable selected;
        Item temp;

        public InitComponentSell (Selectable selected, int i, Item it, JPopupMenu men) {
            super("                                                        ");
            x = 0;
            this.selected = selected;
            this.men = men;
            temp = it;
            //Total.setBounds(0,0,100,20);
            setLayout(null);
            text = new JTextField();
            text.setBounds(45,0,25, 20);
            text.setText(""+x);
            text.setEditable(false);
            text.setHorizontalAlignment(JTextField.CENTER);
            buttonbes = new JButton(">");
            buttonbes.setLayout(null);
            buttonbes.setBounds(70,0,45,20);
            buttonbes.addActionListener(this);
            buttonkec = new JButton("<");
            buttonkec.setBounds(0,0,45,20);
            buttonkec.addActionListener(this);
            buttonBuy = new JButton("sell");
            buttonBuy.setBounds(115,0,75,20);
            buttonBuy.addActionListener(this);
            add(buttonkec);
            add(text);
            add(buttonbes);
            add(buttonBuy);
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buttonbes) {
                if (x < Game.instance().getInventory().getJumItem(temp)) {
                    x += 1;
                }
                text.setText(""+x);
            } else if (e.getSource() == buttonkec) {
                if (x != 0) {
                    x -= 1;
                }
                text.setText(""+x);
            } else if (e.getSource() == buttonBuy) {
                Thread T1 = new Thread(this);
                T1.start();
                men.setVisible(false);
            }
        }

        public void run() {
            Player player = (Player) selected;
            buttonbes.setEnabled(false);
            buttonkec.setEnabled(false);
            int gx = (int)SellBox.this.getPosition().X();
            int gy = (int)SellBox.this.getPosition().Y();
            Object lock = new Object();
            Boolean[] cancel = new Boolean[1];
            player.move(gx, gy, lock,cancel);
            synchronized(lock){
                try {
                    lock.wait();
                } catch (InterruptedException e){
                    return;
                }
            }
            if (!cancel[0]){
                map.popCancel(gx, gy);
                int money = Game.instance().getMoney();
                money = money + x * temp.getCostSell();
                Game.instance().setMoney(money);
                Game.instance().getInventory().delete(temp,x);
                if (temp instanceof WarItem){
                    System.out.print("waritem");
                } else if (temp instanceof FarmItem){
                    System.out.print("farmitem");
                } else if (temp instanceof FoodItem){
                    System.out.print("fooditem");
                    Game.instance().getStory().setSold(temp.getIDitem()-20, x);
                } else if (temp instanceof ArmorItem){
                    System.out.print("armoritem");
                }
                Game.instance().log().append("Sold "+x+" "+temp.getName()+"(s) Rp."+(x*temp.getCostSell())+"\n");
            }
        }
    }
}
