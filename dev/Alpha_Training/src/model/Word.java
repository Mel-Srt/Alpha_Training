package model;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Mel-Srt
 */
public class Word {

    private String word;
    private int lengthWord;
    private char name;
    private File sounds[];

    public Word(String word) {
        this.word = word.toLowerCase();

        //System.out.println("mot choisi : " + word);
        lengthWord = word.length();
        this.sounds = new File[lengthWord];

        //System.out.println("Longueur du mot : " + lengthWord);
        for (int i = 0; i < lengthWord; i++) {
            this.sounds[i] = new File("sounds/" + java.lang.Character.toUpperCase(word.charAt(i)) + ".WAV");
        }
    }

    public void playSounds(int spellingSpeed) throws UnsupportedAudioFileException, LineUnavailableException {
        while (true) {
            try {
                for (int i = 0; i < lengthWord; i++) {
                    //if(i==0)Thread.sleep(200);
                    Clip clip = AudioSystem.getClip();
                    try {
                        clip.open(AudioSystem.getAudioInputStream(sounds[i]));
                    } catch (IOException ex) {
                        Logger.getLogger(Word.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    clip.start();
                    //Thread.sleep(clip.getMicrosecondLength() / 500);
                    Thread.sleep(spellingSpeed);
                }
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt(); 
                break; 
            }
            break; 
        }
    }
}
