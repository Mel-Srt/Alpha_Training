package model;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author Mel-Srt
 */
public class Word {

    String word;
    int lengthWord;
    char name;
    File sounds[];

    public Word(String word) {
        this.word = word;

        System.out.println("mot choisi : " + word);
        
        lengthWord = word.length();
        this.sounds = new File[lengthWord];
        
        System.out.println("Longueur du mot : " + lengthWord);
        
        for(int i = 0; i < lengthWord; i++){
            this.sounds[i] = new File("sounds/" + java.lang.Character.toUpperCase(word.charAt(i)) + ".WAV");
        }
    }

    public void playSounds() {
        try {
            
            for (int i = 0; i< lengthWord;i++){
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(sounds[i]));
                clip.start();
                Thread.sleep(clip.getMicrosecondLength() / 500);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
