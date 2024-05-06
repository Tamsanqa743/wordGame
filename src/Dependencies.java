/**
 *Class that contains score, typed text and total words in game
 *breaks cyclic dependency between WordApp and WordPanel classes
 *@author Tamsanqa Thwala
 *@version 9/09/2021
 */
public class Dependencies{
    /**
     *static variables
     */

    static Score score = new Score();
    static String typedText = "";
    static int wordsToFall;
    static volatile boolean done;
}