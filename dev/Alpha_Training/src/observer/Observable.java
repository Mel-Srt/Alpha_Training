package observer;

public interface Observable {
	public void addObserver(Observer obs);
	public void removeObserver();
	public void notifyObserver(String str);
	public void notifyObserverScore(String str);
	public void notifyObserverTime(String str);
        public void notifyEndGame(String str);
}
