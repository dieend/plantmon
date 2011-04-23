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

public class SaveState extends JFrame implements ActionListener {
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
        JPanel panelSave1           = new JPanel();
        JButton saveButton1         = new JButton("SAVESTATE1");
        JPanel panelSave2           = new JPanel();
        JButton saveButton2         = new JButton("SAVESTATE2");
        JPanel panelSave3           = new JPanel();
        JButton saveButton3         = new JButton("SAVESTATE3");
        JPanel panelSave4           = new JPanel();
        JButton saveButton4         = new JButton("SAVESTATE4");


        panelSave1.add(playerName1);
        panelSave1.add(gameDate1);
        panelSave1.add(saveButton1);
        panelSave2.add(playerName2);
        panelSave2.add(gameDate2);
        panelSave2.add(saveButton2);
        panelSave3.add(playerName3);
        panelSave3.add(gameDate3);
        panelSave3.add(saveButton3);
        panelSave4.add(playerName4);
        panelSave4.add(gameDate4);
        panelSave4.add(saveButton4);
        
        panel.add(panelSave1);
        panel.add(panelSave2);
        panel.add(panelSave3);
        panel.add(panelSave4);

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

        saveButton2.addActionListener(this);
    }
        public void actionPerformed(ActionEvent e) {
      // ActionListener, akan mengecek tiap-tiap aksi yang dilakukan oleh user
    if ("SAVESTATE2".equals(e.getActionCommand())) {
            playerName1.setText("Gurun");
    }

  }

        public static void main (String arg[])
	{
            SaveState SS = new SaveState();
	}
}
