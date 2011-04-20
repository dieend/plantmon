package plantmon.states;

import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import plantmon.entity.Inventory;
import plantmon.entity.Item;
import plantmon.entity.Time;
import plantmon.entity.movingObject.Dwarf;
import plantmon.entity.unmoveable.Land;
import plantmon.entity.unmoveable.Plant;
import plantmon.system.Utilities;

public class Game {
    //ArrayList<ParentState> states;
    ArrayList<Plant> plants;
    ArrayList<Dwarf> dwarfs;
    int[][] farmstatus;
    Inventory inventory;
    Integer money;
    ParentState currentState;
    ParentState pause;
    JPanel dialogBox;
    JTextArea log;
    JScrollPane pane;
    StoryLine story;
    String name;
    private static Game stateManager;
    public int[][] farmstatus() {
        return farmstatus;
    }
    private Game(){
//        states = new ArrayList<ParentState>();
        name = "Opeh";
        farmstatus = new int[20][20];
        for (int i=0; i<20; i++){
            for (int j=0; j<20; j++){
                farmstatus[i][j] = Land.NORMAL;
            }
        }
        dialogBox = null;
        plants = new ArrayList<Plant>();
        dwarfs = new ArrayList<Dwarf>();
        inventory = new Inventory(10);
        money = new Integer(2000);
        currentState = null;
        log = new JTextArea();
        log.setBounds(0, 350, 650, 100);
        log.setEditable(false);
        pane = new JScrollPane(log);
        pane.setBounds(0, 350, 650, 100);
        story = new StoryLine();
        story.setDay(Time.instance().getDay());
    }
    public String getName(){
        return name;
    }
    public JTextArea log() {
        return log;
    }
    public void destroy(){
        stateManager = null;
    }
    public JFrame frame;
    public static Game instance(){
        if (stateManager == null){
            stateManager = new Game();
            Dwarf dw = new Dwarf(null, null, null, 2, Game.instance().money);
            Game.instance().dwarfs.add(dw);
            dw = new Dwarf(null, null, null, 1, Game.instance().money);
            Game.instance().dwarfs.add(dw);
            dw = new Dwarf(null, null, null, 3, Game.instance().money);
            Game.instance().dwarfs.add(dw);
            //map.push(dw.position().IntX(), dw.position().IntY(), dw);
        }
        return stateManager;
    }
    public void setFrame(JFrame frame){
        this.frame = frame;
    }
    public void goTo(int IDstate, Object[] args){
        boolean found = false;
        //int where = currentState;
//        for (int i=0; i<states.size()&&!found; i++){
//            if (IDstate == states.get(i).getID()){
//                found = true;
//                where = i;
//            }
//        }
        
//        System.out.print(com[0].getName());
//        System.out.println(" jumlah componnet frame = "+com.length);
//        if (!found){
//            states.add(StateFactory.createState(IDstate,args));
//            where=states.size()-1;
            if (currentState!=null) {
                currentState.turnOff();
                frame.remove(currentState);
            }
            currentState = StateFactory.createState(IDstate,args);
            frame.add(currentState);
//        }
//        if (currentState >= 0 && currentState< states.size()){
//            states.get(currentState).setVisible(false);
//            states.get(currentState).turnOff();
//        } else {
//            System.out.println("fail");
//        }
        System.out.println("now in state"+currentState.getID());
        //currentState = where;
        currentState.setVisible(true);
        //states.get(currentState).updateUI();
        currentState.updateUI();
        ((Thread) new Thread(currentState)).start();
//        states.get(currentState).repaint();
//        Component[] com = frame.getContentPane().getComponents();
//        for (Component i:com){
//            i.repaint();
//        }
    }
//    public void returnTo(int IDstate){
//        boolean found = false;
//        int where = currentState;
//        for (int i=0; i<states.size()&&!found; i++){
//            if (IDstate == states.get(i).getID()){
//                found = true;
//                where = i;
//            }
//        }
//        System.out.println(where);
//        if (found){
//            for (int i=where+1; i<states.size();i++){
//                frame.remove(states.get(i));
//                states.remove(i);
//            }
//            currentState = where;
//            states.get(currentState).setVisible(true);
//            states.get(currentState).updateUI();
//            ((Thread) new Thread(states.get(currentState))).start();
//        } else {
//            System.out.println("gagal return state");
//        }
//    }
    public void seek(int IDstate,Object[] args){
        currentState.setVisible(false);
        currentState.turnOff();
        pause = StateFactory.createState(IDstate, args);
        pause.setVisible(true);
        frame.add(pause);
    }
    public void returnTo(){
        pause.setVisible(false);
        frame.remove(pause);
        currentState.setVisible(true);
        ((Thread) new Thread(currentState)).start();
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
    public void addPlant(Plant plant){
        plants.add(plant);
    }
    public void removePlant(Plant plant){
        plants.remove(plant);
    }
    public void changeDay(){
        for (int i=0; i<20; i++){
            for (int j=0; j<20; j++){
                farmstatus[i][j] = Land.NORMAL;
            }
        }
        for (Plant p:plants){
            p.grow(Time.instance().getSeason());
            farmstatus[p.getPosition().IntX()/Utilities.GRIDSIZE][p.getPosition().IntY()/Utilities.GRIDSIZE] = Land.PLOWED;
        }
        Time.instance().changeDay();
        Game.instance().goTo(ParentState.HOME, null);
    }
    public JPanel dialogBox(){
        if (dialogBox != null)
            return dialogBox;
        else return new JPanel(null);
    }
    public void setDialogBox(JPanel dialog, JPanel where){
        if (dialogBox!=null){
            dialogBox.getParent().remove(dialogBox);
        }
        dialogBox = dialog;
        if (dialog != null){
            dialogBox.setBounds(0, 350, 650, 100);
            where.add(dialogBox);
        }
    }
    public void dialogOn(){
        if (dialogBox !=null){
            dialogBox.setVisible(true);
            dialogBox.updateUI();
        }
    }
    public void dialogOff(){
        if (dialogBox != null) {
            dialogBox.setVisible(false);
            dialogBox.updateUI();
        }
    }
}