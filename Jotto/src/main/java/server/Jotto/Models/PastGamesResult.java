package server.Jotto.Models;

import java.util.List;

public class PastGamesResult {
    private List<Object> botMovesList;
    private List<Object> userMovesList;

    public PastGamesResult() {

    }

    public List<Object> getBotMovesList() {
        return this.botMovesList;
    }

    public void setBotMovesList(List<Object> b) {
        this.botMovesList = b;
    }

    public List<Object> getUserMovesList() {
        return this.userMovesList;
    }

    public void setUserMovesList(List<Object> u) {
        this.userMovesList = u;
    }
}