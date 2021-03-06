package observer;

import java.util.List;
import model.ScoreLine;

public interface Observer {
	public void update(String string);
	public void updateTimer(String string);
	public void updateScore(String string);
        public void updateCorrection(boolean rightOrFalse, char letter);
        public void updateCorrectionWord(boolean rightOrFalse, String word);
        public void updateEndGame (ScoreLine currentScore, List<ScoreLine> scoreLines);
}
