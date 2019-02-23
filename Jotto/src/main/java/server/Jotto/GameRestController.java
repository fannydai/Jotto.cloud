package server.Jotto;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.web.bind.annotation.*;
import server.Jotto.Models.JottoGameModelRepository;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import server.Jotto.Models.*;
import java.util.*;

@RestController
public class GameRestController /*implements JottoGameModelRepository*/{
    private int size = 5;
    ArrayList<String> random5LetterWords; // using for testing, gonna get rid of later

    // add decorators and spring shit
    // finish this class and add more models

    public GameRestController() throws IOException {
        random5LetterWords = new ArrayList<>();
        File file = new File("temp_words.txt"); //list of most common 5 letter words
		FileReader fileReader = new FileReader(file);
		BufferedReader buffReader = new BufferedReader(fileReader);
		String word;
		while ((word = buffReader.readLine()) != null) {
            random5LetterWords.add(word);
		}
		fileReader.close();
    }
    /**
     * IMPLEMENT THESE 2 LATER
     */

    // public JottoGameModel findByid(String id) { 
    //     return NULL;
    // }
    // public List<JottoGameModel> findAll(){
    //     return NULL;
    // }

    // public JottoGameModel findByid(String id){

    // }

    // @CrossOrigin(origins = "http://localhost:4200")
    // @RequestMapping(value = "/playPage", method = RequestMethod.POST, consumes = {"application/json"}) //MAKE A PAGE FOR THE ACTUAL GAME
    // @ResponseBody
    public void UserMakeGuess(@RequestBody String user) { //takes json, send back matching num letters as json
        //validate guess
        //cal num matching letters
        //add to db 
        //check winner
    }

    public boolean checkWinner(String userGuess, String compWord) {
        return userGuess.equals(compWord);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/playPage", method = RequestMethod.POST, consumes = {"application/json"}) //MAKE A PAGE FOR THE ACTUAL GAME
    @ResponseBody
    public void showGuesses() { //of current game of user and comp 
        //go through db and get guesses
        //cal num matching letters
        //sends back json
    }

    // @CrossOrigin(origins = "http://localhost:4200")
    // @RequestMapping(value = "/", method = RequestMethod.POST, consumes = {"application/json"}) //index page??
    // @ResponseBody
    public void showPastGameResults(@RequestBody String user) { //add user as arg
        //go thorugh db and shows games results
        //takes a user as json
        //sends back json
    }

    /*
     * Counts the amount of matching letters between the actual and guessed word
     * 
     * @param guess     The word which was guessed
     * @param actual    The actual word
     * 
     * @return          The total amount of letters that overlap btw guess & actual
     */
    public int amtMatch(String guess, String actual) {
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
    public String calcCompGuess() { //GENERATING RANDOM 5 LETTER WORDS FOR NOW
        int rand_num = ThreadLocalRandom.current().nextInt(0, random5LetterWords.size()); //2nd arg is exclusive
        return random5LetterWords.get(rand_num);
    }
}