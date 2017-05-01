
import java.io.File;
import java.util.Scanner;

import javax.swing.JFrame;

import model.DataGame;
import controller.AlphabetGame;
import view.MainFrame;

public class Main {

    public static void main(String[] args) {
        //test
        //Model:
        DataGame dataGame = new DataGame();

        //Controller:
        AlphabetGame alphabetGame = new AlphabetGame(dataGame);

        //View:		
        alphabetGame.createFrame();
        //MainFrame frame = new MainFrame(alphabetGame);
        //dataGame.addObserver(frame);
    }

}
