/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Mel-Srt
 */
public interface Game {
    
    public void start();
    public void stop(boolean returnAction); //When the users clics on "return menu" (true) or the game ends (false)
    public void play();
    public void calculateScore();
    
}
