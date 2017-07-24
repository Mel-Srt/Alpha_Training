package controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import model.DataGame;
import model.LetterAlphabet;
import model.ScoreFile;
import model.ScoreLine;
import sound.SoundGame;

import view.MainFrame;

public class AlphabetGame {

    List<LetterAlphabet> letters;
    DataGame dataGame;
    MainFrame frame;
    Timer timer;
    int secondsPassed = 30;
    long startTime; //this variable serves to measure the time before the user gives the good answer

    LetterAlphabet selectedLetter; // The current Letter which the user has to find
    LetterAlphabet previousLetter; //To avoid the same letter twice in a row

    List<ScoreLine> scoreLines; //Will contain all the lines for the score printing at the end of the game

    public AlphabetGame(DataGame dataGame) {
        this.letters = new ArrayList<LetterAlphabet>();
        this.dataGame = dataGame;
    }

    public void createFrame() {
        frame = new MainFrame(this);
    }

    //Initilize the game
    public void start() {
        dataGame.setScore(0);
        dataGame.addObserver(frame);
        letters = new ArrayList<LetterAlphabet>();
        int gameType = dataGame.getGameType();
        switch (gameType) {
            case 1:
                initStandardMode();
                break;
            case 2:
                initOnlyVowels();
                break;
            case 3:
                initOnlyConsonants();
                break;
            default:
                break;
        }
        this.play();
    }

