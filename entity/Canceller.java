package plantmon.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import plantmon.game.AnimatedSprite;
import plantmon.game.Point2D;
import plantmon.system.Cancellable;
import plantmon.system.Drawable;
import plantmon.system.Utilities;

public class Canceller implements Drawable{
    Boolean[] cancel;
    final Object lock;
    AnimatedSprite pict;
    Cancellable cancelled;
    int numAction;
    public Canceller(JPanel panel, Graphics2D g2d,int gx, int gy,Boolean[] cancel,
                    Object lock,Cancellable who, int actionNumber) {
        pict = new AnimatedSprite(panel, g2d);
        this.lock = lock;
        pict.setPosition(new Point2D(gx+Utilities.GRIDGALAT,gy+Utilities.GRIDGALAT));
        this.cancel = cancel;
        cancel[0] =false;
        cancelled=who;
        numAction = actionNumber;
        pict.load("picture/cancel.png", 1, 1, 48, 48);
        init();
    }
    public void init() {
    }
    @Override public void update(){}

    
    @Override public void draw(){
        pict.draw();
        pict.graphics().setColor(Color.red);
        pict.graphics().drawString(""+numAction, pict.position().IntX(),pict.position().IntY());
    }

    public JPopupMenu getMenu() {
        JPopupMenu popmenu = new JPopupMenu();
        JMenuItem menu = new JMenuItem("cancel");
        menu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                synchronized(lock){
                    cancel[0] = true;
                    lock.notify();
                }
                cancelled.cancel(lock);
            }
        });
        popmenu.add(menu);
        return popmenu;
    }
}
