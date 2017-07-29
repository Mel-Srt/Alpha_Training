/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import model.DataGame;
import model.ScoreFile;
import model.ScoreLine;
import model.Word;
import model.WordsLoader;

/**
 *
 * @author Mel-Srt
 */
public class WordsGame implements Game{

    private DataGame dataGame;
    private List<String> wordsList;
    private String selectedWord;
    private String previousWord;
    private Timer timer;
    private long startTime; //this variable serves to measure the time before the user gives the good answer
    private int secondsPassed = 60;
    private List<ScoreLine> scoreLines; //Will contain all the lines for the score printing at the end of the game
    private Thread threadSound; //We can stop this thread with the method interrupt();

    public WordsGame(DataGame dataGame) {
        wordsList = WordsLoader.getInstance().readFile();
        this.dataGame = dataGame;
    }

    //Initilize the game
    public void start() {
        dataGame.setScore(0);
        this.play();
    }
    
    //When the users clics on "return menu" (true) or the game ends (false)
    public void stop(boolean returnAction) {
        if(threadSound != null)threadSound.interrupt();
        selectedWord = null;
        if (!dataGame.isTrainingMode()) {
            if (!returnAction) { //The game end normally
                this.timer.cancel();
                System.out.println("SCORE : " + dataGame.getScore());
                String score_string = Float.toString(dataGame.getScore());
                ScoreLine currentScoreLine = ScoreFile.getInstance().addNewScore(dataGame.getGameType(), score_string, dataGame.getNickname());
                scoreLines = ScoreFile.getInstance().readFile(dataGame.getGameType());
                dataGame.notifyEndGame(currentScoreLine, scoreLines);
            } //The game is cancelled by the user
            else {
                timer.cancel();
                ScoreLine emptyScore = null;
                dataGame.notifyEndGame(emptyScore, scoreLines);
            }

        } else {
            ScoreLine emptyScore = null;
            dataGame.notifyEndGame(emptyScore, scoreLines);
        }
    }

    //Lauch the timer if needed and pick the first letter
    public void play() {
        //Set up the timer if needed
        if (!dataGame.isTrainingMode()) {
            timer = new Timer();
            secondsPassed = 65;
            TimerTask task = new TimerTask() {
                public void run() {
                    secondsPassed--;
                    //Before the game starts, countdown:					
                    if (secondsPassed > 63) {
                        dataGame.notifyObserverTime("Be ready !");
                    } else if (secondsPassed == 63) {
                        dataGame.notifyObserverTime(Integer.toString(3));
                    } else if (secondsPassed == 62) {
                        dataGame.notifyObserverTime(Integer.toString(2));
                    } else if (secondsPassed == 61) {
                        dataGame.notifyObserverTime(Integer.toString(1));
                    } else if (secondsPassed == 60) {
                        dataGame.notifyObserverTime("GO !");
                        pickNewWord();
                    } else if (secondsPassed == 0) {
                        dataGame.notifyObserverTime("STOP");
                        stop(false);
                    } else {
                        dataGame.notifyObserverTime(Integer.toString(secondsPassed));
                    }
                }
            };
            timer.scheduleAtFixedRate(task, 100, 1000); //After 100ms, the timer starts, it decreases every seconds
        } else {
            pickNewWord();
        }
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
        if (!dataGame.isTrainingMode()) {
            startTime = System.nanoTime();
        }

    }

    public boolean sendAnswer(String answer) {
        answer = answer.toLowerCase();
        System.out.println("Right answer : " + this.selectedWord);
        //Check if the answer is right or false
        if (selectedWord != null) {
            if (answer.equals(selectedWord)) {
                this.dataGame.notifyCorrectionWord(true, answer);
                if (!dataGame.isTrainingMode()) {
                    calculateScore();
                }
                pickNewWord();
                return true;
            } else if (!answer.equals(selectedWord)) {
                this.dataGame.notifyCorrectionWord(false, answer);
                if (!dataGame.isTrainingMode()) {
                    dataGame.decrementScore(1);
                }
                System.out.println("False");
                return false;
            }
        }
        
        return false;
    }

    public void playWord() {
        if (selectedWord != null) {
            if(threadSound != null)threadSound.interrupt();
            Word aWord = new Word(selectedWord);
            threadSound = new Thread(new PlayWord(aWord));
            threadSound.start();
        }
    }

    public void calculateScore() {
        long difference = System.nanoTime() - startTime;
        difference = TimeUnit.NANOSECONDS.toMillis(difference);
        float result = (float) difference / 1000;
        float score = 0.5f + (1 / result);
        float bonusWord = this.selectedWord.length() * 1.5f;
        score = score * 2 * bonusWord;
        dataGame.incrementScore(score);
    }
}
