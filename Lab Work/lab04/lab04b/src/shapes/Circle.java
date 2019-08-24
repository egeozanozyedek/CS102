package shapes;

/*
 * Rectangle class which imitates a square
 * @author Ege Ozan Özyedek
 * @version v1.0
 * @date 13/03/18
 */
public class Circle extends Shape
{
  
  //properties
  protected int radius;
  private boolean selected;
  
  //constructors
  
  /*
   * Constructor of Circle class, initializes radius and selected status
   * @param radius, radius that is entered by the user
   */
  public Circle( int radius)
  {
    this.radius = radius;
    selected = false;
  }
  
  //methods
  
  /*
   * A method to get the area of the circle
   * @return double, the area of the circle 
   */
  public double getArea() {
    return Math.PI * Math.pow( radius, 2);
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
  public Circle contains(int x, int y)
  {
    double r;
    
    x = Math.abs( x - this.x);
    y = Math.abs( y - this.y);
    r = Math.sqrt( Math.pow( x, 2) + Math.pow( y, 2) );
    
    if ( r < radius )
    {
      return this;
    }
    else {
      return null;
    }
  }

  /*
   * A method that prints out information about the class
   * @return String, information about the class
   */
  public String toString()
  {
    return "Shape: Circle" + ", Radius: " + radius + ", Area: " + getArea() + ", Selected(T/F): " 
      + selected + ", x: " + x + ", y: " + y;
  }
}
