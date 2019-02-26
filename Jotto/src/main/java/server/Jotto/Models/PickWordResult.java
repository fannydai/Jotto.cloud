package server.Jotto.Models;

public class PickWordResult {
    private boolean valid;
    private String gameId;

    public PickWordResult(){

    }
    public boolean getValid(){
        return this.valid;
    }

    public void setValid(boolean b){
        this.valid = b;
    }

    public String getGameId(){
        return this.gameId;
    }

    public void setGameId(String b){
        this.gameId = b;
    }
    
}