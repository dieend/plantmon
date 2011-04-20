package plantmon.states;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import plantmon.entity.unmoveable.Bawang;
import plantmon.game.GridMap;

public class FrontState extends ParentState{
    public FrontState(Object[] args){
        super();
        setLayout(null);
        ID = FRONTSTATE;
        setname("FrontState");
        JButton newGame = new JButton("New Game");
        newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Game.instance().goTo(ParentState.HOME,new Object[0]);
            }
        });
        newGame.setBounds(30, 30, 300, 30);
        add(newGame);}
//
//        JButton loadGame = new JButton("Load Game");
////        loadGame.addActionListener(new ActionListener(){
////            public void actionPerformed(ActionEvent e){
////                Game.instance().goTo(ParentState., new Object[0]);
////            }
////        });
//        loadGame.setBounds(30, 60, 300, 30);
//        add(loadGame);
//
//        JButton option = new JButton("Option");
////        loadGame.addActionListener(new ActionListener(){
////            public void actionPerformed(ActionEvent e){
////                Game.instance().goTo(ParentState., new Object[0]);
////            }
////        });
//        option.setBounds(30, 90, 300, 30);
//        add(option);
//
//        JButton help = new JButton("Help");
//         // loadGame.addActionListener(new ActionListener(){
////            public void actionPerformed(ActionEvent e){
////                Game.instance().goTo(ParentState., new Object[0]);
////            }
////        });
//        help.setBounds(30, 120, 300, 30);
//        add(help);
//
//        JButton quit = new JButton("Quit");
//         // loadGame.addActionListener(new ActionListener(){
////            public void actionPerformed(ActionEvent e){
////                Game.instance().goTo(ParentState., new Object[0]);
////            }
////        });
//        quit.setBounds(30, 150, 300, 30);
//        add(quit);
//    }



//    public static void main(String[] str){
//        JFrame frame=new JFrame();
//        BufferedImage bf = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
//        FrontState a = new FrontState(new Object[2]);
//        frame.getContentPane().add(a);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(400, 400);
//        frame.setVisible(true);
//    }
}


