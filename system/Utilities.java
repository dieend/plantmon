/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plantmon.system;

/**
 *
 * @author asus
 */
public class Utilities {
    private static Utilities util = null;
    public static final int GRIDSIZE = 80;
    public static final int GRIDGALAT = 5;
    private Utilities (){
    }
    public static Utilities instance(){
        if (util == null){
            util = new Utilities();
        }
        return util;
    }
    public static void destroy(){
        util = null;
    }
}
