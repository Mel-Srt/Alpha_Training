
import java.io.File;
import java.util.Scanner;

import javax.swing.JFrame;

import model.DataGame;
import controller.AlphabetGame;
import model.ScoreFile;
import view.MainFrame;

public class Main {

    public static void main(String[] args) {

        String pseudo = "Melanie";
        
        //Model:
        DataGame dataGame = new DataGame(pseudo);

        //Controller:
        AlphabetGame alphabetGame = new AlphabetGame(dataGame);

        //View:		
        alphabetGame.createFrame();
    }

}
