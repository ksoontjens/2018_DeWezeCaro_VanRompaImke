package hellotvxlet;

import java.awt.event.KeyEvent;
import javax.tv.xlet.*;
import org.havi.ui.event.*;
import org.dvb.event.*;
import org.dvb.ui.*;
import org.havi.ui.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;


public class HelloTVXlet implements Xlet, /*ResourceClient, *//*HBackgroundImageListener, */UserEventListener {
// game logic
    Random r;
    Random rndLetters;
    String xLetter;
    GetData get; // Class to get user input
    
    String[] words = {"banana", "apple", "pear", "melon"};
    String[] alfabet ={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};// hold letters to generate random letters
    
    String word; // Hold on to the chosen word
    String knopNaam ;
    boolean finished = false; // When it's true the loop will end
    int badGuessCount=0; // Counts number of guesses
    boolean [] foundLetters; // Marks letters that are found
    String entryWord =" "; // User's guesses
    
    private boolean goodGuess;
    private String[] letters =word.split("");
    boolean result;
    
    private HScene scene;
    private boolean debug  = true;
    private XletContext actueleXletContext;
    
    private String letterButtons[];
    private String buttonNaam;
    private HTextButton button ;
  
  
    public HelloTVXlet() {
       
        //constructor moet leeg blijven
    }

    public void initXlet(XletContext context) 
    {
        if(debug) System.out.println("initiate Xlet");
        this.actueleXletContext = context;
        
       //template maken
         HSceneTemplate sceneTemplate = new HSceneTemplate();
         
       //grootte en posite aangeven
         sceneTemplate.setPreference(HSceneTemplate.SCENE_SCREEN_DIMENSION, new HScreenDimension(1.0f, 1.0f), HSceneTemplate.REQUIRED );
         sceneTemplate.setPreference(HSceneTemplate.SCENE_SCREEN_LOCATION, new HScreenPoint(0.0f ,0.0f), HSceneTemplate.REQUIRED);
         
       //instantie van de Scene aanvragen
         scene = HSceneFactory.getInstance().getBestScene(sceneTemplate);
         
        r = new Random(); // Makes Random object
        rndLetters = new Random();
        get = new GetData(); // Makes GetData object
         
     //test to see if button appears in scene
      button = new HTextButton("a");
     //button.setName(buttonNaam);
     button.setLocation(0,0);
     button.setSize(50, 50);
     button.setBackground(new DVBColor(0,0,0,179));
     button.setBackgroundMode(HVisible.BACKGROUND_FILL);
     
     scene.add(button);
      
      // logica voor letter buttons te genereren 
      
         /*  for (int i=0; i<word.length(); i++){
             letterButtons[i] = letters[i];
         }
      
    int counter=word.length()-1;
      
      while(letterButtons.length<(letters.length+10)){
          
             do{
                    xLetter = alfabet[rndLetters.nextInt(alfabet.length)];
          
                }
                while(word.indexOf(xLetter) >=0 );
          
  
           letterButtons[counter]= (xLetter);  
           counter++;
         
    }
      
      shuffleArray(letterButtons);
      
       for(int i=0; i<letterButtons.length ; i++){
            
      buttonNaam = "knop"+i;
      button = new HTextButton(letterButtons[i]);
     
     button.setName(buttonNaam);
     button.setLocation(i*25+50,i*25+50);
     button.setSize(50, 50);
     button.setBackground(new DVBColor(0,0,0,179));
     button.setBackgroundMode(HVisible.BACKGROUND_FILL);
     
     scene.add(button);
     
        }
 
      buttonNaam = "knop"+1;
      button = new HTextButton(letterButtons[1]);
     
     //button.setName(buttonNaam);
     button.setLocation(1*25+50,1*25+50);
     button.setSize(50, 50);
     button.setBackground(new DVBColor(0,0,0,179));
     button.setBackgroundMode(HVisible.BACKGROUND_FILL);
     
     scene.add(button);
      */
      
      
      
    }
    

    public void startXlet() throws XletStateChangeException
    {
        if(debug) System.out.println("start Xlet");
       // agrondimg.load(this); //load image
       // EventManager manager = EventManager.getInstance(); //call on EventManager
       // UserEventRepository repository = new UserEventRepository("Voorbeeld");//repository
        
        //add events
        //repository.addKey( org.havi.ui.event.HRcEvent.VK_LEFT);
        //repository.addKey( org.havi.ui.event.HRcEvent.VK_RIGHT);
        
        //make known with EventManager
        //manager.addUserEventListener(this,repository); 
        
        scene.validate();
        scene.setVisible(true);
        
     //   playGame(); // Calls playGame
        
      
    
    }

