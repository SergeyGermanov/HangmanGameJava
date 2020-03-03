package ictgradschool.industry.assignment04;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that allows you to pick a random word from a text file.
 * author: Sergey Germanov (sger197)
 */

public class RandomWords {

    private List<String> wordsList;

    /**
     * Creates the word generator by loading words.txt and adding all words in the file to wordList.
     */


    public RandomWords() {

        try (BufferedReader reader = new BufferedReader( new FileReader( "words.txt" ) )) {
            this.wordsList = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                this.wordsList.add( line );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Gets a random word from the set.
     *
     * @return a word
     */
    public String getRandomWord() {
        int i = (int) (Math.random() * this.wordsList.size());
        return this.wordsList.get( i );
    }

}
