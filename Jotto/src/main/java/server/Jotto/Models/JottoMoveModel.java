package server.Jotto.Models;

import java.util.Map;
import java.util.LinkedHashMap;

/**
 * POJO model representation of move in Jotto game.
 * NOTE: We are assuming all words are UPPER case. Front-end will pass in uppercase & dictionary will ALWAYS be in uppercase
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
     * Calculates the amount of letters matched. If the word matches, user/bot won.
     * @param guess             Word which was guessed
     * @return                  true if the user won
     *                          false o.w.
     */
    public boolean won(String guess) {
        int amtMatch = amtMatch(guess);
        this.guessedWord.put(guess, amtMatch);
        return amtMatch == this.size ? true : false;
    }

    /*
     * Counts the amount of matching letters between the actual and guessed word
     * 
     * @param guess     The word which was guessed
     * @param actual    The actual word
     * 
     * @return          The total amount of letters that overlap btw guess & actual
     */
    public int amtMatch(String guess) {
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
    public boolean validateWord(String guess) {
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