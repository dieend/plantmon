/*
 * PlantmonApp.java
 */

package plantmon;

import javax.swing.JFrame;
import plantmon.states.ParentState;
import plantmon.states.Game;


/**
 * The main class of the application.
 */
public class PlantmonApp{
    //public FarmState p;
    public void init() {
        System.out.println("startup");
        // mengeset state = FarmState
        //p = new FarmState(10,10);
        //mainFrame = new JFrame();
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
    }
    

    public static void main(String[] args) {
        PlantmonApp app = new PlantmonApp();
        app.start();
    }
    protected void start() {
        init();
        Object[] args = new Object[0];
        Game.instance().goTo(ParentState.FRONTSTATE,args);
    }
}
