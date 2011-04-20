/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plantmon.system;

/**
 *
 * @author novan hamonangan
 */
public interface Jobable {

    public void move(int gx,int gy,Object lock,Boolean[] cancel);

    

}
