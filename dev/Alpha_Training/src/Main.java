import model.DataGame;
import view.MainFrame;
public class Main {
    public static void main(String[] args) {
        DataGame dataGame = new DataGame();
        MainFrame frame = new MainFrame(dataGame);   
    }
}
