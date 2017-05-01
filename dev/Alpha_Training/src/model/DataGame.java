package model;

import java.util.ArrayList;

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
        this.score = score + increment;
        this.notifyObserverScore(Float.toString(this.score));
    }

    public void decrementScore(float increment) {
        this.score = score - increment;
        this.notifyObserverScore(Float.toString(this.score));
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

    @Override
    public void notifyEndGame(String str) {
        for (Observer obs : listObserver) {
            obs.updateEndGame(str);
        }
    }
}
