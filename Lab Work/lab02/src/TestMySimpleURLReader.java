/*
 * A program that tests methods of MySimpleURLReader 
 * @author Ege Ozan Özyedek
 * @version v1.0
 * @date 25/02/18
 */
public class TestMySimpleURLReader 
{
  public static void main(String[] args)
  {
    //constants
    final String url = "http://www.cs.bilkent.edu.tr/~david/housman.txt" ;
    
    //variables
    MySimpleURLReader reader;
    
    //program code
    reader = new MySimpleURLReader( url);
    
    System.out.println( "\nContents\n" + reader.getPageContents());
    System.out.println( "Line Count: " + reader.getLineCount());
    System.out.println( "\nURL Name: " + reader.getName() + "\nURL: " + reader.getURL());
  }
}