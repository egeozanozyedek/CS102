import javax.swing.*;
import cs101.sosgame.SOS;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/*
 * A Panel in which the user can play the game SOS. Uses SOSCanvas which is the game canvas.
 * @author Ege Ozan Özyedek
 * @version v1.0
 * @date 03/04/18
 */
public class SOSGUIPanel extends JPanel {
  
  //properties
  private SOS sos;
  private JPanel p;
  private JLabel l1,l2;
  private JRadioButton rb1, rb2;
  private SOSCanvas canvas;
  private String n1, n2, winner;
  private ButtonGroup g;
  
  //constructors
  
  /*
   * Constructor of SOSGUIPanel, which saves gven parameters, creates components and sets its size
   * @param sos, sos game
   * @param n1, name of player 1
   * @param n2, name of player 2
   */
  public SOSGUIPanel( SOS sos, String n1, String n2 ) {
    this.n1 = n1;
    this.n2 = n2;
    this.sos = sos;
    setLayout( new BoxLayout( this, BoxLayout.PAGE_AXIS));
    createComponents();
    setMinimumSize( new Dimension( canvas.getMaximumSize().width + p.getMaximumSize().width + 1,
                                  canvas.getMaximumSize().height + p.getMaximumSize().height + 2));
  }
  
  //methods
  
  /*
   * A method which creates components for the panel and creates a panel
   */
  private void createComponents() {
    
    //creates a new canvas and sos with the same dimension
    sos = new SOS( sos.getDimension());
    canvas = new SOSCanvas( sos);
    canvas.addMouseListener( new Listener());
    
    //name of player 1
    l1 = new JLabel( n1);
    l1.setHorizontalAlignment( SwingConstants.CENTER);
    l1.setOpaque( true);
    l1.setBackground( Color.GREEN);
    
    //name of player 2
    l2 = new JLabel( n2);
    l2.setHorizontalAlignment( SwingConstants.CENTER);
    l2.setOpaque( true);
    l2.setBackground( Color.GRAY);
    
    //radio buttons to chpose S or O
    rb1 = new JRadioButton("S", true);
    rb1.setHorizontalAlignment( SwingConstants.CENTER);
    rb2 = new JRadioButton("O");
    rb2.setHorizontalAlignment( SwingConstants.CENTER);
    g = new ButtonGroup();
    g.add( rb1);
    g.add( rb2);
    
    //the control panel whic contains elements above
    p = new JPanel( new GridLayout( 1,4));
    p.add( l1);
    p.add( rb1);
    p.add( rb2);
    p.add( l2);
    
    //Makes the panel look more organized
    add( Box.createRigidArea( new Dimension( 25, 25)));
    add( Box.createHorizontalGlue());
    //  canvas.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), canvas.getBorder()));
    canvas.setAlignmentX( Component.CENTER_ALIGNMENT);
    
    //adding the panel and canvas
    add( canvas);
    p.setMaximumSize( new Dimension( canvas.getMaximumSize().width + 1, 25));
    add( p);
    repaint();
  }
  
  /*
   * A method which restarts the game by removing and then creating components
   */
  public void restart() {
    removeAll();
    revalidate();
    repaint();
    createComponents();
  }
  
  
  /*
   * Listener class which implements MouseListener
   * @author Ege Ozan Özyedek
   * @version v1.0
   * @date 03/04/18
   */
  public class Listener implements MouseListener {
    
    /*
     * mouseListener method which gets the x and y values of canvas and finds the selected 
     * grid and then updates the canvas with the corresponding letter
     * @param MouseEvent e, the mouse event 
     */
    public void mouseClicked(MouseEvent e) {
      int x, y;
      char c;
      
      //if radiobutton1 is selected than the char is s
      if ( rb1.isSelected()) {
        c = 's';
      }
      //else its o
      else {
        c = 'o';
      }
      
      //sets the x and y values of event e to comply with the grid
      x = (int)(e.getX() / canvas.getCSize()) + 1;
      y = (int)(e.getY() / canvas.getCSize()) + 1;
      
      //plays the char with the new x and y
      sos.play( c, x,y);
      
      //two if / else if statements below determine the turn of player and change the background color accordingly
      if ( sos.getTurn() == 1) {
        l1.setBackground(Color.GREEN);
        l2.setBackground( Color.GRAY);
      }
      else if ( sos.getTurn() == 2){
        l2.setBackground( Color.GREEN);
        l1.setBackground( Color.GRAY);
      }
      
      //changes the points by getting the values from sos class
      l1.setText( n1 + ": " + sos.getPlayerScore1());
      l2.setText( n2 + ": " + sos.getPlayerScore2());
      canvas.repaint();
      
      //if game is over the code wll create a j option frame with a message determined by the if else statements inside
      if ( sos.isGameOver())  {
        winner = n1 + " won, congratulations!";
        if ( sos.getPlayerScore2() > sos.getPlayerScore1())
          winner = n2 + " won, congratulations!";
        else if ( sos.getPlayerScore1() == sos.getPlayerScore2())
          winner = "It's a draw!";
        JOptionPane.showConfirmDialog( SOSGUIPanel.this,
                                      winner, "Game Over", JOptionPane.DEFAULT_OPTION);
      }
      repaint();
    }
    public void mousePressed(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
  }
  
  
}
