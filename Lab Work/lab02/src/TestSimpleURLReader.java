import cs1.SimpleURLReader;
/*
 * A program that tests methods of SimpleURLReader 
 * @author Ege Ozan Özyedek
 * @version v1.0
 * @date 25/02/18
 */
public class TestSimpleURLReader 
{
  public static void main(String[] args) 
  {
    //constants
    final String url = "http://www.cs.bilkent.edu.tr/~david/housman.txt" ;
    
    //variables
    SimpleURLReader reader;
    
    //program code
    reader = new SimpleURLReader( url);
    
    System.out.println( "\nContents\n" + reader.getPageContents());
    System.out.println( "Line Count: " + reader.getLineCount());
  }
}