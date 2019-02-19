package server.Jotto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.security.Principal;
import server.Jotto.Models.*;


import java.util.*;

@RestController
public class GameRestController{

    ArrayList<String> user_guesses = new ArrayList<>(); //used to store user guesses
    ArrayList<String> comp_guesses = new ArrayList<>(); //used to store comp guesses
    String users_selected_word; //the word the comp needs to guess for it to win
    String comps_selected_word; //the word the user needs to guess for it ot win

    //basic template for game logic
    //ADD DECORATORS AND SPRING SHIT!!
    // @everyone: add more funcitons/ other shit if neccessary lol

    /**
     * methods we need:
     * 1. make a guess
     * 2. computer guess (algo)
     * 3. check winner
     * 4. display past game results
     * 5. validate word
     * 6. show user & comp guesses
     * 7. primary function that implements game
     */

     /**
      * validate word
      * determine # of letter matches
      * add guess word & # of matches to db
      * check if it's the same as computer's guess
      * check winner
      */
     public void MakeGuess(){
          
     }

    /**
     * determine word to guess
     * determine # of letter matches
     * add guess word & # of matches to db
     * check if it's users guess
     * check winner
     */
     public void PlayComputer(){

     }

     /**
      * checks equality of both
      * @param user_guess
      * @param comps_word
      * @return if both guesses are equal or not
      */
     public boolean CheckWinner(String user_guess, String comps_word){
         return user_guess.equals(comps_word);
     }

    /**
     * shows words seleced by user and comp
     * shows list of guesses by user and comp
     * calculates num of letters of each guess matched with each word
     */
     public void ShowPastResults(){

     }

     /**
      * check if word is 5 letters and has no repeating letters
      * @param word_to_check
      * @return if word is valid or not
      */
     public boolean ValidateWord(String word_to_check){
         if(word_to_check.length() == 5){
            for (int i = 0; i < word_to_check.length(); i++) 
                for (int j = i + 1; j < word_to_check.length(); j++) 
                    if (word_to_check.charAt(i) == word_to_check.charAt(j)) 
                        return false; 
            return true;
         }
         else
            return false;
     }

     /**
      * shows guesses of both user and comp in current game
      */
     public void ShowBothGuesses(){

     }

     /**
      * main function that implements game
      */
     public void PlayGame(){

     }
}