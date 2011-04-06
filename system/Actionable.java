/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plantmon.system;

import javax.swing.JPopupMenu;

/**
 *
 * @author asus
 */
public interface Actionable {
    public JPopupMenu getMenu(Selectable selected);
}
