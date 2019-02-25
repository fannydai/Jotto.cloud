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
    // Words that the bot has to calclate
    private ArrayList<Word> botWords;
    // -1 = not in word. 0 = idk. 1 = is in word. Chars that indicate if it is in the word the bot is guessing.
    private int[] botLetters;
    
    public JottoGameModel(String answerWord, ArrayList<String> botDict) {
        this.size = 5;
        this.botDict = botDict;
        this.botWords = new ArrayList<Word>();
        this.botLetters = new int[26];

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
            if(botLetters[(int)c-65]==0) {
                botLetters[(int)c-65] = (flag ? 1 : -1);
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
     * Removes all letters from given word.
     * If the value d.n.e in actual word, remove it from the String guess
     * If the value exist (botLettters[char]=1) then remove that letter and also -- on amtMatch
     * 
     * @param guess     Takes in a char and removes words with/without that char       
     */
    private String removeLetter(Word word){
        String temp = "";
        for(int i=0; i<word.getGuess().length(); i++){
            for(int j=0; j<userMoves.getWord().length(); j++){
                if(word.getGuess().charAt(i) == userMoves.getWord().charAt(j)) //detects matching character
                    botLetters[word.getGuess().charAt(i)-65] = 1; //setting to true if character is in guessed word
                else
                    temp += word.getGuess().charAt(i); //adds all letter not in guess to temporary string
            }
        }
        botWords.add(new Word(temp, word.getAmtMatch()));
        return temp;
    }

    /*
     * Find the common characters that both str1 & str2 has. Return those values as a string.
     * 
     * @param guess     String1 to look for common letters
     */
    private void filterBotWord(Word word) {
        removeLetter(word);
        for(Word w : botWords) {
            
            // case 1: If the difference of the 2 amtMatch are 1 & the String only have 1 value in difference
        }
    }

    private void botLogic(String guess) {
        int amtMatch = botMoves.addGuessWord(guess);
        if(amtMatch == 0) {                         // If none of the letters were guessed
            removeWord(guess, false);
        } else if(amtMatch == guess.length()) {     // If all the letters matched
            removeWord(guess, true);
        } else {
            // remove all letters we know are in/not in the String.
            Word word = new Word(guess, amtMatch);
            word = removeLetter(word);
            filterBotWord(word);
        }
    }
}