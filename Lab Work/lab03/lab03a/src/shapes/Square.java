package shapes;
/*
 * Rectangle class which imitates a square
 * @author Ege Ozan Özyedek
 * @version v1.0
 * @date 13/03/18
 */
public class Square extends Rectangle
{
  
  //properties
  private int side;
  private boolean selected;
  
  //constructors
  
  /*
   * Constructor of Square class, initializes side and selected status
   * @param radius, radius that is entered by the user
   */
  public Square(int side)
  {
    super(side, side);
    this.side = side;
    selected = false;
  }
  
  //methods
  
  /*
   * A method to get the area of the square
   * @return double, the area of the square
   */
  public double getArea()
  {
    return super.getArea();
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
  public Square contains(int x, int y)
  {
    x = Math.abs( x - this.x);
    y = Math.abs( y - this.y);
    
    if ( Math.abs( x) > side / 2 || Math.abs( y) > side / 2 ) {
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
    return "Shape: Square" + ", Side Length: " + side + ", Area: " + getArea() + ", Selected(T/F): " + selected
      + ", x: " + x + ", y: " + y;
  }
}
