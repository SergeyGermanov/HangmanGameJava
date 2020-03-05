package ictgradschool.industry.assignment04;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A panel which displays the graphics for a hangman game.
 * author: Sergey Germanov
 */

public class HangmanPanel extends JPanel {

    private int number = 8;
    private boolean won;
    private boolean p;
    private Color x;
    private int y = 110;


    HangmanPanel() {
        setPreferredSize( new Dimension( 200,200 ) );
    }


    public void rePaint( int number,boolean won ) {
        this.number = number;
        this.won = won;

//        starts timer
        if (this.number == 0 || this.won) {
            Timer timer = new Timer( 1000,new ActionListener() {
                public void actionPerformed( ActionEvent e ) {
                    x = (p ? Color.blue : Color.red);
                    p = !p;
                    y = (p && won ? 50 : 110);
                    repaint();
                }
            } );
            timer.start();
        }
        repaint();
    }

    /**
     * Draws the hangman
     */
    @Override
    protected void paintComponent( Graphics g ) {
        super.paintComponent( g );

        // Outline (should always be drawn)

        g.drawRect( 0,0,199,199 );

        if (number <= 7 && !won) {
            // Gallows
            g.drawLine( 150,20,150,199 );
            g.drawLine( 50,20,150,20 );
        }
        if (number <= 6 && !won || won) {
//        set color after winning and changes it in animation
            g.drawOval( 30,30,40,40 );
            if (!p) {
                //            draw eyes
                g.drawOval( 30,30,40,40 );
                g.setColor( Color.BLUE );
                g.setColor( x );
                g.fillOval( 40,40,7,7 );
                g.setColor( Color.BLACK );
                g.drawOval( 40,40,7,7 );
                g.setColor( Color.BLUE );
                g.setColor( x );
                g.fillOval( 53,40,7,7 );
                g.setColor( Color.BLACK );
                g.drawOval( 53,40,7,7 );
                g.drawLine( 42,58,59,58 );
            }
        }

        if (p && !won) {
            //        sad eyes
            g.setColor( x );
            g.fillOval( 40,40,7,7 );
            g.setColor( Color.BLACK );
            g.drawOval( 40,40,7,7 );
            g.drawLine( 40,40,47,47 );
            g.drawLine( 40,47,47,40 );
            g.setColor( x );
            g.fillOval( 53,40,7,7 );
            g.setColor( Color.BLACK );
            g.drawOval( 53,40,7,7 );
            g.drawLine( 53,40,60,47 );
            g.drawLine( 53,47,60,40 );
            //        sad mouth
            g.drawLine( 45,60,50,55 );
            g.drawLine( 50,55,55,60 );
        } else if (p && won) {
//        happy eyes
            g.drawLine( 40,45,43,40 );
            g.drawLine( 43,40,46,45 );
            g.drawLine( 53,45,56,40 );
            g.drawLine( 56,40,59,45 );
//       Happy mouth
            g.drawLine( 42,55,58,55 );
            g.drawLine( 42,55,50,63 );
            g.drawLine( 50,63,58,55 );
        }

        if (number <= 5 && !won || won) {
            // Body
            g.drawLine( 50,70,50,150 );
        }

        if (number <= 4 && !won || won) {
            // Arm 1
            g.drawLine( 50,80,20,y );
        }

        if (number <= 3 && !won || won) {
            // Arm 2
            g.drawLine( 50,80,80,y );
        }
        if (number <= 2 && !won || won) {
            // Leg 1
            g.drawLine( 50,150,20,180 );
        }
        if (number <= 1 && !won || won) {
            // Leg 2
            g.drawLine( 50,150,80,180 );
        }
        if (number == 0 && !won) {
            // Noose
            g.drawLine( 50,20,50,30 );
        }
    }
}
