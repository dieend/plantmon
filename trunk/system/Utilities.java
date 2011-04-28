/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plantmon.system;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
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
    HashMap<String,Clip> music;
    HashMap<String,Sequencer> midi;
    HashMap<String, Boolean> music_active;
    private Utilities (){
        music = new HashMap<String, Clip>();
        music_active = new HashMap<String, Boolean>();
        midi = new HashMap<String, Sequencer>();
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
    
    public void musicPlayUntilTurnedOff(String filename){
        try {
            music_active.put(filename, Boolean.TRUE);
            InputStream file = this.getClass().getResourceAsStream(filename);//new File(filename);
            stream = AudioSystem.getAudioInputStream(file);
            music.put(filename, AudioSystem.getClip());
            (new Thread(new PlayMusic(filename,true))).start();
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex){
        }
    }
    public void midiPlayUntilTurnedOff(String filename){
        try {
            // From file
            music_active.put(filename, Boolean.TRUE);
            Sequence sequence = MidiSystem.getSequence(this.getClass().getResource(filename));
            // Create a sequencer for the sequence
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.setSequence(sequence);
            midi.put(filename,sequencer);
            (new Thread(new PlayMidi(filename,true))).start();

        } catch (MalformedURLException e) {
        } catch (IOException e) {
        } catch (MidiUnavailableException e) {
        } catch (InvalidMidiDataException e) {
        }
    }
    public void musicPlayOnce(String filename){
        try {
            music_active.put(filename, Boolean.TRUE);
            InputStream file = this.getClass().getResourceAsStream(filename);//new File(filename);
            stream = AudioSystem.getAudioInputStream(file);
            music.put(filename, AudioSystem.getClip());
            (new Thread(new PlayMusic(filename))).start();
        } catch (UnsupportedAudioFileException ex){
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (LineUnavailableException ex){
            ex.printStackTrace();
        }
    }
    public void midiPlayOnce(String filename){
        try {
            // From file
            music_active.put(filename, Boolean.TRUE);
            Sequence sequence = MidiSystem.getSequence(this.getClass().getResource(filename));
            // Create a sequencer for the sequence
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.setSequence(sequence);
            midi.put(filename,sequencer);
            (new Thread(new PlayMidi(filename))).start();

        } catch (MalformedURLException e) {
        } catch (IOException e) {
        } catch (MidiUnavailableException e) {
        } catch (InvalidMidiDataException e) {
        }
    }
    public void musicOff(String filename){
        music_active.put(filename,Boolean.FALSE);
        music.get(filename).stop();
    }
    public void midiOff(String filename){
        music_active.put(filename,Boolean.FALSE);
        midi.get(filename).stop();
    }
    class PlayMusic implements Runnable{
        String filename;
        boolean loop;
        PlayMusic(String fn){
            filename = fn;
            loop = false;
        }
        PlayMusic(String fn, boolean loop){
            filename = fn;
            this.loop = loop;
        }
        public void run() {
            try {
            music.get(filename).open(stream);
            if (loop){
                music.get(filename).loop(music.get(filename).LOOP_CONTINUOUSLY);
            } else {
                music.get(filename).start();
            }
            while(music_active.get(filename)){
                if(music.get(filename).getMicrosecondPosition()== music.get(filename).getMicrosecondLength()){
                    if (!loop){
                        System.out.println("a");
                        music.get(filename).stop();
                        music_active.put(filename, Boolean.FALSE);
                    } else {
                    }
                }
                System.out.println("c");
            }
            music_active.put(filename, Boolean.FALSE);
            music.get(filename).close();
            } catch(IOException e){
                e.printStackTrace();
            } catch(LineUnavailableException e){
                e.printStackTrace();
            }
        }

    }
    class PlayMidi implements Runnable {
        String filename;
        boolean loop;
        PlayMidi(String fn){
            filename = fn;
            loop = false;
        }
        PlayMidi(String fn, boolean loop){
            filename = fn;
            this.loop = loop;
        }
        public void run() {
            try {
                // Start playing
                midi.get(filename).open();
            } catch (MidiUnavailableException ex) {
                ex.printStackTrace();
            }
            midi.get(filename).start();
            if (loop){
                midi.get(filename).setLoopCount(midi.get(filename).LOOP_CONTINUOUSLY);
            }
            music_active.put(filename,Boolean.TRUE);
            while (music_active.get(filename)) {
                if (midi.get(filename).getMicrosecondPosition() == midi.get(filename).getMicrosecondLength()){
                    if (!loop){
                        midi.get(filename).stop();
                        music_active.put(filename, Boolean.FALSE);
                    } else {
                    }
                }
            }
            music_active.put(filename,Boolean.FALSE);
            midi.get(filename).close();
        }

    }
    public static void main(String[] args){
//        Utilities.instance().midiPlayUntilTurnedOff("012-Theme01.mid");
//        Utilities.instance().musicPlayUntilTurnedOff("bugsbunny1.wav");
        Utilities.instance().midiPlayOnce("012-Theme01.mid");
    }
}
