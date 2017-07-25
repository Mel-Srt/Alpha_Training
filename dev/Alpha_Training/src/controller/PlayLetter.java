/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.LetterAlphabet;

/**
 *
 * @author Mel-Srt
 */
class PlayLetter implements Runnable {

    LetterAlphabet letterAlphabet;

    public PlayLetter(LetterAlphabet letter) {
        this.letterAlphabet = letter;
    }

    public void run() {
        letterAlphabet.playSound();
    }

}