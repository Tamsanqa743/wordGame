//package skeletonCodeAssgnmt2;

public class WordRecord {
	private String text;
	private  int x;
	private int y;
	private int maxY;
	private boolean dropped;
	
	private int fallingSpeed;
	private static int maxWait=1500;
	private static int minWait=100;

	public static WordDictionary dict;
	
  /**
   *No arguments constructor
   */
	
	WordRecord() {
		text="";
		x=0;
		y=0;	
		maxY=300;
		dropped=false;
		fallingSpeed=(int)(Math.random() * (maxWait-minWait)+minWait); 
	}
	/**
    *Create word record object with given string
    *@param text Text for creating wordRecoed object
    */
	WordRecord(String text) {
		this();
		this.text=text;
	}
	/**
    *Create word record object with given string
    *@param text Text for creating wordRecoed object
    *@param x max value for x
    *@param y Max value for y word
    */
	WordRecord(String text,int x, int maxY) {
		this(text);
		this.x=x;
		this.maxY=maxY;
	}
	
// all getters and setters must be synchronized
  /**
   *set Y value
   *@param y value you want to set y to
   */
	public synchronized  void setY(int y) {
		if (y>maxY) {
			y=maxY;
			dropped=true;
		}
		this.y=y;
	}
	 /**
   *set x value
   *@param x value you want to set x to
   */
	public synchronized  void setX(int x) {
		this.x=x;
	}
	
   /**
    *set word to given string
    *@param text text to set word to
    */
	public synchronized  void setWord(String text) {
		this.text=text;
	}
   /** 
    *get word from wordrecord
    *@return Word contained in wordrecord object
    */
	public synchronized  String getWord() {
		return text;
	}
	/**
    *get x value for word
    *@return x value for word
    */
	public synchronized  int getX() {
		return x;
	}	
	
	/**
    *get y value for word
    *@return y value for word
    */
	public synchronized  int getY() {
		return y;
	}
	
   /**
    *get speed of word
    *@return falling speed of word
    */
	public synchronized  int getSpeed() {
		return fallingSpeed;
	}
   /**
    *set position of word
    *@param x x position of word on screen
    *@param y y position of word on screen
    */
	public synchronized void setPos(int x, int y) {
		setY(y);
		setX(x);
	}
   /**
    *reset position of word
    */
	public synchronized void resetPos() {
		setY(0);
	}
   /**
    *reset word to starting position
    *reset word falling speed
    */
	public synchronized void resetWord() {
		resetPos();
		text=dict.getNewWord();
		dropped=false;
		fallingSpeed=(int)(Math.random() * (maxWait-minWait)+minWait); 
		//System.out.println(getWord() + " falling speed = " + getSpeed());

	}
	
   /**
    *check if this word matches typedText
    *@param typedText text to be checked
    *@return a boolean for words equality
    */
	public synchronized boolean matchWord(String typedText) {
		//System.out.println("Matching against: "+text);
		if (typedText.equals(this.text)) {
			resetWord();
			return true;
		}
		else
			return false;
	}
	
   /**
    *drop word
    *@param inc number to increment position of word
    */

	public synchronized  void drop(int inc) {
		setY(y+inc);
	}
	
   /**
    *check if word is dropped
    *@return boolean on word is dropped or not
    */
	public synchronized  boolean dropped() {
		return dropped;
	}

}
