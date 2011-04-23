/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plantmon.states;

/**
 *
 * @author Gurun Nevada
 */
import javax.swing.*;
import java.awt.event.*;

public class LoadState extends JFrame implements ActionListener {
JLabel playerName1;
JLabel gameDate1;
JLabel playerName2;
JLabel gameDate2;
JLabel playerName3;
JLabel gameDate3;
JLabel playerName4;
JLabel gameDate4;

        SaveState () {
        playerName1 = new JLabel ("Empty");
        playerName2 = new JLabel ("Empty");
        playerName3 = new JLabel ("Empty");
        playerName4 = new JLabel ("Empty");

        gameDate1   = new JLabel ("Empty");
        gameDate2   = new JLabel ("Empty");
        gameDate3   = new JLabel ("Empty");
        gameDate4   = new JLabel ("Empty");

        JPanel panel                = new JPanel();
        JPanel panelLoad1           = new JPanel();
        JButton loadButton1         = new JButton("LOADSTATE1");
        JPanel panelLoad2           = new JPanel();
        JButton loadButton2         = new JButton("LOADSTATE2");
        JPanel panelLoad3           = new JPanel();
        JButton loadButton3         = new JButton("LOADSTATE3");
        JPanel panelLoad4           = new JPanel();
        JButton loadButton4         = new JButton("LOADSTATE4");


        panelLoad1.add(playerName1);
        panelLoad1.add(gameDate1);
        panelLoad1.add(loadButton1);
        panelLoad2.add(playerName2);
        panelLoad2.add(gameDate2);
        panelLoad2.add(loadButton2);
        panelLoad3.add(playerName3);
        panelLoad3.add(gameDate3);
        panelLoad3.add(loadButton3);
        panelLoad4.add(playerName4);
        panelLoad4.add(gameDate4);
        panelLoad4.add(loadButton4);

        panel.add(panelLoad1);
        panel.add(panelLoad2);
        panel.add(panelLoad3);
        panel.add(panelLoad4);

        //panel.setLayout(null);
        //panelSave1.setLayout(null);
        //panelSave2.setLayout(null);
        //panelSave3.setLayout(null);
        //panelSave4.setLayout(null);

        //panelSave1.setBounds(100, 100, 200, 200);
        //panelSave2.setBounds(300, 100, 200, 200);
        //panelSave3.setBounds(500, 100, 200, 200);
        //panelSave4.setBounds(700, 100, 200, 200);

        //playerName1.setBounds(100,100,200,100);
        //gameDate1.setBounds(100,200,200,100);
        //playerName2.setBounds(100,300,200,100);
        //gameDate2.setBounds(100,400,200,100);
        //playerName3.setBounds(100,500,200,100);
        //gameDate3.setBounds(100,600,200,100);
        //playerName4.setBounds(100,700,200,100);
        //gameDate4.setBounds(100,800,200,100);






        add(panel);
	this.setSize(1200, 1000);
	setVisible(true);

        loadButton2.addActionListener(this);
    }
        public void actionPerformed(ActionEvent e) {
      // ActionListener, akan mengecek tiap-tiap aksi yang dilakukan oleh user
    if ("SAVESTATE2".equals(e.getActionCommand())) {
            playerName1.setText("Gurun");
    }

  }

        public static void main (String arg[])
	{
            LoadState LS = new LoadState();
	}
}