    public void pauseXlet() 
    {
        System.out.println("pauseXlet");
     
    }

    public void destroyXlet(boolean unconditional) throws XletStateChangeException
    {
        System.out.println("destroyXlet");
   //  agrondimg.flush(); //destroy image
     
    }
    
    
     public void userEventReceived(org.dvb.event.UserEvent e) {
         // catching Key Events
         
         if(e.getType() == KeyEvent.KEY_PRESSED){
             System.out.println("Pushed Button");
             switch (e.getCode()){
                 case HRcEvent.VK_LEFT:
                     System.out.println("VK_LEFT is PRESSED");
                  case HRcEvent.VK_RIGHT:
                     System.out.println("VK_RIGHT is PRESSED");
                  break;
             }
             }
             
         }
        
    


    private void checkGuess() {
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

    private void getGuess() {
        System.out.println("\n\n\nGive a letter.");
        System.out.println("If you want to guess the whole word, just type it.");
        System.out.println("You have "+ (6 - badGuessCount) + " guesses left.");
        System.out.println("Enter guess");
        entryWord = get.aWord();
    }


    private void playGame() {
        word = words[r.nextInt(words.length)];  // Choose random word form list
        foundLetters = new boolean[word.length()]; // Boolean = size of word
        
        while (!finished)
        {
            showGallows(); // Printes the gallows and person
            showWord(); // Prints the dashes that represents the words
            getGuess(); // Gets the guess
            checkGuess(); // Check to see if the guess is true
            if (badGuessCount==6) // Happens when they didn't guess the word
            {
                System.out.print('\u000C'); //clears the screen
                showGallows();
                System.out.println("You lost! The word was: "+word);
                finished=true; // Game ends
            }
        }
    }

    private void showGallows() {
             System.out.print('\u000C'); // clears the screen
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
        return goodGuess;
    }
    
    private void man_0() {
        System.out.println("_____");
        System.out.println("|   |");
        System.out.println("|   ");
        System.out.println("|   ");
        System.out.println("|   ");
    }

    private void man_1() {
        System.out.println("_____");
        System.out.println("|   |");
        System.out.println("|   o");
        System.out.println("|   ");
        System.out.println("|   ");
    }

    private void man_2() {
        System.out.println("_____");
        System.out.println("|   |");
        System.out.println("|   o");
        System.out.println("|   | ");
        System.out.println("|   ");
    }

    private void man_3() {
        System.out.println("_____");
        System.out.println("|   |");
        System.out.println("|   o");
        System.out.println("|  /| ");
        System.out.println("|   ");
    }

    private void man_4() {
        System.out.println("_____");
        System.out.println("|   |");
        System.out.println("|   o");
        System.out.println("|  /|\\");
        System.out.println("|   ");
    }

    private void man_5() {
        System.out.println("_____");
        System.out.println("|   |");
        System.out.println("|   o");
        System.out.println("|  /|\\");
        System.out.println("|  /");
    }

    private void man_6() {
        System.out.println("_____");
        System.out.println("|   |");
        System.out.println("|   o");
        System.out.println("|  /|\\");
        System.out.println("|  / \\");
    }
    
        public void completedMan()
    {
        System.out.println("_____");
        System.out.println("|   |");
        System.out.println("|   o");
        System.out.println("|  /|\\"); // Double \\ because then \ is used as \ and not as a special character
        System.out.println("|  / \\");
    }
        //poging om de letter buttons op een andere manier op scherm te krijgen 
  /*  public void makeButton ( int index, String letter){
    
      String buttonNaam = "knop"+index;
     HTextButton button = new HTextButton(letter);
     
     button.setName(buttonNaam);
     button.setLocation(index*25+50,index*25+50);
     button.setSize(50, 50);
     button.setBackground(new DVBColor(0,0,0,179));
     button.setBackgroundMode(HVisible.BACKGROUND_FILL);
     
     scene.add(button);
     
    }*/

        // shuffeld array zodat we de letterbutons niet in volgorde genereren
    static void shuffleArray(String [] ar) {
       {
    // If running on Java 6 or older, use `new Random()` on RHS here
    Random rnd = new Random();
    for (int i = ar.length - 1; i > 0; i--)
    {
      int index = rnd.nextInt(i + 1);
      // Simple swap
      String a = ar[index];
      ar[index] = ar[i];
      ar[i] = a;
    }
  } 
    }

   

   
  



}