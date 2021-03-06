package controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import model.Word;

/**
 *
 * @author Mel-Srt
 */
public class PlayWord implements Runnable {

    private Word word;
    private int spellingSpeed;

    public PlayWord(Word word, int spellingSpeed) {
        this.word = word;
        this.spellingSpeed = spellingSpeed;
    }

    public void run() {
        try {
            word.playSounds(spellingSpeed);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(PlayWord.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(PlayWord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
