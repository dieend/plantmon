/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plantmon.system;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author asus
 */
public class Utilities {
    private static Utilities util = null;
    public static final int GRIDSIZE = 48;
    public static final int GRIDGALAT = 5;
    AudioInputStream stream;
    Clip music;
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
    public void music(String filename){
        try {
            File file = new File("bugsbunny1.wav");
            stream = AudioSystem.getAudioInputStream(file);
            music = AudioSystem.getClip();
            // PLAY
            music.open(stream);
            music.start();
            music.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex){
        }
    }
}
