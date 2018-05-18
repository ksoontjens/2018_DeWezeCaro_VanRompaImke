package hellotvxlet;

import java.util.Random;
import javax.microedition.xlet.XletContext;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.tv.xlet.*;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HStaticText;
import org.havi.ui.HTextButton;
import org.havi.ui.HVisible;
import org.havi.ui.event.HActionListener;

public class hellotvxlet implements Xlet // Constructor
{
    Random r;
    GetData get; // Class to get user input
    String[] words = {"banana", "apple", "pear", "melon"};
    String word; // Hold on to the chosen word
    boolean finished = false; // When it's true the loop will end
    int badGuessCount=0; // Counts number of guesses
    boolean [] foundLetters; // Marks letters that are found
    String entryWord =" "; // User's guesses
    private boolean Goodguess;
    
    public hellotvxlet()
    {
        r = new Random(); // Makes Random object
        get = new GetData(); // Makes GetData object
        playGame(); // Calls playGame
    }
    
    public void playGame() 
    {
        word = words[r.nextInt(words.length)]; // Choose random word form list
        foundLetters = new boolean[word.length()]; // Boolean = size of word
        
        while (!finished)
        {
            showGallows(); // Printes the gallows and person
            showWord(); // Prints the dashes that represents the words
            getGuess(); // Gets the guess
            checkGuess(); // Check to see if the guess is true
            if (badGuessCount==6) // Happens when they didn't guess the word
            {
                System.out.print('\u000C');
                showGallows();
                System.out.println("You lost! The word was: "+word);
                finished=true; // Game ends
            }
        }
    }
    
    public void showGallows() {
        System.out.print('\u000C');
        if (badGuessCount==0)
        {
            man_0();
        }
        if (badGuessCount==1)
        {
            man_1();
        }
        if (badGuessCount==2)
        {
            man_2();
        }
        if (badGuessCount==3)
        {
            man_3();
        }
        if (badGuessCount==4)
        {
            man_4();
        }
        if (badGuessCount==5)
        {
            man_5();
        }
        if (badGuessCount==6)
        {
            man_6();
        }
    }
    
    public boolean showWord() {
        boolean goodGuess = false; // Guess = false
        char ch = entryWord.charAt(0); // ch = space, 0 position
        for (int lc=0; lc < word.length(); lc++) // Loops trough all the letters of the word
            if (foundLetters[lc]==true) // Prints letters if it's correct
            {
                System.out.print(word.charAt(lc)+" "); // lc = place of the true letter
            }
            else if (word.charAt(lc)==ch)
            {
                System.out.print(word.charAt(lc)+" ");
                foundLetters[lc] = true;
                goodGuess = true;
            }
            else
            {
                System.out.print("_ "); // Prints _
            }
        return Goodguess;
    }
    
    public void getGuess() {
        System.out.println("\n\n\nGive a letter.");
        System.out.println("If you want to guess the whole word, just type it.");
        System.out.println("You have "+ (6 - badGuessCount) + " guesses left.");
        System.out.println("Enter guess");
        entryWord = get.aWord();
    }

    public void checkGuess() {
        boolean goodGuess;
        if (entryWord.length()>1)
        {
            if (entryWord.equals(word))
            {
                System.out.println("\n\nYou won!");
                finished = true;
                System.out.println("Close and run, if you want to play again");
                String pause = get.aWord();
            }
        }
        else
        {
            showGallows();
            goodGuess = showWord();
            if (goodGuess)
            {
                System.out.println("\n\nGood guess");
                System.out.println("Press enter to continue");
                String pause = get.aWord();
            }
        }
    }
    
    public void completedMan()
    {
        System.out.println("_____");
        System.out.println("|   |");
        System.out.println("|   o");
        System.out.println("|  /|\\"); // Double \\ because then \ is used as \ and not as a special character
        System.out.println("|  / \\");
    }
    
    public void man_0()
    {
        System.out.println("_____");
        System.out.println("|   |");
        System.out.println("|   ");
        System.out.println("|   ");
        System.out.println("|   ");
    }
    
    public void man_1()
    {
        System.out.println("_____");
        System.out.println("|   |");
        System.out.println("|   o");
        System.out.println("|   ");
        System.out.println("|   ");
    }
    
    public void man_2()
    {
        System.out.println("_____");
        System.out.println("|   |");
        System.out.println("|   o");
        System.out.println("|   | ");
        System.out.println("|   ");
    }
    
    public void man_3()
    {
        System.out.println("_____");
        System.out.println("|   |");
        System.out.println("|   o");
        System.out.println("|  /| ");
        System.out.println("|   ");
    }
    
    public void man_4()
    {
        System.out.println("_____");
        System.out.println("|   |");
        System.out.println("|   o");
        System.out.println("|  /|\\");
        System.out.println("|   ");
    }
    
    public void man_5()
    {
        System.out.println("_____");
        System.out.println("|   |");
        System.out.println("|   o");
        System.out.println("|  /|\\");
        System.out.println("|  /");
    }
    
    public void man_6()
    {
        System.out.println("_____");
        System.out.println("|   |");
        System.out.println("|   o");
        System.out.println("|  /|\\");
        System.out.println("|  / \\");
    }
    
    public void startXlet() {
    
    }

    public void pauseXlet() {
     
    }

    public void destroyXlet(boolean unconditional) {
     
    }

    public void initXlet(XletContext ctx) throws XletStateChangeException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}