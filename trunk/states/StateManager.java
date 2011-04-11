package plantmon.states;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JFrame;

public class StateManager {
    ArrayList<ParentState> states;
    int currentState;
    private static StateManager stateManager;
    private StateManager(){
        states = new ArrayList<ParentState>();
        currentState = -1;
    }
    public JFrame frame;
    public static StateManager instance(){
        if (stateManager == null){
            stateManager = new StateManager();
        }
        return stateManager;
    }
    public void setFrame(JFrame frame){
        this.frame = frame;
    }
    public void goTo(int IDstate){
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
            states.add(StateFactory.createState(IDstate));
            where=states.size()-1;
            frame.add(states.get(where));
        }
        if (currentState >= 0 && currentState< states.size()){
            states.get(currentState).setVisible(false);
        } else {
            System.out.println("fail");
        }
        currentState = where;
        states.get(currentState).setVisible(true);
        states.get(currentState).updateUI();
//        states.get(currentState).repaint();
//        Component[] com = frame.getContentPane().getComponents();
//        for (Component i:com){
//            i.repaint();
//        }
    }
}