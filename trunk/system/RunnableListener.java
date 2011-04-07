/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plantmon.system;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author asus
 */
public abstract class RunnableListener implements ActionListener, Runnable{
    protected Selectable selected;
    protected RunnableListener(Selectable selected){
        this.selected = selected;
    }
    public void actionPerformed (ActionEvent e){
        Thread T1 = new Thread(this);
        T1.start();
    }
}
