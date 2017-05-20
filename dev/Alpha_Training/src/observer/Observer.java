package observer;

import java.util.List;
import model.ScoreLine;

public interface Observer {
	public void update(String string);
	public void updateTimer(String string);
	public void updateScore(String string);
        public void updateCorrection(boolean rightOrFalse, char letter);
        public void updateEndGame (String score, List<ScoreLine> scoreLines);
}
