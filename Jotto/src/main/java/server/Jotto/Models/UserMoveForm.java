package server.Jotto.Models;

public class UserMoveForm {
    private String move;
    private String gameId;

    public UserMoveForm(){

    }
    public String getMove() {
        return this.move;
    }
    public void setMove(String move) {
        this.move = move;
    }
    public void setGameId(String username) {
        this.gameId = username;
    }
    public String getGameId() {
        return this.gameId;
    }
}