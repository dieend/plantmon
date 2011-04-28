/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plantmon.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JFrame;
import plantmon.entity.movingObject.PulmosisLand;
import plantmon.game.ImageEntity;

/**
 *
 * @author Septu
 */
public class SelectPulmosis extends ParentState {
    private int count;
    Object [] pulmos;
    PulmosisLand pulmo;
    Object[] inte;
    public SelectPulmosis(Object[] args) {
        super();
        inte = args;
        ID = SELECTPULMOSIS;
        initComponents();
        pulmos = new PulmosisLand[3];
        backbuffer = new BufferedImage(640,480, BufferedImage.TYPE_INT_ARGB);
        g2d = backbuffer.createGraphics();
        background = new ImageEntity(this);
        background.load("picture/Gurun_Battle_Field.png");
        count = 0;
    }

    @Override
    public void run(){
        active = true;
        while (active) {
//            System.out.format("There are currenty %d Thread running\n",Thread.activeCount());
            try {
                Thread.sleep(20);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            repaint();
            gameUpdate();
//            System.out.print(""+player.position().IntX()/Utilities.GRIDSIZE+""+player.position().IntY()/Utilities.GRIDSIZE+"\n");
        }

    }
    
    public void gameUpdate() {
        for(int i = 0; i <3; i++) {
            if (pulmos[i] instanceof PulmosisLand) {
                jPanel2.add(((PulmosisLand) pulmos[i]).get_Info());
                jPanel2.setVisible(true);
            }
        }
    }
    
    public void updated(){
        g2d.drawImage(background.getImage(), 0, 0, this);
    }
    
    @Override public void paintComponent(Graphics g) {
//        System.out.println("paintComponent - CobaOpeh");
        updated();
        g.drawImage(backbuffer,0, 0, this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        this.setLayout(null);
        this.setBackground(Color.red);

        jPanel1.setBackground(new java.awt.Color(153, 153, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(new GridLayout(15,2));
        for (PulmosisLand p:Game.instance().getStory().getPulmosis()) {
            final JButton a = new JButton(""+p.getName());
            this.pulmo = p;
            a.addActionListener(new Click(p,a));
            jPanel1.add(a);
        }

        this.add(jPanel1);
        jPanel1.setBounds(10, 55, 213, 300);

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 397, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 359, Short.MAX_VALUE)
        );

        this.add(jPanel2);
        jPanel2.setBounds(233, 55, 397, 300);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel1.setText("Pulmosis -- (Choose three of them)");
        jLabel1.setForeground(Color.RED);
        this.add(jLabel1);
        jLabel1.setBounds(10, 24, 213, 38);

        jButton1.setText("Exit Area");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        this.add(jButton1);
        jButton1.setBounds(5, 400, 150, 23);
        jButton1.getAccessibleContext().setAccessibleName("Exit Area");
        if(((Integer) inte[0]) == 4) {
            jButton1.setEnabled(false);
        }

        jButton2.setText("Start Battle");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        this.add(jButton2);
        jButton2.setBounds(470, 400, 150, 23);
        jButton2.setEnabled(false);
        jButton2.getAccessibleContext().setAccessibleName("Start Battle");
    }// </editor-fold>

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
       Game.instance().goTo(ParentState.MAPSTATE, null);
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        if (((Integer) inte[0]) == 0) {
            Game.instance().goTo(ParentState.BATTLEGURUN, pulmos);
        } else if (((Integer) inte[0]) == 1) {
            Game.instance().goTo(ParentState.BATTLEPRAMA, pulmos);
        } else if (((Integer) inte[0]) == 2) {
            Game.instance().goTo(ParentState.BATTLESEPTU, pulmos);
        } else if (((Integer) inte[0]) == 3) {
            Game.instance().goTo(ParentState.BATTLENOVAN, pulmos);
        } else if (((Integer) inte[0]) == 4) {
            Game.instance().goTo(ParentState.LASTBATTLE, pulmos);
        }
    }

    class Click implements ActionListener {
        PulmosisLand pul;
        JButton but1;
        public Click(PulmosisLand p, JButton but) {
            pul = p;
            but1 = but;
        }

        public void actionPerformed(ActionEvent e) {
            if (count < 2) {//ini dia < 2
                pulmos[count] =pul;
                count++;
                but1.setEnabled(false);
            } else {
                pulmos[count] =pul;
                count++;
                but1.setEnabled(false);
                jButton2.setEnabled(true);
            }
        }
        
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration

    public static void main(String args[]) {
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
        Game.instance().goTo(ParentState.SELECTPULMOSIS,null);
    }

}
