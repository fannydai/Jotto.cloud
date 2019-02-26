package server.Jotto.Models;

import java.util.ArrayList;

/**
 * POJO model representation of move in Jotto game.
 * NOTE: We are assuming all words are UPPER case. Front-end will pass in uppercase & dictionary will ALWAYS be in uppercase
 * 
 * This class is the moves of the user and the bot. All moves go here. Verifying the word will also be done here.
 */
public class JottoMoveModel {
    @Id
    public String id;
    private String word;
    private final int size;
    private ArrayList<Word> guessedWord;

    public JottoMoveModel(String word, int size) {
        this.word = word;
        this.size = size;
        this.guessedWord = new ArrayList<Word>();
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
        this.guessedWord.add(new Word(guess, amtMatch));
        return amtMatch;
    }

    /* 
     * Checks to see if the word was guessed
     * 
     * @param guess     Word which was guessed. Check to see if it is equal to the actual word.
     * @return          True if the guess word is equal to the actual word. False o.w.
     */
    public boolean won(String guess) {
        return this.word.equals(guess);
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
     * Checks to see if the word guess is in guessWord.
     * 
     * @param guess     The word to check if it is in guessWord
     * @return          true if word in the guessWord. false o.w.
     */
    private boolean containsWord(String guess) {
        for(Word w : guessedWord)
            if(w.getGuess().equals(guess))
                return true;
        return false;
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
        if(guess.length() == this.size && !this.containsWord(guess)) {
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
    public ArrayList<Word> getGuessedWords() {
        return this.guessedWord;
    }
}