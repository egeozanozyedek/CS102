package components;

import shapes.*;
import java.awt.*;

/*
 * Rectangle class which imitates a square
 * @author Ege Ozan Özyedek
 * @version v1.0
 * @date 26/03/18
 */
public class Balloon extends Circle implements Drawable {

    //properties
    private static final int START_RADIUS = 25;

    //constructor
    
      /*
   * Constructor of Balloon class, calls the constructor of super with the desired start radius
   * @param radius, radius that is entered by the user
   */
    public Balloon() {
       super( START_RADIUS);
    }

    //methods
    
  /*
   * draw method which draws the balloon (which is a circle)
   */
    public void draw( Graphics g) {
        g.drawOval( getX() - radius, getY() - radius, radius * 2, radius * 2);
    }

  /*
   * grow method which grows the balloon by increasing its radius by 1 everytime and resets the radius to 0 and sets 
   * selected when the radius is equal to 100
   */
    public void grow() {
        if (  radius < 100)
            radius++;
        else {
            setSelected( true);
            radius = 0;
        }
    }
}
