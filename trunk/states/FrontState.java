package plantmon.states;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class FrontState extends ParentState{
    public FrontState(){
        super();
        ID = FRONTSTATE;
        setname("FrontState");
        JButton newGame = new JButton("New Game");
        newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StateManager.instance().goTo(ParentState.FARMSTATE);
            }
        });
        add(newGame);
        updateUI();
        //repaint();
    }
}
