package server.Jotto.Models;

import org.springframework.data.annotation.Id;
import java.util.ArrayList;
/**
 * The User class is the User model stored into mongodb
 */

//todo -- password hashing, currently password is stored in plain text on the database

public class User{
    @Id
    public String id;

    public String username;
    public String password;
    private ArrayList<JottoGameModel> games;    

    public User(){

    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.games = new ArrayList<JottoGameModel>();
    }

    @Override
    public String toString(){
        return String.format("User[id = %s, username = %s, password = %s]",id,username,password);
    }

    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return this.username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }
    public ArrayList<JottoGameModel> getGamesList(){
        return this.games;
    }
    public void emptyGamesList(){
        this.games = new ArrayList<JottoGameModel>();
    }
    public void setGamesList(ArrayList<JottoGameModel> newGamesList){
        this.games = newGamesList;
    }
}
