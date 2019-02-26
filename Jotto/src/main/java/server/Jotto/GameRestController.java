package server.Jotto;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

import server.Jotto.Models.*;

@RestController
public class GameRestController {

    @Autowired
    JottoGameModelRepository gameRepository;
    @Autowired
    private UserRepository userRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/pickWord", method = RequestMethod.POST, consumes = {"application/json"}) //pickword game.service.ts
    @ResponseBody
    public PickWordResult pickWord(@RequestBody PickWordForm form ){
        PickWordResult res = new PickWordResult();
        final  ArrayList<String> dictionary = fillUpWords();
        if(form.getWord().length() == 5 && form.getWord().matches("[a-zA-Z]+") && dictionary.contains(form.getWord())){
            JottoGameModel newGame = new JottoGameModel(form.getWord(), dictionary);
            gameRepository.save(newGame);

            UserModel user = userRepository.findByusername(form.getUsername());
            user.getGamesList().add(newGame.id);
            userRepository.save(user);
            res.setValid(true);
        }else{
            res.setValid(false);
        }
        
        return res;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/1", method = RequestMethod.POST, consumes = {"application/json"}) //MAKE A PAGE FOR THE ACTUAL GAME
    @ResponseBody
    public void UserMakeGuess(@RequestBody String user) { //takes json, send back matching num letters as json
        //validate guess
        //cal num matching letters
        //add to db 
        //check winner
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/2", method = RequestMethod.POST, consumes = {"application/json"}) //MAKE A PAGE FOR THE ACTUAL GAME
    @ResponseBody
    public void showGuesses() { //of current game of user and comp **add current user as arg**
        //go through db and get guesses
        //cal num matching letters
        //sends back json
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/3", method = RequestMethod.POST, consumes = {"application/json"}) //index page??
    @ResponseBody
    public void showPastGameResults(@RequestBody String user) { //add user as arg
        //go thorugh db and shows games results
        //takes a user as json
        //sends back json
    }

    // @CrossOrigin(origins = "http://localhost:4200")
    // @RequestMapping(value = "/playPage", method = RequestMethod.POST, consumes = {"application/json"}) //MAKE A PAGE FOR THE ACTUAL GAME
    // @ResponseBody
    // public String calcCompGuess() { //GENERATING RANDOM 5 LETTER WORDS FOR NOW
    //     int rand_num = ThreadLocalRandom.current().nextInt(0, random5LetterWords.size()); //2nd arg is exclusive
    //     return random5LetterWords.get(rand_num);
    // }

    /*
     * Used for the first word the user enters - the word the bot will guess
     * Want to make sure it is valid
     * 
     * @param s     The string which the user passed in - want the bot to guess
     * @param dict  The dictionary with all the word (was called using fullUpWords - just pass that in)
     * 
     * @return      True if the word is in the dictionary
     *              False if the word is not valid
     */
    private boolean validGuessWord(String s, ArrayList<String> dict) {
        if(!s.matches("^[A-Z][A-Z][A-Z][A-Z][A-Z]$"))
            return false;
        for(String w : dict) {
            if(w.equals(s))
                return true;
        }
        return false;
    }
    /*
     * This method fills up the possible words that the bot can call. It should only be CALLED ONCE!!!
     * Bc it will prob take mad long. I'm WARNING YOU!!!
     */
    private ArrayList<String> fillUpWords() {
        ArrayList <String> dictionary = new ArrayList<String>();
        try {
            FileReader fileReader = new FileReader(new File(System.getProperty("user.dir") + "/src/main/java/server/Jotto/" + "dictionary.txt"));
            BufferedReader buffReader = new BufferedReader(fileReader);
            String word;
            while ((word = buffReader.readLine()) != null) {
                dictionary.add(word);
            }
            fileReader.close();
            
        } catch (FileNotFoundException e) {
			System.out.println("\nFile Not Found.\n");
		} catch(NoSuchElementException e){
			System.out.println("File not correctly formatted.\n");
		} catch (Exception ex) {

        }
        return dictionary;
    }
}