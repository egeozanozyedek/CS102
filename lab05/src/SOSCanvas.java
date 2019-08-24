import javax.swing.*;
import java.awt.*;
import cs101.sosgame.SOS;

/*
 * A Panel which creates a canvas to play the SOS game in. It also overrides paintComponent to update evry turn.
 * @author Ege Ozan Özyedek
 * @version v1.0
 * @date 03/04/18
 */
public class SOSCanvas extends JPanel {
  //properties
  private SOS sos;
  private final int PANEL_SIZE = 300;
  private int cSize;
  
  //constructors
  
  /*
   * SOSCanvas constructor which gets sos input and saves it.
   * Also sets size.
   */
  public SOSCanvas( SOS sos) {
    this.sos = sos;
    setPreferredSize( new Dimension( PANEL_SIZE + 1, PANEL_SIZE + 1));
    setMaximumSize( getPreferredSize());
  }
  
  //methods
  
  /*
   * Overridden painComponents method which paints the grid that is going to be played in the game
   * @param, graphics, graphics of panel
   */
  @Override
  public void paintComponent(Graphics g) {
    int d, i, j, x, y;
    String content;
    
    //calls super of method
    super.paintComponent(g);
    
    d = sos.getDimension();
    
    //determines the side of every square that is going to be drawn
    cSize = ( int) PANEL_SIZE / d;
    
    
    //2 for loops which draw the grid row to row
    y = 0;
    for ( i = 0; i < d; i++) {
      x = 0;
      for ( j = 0; j < d; j++) {
        
        g.drawRect( x, y, cSize, cSize);
        //gets contents of cell and updates the grid
        content = "" + sos.getCellContents( j, i);
        g.setFont( new Font ("Calibri",Font.BOLD, 30));
        g.drawString( content, x + cSize/2, y + cSize/2);
        
        x += cSize;
      }
      y += cSize;
    }
    
  }
  
  /*
   * A method which returns the side of one grid square
   * @return cSize, the size of one grid quare
   */
  public int getCSize() {
    return cSize;
  }
}
