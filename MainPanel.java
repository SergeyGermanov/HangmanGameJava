package ictgradschool.industry.assignment04;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The main JPanel for Hangman, which contains all other GUI controls.
 * author: Sergey Germanov
 */


public class MainPanel extends JPanel implements HangmanListener, ActionListener {
//    from Game class

    private Game game = new Game( new RandomWords().getRandomWord() );
//    from Game class


    private JTextField txtGuess;
    private JLabel lblRevealedWord;
    private JLabel lblStatus;
    private JLabel lblYouAreGuessing;
    private HangmanPanel hangmanPanel;
    private JButton btnGuess;


    MainPanel() {

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0};
        gridBagLayout.rowHeights = new int[]{0,0,0,0};
        gridBagLayout.columnWeights = new double[]{1.0};
        gridBagLayout.rowWeights = new double[]{0,0,1.0,0};
        setLayout( gridBagLayout );

        JPanel pnlRevealedWord = new JPanel();
        GridBagConstraints gbc_pnlGuessing = new GridBagConstraints();
        gbc_pnlGuessing.insets = new Insets( 0,0,5,0 );
        gbc_pnlGuessing.gridx = 0;
        gbc_pnlGuessing.gridy = 0;
        add( pnlRevealedWord,gbc_pnlGuessing );

        lblYouAreGuessing = new JLabel( "You are guessing: " );
        pnlRevealedWord.add( lblYouAreGuessing );

        lblRevealedWord = new JLabel( game.changeTheLength( game.secret ) );
        pnlRevealedWord.add( lblRevealedWord );


        lblStatus = new JLabel( "You have 8 guesses remaining." );

        GridBagConstraints gbc_lblStatus = new GridBagConstraints();
        gbc_lblStatus.insets = new Insets( 0,0,5,0 );
        gbc_lblStatus.gridx = 0;
        gbc_lblStatus.gridy = 1;
        add( lblStatus,gbc_lblStatus );


        hangmanPanel = new HangmanPanel();
        GridBagConstraints gbc_hangmanPanel = new GridBagConstraints();
        gbc_hangmanPanel.insets = new Insets( 0,0,5,0 );
        gbc_hangmanPanel.gridx = 0;
        gbc_hangmanPanel.gridy = 2;
        add( hangmanPanel,gbc_hangmanPanel );

        JPanel pnlControls = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 3;
        add( pnlControls,gbc_panel );

        JLabel lblEnterYourGuess = new JLabel( "Enter your guess: " );
        pnlControls.add( lblEnterYourGuess );

        txtGuess = new JTextField();
        pnlControls.add( txtGuess );
        txtGuess.setColumns( 20 );

        btnGuess = new JButton( "Guess!" );
        pnlControls.add( btnGuess );
        btnGuess.addActionListener( this );

        game.addListeners( this );


    }


    private int getNumber() {
        return game.numberOfGuesses;
    }

    //listens for the press of the button and text entry
    public void actionPerformed( ActionEvent e ) {

        if (e.getSource() == btnGuess) {
            game.guess = txtGuess.getText();
            game.charactersUsed( game.guess );
            game.addCharachter( game.guess );
            update( game );
        }
    }

//    updates and repaint the panel
    @Override
    public void update( Game game ) {

        txtGuess.setText( game.warning );
        game.addCharachter( game.guess );
        lblRevealedWord.setText( game.revieled );
        lblStatus.setText( game.winnerCheck() );
        if (game.numberOfGuesses == 0 || game.hasWon() || game.allLettersGuessed()) {
            btnGuess.setEnabled( false );
            lblYouAreGuessing.setVisible( false );
            lblRevealedWord.setVisible( false );
        }
        hangmanPanel.rePaint( getNumber(),game.hasWon() );
    }
}
