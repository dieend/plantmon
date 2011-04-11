package plantmon.states;

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
        if (!found){
            states.add(StateFactory.createState(IDstate));
            where=states.size()-1;
            frame.add(states.get(where));
        }
        if (currentState != -1){
            states.get(currentState).setVisible(false);
        }
        System.out.print(currentState);
        currentState = where;
        states.get(currentState).setVisible(true);
        System.out.println(states.get(currentState).getName());
    }
}