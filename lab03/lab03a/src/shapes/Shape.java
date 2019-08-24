package shapes;
/*
 * Shape class which imitates a shape
 * @author Ege Ozan Özyedek
 * @version v1.0
 * @date 13/03/18
 */
public abstract class Shape implements Locatable {
  
  //properties
  int x;
  int y;
  
  //constructor
  
  //methods
  
  /*
   * Abstract method to get the area of shape
   * @return double, area of shape
   */
  public abstract double getArea();
  
  /*
   * Interface method to get the x coordinate of shape
   * @return int, x coordinate
   */
  public int getX() {
    return x;
  }
  
  /*
   * Interface method to get the y coordinate of shape
   * @return int, y coordinate
   */
  public int getY() {
    return y;
  }
  
  /*
   * Interface method to set location by getting x and y values
   * @param x, x coordinate
   * @param y, y coordinate
   */
  public void setLocation( int x, int y) {
    this.x = x;
    this.y = y;
  }
  
}
