import java.util.ArrayList;
import java.util.Scanner;
/*
 * A program that can store poems from websites and can let the user view them
 * @author Ege Ozan Özyedek
 * @version v1.0
 * @date 25/02/18
 */
public class PoemProgram 
{
  public static void main(String[] args)
  {
    //constants
    
    //variables
    ArrayList<MySimpleURLReader> list;
    MySimpleURLReader reader;
    Scanner scan;
    String input, contents , size;
    
    //program code
    
    input = "";
    scan = new Scanner(System.in);
    list = new ArrayList<MySimpleURLReader>();
    System.out.println(printMenu());
    input = scan.next();
    size = "";
    
    //while loop to print the menu and get input till input is 3
    while (!input.equals("3"))
    {
      
      //if the user input is 1 the code will flow through here
      //adds a new reader depending on the name and its type
      if (input.equals( "1"))
      {
        input = scan.next();
        
        //if the name of the site ends with anything other than type .txt then it will create a html reader
        if (input.indexOf( "txt", input.lastIndexOf( "/")) == -1) 
          reader = new HTMLFilteredReader( input);
        
        //else a normal url reader
        else
          reader = new MySimpleURLReader( input);
        
        list.add( reader);
      }
      //else if the user input is 2 the code will flow through here  
      else if ( input.equals( "2"))
      {
        contents = "";
        
        //creates a menu for the user to see with index numbers and names of sites
        for (int i = 0; i < list.size(); i++)
        {
          contents += "<" + i + ">" + " " + "Name: " + list.get(i).getName() + "\n";
        }
        
        size = list.size() + "";
        // a do while so that the user can see the menu even after they choose one to view
        do 
        {
          System.out.println( contents + "\nEnter " + size + " to go to the main menu");
          input = scan.next();
          
          if (!input.equals( size) && ( Integer.parseInt(input) < list.size()))
            System.out.println(list.get( Integer.parseInt(input)).getPageContents());
          
          else if (!input.equals( size))
            System.out.println( "Invalid index.");
          
        } while (!input.equals( size ));
      }
      
      //else the code will flow through here and since the input is not on the menu it will print an error
      else
        System.out.println( "Invalid input, try again.");
      
      System.out.println( printMenu());
      input = scan.next();
    }
    System.out.println( "Thanks for using PoemProgram!");
    scan.close();
  }
  
  public static String printMenu() 
  {
    return "\n(1) Enter URL of poem" + "\n(2) List all poems" + "\n(3) Quit";
  }
  
}

