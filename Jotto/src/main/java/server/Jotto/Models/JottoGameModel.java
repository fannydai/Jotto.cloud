package server.Jotto.Models;

import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
import org.springframework.data.annotation.Id;

/**
 * POJO representation of Jotto game
 */
public class JottoGameModel {
    @Id
    public String id;
    private final int size;
    private JottoMoveModel userMoves;
    private JottoMoveModel botMoves;
    private ArrayList<String> botWordList;

    public JottoGameModel(String answerWord) {
        this.size = 5;
        fillUpWords();
        this.userMoves = new JottoMoveModel(generateAnswerWord(), this.size);
        this.botMoves = new JottoMoveModel(answerWord, this.size);
    }

    /*
     * This method fills up the possible words that the bot can call. It should only be called once!!!
     */
    private void fillUpWords() throws IOException {
        File file = new File("../../../../../../dictionary.txt");

    }
    private String generateAnswerWord() {
        return "Answer";
    }

    public JottoMoveModel getUserMoves() {
        return this.userMoves;
    }
    public JottoMoveModel getBotMoves() {
        return this.botMoves;
    }
    
}

// Computer's word + how many match the users answer word