package server.Jotto.Models;

import java.util.ArrayList;

/**
 * POJO model representation of computer guessed words
 */
public class JottoCompGuessModel {
    ArrayList<String> compGuess;

    public JottoCompGuessModel() {
        compGuess = new ArrayList<String>();
    }

    public void addCompGuessedWord(String newGuessedWord) {
        compGuess.add(newGuessedWord);
    }
    public ArrayList<String> getCompGuessedWords() {
        return this.compGuess;
    }
}