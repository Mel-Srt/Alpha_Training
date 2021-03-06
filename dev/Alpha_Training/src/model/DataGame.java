package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import observer.Observable;
import observer.Observer;

public class DataGame implements Observable {

    private ArrayList<Observer> listObserver = new ArrayList<Observer>();
    private boolean trainingMode;
    private String nickname;
    private String answer;
    private float score;
    
    //Variables for the Alphabet Game
    private int gameType; // Alphabet, Vowels or Consonnants
    
    //Variables for the word Game :
    private int wordLengthMax;
    private int spellingSpeed;
    private final int SPELLING_SPEED_SLOW = 1000; //Pause between each letters of a word
    private final int SPELLING_SPEED_MEDIUM = 750;
    private final int SPELLING_SPEED_FAST = 500;

    public DataGame() {
        this.score = 0;
        this.nickname = "Unknown";
    }

    public int getGameType() {
        return gameType;
    }

    public void setGameType(String type) {
        String str = type;
        System.out.println("GameType = " + type);

        if (type.equals("Standard A-Z")) {
            gameType = 1;
        } else if (type.equals("Vowels")) {
            gameType = 2;
        } else if (type.equals("Consonnants")) {
            gameType = 3;
        } else if (type.equals("Words")) {
            gameType = 4;
        } else {
            gameType = 1;
        }
    }

    /**
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname the nickname to set
     */
    public void setNickname(String nickname) {
        if (nickname.equals("")) {
            this.nickname = "Unknown";
        } else {
            this.nickname = nickname.replace(';', ',');
        }
        System.out.println(this.nickname);
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
        this.notifyObserverScore(Float.toString(this.score));
    }

    public void incrementScore(float increment) {
        this.score = round(score + increment, 2);
        this.notifyObserverScore(Float.toString(this.score));
    }

    public void decrementScore(float increment) {
        this.score = round(score - increment, 2);
        this.notifyObserverScore(Float.toString(this.score));
    }

    //d = number to round, decimalPlace = number of character after the coma
    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isTrainingMode() {
        return trainingMode;
    }

    public void setTrainingMode(boolean trainingMode) {
        this.trainingMode = trainingMode;
    }
    
    
    /**
     * @return the wordLengthMax
     */
    public int getWordLengthMax() {
        return wordLengthMax;
    }

    /**
     * @param wordLengthMax the wordLengthMax to set
     */
    public void setWordLengthMax(int wordLengthMax) {
        this.wordLengthMax = wordLengthMax;
    }

    public void addObserver(Observer obs) {
        this.listObserver.add(obs);
    }

    public void removeObserver() {
        listObserver = new ArrayList<Observer>();
    }

    public void notifyObserver(String str) {
        for (Observer obs : listObserver) {
            obs.update(str);
        }
    }

    public void notifyObserverTime(String seconds) {
        for (Observer obs : listObserver) {
            obs.updateTimer(seconds);
        }
    }

    @Override
    public void notifyObserverScore(String str) {
        for (Observer obs : listObserver) {
            obs.updateScore(str);
        }
    }

    public void notifyEndGame(ScoreLine currentScore, List<ScoreLine> scoreLines) {
        for (Observer obs : listObserver) {
            obs.updateEndGame(currentScore, scoreLines);
        }
    }

    public void notifyCorrection(boolean rightOrFalse, char letter) {
        for (Observer obs : listObserver) {
            obs.updateCorrection(rightOrFalse, letter);
        }
    }

    @Override
    public void notifyCorrectionWord(boolean rightOrFalse, String word) {
        for (Observer obs : listObserver) {
            obs.updateCorrectionWord(rightOrFalse, word);
        }
    }

    /**
     * @return the spellingSpeed
     */
    public int getSpellingSpeed() {
        return spellingSpeed;
    }

    /**
     * @param spellingSpeed the spellingSpeed to set
     */
    public void setSpellingSpeed(String difficulty) {
        if(difficulty == "slow") this.spellingSpeed = SPELLING_SPEED_SLOW;
        else if(difficulty == "fast") this.spellingSpeed = SPELLING_SPEED_FAST;
        else this.spellingSpeed = SPELLING_SPEED_MEDIUM;
    }
}
