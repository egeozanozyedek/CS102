package shapes;

import java.awt.*;

/*
 * Drawable interface which makes shapes drawable
 * @author Ege Ozan Özyedek
 * @version v1.0
 * @date 26/03/18
 */
public interface Drawable {
  
  /*
   * Interface method to draw desired shape
   * @param g, graphics
   */
  void draw( Graphics g);
}
