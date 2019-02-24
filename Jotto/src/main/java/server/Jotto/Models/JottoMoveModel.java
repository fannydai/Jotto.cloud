package server.Jotto.Models;

import java.util.Map;
import java.util.LinkedHashMap;

/**
 * POJO model representation of move in Jotto game.
 * NOTE: We are assuming all words are UPPER case. Front-end will pass in uppercase & dictionary will ALWAYS be in uppercase
 * 
 * This class is the moves of the user and the bot. All moves go here. Verifying the word will also be done here.
 */
public class JottoMoveModel {
    private String word;
    private final int size;
    Map<String, Integer> guessedWord;

    public JottoMoveModel(String word, int size) {
        this.word = word;
        this.size = size;
        this.guessedWord = new LinkedHashMap<String, Integer>();
    }

    /* 
     * Calculates the amount of letters matched.
     * @param guess     Word which was guessed
     * @return          The amt of chars that was matched.
     *                  -1 if the guess word is not valid.
     */
    public int addGuessWord(String guess) {
        if(!validateWord(guess))
            return -1;
        int amtMatch = amtMatch(guess);
        this.guessedWord.put(guess, amtMatch);
        return amtMatch;
    }

    /*
     * Counts the amount of matching letters between the actual and guessed word
     * 
     * @param guess     The word which was guessed
     * @param actual    The actual word
     * 
     * @return          The total amount of letters that overlap btw guess & actual
     */
    private int amtMatch(String guess) {
        int counter = 0;
        boolean[] letters = new boolean[26];
        
        for(int i=0; i<size; i++) {
            letters[this.word.charAt(i)-65] = true;
        }
        for(int i=0; i<size; i++) {
            if(letters[guess.charAt(i)-65] == true) {
                counter++;
            }
        }
        return counter;
    }

    /*
     * Checks to ensure passed in parameter is exactly length 5 and all leters are unique.
     * Makes sure the word was not already used.
     * 
     * @param word      String which the user typed & needs to be checked
     * @return          true if word is length 5 and all letters are unique (case insensitive) and word was not used previously
     */
    private boolean validateWord(String guess) {
        boolean[] letters = new boolean[26];
        if(guess.length() == this.size && !this.guessedWord.containsKey(guess)) {
            // Make sure all letters are unique
            for(int i=0; i<this.size; i++) {
                if(letters[guess.charAt(i)-65] == true)
                    return false;
                else
                    letters[guess.charAt(i)-65] = true;
            }
            return true;
        }
        return false;
    }

    public String getWord() {
        return this.word;
    }
    public int getSize() {
        return this.size;
    }
    public Map<String, Integer> getGuessedWords() {
        return this.guessedWord;
    }
}