/*
 * A program that tests methods of SuperHTMLFilteredReader
 * @author Ege Ozan Özyedek
 * @version v1.0
 * @date 25/02/18
 */
public class TestSuperHTMLFilteredReader
{
  public static void main(String[] args)
  {
    //constants
    final String url = "http://www.cs.bilkent.edu.tr/~david/index.html" ;
    
    //variables
    SuperHTMLFilteredReader reader;
    
    //program code
    reader = new SuperHTMLFilteredReader( url);
    System.out.println( "\nLinks\n" + reader.getLinks().toString());
    System.out.println( "\nOverhead: %" + reader.getOverhead());
    System.out.println( "\nContents\n" + reader.getPageContents());
    System.out.println( "\nUnfiltered Contents\n" + reader.getUnfilteredPageContents());
    System.out.println( "Line Count: " + reader.getLineCount());
    System.out.println( "\nURL Name: " + reader.getName() + "\nURL: " + reader.getURL()); 
  }
}