package server.Jotto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import server.Jotto.Models.*;

@RestController
public class GameRestController{
    //add decorators and spring shit
    // finish this class and add more models


    public void UserMakeGuess(){

    }

    public boolean ValidateWord(String to_check){
        if(to_check.length() == 5){
            for(int i=0; i<to_check.length(); i++){
                for(int j=i+1; j<to_check.length(); j++){
                    if(to_check.charAt(i) == to_check.charAt(j))
                        return false;
                }
            }
            return true;
        }
        else
            return false;
    }

    public boolean CheckWinner(String userGuess, String compWord){
        return userGuess.equals(compWord);
    }

    public void ShowGuesses(){ //of current game of user and comp

    }

    public void ShowPastGameResults(){

    }

    public void CalNumMatchingLetters(String guess, String realWord){

    }

    public void CalcCompGuess(String prevUserGuess){

    }
}