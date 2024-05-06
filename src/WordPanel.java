//package skeletonCodeAssgnmt2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JButton;
import javax.swing.JPanel;

public class WordPanel extends JPanel implements Runnable {
      
		public static volatile boolean done;
		private WordRecord[] words;
		private int noWords;
		private int maxY;
      
      private volatile int iVolatile = 0;
      private int index = 0;
      AtomicInteger missed = new AtomicInteger(0);
      static AtomicBoolean fell = new AtomicBoolean(false);
      static volatile boolean end;
      static WordPanel w;

		
		public void paintComponent(Graphics g) {
		    int width = getWidth();
		    int height = getHeight();
		    g.clearRect(0,0,width,height);
		    g.setColor(Color.red);
		    g.fillRect(0,maxY-10,width,height);

		    g.setColor(Color.black);
		    g.setFont(new Font("Helvetica", Font.PLAIN, 26));
		   //draw the words
		   //animation must be added
		    for (int i=0;i<noWords;i++){	    	
		    	//g.drawString(words[i].getWord(),words[i].getX(),words[i].getY());	
		    	g.drawString(words[i].getWord(),words[i].getX(),words[i].getY()+20);  //y-offset for skeleton so that you can see the words
            
          }
		   
		  }
		
		WordPanel(WordRecord[] words, int maxY) {
			this.words=words; //will this work?
			noWords = words.length;
			done=false;
			this.maxY=maxY;		
		}
      

		public void run(){
          while (index < Dependencies.wordsToFall){
              
              Thread animation = new Thread(new Animate(index, words));
              animation.start();
              repaint();
              
              
              
              
              if (index < Dependencies.wordsToFall){
                  iVolatile++;
                  index = iVolatile;
              }
             
          }
      }
     
      
      /**
       *inner class to drop words
       */
      
      private class Animate implements Runnable {
          
          private WordRecord [] wordArr;
          private int wordCounter;
          
          private Score score = new Score();
      
         Animate(int wordCounter, WordRecord[] wordArr){
             this.wordArr = wordArr;
             this.wordCounter = wordCounter;
      }
      
      /**
       *overriding run method
       */
       
       public void run(){
           while(wordCounter != Dependencies.wordsToFall){//!WordApp.done ){
               if(!wordArr[wordCounter].dropped()){
                   wordArr[wordCounter].drop(10);
                   try{
                       synchronized(this){
                          Thread.sleep(words[wordCounter].getSpeed());
                          repaint();
                       }
                     
                  }
                  catch(InterruptedException e){
                     e.printStackTrace();
                }
                //when word reaches red line, reset it
                if (wordArr[wordCounter].dropped()){
                    wordArr[wordCounter].resetWord();
                    Dependencies.score.missedWord();

                }
                
                //if word is caught increment the number caught and score
                if(wordArr[wordCounter].matchWord(Dependencies.typedText)){
                    int length = Dependencies.typedText.length();
                    Dependencies.score.caughtWord(length);
                    
                }
                
                //if end button has been pressed, reset position of words
                if(end){
                    Thread.interrupted();
                    
                    wordArr[wordCounter].resetPos();
                    
                }
              }
          }

       }
    }
}