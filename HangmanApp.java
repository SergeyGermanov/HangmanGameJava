package ictgradschool.industry.assignment04;

import javax.swing.*;

/**
 * author: Sergey Germanov
 */
public class HangmanApp extends JFrame {

    public HangmanApp() {
        setTitle( "Hangman" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setContentPane( new MainPanel() );
        pack();
        setLocationRelativeTo( null );

    }

    /**
     * Program entry point.
     */
    public static void main( String[] args ) {
        SwingUtilities.invokeLater( new Runnable() {
            @Override
            public void run() {
                HangmanApp frame = new HangmanApp();
                frame.setVisible( true );
            }
        } );
    }

}
