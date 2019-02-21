package server.Jotto.Models;

import java.util.ArrayList;

/**
 * POJO model representation of move in Jotto game.
 */
public class JottoMoveModel {
    ArrayList<String> guessedWord;

    public JottoMoveModel() {
        guessedWord = new ArrayList<String>();
    }

    public void addGuessedWord(String newGuessedWord) {
        guessedWord.add(newGuessedWord);
    }
    public ArrayList<String> getGuessedWords() {
        return this.guessedWord;
    }
}