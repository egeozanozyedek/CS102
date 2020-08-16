import java.util.Scanner; 

/**
 * TestHangman class which test the Hangman class by creating the hangman game
 * @author Ege Ozan Özyedek
 * @date 7/2/2018
 * @version 2.0
 */
public class TestHangman {
  
  /**
   * Operates hangman game
   * @param args String Array args
   * @return void
   */
  public static void main( String[] args) {
    //constants
    //variables
    Scanner scan; 
    String guess;
    int tmp;
    Hangman game;
    
    //program code
    scan = new Scanner( System.in );
    game = new Hangman();
    
    //this do-while loops till the game is  over
    do {
      
      //this part displays the U.I. which shows things like incorrect tries and the  known letters as of now
      System.out.println( "* Used Letters: " + game.getUsedLetters() );
      System.out.println( "* Number of Incorrect Tries: " + game.getNumOfIncorrectTries() );
      System.out.println( "* Known So Far: " + game.getKnownSoFar() );
      System.out.println();
      
      //this do while gets valid input from the user
      do {
        System.out.print( "Please enter a letter: " );
        guess = scan.nextLine();
      } while( guess.length() == 0 );
      
      //getting first character of user input, rest is ignored
      guess = guess.toLowerCase().charAt(0) + "";
      tmp = game.tryThis( guess );
      
      //checks all cases and errors from tryThis() method
      if ( tmp == -1 ) {
        System.out.println( "The letter doesn't exist in the word" );
      }
      else if ( tmp == -2 ) {
        System.out.println( "The letter was already used." );
      }
      else if ( tmp >= 0 ) {
        System.out.println( "Bravo! Your guess was correct. Times the letter appears in the word: " + tmp );
      }
    } while ( !game.isGameOver() );
    
    //checking if the player has won or lost
    if( game.hasLost() ) {
      System.out.println("You Lost! The secret word was: "  + game.getSecretWord());
    }
    
    else {
      System.out.println("You Won!");
    }
  }
}
