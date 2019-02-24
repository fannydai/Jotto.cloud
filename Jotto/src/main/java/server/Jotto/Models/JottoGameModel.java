package server.Jotto.Models;

import java.util.ArrayList;
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
    // -1 = not in word. 0 = idk. 1 = is in word. Chars that indicate if it is in the word the bot is guessing.
    private Integer[] botLetters;

    public JottoGameModel(String answerWord, ArrayList<String> botDict) {
        this.size = 5;
        botLetters = new Integer[26];           // Default value is set to 0
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
    private void removeWord(char character, boolean flag) {
        if(botLetters[((int)character)-65] != 0)
            return;

        if(flag) {
            botLetters[((int)character)-65] = 1;
        } else if(!flag) {
            botLetters[((int)character)-65] = -1;
        }

        ArrayList<String> tempBotWordList = new ArrayList<String> ();
        for(String word : botDict) {
            boolean hasChar = false;
			for(char c: word.toCharArray()) {
                if(character == c)
                    hasChar = true;
            }
            if(hasChar == flag)
                tempBotWordList.add(word);
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