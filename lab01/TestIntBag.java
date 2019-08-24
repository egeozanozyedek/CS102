import java.util.Scanner;

/*
 * TestIntBag class, a class that test the IntBag class by creating a menu and letting the user assign values
 * @author Ege Ozan Özyedek
 * @version v2.0
 * @date 06/02/18
 */
public class TestIntBag {
  public static void main( String[] args ) {
    
    //constants
    
    //variables
    Scanner scan;
    IntBag bag;
    int userInput;
    int indexInput;
    int maxSize;
    int testV;
    IntBag locations;
    
    //program code
    
    //Assigning some variables
    scan = new Scanner( System.in );
    bag = null; //bag is declared as null so that it can print an error message
    testV = -1;
    locations = null;
    
    //A do-while loop, so that it first asks the user an int then checks whether it should continue the loop
    do {
      
      //prints the menu and asks the user to enter an int
      System.out.println( printMenu() );
      userInput = scan.nextInt();
      
      //if the input is 1, the user will 
      if ( userInput == 1 ) {
        System.out.println( "Please enter the maximum capacity" );
        maxSize = scan.nextInt();
        bag = new IntBag( maxSize );
      }
      
      //else if the user enters another number than 1 before assigning a max capacity, it will print an error message
      //Also added so that it wouldnt print out an error message when the user simply wanted to quit (a.k.a entering 9)
      else if ( bag == null && userInput != 9 ) {
        System.out.println( "Please assign a maximum value to the IntBag" );
      }
      
      //if the user enters another number that is not 1 and has assigned a maximum capacity, the code will continue 
      else {
        
        //if input is 2, then it will request the user to enter values to the bag
        if ( userInput == 2 ) {
          System.out.println( "Please enter the values you want to be added to the bag" );
          userInput = scan.nextInt();
          
          //a while loop so that it continues to scan for input from the user
          while ( userInput > 0 ) {
            bag.add( userInput );           
            userInput = scan.nextInt();
          }
        }
        
        //else if the user inputs 3 it will print out the values (or the error message if there are none)
        else if ( userInput == 3 ) {
          System.out.println( bag.toString() );
        }
        
        //else if the user inputs 4 it will add a value from the user to the index the user requests
        else if ( userInput == 4 ) {
          System.out.println( "Please enter the value you want to be added to the bag" );
          userInput = scan.nextInt();
          System.out.println( "Please enter the index to add the value" );
          indexInput = scan.nextInt();
          bag.add( indexInput, userInput );
        }
        
        //else if the input is 5 the code will remove the value at the requested index
        else if ( userInput == 5 ) {
          System.out.println( "Please enter the index to remove the value" );
          indexInput = scan.nextInt();
          bag.remove( indexInput );
        }
        
        //else if the user input is 6, the user can decide to choose a test value
        else if ( userInput == 6 ) {
          System.out.println( "Enter Test Value" );
          testV = scan.nextInt();
        }
        
        //this is so that if the testV is not changed and user decides to enter 7 or 8, it can print an error message
        if ( testV == -1 && ( userInput == 7 || userInput == 8 ) ) {
          System.out.println( "Test value not read" );
        }
        
        //if test value is read, the code continues here
        else {
          //using the findAll method, this if finds all instances and declares the locations as the return
          if ( userInput == 7 ) {
            locations = bag.findAll( testV );
            System.out.println( "Located indexes." );
          }
          
          //prints the locations of said value(or an error message saying there is no instance of said value)
          else if ( userInput == 8 && locations != null ) {
            System.out.println( "Locations: " + locations );
          } 
        }
      }
      //this while controls the loop, if user inputs 9 then the code stops and prints out a goodbye
    }while ( userInput != 9 );
    System.out.println( "Goodbye!" );
  }
  
  /*
   * This method is a simple method to print the menu
   * @return String, the menu
   */
  public static String printMenu() {
    return "\n" + "1. Create a new empty collection with a specified maximum capacity (any previous values are lost!)"
      +"\n" + "2. Read a set of positive values into the collection (use a negative value to indicate all the values have been entered.)" 
      +"\n" + "3. Print the collection of values."
      +"\n" + "4. Add a value to the collection of values at a specified location"
      +"\n" + "5. Remove the value at a specified location from the collection of values"
      +"\n" + "6. Read a single test value."
      +"\n" + "7. Compute the set of locations of the test value within the collection"
      +"\n" + "8. Print the set of locations."
      +"\n" + "9. Quit the program." +"\n";
  }
}