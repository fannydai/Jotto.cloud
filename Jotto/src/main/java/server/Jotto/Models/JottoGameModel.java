package server.Jotto.Models;

import java.util.ArrayList;
import org.springframework.data.annotation.Id;

/**
 * POJO representation of Jotto game
 */
public class JottoGameModel {
    @Id
    public String id;
    private String answerword;
    private ArrayList<JottoMoveModel> moves;
    private int moveamount;
    private final int moveLimit;

    public JottoGameModel() {
        this.moveLimit = 15;
        this.answerword = generateAnswerWord();
        this.moveamount = 0;
        this.moves = new ArrayList<JottoMoveModel>();
    }

    public boolean evaluate() {
        return true;
    }

    private String generateAnswerWord() {
        return "Answer";
    }

    public void addNewMove(JottoMoveModel newMove) {
        this.moves.add(newMove);
    }

    public String getAnswerWord(){
        return this.answerword;
    }
    public void setAnswerWord(String newAnswerWord) {
        this.answerword = newAnswerWord;
    }
    public ArrayList<JottoMoveModel> getMoves() {
        return this.moves;
    }
    public void setMoves(ArrayList<JottoMoveModel> newMoveList) {
        this.moves = newMoveList;
        this.moveamount = moves.size();
    }
    public int getMoveAmount() {
        return this.moveamount;
    }
    public int getMoveLimit() {
        return this.moveLimit;
    }
}