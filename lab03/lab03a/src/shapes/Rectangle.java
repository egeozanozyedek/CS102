package shapes;
/*
 * Rectangle class which imitates a rectangle
 * @author Ege Ozan Özyedek
 * @version v1.0
 * @date 13/03/18
 */
public class Rectangle extends Shape
{
  
  //properties
  private int width;
  private int height;
  private boolean selected;
  
  //constructors
  /*
   * Constructor of Rectangle class, initializes sides and selected status
   * @param width, width that is entered by the user
   * @param height, height that is entered by the user
   */
  public Rectangle( int width, int height)
  {
    this.width = width;
    this.height = height;
    selected = false;
  }
  
  //methods
  
  /*
   * A method to get the area of the rectangle
   * @return double, the area of the rectangle
   */
  public double getArea()
  {
    return width * height;
  }
  
  /*
   * A method that returns info about the selected state
   * @return boolean, the selected state
   */
  public boolean getSelected()
  {
    return selected;
  }
  
  /*
   * A method that changes the selected state
   * @param c, the selected state that will be changed to
   */
  public void setSelected(boolean c)
  {
    selected = c;
  }
  
  /*
   * A method that determines whether the shape contains the x,y point
   * @param x, x coordinate
   * @param y, y coordinate
   */
  public Rectangle contains(int x, int y)
  {
    x = Math.abs( x - this.x);
    y = Math.abs( y - this.y);
    
    if ( Math.abs( x) > width / 2 || Math.abs( y) > height / 2)
    {
      return null;
    }
    return this;
  }
  
  /*
   * A method that prints out information about the class
   * @return String, information about the class
   */
  public String toString()
  {
    return "Shape: Rectangle" + ", Width: " + width + ", Height: "
      + height + ", Area: " + getArea() + ", Selected(T/F): " + selected + ", x: " + x + ", y: " + y;
  }
}
