import javax.swing.*;

/*
 * A class to run and set up LuckyFrame.
 * @author Ege Ozan Özyedek
 * @version v1.0
 * @date 26/03/18
 */
public class PotLuck {
  
  public static void main(String[] args) {
    
    //variables
    JFrame frame;
    
    //program code
    
    //general frame set up
    frame = new LuckyFrame();
    frame.setTitle("Pot Luck");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible( true);
  }
}
