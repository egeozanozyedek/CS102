import cs101.sosgame.SOS;

import javax.swing.*;
import java.awt.*;

/*
 * A Frame to test the SOSCanvas class
 * @author Ege Ozan Özyedek
 * @version v1.0
 * @date 03/04/18
 */
public class TestCanvas {
  
  public static void main(String[] args) {
    //constants
    final int FRAME_WIDTH = 270;
    final int FRAME_HEIGHT = 290;
    final int SOS_DIMENSION = 5;
    
    //variables
    JFrame f;
    SOSCanvas c;
    SOS s;
    
    s = new SOS( SOS_DIMENSION);
    c = new SOSCanvas( s);
    f = new JFrame( "SOS Game");
    f.setSize( FRAME_WIDTH, FRAME_HEIGHT);
    f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
    f.add( c);
    f.setVisible( true);
    
    
  }
}
