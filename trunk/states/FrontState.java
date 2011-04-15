package plantmon.states;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class FrontState extends ParentState{
    public FrontState(Object[] args){
        super();
        ID = FRONTSTATE;
        setname("FrontState");
        JButton newGame = new JButton("New Game");
        newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Game.instance().goTo(ParentState.FARMSTATE,new Object[0]);
            }
        });
        add(newGame);
        //repaint();
    }
}
