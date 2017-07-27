/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import java.util.Random;
import model.Word;
import model.WordsLoader;

/**
 *
 * @author Mel-Srt
 */
public class WordsGame {

    List<String> wordsList;
    String selectedWord;
    String previousWord;

    public WordsGame() {
        wordsList = WordsLoader.getInstance().readFile();
        pickNewWord();
    }

    public void pickNewWord() {
        if (selectedWord == null) {
            int nbWords = wordsList.size();
            // Choose a letter among all the loaded letters
            Random rand = new Random();
            int pickedNumber = rand.nextInt(nbWords);
            selectedWord = wordsList.get(pickedNumber);
        } else {
            while (previousWord == selectedWord) {
                int nbWords = wordsList.size();
                // Choose a letter among all the loaded letters
                Random rand = new Random();
                int pickedNumber = rand.nextInt(nbWords);
                selectedWord = wordsList.get(pickedNumber);
            }
        }
        previousWord = selectedWord;
        playWord();

    }

    public void sendAnswer(String answer) {
        if (answer.equals(selectedWord)) {
            System.out.println("right");
            pickNewWord();
        } else {
            System.out.println("false");
        }
    }

    public void playWord() {
        if (selectedWord != null) {
            Word aWord = new Word(selectedWord);
            Thread threadSound = new Thread(new PlayWord(aWord));
            threadSound.start();
        }
    }
}
