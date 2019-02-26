package server.Jotto.Models;

public class Word {
    @Id
    public String id;
    private String guess;
    private int amtMatch;

    public Word(String guess, int amtMatch) {
        this.guess = guess;
        this.amtMatch = amtMatch;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }
    public void setAmtMatch(int amtMatch) {
        this.amtMatch = amtMatch;
    }

    public String getGuess() {
        return this.guess;
    }
    public int getAmtMatch() {
        return this.amtMatch;
    }
}