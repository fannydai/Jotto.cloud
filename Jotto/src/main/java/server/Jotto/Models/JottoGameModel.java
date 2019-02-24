package server.Jotto.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
import java.util.regex.*;
import org.springframework.data.annotation.Id;

/**
 * POJO representation of Jotto game
 * 
 * This is where the game takes place.
 */
public class JottoGameModel {
    @Id
    public String id;
    private final int size;

    private JottoMoveModel userMoves;
    private JottoMoveModel botMoves;

    // Words that the bot has to calclate
    private HashMap<String,Integer> botWords;

    // A list of words that the bot can guess.
    private ArrayList<String> botDict;
    // false means we don't know if is in word or not. True means it is either in word or not (We don't care bc we modify the dictionary as we figure it out)
    private Boolean[] botLetters;
    
    public JottoGameModel(String answerWord, ArrayList<String> botDict) {
        this.size = 5;
        this.botDict = botDict;

        botLetters = new Boolean[26];
        Arrays.fill(botLetters, Boolean.FALSE);

        this.userMoves = new JottoMoveModel(generateAnswerWord(), this.size);
        this.botMoves = new JottoMoveModel(answerWord, this.size);
    }

    private String generateAnswerWord() {
        return botDict.get((int)Math.random() * botDict.size());
    }

    public JottoMoveModel getUserMoves() {
        return this.userMoves;
    }
    public JottoMoveModel getBotMoves() {
        return this.botMoves;
    }
    public int getSize() {
        return this.size;
    }

    /*
     * #########################################################################################################
     *                          THIS IS WHERE THE BOT LOGIC BEGINS
     * #########################################################################################################
     */
    /*
     * Removes all possible guess words for the bot with that certain char -- THIS WAS TESTED AND WORKS -- FANNY
     * 
     * @param character     Takes in a char and removes words with/without that char
     * @param flag          True = character is in the word. Find all words that do not have it and remove it
     *                      False = character is not in the word. Find all words that have this char and remove it
     */
    private void removeWord(String str, boolean flag) {
        ArrayList<String> tempBotWordList = new ArrayList<String> ();

        // Make my regex for letters to remove
        String regex = "";
        for(char c : str.toCharArray()) {
            if(botLetters[(int)c-65]==false) {
                botLetters[(int)c-65] = true;
                regex += c;
            }
        }
        if(regex.length()==0) return;
        regex = "\\w*[" + regex + "]+\\w*";

        for(String dictWord : botDict) {
            if(Pattern.matches(regex, dictWord) == flag) {
                tempBotWordList.add(dictWord);
            }
		}
		botDict = tempBotWordList;
    }

    /*
     * Find the common characters that both str1 & str2 has. Return those values as a string.
     * 
     * @param str1      String1 to look for common letters
     * @param str2      String2 to look for common letters
     * 
     * @return          A String which each chars are in both str1 and str2
     */
    private String unique_char(String str1, String str2) {
        // boolean[] letters = new boolean[26];
        
        // for(int i=0; i<size; i++) {
        //     letters[this.word.charAt(i)-65] = true;
        // }
        // for(int i=0; i<size; i++) {
        //     if(letters[guess.charAt(i)-65] == true) {
        //         counter++;
        //     }
        // }

    }

    private void botLogic(String guess) {
        int amtMatch = botMoves.addGuessWord(guess);
        switch(amtMatch) {
            case 0:
                removeWord(guess, false);
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                if(botMoves.wong()) {
                    // Bot won
                } else {
                    removeWord(guess, true);
                }
                break;
            default:
                // there was an error with the word
        }
    }
}