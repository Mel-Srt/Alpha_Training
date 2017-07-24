
import model.DataGame;
import controller.AlphabetGame;

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
