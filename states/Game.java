package plantmon.states;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
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

public class Game implements Serializable{
    //ArrayList<ParentState> states;
    private ArrayList<Plant> plants;
    transient ArrayList<Dwarf> dwarfs;
    int[][] farmstatus;
    Inventory inventory;
    Integer money;
    transient ParentState currentState;
    transient ParentState pause;
    transient JFrame frame;
    transient JPanel dialogBox;
    JTextArea log;
    transient JScrollPane pane;
    private StoryLine story;
    String name;
    private static Game stateManager;
    private int weather;
    public static final int SUNNY = 0;
    public static final int RAINY = 1;
    public static final int STORM = 2;
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
            if (frame == null){
                System.out.print("daaaaamn");
            }
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
        ((Thread)new Thread(pause)).start();
        frame.add(pause);
    }
    public void returnTo(){
        pause.setVisible(false);
        pause.turnOff();
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
        getPlants().add(plant);
    }
    public void removePlant(Plant plant){
        getPlants().remove(plant);
    }
    public void changeDay(){
        System.out.println("Change Day");
        Integer[] ran = new Integer[100];
        int x;
        for (int i = 0; i <60; i++) {
            ran[i] = SUNNY;
        }
        for (int i = 60; i <90; i++) {
            ran[i] = RAINY;
        }
        for (int i = 90; i <100; i++) {
            ran[i] = STORM;
        }
        Random ranNum = new Random();
        x = ranNum.nextInt(100);
        weather = ran[x];
//        weather = RAINY;
        if (weather == SUNNY) {
            for (int i=0; i<20; i++){
                for (int j=0; j<20; j++){
                    farmstatus[i][j] = Land.NORMAL;
                }
            }
            for (Plant p:getPlants()){
                farmstatus[p.getPosition().IntX()/Utilities.GRIDSIZE][p.getPosition().IntY()/Utilities.GRIDSIZE] = Land.PLOWED;
                p.grow(Time.instance().getSeason());
            }
        } else if (weather == RAINY) {
            for (int i=0; i<20; i++){
                for (int j=0; j<20; j++){
                    farmstatus[i][j] = Land.NORMAL;
                }
            }
            for (Plant p:getPlants()){
                farmstatus[p.getPosition().IntX()/Utilities.GRIDSIZE]
                          [p.getPosition().IntY()/Utilities.GRIDSIZE] = Land.WATERED;
                p.grow(Time.instance().getSeason());
                p.doWater();
            }
        } else if (weather == STORM) {
            for (int i=0; i<20; i++){
                for (int j=0; j<20; j++){
                    farmstatus[i][j] = Land.NORMAL;
                }
            }    
            for (Iterator<Plant> iter =plants.iterator(); iter.hasNext();){
                x = ranNum.nextInt(100);
                Plant p = iter.next();
                if (x >= 60) {
                    iter.remove();
                } else {
                   farmstatus[p.getPosition().IntX()/Utilities.GRIDSIZE][p.getPosition().IntY()/Utilities.GRIDSIZE] = Land.PLOWED;
                   p.grow(Time.instance().getSeason());
                }
            }
        }
        Time.instance().changeDay();
        story.setDay(Time.instance().getDay());
        story.setSeason(Time.instance().getSeason());
        story.getKentang().setWatered(false);
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

    public StoryLine getStory() {
        return story;
    }

    public void setStory(StoryLine story) {
        this.story = story;
    }

    /**
     * @return the weather
     */
    public int getWeather() {
        return weather;
    }
    public void save(String filename){
        //filename = "file.txt";
        FileOutputStream os = null;
        ObjectOutputStream ob = null;
        try {
            os = new FileOutputStream(filename);
            ob = new ObjectOutputStream(os);
            ob.writeObject(this);
            ob.writeObject(Time.instance());
            ob.close();
        } catch (IOException ex){
            ex.printStackTrace();
        } 
    }
    public void load(String filename){
        frame.dispose();
        Game.instance().destroy();
        FileInputStream is = null;
        ObjectInputStream in = null;
        try {
            is = new FileInputStream(filename);
            in = new ObjectInputStream(is);
            stateManager = (Game) in.readObject();
            Time.instance().load((Time) in.readObject());
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }        catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        JFrame mainFrame = new JFrame();
        mainFrame.setSize(640, 480);
        mainFrame.setResizable(false);
//        //mainFrame.add(p, BorderLayout.CENTER);
////        JPanel panel = new JPanel();
////        mainFrame.add(panel);
//        //mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        stateManager.setFrame(mainFrame);
        stateManager.dwarfs = new ArrayList<Dwarf>();
        Dwarf dw = new Dwarf(null, null, null, 2, Game.instance().money);
        stateManager.dwarfs.add(dw);
        dw = new Dwarf(null, null, null, 1, Game.instance().money);
        stateManager.dwarfs.add(dw);
        dw = new Dwarf(null, null, null, 3, Game.instance().money);
        stateManager.dwarfs.add(dw);
        stateManager.goTo(ParentState.HOME, null);
    }
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Plantmon");
        mainFrame.setSize(640, 480);
//        mainFrame.setResizable(false);
        //mainFrame.add(p, BorderLayout.CENTER);
//        JPanel panel = new JPanel();
//        mainFrame.add(panel);
        //mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        Game.instance().setFrame(mainFrame);
        Game.instance().goTo(ParentState.FRONTSTATE, null);
        Game.instance().save("B:/tes.txt");
        Game.instance().load("B:/tes.txt");
//        System.out.print(Game.instance().currentState.ID);
    }

    public ArrayList<Plant> getPlants() {
        return plants;
    }

    public void setPlants(ArrayList<Plant> plants) {
        this.plants = plants;
    }
}