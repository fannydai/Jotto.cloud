package server.Jotto.Models;

import org.springframework.data.annotation.Id;
import java.util.ArrayList;
import org.springframework.security.crypto.bcrypt.BCrypt;
/**
 * The User class is the User model stored into mongodb
 */

//todo -- password hashing, currently password is stored in plain text on the database

public class UserModel {
    @Id
    public String id;
    private String username;
    private String password;
    private String salt;
    private ArrayList<JottoGameModel> games;

    public UserModel() {

    }

    public UserModel(String username, String password) {
        this.username = username;
        this.setPassword(password);
        this.games = new ArrayList<JottoGameModel>();
    }

    @Override
    public String toString() {
        return String.format("User[id = %s, username = %s, password = %s]",id,username,password);
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return this.username;
    }
    public void setPassword(String password) {
        this.salt = BCrypt.gensalt();
        this.password = BCrypt.hashpw(password, this.salt);
    }
    public String getPassword() {
        return this.password;
    }
    public boolean checkPassword(String password) {
        if (BCrypt.checkpw(password, this.password))
            return true;
        else
            return false;
    }
    public ArrayList<JottoGameModel> getGamesList() {
        return this.games;
    }
    public void emptyGamesList() {
        this.games = new ArrayList<JottoGameModel>();
    }
    public void setGamesList(ArrayList<JottoGameModel> newGamesList) {
        this.games = newGamesList;
    }
}
