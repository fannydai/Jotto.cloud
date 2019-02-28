package server.Jotto.Models;

public class BotMoveResult {
    private String result;
    private String word;

    public BotMoveResult() {

    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String res) {
        this.result = res;
    }

    public String getWord() {
        return this.word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}