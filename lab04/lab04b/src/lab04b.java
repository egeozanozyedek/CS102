import components.BalloonsGamePanel;

import javax.swing.*;

public class lab04b {
    public static void main( String[] args) {
        //constants
        final int FRAME_WIDTH = 550;
        final int FRAME_HEIGHT = 550;
        //variables
        JFrame frame;

        //program code

        frame = new JFrame();
        frame.setSize( FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLocationRelativeTo( null);
        frame.setTitle( "Balloons Game");
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        frame.add( new BalloonsGamePanel());
        frame.setVisible( true);

    }
}
