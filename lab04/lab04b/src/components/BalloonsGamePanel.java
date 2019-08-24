package components;

import shapes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;

/*
 * BalloonGamePanel class which extends JPanel and creates a panel which creates a game in which you can blow bubbles up
 * @author Ege Ozan Özyedek
 * @version v1.0
 * @date 26/03/18
 */
public class BalloonsGamePanel extends JPanel {
  
  //properties
  private ShapeContainer balloons;
  private int points, x, y;
  private double elapsedTime;
  private JLabel label;
  private Timer timer;
  private Iterator it;
  
  private final double SECONDS = 0.25;
  private final int MAX_RUN_TIME = 10;
  private final int MAX_BALLOONS = 25;
  
  //constructors
  
  /*
   * Constructor of Balloon games panel which creates contents and add these contents to the panel.
   */
  public BalloonsGamePanel() {
    
    label = new JLabel( "Points: " + points);
    label.setFont( new Font("Calibri", Font.PLAIN, 14));
    label.setHorizontalAlignment( SwingConstants.CENTER);
    setLayout( new BorderLayout());
    setBackground( Color.green);
    add( label, BorderLayout.NORTH);
    addMouseListener(new Listener());
    createBalloons();
    timer = new Timer((int)(SECONDS * 1000), new TaskPerformer());
    timer.start();
  }
  
  
  //methods
  
  /*
   * An override of the paintComponent method which calls the super and also draws all balloons from the iterator
   * of shape container
   */
  public void paintComponent(Graphics g) {
    
    super.paintComponent(g);
    it = balloons.iterator();
    while (it.hasNext()) {
      ((Balloon) it.next()).draw( g);
    }
  }
  
  /*
   * TaskPerformer class which implements ActionListener which is the listener that is being used in the timer
   * @author Ege Ozan Özyedek
   * @version v1.0
   * @date 26/03/18
   */
  public class TaskPerformer implements ActionListener {
    
    /*
     * A method which is the root of the code. It controls the timer and ends the game if the elapsed time 
     * is equal to the max run time. It also removes the selected shapes.
     * @param ActionEvent, the click on the shape
     */
    public void actionPerformed(ActionEvent e) {
      
      //if the elapsed time is equalto max run time it will stop the game and ask the user whether he/she wants to
      //continue the game
      if ( elapsedTime > MAX_RUN_TIME) {
        timer.stop();
        int n = JOptionPane.showConfirmDialog(BalloonsGamePanel.this,
                                              "Game Over. You scored " + points + " points. Would you like to play again?",
                                              "Game Over!",
                                              JOptionPane.YES_NO_OPTION);
        
        //if the user selects no then the window will be closed
        if ( n == JOptionPane.NO_OPTION) {
          System.exit( 0);
        }
        
        //resets game
        else {
          elapsedTime = 0;
          points = 0;
          createBalloons();
          timer.restart();
          revalidate();
          repaint();
        }
      }
      
      //increments elapsed time
      elapsedTime += SECONDS;
      it = balloons.iterator();
      while (it.hasNext()) {
        ((Balloon) it.next()).grow();
      }
      
      //removes selected balloons 
      balloons.removeSelected();
      label.setText("Points: " + points);
      
      //adds balloons if there are less than 15 on the panel
      if ( balloons.size() < 15) {
        Balloon b;
        b = new Balloon();
        x = ( int) ( Math.random() * 475) + 25 ;
        y = ( int) ( Math.random() * 475) + 25 ;
        b.setLocation( x, y);
        balloons.add( b);
      }
      //repaints all components to the new set order
      repaint();
    }
  }
  
  /*
   * A method which creates balloons by adding them into a shape container
   */
  private void createBalloons() {
    
    //createes balloons and gives them a random x and y to be placed
    balloons = new ShapeContainer();
    for ( int i = 0; i < MAX_BALLOONS; i++) {
      x = ( int) ( Math.random() * 475) + 25 ;
      y = ( int) ( Math.random() * 475) + 25 ;
      
      Balloon b = new Balloon();
      b.setLocation( x, y);
      balloons.add( b);
    }
  }
  
  
  /*
   * Listener which implements MouseListener. It determines how many balloons we clicked and increments the points
   * @author Ege Ozan Özyedek
   * @version v1.0
   * @date 26/03/18
   */
  public class Listener implements MouseListener {
    
    /*
     * When the mouse is pressed, it will increment points if it selected more than 2 balloons
     * @param MouseEvent e, the mouse event
     */
    public void mousePressed(MouseEvent e) {
      int selected;
      selected = balloons.selectAllAt( e.getX(), e.getY());
      if ( selected > 1)
        points++;
    }
    public void mouseClicked(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) {
    }
  }
  
}
