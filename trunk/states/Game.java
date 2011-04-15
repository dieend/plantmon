package plantmon.states;

import java.util.ArrayList;
import javax.swing.JFrame;
import plantmon.entity.Inventory;
import plantmon.entity.Item;

public class Game {
    ArrayList<ParentState> states;
    Inventory inventory;
    Integer money;
    int currentState;
    private static Game stateManager;
    private Game(){
        states = new ArrayList<ParentState>();
        inventory = new Inventory(10);
        money = new Integer(2000);
        currentState = -1;
    }
    public void destroy(){
        stateManager = null;
    }
    public JFrame frame;
    public static Game instance(){
        if (stateManager == null){
            stateManager = new Game();
        }
        return stateManager;
    }
    public void setFrame(JFrame frame){
        this.frame = frame;
    }
    public void goTo(int IDstate, Object[] args){
        boolean found = false;
        int where = currentState;
        for (int i=0; i<states.size()&&!found; i++){
            if (IDstate == states.get(i).getID()){
                found = true;
                where = i;
            }
        }
        
//        System.out.print(com[0].getName());
//        System.out.println(" jumlah componnet frame = "+com.length);
        if (!found){
            states.add(StateFactory.createState(IDstate,args));
            where=states.size()-1;
            frame.add(states.get(where));
        }
        if (currentState >= 0 && currentState< states.size()){
            states.get(currentState).setVisible(false);
            states.get(currentState).turnOff();
        } else {
            System.out.println("fail");
        }
        System.out.println("now in state"+where);
        currentState = where;
        states.get(currentState).setVisible(true);
        states.get(currentState).updateUI();
        ((Thread) new Thread(states.get(currentState))).start();
//        states.get(currentState).repaint();
//        Component[] com = frame.getContentPane().getComponents();
//        for (Component i:com){
//            i.repaint();
//        }
    }
    public void returnTo(int IDstate){
        boolean found = false;
        int where = currentState;
        for (int i=0; i<states.size()&&!found; i++){
            if (IDstate == states.get(i).getID()){
                found = true;
                where = i;
            }
        }
        System.out.println(where);
        if (found){
            for (int i=where+1; i<states.size();i++){
                frame.remove(states.get(i));
                states.remove(i);
            }
            currentState = where;
            states.get(currentState).setVisible(true);
            states.get(currentState).updateUI();
            ((Thread) new Thread(states.get(currentState))).start();
        } else {
            System.out.println("gagal return state");
        }
    }
    public Inventory getInventory() {
        return inventory;
    }
    public void setInventory (Item i,int Jumlah) {
        inventory.add(i, Jumlah);
    }
    public void setMoney(int uang) {
        money = uang;
    }

    public int getMoney() {
        return money;
    }
}