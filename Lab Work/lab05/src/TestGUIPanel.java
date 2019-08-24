import cs101.sosgame.SOS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * A Frame to test the SOSGUIPanel class
 * @author Ege Ozan Özyedek
 * @version v1.0
 * @date 03/04/18
 */
public class TestGUIPanel {
  
  public static void main(String[] args) {
    //constants
    final int FRAME_WIDTH = 300;
    final int FRAME_HEIGHT = 350;
    
    //variables
    JFrame f;
    JTextField tf1, tf2, tf3;
    JButton b;
    JLabel l;
    JPanel inputP, textP;
    SOS s;
    String name1, name2;
    int dimension;
    
    //creates frame and two panels to create an input frame
    f = new JFrame("SOS Game");
    inputP = new JPanel(new BorderLayout());
    textP = new JPanel( new GridLayout());
    l = new JLabel( "Enter dimension and player names or click continue to play with default settings.");
    l.setFont( new Font ("Calibri",Font.PLAIN, 15));
    tf1 = new JTextField("3");
    tf2 = new JTextField("Player 1");
    tf3 = new JTextField("Player 2");
    b = new JButton("Confirm");
    
    //action listener which creates the game after the input
    b.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
      
      //removes the input components from frame
      f.remove( inputP);
      SOSGUIPanel p = new SOSGUIPanel( new SOS( Integer.parseInt(tf1.getText())), tf2.getText(), tf3.getText());
      JButton restart = new JButton("Restart");
      restart.setBackground( Color.yellow);
      restart.setFont( new Font ("Calibri",Font.PLAIN, 15));
      restart.addActionListener( new ActionListener() { public void actionPerformed(ActionEvent e){ p.restart();}});
      restart.setMaximumSize( new Dimension (50, 30));
      f.add(restart, BorderLayout.NORTH);
      f.add( p, BorderLayout.CENTER);
      f.setSize( p.getMaximumSize());
      f.pack();
      f.setLocationRelativeTo( null);
      f.revalidate();
      f.repaint(); }});
    
    inputP.setPreferredSize( new Dimension( 500,100));
    
    //adds input components to the panel
    inputP.add( l, BorderLayout.NORTH);
    textP.add( tf1);
    textP.add( tf2);
    textP.add( tf3);
    inputP.add( textP, BorderLayout.CENTER);
    inputP.add( b, BorderLayout.SOUTH);
    
    //adds input components to the frame
    f.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.add(inputP);
    f.pack();
    f.setLocationRelativeTo( null);
    f.setVisible(true);
    
  }
  
}
