package server.Jotto;

import org.springframework.web.bind.annotation.*;

@RestController
public class GameRestController{
    private int size = 5;
    //add decorators and spring shit
    // finish this class and add more models

    // @CrossOrigin(origins = "http://localhost:4200")
    // @RequestMapping(value = "/playPage", method = RequestMethod.POST, consumes = {"application/json"}) //MAKE A PAGE FOR THE ACTUAL GAME
    // @ResponseBody
    public void UserMakeGuess(){
        //validate guess
        //cal num matching letters
        //add to db 
        //check winner
    }

    /*
     * Checks to ensure passed in parameter is exactly length 5 and all leters are unique.
     * @param word      String which the user typed & needs to be checked
     * @return          true if word is length 5 and all letters are unique (case insensitive)
     */
    public boolean validateWord(String word) {
        boolean[] letters = new boolean[26];
        if(word.length() == size) {
            word = word.toUpperCase();
            for(int i=0; i<size; i++) {
                if(letters[word.charAt(i)-65] == true)
                    return false;
                else
                    letters[word.charAt(i)-65] = true;
            }
            return true;
        }
        return false;
    }

    public boolean checkWinner(String userGuess, String compWord){
        return userGuess.equals(compWord);
    }

    // @CrossOrigin(origins = "http://localhost:4200")
    // @RequestMapping(value = "/playPage", method = RequestMethod.POST, consumes = {"application/json"}) //MAKE A PAGE FOR THE ACTUAL GAME
    // @ResponseBody
    public void showGuesses(){ //of current game of user and comp **add current user as arg**
        //go through db and get guesses
        //cal num matching letters
    }

    // @CrossOrigin(origins = "http://localhost:4200")
    // @RequestMapping(value = "/", method = RequestMethod.POST, consumes = {"application/json"}) //index page??
    // @ResponseBody
    public void showPastGameResults(){ //add user as arg
        //go thorugh db and shows games results
    }

    /*
     * Counts the amount of matching letters between the actual and guessed word
     * 
     * @param guess     The word which was guessed
     * @param actual    The actual word
     * 
     * @return          The total amount of letters that overlap btw guess & actual
     */
    public int amtMatch(String guess, String actual){
        int counter = 0;
        boolean[] letters = new boolean[26];
        
        guess = guess.toUpperCase();
        actual = actual.toUpperCase();
        
        for(int i=0; i<size; i++) {
            letters[actual.charAt(i)-65] = true;
        }
        for(int i=0; i<size; i++) {
            if(letters[guess.charAt(i)-65] == true) {
                counter++;
            }
        }
        return counter;
    }

    // @CrossOrigin(origins = "http://localhost:4200")
    // @RequestMapping(value = "/playPage", method = RequestMethod.POST, consumes = {"application/json"}) //MAKE A PAGE FOR THE ACTUAL GAME
    // @ResponseBody
    public void calcCompGuess(@RequestBody String prevUserGuess){ //change arg for later (user)

    }
}