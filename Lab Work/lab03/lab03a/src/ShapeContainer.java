import java.util.ArrayList;
import java.util.Iterator;
import shapes.*;
/*
 * Shape container which contains shapes 
 * @author Ege Ozan Özyedek
 * @version v1.0
 * @date 13/03/18
 */
public class ShapeContainer {
  
  //properties
  private ArrayList<Shape> shapes;
  
  //constructors
  
  /*
   * Constructor of ShapeContainer class which assigns a new shapes list
   */
  public ShapeContainer()
  {
    shapes = new ArrayList<Shape>();
  }
  
  //methods
  
  /*
   * A method to add a shape to the list
   * @param s, shape to be added
   */
  public void add(Shape s)
  {
    shapes.add(s);
  }
  
  /*
   * A method to get the total area of shapes
   * @return double, the total area of shapes
   */
  public double getArea()
  {
    double totalArea;
    totalArea = 0;
    
    Iterator it;
    it = shapes.iterator();
    
      while (it.hasNext()) {
        Shape s;
        s = (Shape) it.next();
        totalArea = totalArea + s.getArea();
      }
    /*
    for ( int i = 0; i < shapes.size(); i++)
    {
      totalArea += shapes.get(i).getArea();
    }*/
    return totalArea;
    
  }
  
  /*
   * A method to get the total area of shapes
   * @param x, x coordinate
   * @param y, y coordinate
   * @return Shape, null or the shape that contains x and y
   */
  public Shape findFirst( int x, int y)
  {
    for ( int i = 0; i < shapes.size(); i++)
    {
      if ( !(shapes.get(i).contains( x, y) == null) && !shapes.get(i).getSelected())
      {
        return shapes.get( i);
      }
    }
    return null;
  }
  
  /*
   * A method to remove the shapes which are selected
   */
  public void removeSelected()
  {
    for ( int i = 0; i < shapes.size(); i++) {
      if ( shapes.get(i).getSelected())
      {
        shapes.remove(i);
        i--;
      }
    }
  }
  
  /*
   * A method to get the size of list
   * @return int, size of the list
   */
  public int getSize()
  {
    return shapes.size();
  }
  
  /*
   * A method to get the shape at a specific index
   * @param i, the index
   * @return Shape, the shape at the index
   */
  public Shape getShape( int i)
  {
    return shapes.get(i);
  }
  
  @Override
  public String toString()
  {
    String result;
    result = "";
    
    for ( int i = 0; i < shapes.size(); i++)
    {
      result += "Index: " + i + ", " + shapes.get(i).toString() + "\n" ;
    }
    
    return result;
  }
}
