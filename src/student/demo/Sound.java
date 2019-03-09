/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.demo;

import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author tamseelong
 */
public class Sound {

    private static String audioPath;

    Sound(String audioPath) {
        this.audioPath = audioPath;
    }

    public void play() {
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(audioPath));

            Clip audioClip = AudioSystem.getClip();
            
            audioClip.open(audio);
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
            audioClip.start();
        } catch (LineUnavailableException e) {
        } catch (IOException e){           
        } catch (UnsupportedAudioFileException e){            
        }

    }
}
