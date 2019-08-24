import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/*
 * A frame which extends JFrame to create a game in which you guess a number and it tells you whether it is the four 
 * leaved clover or not
 * @author Ege Ozan Özyedek
 * @version v1.0
 * @date 26/03/18
 */
public class LuckyFrame extends JFrame{
  
  //properties
  private JPanel panel, statusBar;
  private JLabel label;
  private JButton fourLeafClover;
  private ActionListener listener;
  private int count;
  private double elapsedTime;
  private boolean buttonEnable;
  private Timer timer;
  
  private static final int FRAME_WIDTH = 600;
  private static final int FRAME_HEIGHT = 600;
  private static final int MATRIX = 5;
  private static final double COLORS = 0x1000000;
  
  //constructors
  
  /*
   * Constructor of LuckyFrame which sets the size 
   * of the frame and creates contents of the frame
   */
  public LuckyFrame() {
    setSize( FRAME_WIDTH, FRAME_HEIGHT);
    //centered
    setLocationRelativeTo( null);
    createContents();
  }
  
  //methods
  
  /*
   * A method to create the contents of the frame such 
   * as the panel, the status bar and the buttons 
   */
  private void createContents() {
    
    //creates a panel with grid layout to fill with buttons that will be later added
    panel = new JPanel( new GridLayout( MATRIX , MATRIX));
    
    //status bar panel which will show the count amount and whether if it is the wrong button or not
    statusBar = new JPanel( new BorderLayout());
    statusBar.setBackground( Color.GREEN);
    
    //assigns a listener which will determine the button that  is pressed
    listener = new ClickListener();
    
    int index;
    index = (int) ( Math.random() * Math.pow( MATRIX, 2));
    
    //assigns buttons and determines the fourleafclover
    for ( int i = 0; i < Math.pow( MATRIX, 2); i++ ) {
      
      //creates a new button object, assigns the listener and a color
      JButton button = new JButton( "" + ( i + 1));
      button.addActionListener( listener);
      button.setBackground( new Color((int)(Math.random() * COLORS)));
      
      //if i is mequal to the index determined beforehand than that index will be the fourleafclover
      if (index == i) {
        fourLeafClover = button;
        panel.add(fourLeafClover);
      }
      
      //else it will add the regular button to the shape container
      else {
        panel.add(button);
      }
      
    }
    
    //sets a timer that has a delay of 100 ms which will determine if the game is over and update the label
    timer = new Timer( 100, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        elapsedTime += 0.1;
        //if the button enable condition is false then it means the game is over 
        if ( !buttonEnable) {
          timer.stop();
          label.setText( "You guessed right! You guessed " + count + " times. Elapsed time: " + (int)elapsedTime + " seconds");
        }
        else {
          label.setText( "Wrong guess! Guess count: " + count + ". Elapsed time: " + (int)elapsedTime + " seconds");
        }
      }
    });
    
    //sets the button enable condition true
    buttonEnable = true;
    
    //sets up the label
    label = new JLabel("Welcome to Pot Luck!", SwingConstants.CENTER);
    label.setForeground(Color.BLACK);
    label.setFont(new Font("Calibri", Font.PLAIN, 14));
    
    //adds the contents to the panels
    statusBar.add( label);
    add( panel);
    add( statusBar, BorderLayout.NORTH);
    
    //starts the timer
    timer.start();
  }
  
  /*
   * An inner class ClickListener which implements ActionListener to use to determine the button pressed
   * @author Ege Ozan Özyedek
   * @version v1.0
   * @date 26/03/18
   */
  public class ClickListener implements ActionListener {
    
    /*
     * A method which checks whether the pressed button is the fourleafclover
     * @param ActionEvent e, the button clicked
     */
    public void actionPerformed(ActionEvent e) {
      
      //gets the source of the button and controls if it is the fourleafclover
      JButton clicked;
      clicked = ( JButton) e.getSource();
      count++;
      
      if ( clicked.equals( fourLeafClover)) {
        
        //sets the button icon of the fourleafclover to a four leaf clover after it is guessed correctly
        Image img, newImg;
        ImageIcon icon;
        icon = new ImageIcon( "Clover.png");
        img = icon.getImage() ;
        newImg= img.getScaledInstance( fourLeafClover.getWidth(),
                                      fourLeafClover.getHeight(),
                                      Image.SCALE_SMOOTH );
        icon = new ImageIcon( newImg );
        fourLeafClover.setIcon( icon);
        
        //sets the button enable condition to false and disables every button since the game is over
        buttonEnable = false;
        for ( int i = 0; i < MATRIX * MATRIX; i++) {
          panel.getComponents()[i].setEnabled( buttonEnable);
        }
      }
    }
  }
}