    //When the users clics on "return menu" (true) or the game ends (false)
    public void stop(boolean returnAction) {
        selectedLetter = null;
        if (!dataGame.isTrainingMode()) {

            if (!returnAction) { //The game end normally
                timer.cancel();
                System.out.println("SCORE : " + dataGame.getScore());
                String score_string = Float.toString(dataGame.getScore());
                ScoreLine currentScoreLine = ScoreFile.getInstance().addNewScore(dataGame.getGameType(), score_string, dataGame.getPseudo());
                scoreLines = ScoreFile.getInstance().readFile(dataGame.getGameType());
                dataGame.notifyEndGame(currentScoreLine, scoreLines);
            }
            //The game is cancelled by the user
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

    // Mode: 1
    public void initStandardMode() {
        letters.add(new LetterAlphabet('A'));
        letters.add(new LetterAlphabet('B'));
        letters.add(new LetterAlphabet('C'));
        letters.add(new LetterAlphabet('D'));
        letters.add(new LetterAlphabet('E'));
        letters.add(new LetterAlphabet('F'));
        letters.add(new LetterAlphabet('G'));
        letters.add(new LetterAlphabet('H'));
        letters.add(new LetterAlphabet('I'));
        letters.add(new LetterAlphabet('J'));
        letters.add(new LetterAlphabet('K'));
        letters.add(new LetterAlphabet('L'));
        letters.add(new LetterAlphabet('M'));
        letters.add(new LetterAlphabet('N'));
        letters.add(new LetterAlphabet('O'));
        letters.add(new LetterAlphabet('P'));
        letters.add(new LetterAlphabet('Q'));
        letters.add(new LetterAlphabet('R'));
        letters.add(new LetterAlphabet('S'));
        letters.add(new LetterAlphabet('T'));
        letters.add(new LetterAlphabet('U'));
        letters.add(new LetterAlphabet('V'));
        letters.add(new LetterAlphabet('W'));
        letters.add(new LetterAlphabet('X'));
        letters.add(new LetterAlphabet('Y'));
        letters.add(new LetterAlphabet('Z'));
    }

    // Mode: 2
    public void initOnlyVowels() {
        letters.add(new LetterAlphabet('A'));
        letters.add(new LetterAlphabet('E'));
        letters.add(new LetterAlphabet('I'));
        letters.add(new LetterAlphabet('O'));
        letters.add(new LetterAlphabet('U'));
        letters.add(new LetterAlphabet('Y'));
    }

    // Mode: 3
    public void initOnlyConsonants() {
        letters.add(new LetterAlphabet('B'));
        letters.add(new LetterAlphabet('C'));
        letters.add(new LetterAlphabet('D'));
        letters.add(new LetterAlphabet('F'));
        letters.add(new LetterAlphabet('G'));
        letters.add(new LetterAlphabet('H'));
        letters.add(new LetterAlphabet('J'));
        letters.add(new LetterAlphabet('K'));
        letters.add(new LetterAlphabet('L'));
        letters.add(new LetterAlphabet('M'));
        letters.add(new LetterAlphabet('N'));
        letters.add(new LetterAlphabet('P'));
        letters.add(new LetterAlphabet('Q'));
        letters.add(new LetterAlphabet('R'));
        letters.add(new LetterAlphabet('S'));
        letters.add(new LetterAlphabet('T'));
        letters.add(new LetterAlphabet('V'));
        letters.add(new LetterAlphabet('W'));
        letters.add(new LetterAlphabet('X'));
        letters.add(new LetterAlphabet('Z'));
    }

    public LetterAlphabet randomLetter() {
        int nbLetters = letters.size();

        // Choose a letter among all the loaded letters
        Random rand = new Random();
        int pickedNumber = rand.nextInt(nbLetters);

        // Get the letter associated with the number (A=0,B=1,C=2,etc...)
        LetterAlphabet selectedLetter = letters.get(pickedNumber);
        return selectedLetter;
    }

    //Lauch the timer if needed and pick the first letter
    public void play() {

        //Set up the timer if needed
        if (!dataGame.isTrainingMode()) {
            timer = new Timer();
            secondsPassed = 35;
            TimerTask task = new TimerTask() {

                public void run() {

                    secondsPassed--;

                    //Before the game starts, countdown:					
                    if (secondsPassed > 33) {
                        dataGame.notifyObserverTime("Be ready !");
                    } else if (secondsPassed == 33) {
                        dataGame.notifyObserverTime(Integer.toString(3));
                    } else if (secondsPassed == 32) {
                        dataGame.notifyObserverTime(Integer.toString(2));
                    } else if (secondsPassed == 31) {
                        dataGame.notifyObserverTime(Integer.toString(1));
                    } else if (secondsPassed == 30) {
                        dataGame.notifyObserverTime("GO !");
                        pickNewLetter();
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
            pickNewLetter();
        }
    }

    public void pickNewLetter() {
        if (selectedLetter == null) {
            selectedLetter = this.randomLetter();
        } else {
            while (previousLetter == selectedLetter) {
                selectedLetter = this.randomLetter();
            }
        }
        previousLetter = selectedLetter;
        Thread threadSound = new Thread(new PlayLetter(selectedLetter));
        threadSound.start();

        //If we measure the score, we will compare the startTime and the AnswerTime
        if (!dataGame.isTrainingMode()) {
            startTime = System.nanoTime();

        }
    }

    public void sendAnswer(char answer) {

        answer = Character.toUpperCase(answer);

        //Check if the answer is right or false
        if (selectedLetter != null) {
            if (answer == selectedLetter.getName()) {
                this.dataGame.notifyCorrection(true, answer);
                if (!dataGame.isTrainingMode()) {
                    calculateScore();
                }
                Thread rightSound = new Thread(new SoundGame(true));
                rightSound.start();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                pickNewLetter();

            } else if (answer != selectedLetter.getName()) {
                this.dataGame.notifyCorrection(false, answer);
                Thread threadSound = new Thread(new PlayLetter(new LetterAlphabet(answer)));
                if (!dataGame.isTrainingMode()) {
                    dataGame.decrementScore(1);
                }
                threadSound.start();
                System.out.println("False");
            }
        }

    }

    public void playLetter() {
        if (selectedLetter != null) {
            Thread threadSound = new Thread(new PlayLetter(selectedLetter));
            threadSound.start();
        }
    }

    public DataGame getDataGame() {
        return dataGame;
    }

    public void setDataGame(DataGame dataGame) {
        this.dataGame = dataGame;
    }

    public void calculateScore() {
        long difference = System.nanoTime() - startTime;
        difference = TimeUnit.NANOSECONDS.toMillis(difference);
        float result = (float) difference / 1000;

        float score = 0.5f + (1 / result);
        score = score * 2;
        dataGame.incrementScore(score);
    }
}

class PlayLetter implements Runnable {

    LetterAlphabet letterAlphabet;

    public PlayLetter(LetterAlphabet letter) {
        this.letterAlphabet = letter;
    }

    public void run() {
        letterAlphabet.playSound();
    }

}
