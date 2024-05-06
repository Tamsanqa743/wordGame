

public class Score {
	private int missedWords;
	private int caughtWords;
	private int gameScore;
	
   /**
    *No arguments constructor
    */
	Score() {
		missedWords=0;
		caughtWords=0;
		gameScore=0;
	}
		
	// all getters and setters must be synchronized
	/**
    *get number of missed words
    *@return returns number of missed words
    */
	public synchronized int getMissed() {
		return missedWords;
	}
   
    /**
    *get number of caught words
    *@return returns number of caught words
    */
	public synchronized int getCaught() {
		return caughtWords;
	}
	 /**
    *get number of total of words
    *@return returns number of all words
    */
	public synchronized int getTotal() {
		return (missedWords+caughtWords);
	}
  	/**
    *get total score 
    *@return returns total score
    */
	public synchronized int getScore() {
		return gameScore;
	}
	/**
    *record missed Words
    */
	public synchronized void missedWord() {
		missedWords++;
	}
   /**
    *record caught word and increment score by word length
    *@param length length of caught word
    */
	public synchronized void caughtWord(int length) {
		caughtWords++;
		gameScore+=length;
	}
   /**
    *Reset score
    */
	public synchronized void resetScore() {
		caughtWords=0;
		missedWords=0;
		gameScore=0;
	}
}
