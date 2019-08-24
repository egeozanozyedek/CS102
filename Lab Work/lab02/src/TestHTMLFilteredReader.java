/*
 * A program that tests methods of HTMLFilteredReader
 * @author Ege Ozan Özyedek
 * @version v1.0
 * @date 25/02/18
 */
public class TestHTMLFilteredReader
{
  public static void main(String[] args)
  {
    //constants
    final String url = "http://www.cs.bilkent.edu.tr/~david/housman.htm" ;
    
    //variables
    HTMLFilteredReader reader;
    
    //program code
    reader = new HTMLFilteredReader( url);
    System.out.println( "\n|Contents|\n" + reader.getPageContents());
    System.out.println( "\n|Unfiltered Contents|\n" + reader.getUnfilteredPageContents());
    System.out.println( "Line Count: " + reader.getLineCount());
    System.out.println( "\nURL Name: " + reader.getName() + "\nURL: " + reader.getURL());
  }
}