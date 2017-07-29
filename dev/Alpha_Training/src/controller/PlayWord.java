/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    Word word;

    public PlayWord(Word word) {
        this.word = word;
    }

    public void run() {
        try {
            word.playSounds();
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(PlayWord.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(PlayWord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
