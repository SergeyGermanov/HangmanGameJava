package ictgradschool.industry.assignment04;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * author: Sergey Germanov (sger197)
 */

public class Game {

    public String secret;
    String guess;
    private Set<String> correctGuesses = new TreeSet<>();
    protected int numberOfGuesses = 8;
    String revieled = "";
    String warning;


    List<HangmanListener> listeners = new ArrayList<>();


    Game( String secret ) {
        this.secret = secret;
    }

    //check if word is guessed or not
    public boolean hasWon() {
        return guess.equals( secret ) || allLettersGuessed();
    }

    public void addListeners( HangmanListener i ) {
        listeners.add( i );
    }

    //check if the character guessed or not. If yes - add to the list of correctGuesses
    public void charactersUsed( String guess ) {
        if (secret.contains( guess )) {
            correctGuesses.add( guess );
        } else if (warning == null || guess.length() != warning.length()) {
            numberOfGuesses--;
        }
    }

    //checks if all the characters of the word were guessed
    public boolean allLettersGuessed() {
        Set<String> secretSet = new TreeSet<>();
        for (int i = 0; i < secret.length(); i++) {
            secretSet.add( String.valueOf( secret.charAt( i ) ) );
        }
        correctGuesses.remove( "" );
        return secretSet.equals( correctGuesses );
    }

    //add characters to display
    public void addCharachter( String input ) {
        char charachter = ' ';
        warning = "";
        if (input.equals( "Please type a letter or a full word!" )) {
            input = "";
        }
        try {
            charachter = input.toUpperCase().charAt( 0 );
            String secretUpper = addSpaces( secret ).toUpperCase();
            char[] charArray = revieled.toCharArray();
            for (int index = secretUpper.indexOf( charachter ); index >= 0; index = secretUpper.indexOf( charachter,index + 1 )) {
                charArray[index] = charachter;
                revieled = new String( charArray );
            }
        } catch (StringIndexOutOfBoundsException e) {
            e.getMessage();
            warning = "Please type a letter or a full word!";
        }
    }


    //    add the spaces to the word. Used just before the display
    private String addSpaces( String word ) {
        char[] spaceArray = word.toCharArray();
        StringBuilder newArray = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            newArray.append( spaceArray[i] ).append( " " );
        }
        return newArray.toString();
    }

    public String changeTheLength( String secret ) {
        for (int i = 0; i <= secret.length() - 1; i++) {
            revieled += "_ ";
        }
        return revieled;
    }


    public String winnerCheck() {
        //checks if you still have tries and not guessed
        if (!hasWon() && numberOfGuesses == 0) {
            return "The secret word is " + "\"" + secret.toUpperCase() + "\"." + " Too bad, you lost!";
        }
        //            if user guess the full word than it prints the secret word and congratulations

        else if (hasWon()) {
            return "The secret word is  " + "\"" + secret.toUpperCase() + "\"." + "  Congratulations, you win!";

        }
//        otherwise print out how many guesses left
        return "You have " + numberOfGuesses + " guesses remaining.";
    }

}
