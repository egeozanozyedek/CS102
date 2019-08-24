
/*
 * Subclass of MySimpleURLReader, this class outputs html websites without the html code
 * @author Ege Ozan Özyedek
 * @version v1.0
 * @date 25/02/18
 */
public class HTMLFilteredReader extends MySimpleURLReader
{
  //properties
  String pageContents;
  
  //constructors
  
  /*
   * Constructor of class, calls the constructor of super class
   * gets the url and the page contents from the super class and saves them
   */
  public HTMLFilteredReader( String url) 
  {
    super( url);
    this.url = url;
    pageContents = super.getPageContents();
  }
  
  //methods
  
  /*
   * This method returns the page contents with the html code
   * @return pageContents, which is the page contents with the html code
   */
  public String getUnfilteredPageContents()
  {
    return pageContents;
  }
  
  /*
   * This method returns the page contents w/o the html code by removing anything between < and >
   * @return page, which is the page contents w/o the html code
   */
  public String getPageContents()
  {
    int i, j;
    String page;
    
    page = pageContents;

    
    while (page.indexOf("<") != -1)
    {
      i = page.indexOf("<");
      j = page.indexOf(">");
      page = page.substring( 0, i) + page.substring( j + 1);
    }
    
     page = page.replaceAll("&quot;", "\"" );
     page = page.replaceAll("&nbsp;", " " );
    
    return page;
  }
}