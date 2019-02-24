package server.Jotto.Models;

import java.util.ArrayList;
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

    // A list of words that the bot can guess.
    private ArrayList<String> botDict;
    // false means we don't know if is in word or not. True means it is either in word or not (We don't care bc we modify the dictionary as we figure it out)
    private Boolean[] botLetters;

    public JottoGameModel(String answerWord, ArrayList<String> botDict) {
        this.size = 5;
        botLetters = new Boolean[26];           // Default value is set to false
        this.botDict = botDict;

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
     * @param character     Takes in a char and removes words with/without that char
     * @param flag          True = character is in the word. Find all words that do not have it and remove it
     *                      False = character is not in the word. Find all words that have this char and remove it
     */
    private void removeWord(char[] chars, boolean flag) {
        ArrayList<String> tempBotWordList = new ArrayList<String> ();

        // Make my regex for letters to remove
        String regex = "";
        for(char c : chars) {
            if(botLetters[(int)c-65]==false) {
                botLetters[(int)c-65] = true;
                regex += c;
            }
        }
        if(regex.length()==0) return;
        regex = "\"[" + regex + "] \"";

        for(String dictWord : botDict) {
            if(Pattern.matches(dictWord, regex) == flag) {
                tempBotWordList.add(dictWord);
            }
		}
		botDict = tempBotWordList;
    }

    private void botLogic(String guess) {
        int amtMatch = botMoves.addGuessWord(guess);
        switch (amtMatch) {
            case 0:
                // 0 - loop through the entire word and call removeWord(char, false)
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
                // 5 - check if won. If not, loop through word & call removeWord(char, true)
                break;
            default:
                // there was an error with the word
        }
}