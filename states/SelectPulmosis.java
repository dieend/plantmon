/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plantmon.states;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import plantmon.entity.movingObject.PulmosisLand;

/**
 *
 * @author Septu
 */
public class SelectPulmosis extends ParentState {
    private int count;
    Object [] pulmos;
    PulmosisLand pulmo;
    public SelectPulmosis(Object[] args) {
        super();
        ID = SELECTPULMOSIS;
        initComponents();
        pulmos = new PulmosisLand[3];
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
            }
        }
    }
    
    public void updated(){
    }
    
    @Override public void paintComponent(Graphics g) {
//        System.out.println("paintComponent - CobaOpeh");
        updated();
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

        jPanel1.setBackground(new java.awt.Color(153, 153, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(new GridLayout(15,2));
        for (PulmosisLand p:Game.instance().getStory().getPulmosis()) {
            JButton a = new JButton(""+p.getName());
            this.pulmo = p;
            a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (count < 3) {
                    pulmos[count] = pulmo;
                    count++;
                } else {
                }
            }
        });
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
        this.add(jLabel1);
        jLabel1.setBounds(10, 11, 213, 38);

        jButton1.setText("Delete");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        this.add(jButton1);
        jButton1.setBounds(300, 400, 150, 23);
        
        jButton2.setText("Start Battle");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        this.add(jButton2);
        jButton2.setBounds(470, 400, 150, 23);
        jButton2.getAccessibleContext().setAccessibleName("Start Battle");
    }// </editor-fold>

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        count = 0;
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        Game.instance().goTo(ParentState.BATTLEGURUN, pulmos);
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration

    class Click implements ActionListener {
        PulmosisLand pulmo;
        public Click (PulmosisLand pul) {
            this.pulmo = pul;
        }
        public void actionPerformed(ActionEvent e) {
            if (count < 3) {
                pulmos[count] = pulmo;
                count++;
            } else {
            }
        }
    }

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