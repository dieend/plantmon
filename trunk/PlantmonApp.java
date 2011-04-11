/*
 * PlantmonApp.java
 */

package plantmon;

import javax.swing.JFrame;
import javax.swing.JPanel;
import plantmon.states.ParentState;
import plantmon.states.StateManager;


/**
 * The main class of the application.
 */
public class PlantmonApp {
    //public FarmState p;
    public void init() {
        System.out.println("startup");
        // mengeset state = FarmState
        //p = new FarmState(10,10);
        //mainFrame = new JFrame();
        JFrame mainFrame = new JFrame();
        mainFrame.setSize(640, 480);
        mainFrame.setResizable(false);
        //mainFrame.add(p, BorderLayout.CENTER);
        //JPanel panel = new JPanel();
        //mainFrame.add(panel);
        //mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        StateManager.instance().setFrame(mainFrame);
    }
    public void start() {
    }

    public static void main(String[] args) {
        PlantmonApp app = new PlantmonApp();
        app.init();
        app.start();
        System.out.println("lalala");
        StateManager.instance().goTo(ParentState.FRONTSTATE);
    }
}
