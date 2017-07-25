/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
        word.playSounds();
    }
}
