package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import observer.Observable;
import observer.Observer;

public class DataGame implements Observable {

    private ArrayList<Observer> listObserver = new ArrayList<Observer>();
    private boolean trainingMode;
    String answer;
    int gameType;
    float score;
    
    public DataGame(){
        this.score=0;
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
        } else {
            gameType = 1;
        }
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
        this.notifyObserverScore(Float.toString(this.score));
    }

    public void incrementScore(float increment) {
        this.score = round(score + increment,2);
        this.notifyObserverScore(Float.toString(this.score));
    }

    public void decrementScore(float increment) {
        this.score = score - increment;
        this.notifyObserverScore(Float.toString(this.score));
    }
    
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

    public void notifyEndGame(String score, List<ScoreLine> scoreLines) {
        for (Observer obs : listObserver) {
            obs.updateEndGame(score, scoreLines);
        }
    }

    public void notifyCorrection(boolean rightOrFalse, char letter) {
        for (Observer obs : listObserver) {
            obs.updateCorrection(rightOrFalse, letter);
        }
    }
}
