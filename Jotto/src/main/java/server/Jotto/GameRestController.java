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

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/playPage", method = RequestMethod.POST, consumes = {"application/json"}) //MAKE A PAGE FOR THE ACTUAL GAME
    @ResponseBody
    public void UserMakeGuess(){
        //validate guess
        //cal num matching letters
        //add to db 
        //check winner
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

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/playPage", method = RequestMethod.POST, consumes = {"application/json"}) //MAKE A PAGE FOR THE ACTUAL GAME
    @ResponseBody
    public void ShowGuesses(){ //of current game of user and comp
        //go through db and get guesses
        //cal num matching letters
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = {"application/json"}) //index page??
    @ResponseBody
    public void ShowPastGameResults(){
        //go thorugh db and shows games results
    }

    public int CalNumMatchingLetters(String guess, String realWord){
        int numMatches = 0;
        for(int i=0; i<realWord.length(); i++){
            for(int j=0; j<guess.length(); j++){
                if((realWord.charAt(i) == guess.charAt(j)) && 
                    (guess.lastIndexOf(guess.charAt(j)) == realWord.lastIndexOf(realWord.charAt(j)))){ //second statement checks for duplicate letters
                    numMatches+=1;
                }
            }
        }
        return numMatches;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/playPage", method = RequestMethod.POST, consumes = {"application/json"}) //MAKE A PAGE FOR THE ACTUAL GAME
    @ResponseBody
    public void CalcCompGuess(String prevUserGuess){ //comp algo

    }
}