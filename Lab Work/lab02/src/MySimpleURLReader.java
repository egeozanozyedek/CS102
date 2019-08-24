import cs1.SimpleURLReader;
/*
 * Subclass of SimpleURLReader, this class can return the url, name and page contents(w/o null)
 * @author Ege Ozan Özyedek
 * @version v1.0
 * @date 25/02/18
 */
public class MySimpleURLReader extends SimpleURLReader 
{
  //properties
  String url;
  
  //constructors
  
  /*
   * Constructor of class, calls the constructor of super class
   * gets the url from the super class and saves them
   */
  public MySimpleURLReader( String url) 
  {
    super(url);
    this.url = url;
  }
  
  //methods
  
  /*
   * A method to return the entered url
   * @return url, which is the url that was used in the constructor
   */
  public String getURL() 
  {
    return url;
  }
  
  /*
   * A method to return the name of the website
   * @return name, which is the name of the website 
   */
  public String getName()
  {
    return url.substring( url.lastIndexOf("/") + 1);
  }
  
  /*
   * A method that returns the page contents to the user without the "null" at the start
   * @return result, which is the page contents without null
   */
  public String getPageContents()
  {
    String result;
    result = super.getPageContents();
    result = result.substring( result.indexOf("null") + "null".length());
    return result;
  }
}