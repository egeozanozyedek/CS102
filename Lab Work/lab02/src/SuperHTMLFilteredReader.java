import java.util.ArrayList;
/*
 * Subclass of HTMLFilteredReader, this class can return the links on the desired URL and can return the overhead
 * @author Ege Ozan Özyedek
 * @version v1.0
 * @date 25/02/18
 */
public class SuperHTMLFilteredReader extends HTMLFilteredReader
{
  //properties
  ArrayList<String> links;
  
  //constructors
  
  /*
   * Constructor of class, calls the constructor of super class
   * creates  an arraylist to store the links in the html code
   */
  public SuperHTMLFilteredReader(String url) 
  {
    super(url);  
    links = new ArrayList<String>();
  }
  
  //methods
  
  /*
   * A method to return the links that are in a website with html code
   * @return result, which are the links that are formatted to look better than the arraylist toString()
   */
  public ArrayList getLinks() 
  {
    int i, j;
    String page, result;
    page =  super.getUnfilteredPageContents();
    
    while (page.indexOf("href=\"") != -1) 
    {
      i = page.indexOf("href=\"") + "href=\"".length();
      j = page.indexOf("\"", i);
      links.add(page.substring( i , j + 1));
      page = page.substring( j + 1);
    }
    
    
    return links;
  }
  
  /*
   * A method that returns the persentage of overhead
   * @return overhead, the persentage increase
   */
  public int getOverhead() 
  {
    double i, j, result;
    
    i = super.getUnfilteredPageContents().length();
    j = super.getPageContents().length();
    result = ( 1 - ( ( Math.abs( i - j) ) / i ) ) * 100;

    return (int) result ;
  } 
  
}