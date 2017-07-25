
import model.DataGame;
import controller.AlphabetGame;

public class Main {

    public static void main(String[] args) {
        
        //Model:
        DataGame dataGame = new DataGame();

        //Controller:
        AlphabetGame alphabetGame = new AlphabetGame(dataGame);

        //View:		
        alphabetGame.createFrame();
    }

}
