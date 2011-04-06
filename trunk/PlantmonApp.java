/*
 * PlantmonApp.java
 */

package plantmon;

import plantmon.states.FarmState;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JFrame;


/**
 * The main class of the application.
 */
public class PlantmonApp {
    JFrame mainFrame;
    public Thread gameloop;
    public FarmState p;
    public void init() {
        System.out.println("startup");
        // mengeset state = FarmState
        p = new FarmState();
        p.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        p.setPreferredSize(new Dimension(640, 480));
        mainFrame = new JFrame();
        mainFrame.getContentPane().add(p, BorderLayout.CENTER);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
    public void start() {
        gameloop = new Thread(p);
        gameloop.start();
    }

    public static void main(String[] args) {
        PlantmonApp app = new PlantmonApp();
        app.init();
        app.start();
        System.out.println("lalala");
        
    }
}
