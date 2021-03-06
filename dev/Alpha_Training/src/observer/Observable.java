package observer;

import java.util.List;
import model.ScoreLine;

public interface Observable {
	public void addObserver(Observer obs);
	public void removeObserver();
	public void notifyObserver(String str);
	public void notifyObserverScore(String str);
        public void notifyCorrection(boolean rightOrFalse, char letter);
        public void notifyCorrectionWord(boolean rightOrFalse, String word);
	public void notifyObserverTime(String str);
        public void notifyEndGame(ScoreLine currentScore, List<ScoreLine> scoreLines);
}
