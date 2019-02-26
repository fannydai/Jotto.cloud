package server.Jotto.Models;

import java.util.ArrayList;
import java.util.Arrays;
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

    // A list of words that the bot can guess.
    private ArrayList<String> botDict;
    // Words that the bot has to calclate
    private ArrayList<Word> botWords;
    // -1 = not in word. 0 = idk. 1 = is in word. Chars that indicate if it is in the word the bot is guessing.
    private int[] botLetters;

    private JottoMoveModel userMoves;
    private JottoMoveModel botMoves;
    
    public JottoGameModel(String answerWord, ArrayList<String> botDict) {
        this.size = 5;
        this.botDict = botDict;
        this.botWords = new ArrayList<Word>();
        this.botLetters = new int[26];
        Arrays.fill(this.botLetters, 0);

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
     *                          THIS IS WHERE THE User LOGIC BEGINS
     * #########################################################################################################
     */

  



    /*
     * #########################################################################################################
     *                          THIS IS WHERE THE BOT LOGIC BEGINS
     * #########################################################################################################
     */

    /*
     * Fills out a boolean array where each index is associated with a letter
     * If str has this letter, the index associated will = true
     * o.w. this associated index = false
     * 
     * @param str           A string to fill the boolean array
     * @return              A boolean array with the associated values of string
     */
    private boolean[] letters(String str) {
        boolean[] l = new boolean[26];
        Arrays.fill(l, Boolean.FALSE);
        for(char c : str.toCharArray())
            l[(int)c - 65] = true;
        return l;
    }

    /*
     * Removes all possible guess words for the bot with that certain char -- THIS WAS TESTED AND WORKS -- FANNY
     * 
     * @param character     Takes in a char and removes words with/without that char
     * @param flag          True = character is in the word. Find all words that do not have it and remove it
     *                      False = character is not in the word. Find all words that have this char and remove it
     */
    private void removeWordDict(String str, boolean flag) {
        ArrayList<String> tempBotWordList = new ArrayList<String> ();

        // Fills out the boolean array. Also sets botLetters to the correct value.
        boolean[] l = new boolean[26];
        Arrays.fill(l, Boolean.FALSE);
        for(char c : str.toCharArray()) {
            if(botLetters[(int)c-65] == 0) {
                botLetters[(int)c-65] = flag?1:-1;
                l[(int)c - 65] = true;
            }
        }

        for(String w : botDict) {
            boolean hasChar = false;
            for(char c : w.toCharArray()) {
                if(l[(int)c - 65]) hasChar = true;
            }
            // Filtering out the word accoring to the flag
            if(hasChar == flag) tempBotWordList.add(w);
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
    private Word removeLetter(Word word) {
        String temp = "";
        int amtMatch = word.getAmtMatch();
        for(char c : word.getGuess().toCharArray()) {
            if(botLetters[(int)c-65]==0) {
                temp += c;
            } else if(botLetters[(int)c-65]>0) {
                amtMatch --;
            }
        }
        word.setGuess(temp);
        word.setAmtMatch(amtMatch);
        return word;
    }

    /*
     * Looks for the union of chars ofstr1 & str2.
     * This is a helper method for filterBotWord.
     * 
     * @param str1      The first string which we will union
     * @param str2      The second string which we will union
     * @return          A String which has all chars which has letters which both str1 & str2 has
     */
    private String union(String str1, String str2) {
        boolean[] l = letters(str1);
        String common = "";

        for(char c : str2.toCharArray())
            // If the char in str2 is not in str1
            if(l[(int)c - 65])
               common += c; 
        return common;
    }

    /*
     * Filter out words accoring to w1 & w2.
     * This is a helper method for filterBotWord.
     * 
     * @param w1        The String value has a char which is in the actual word
     * @param w2        The String value has a char which is NOT in the actual word
     */
    private void caseOne(Word w1, Word w2, String common) {
        boolean[] l = letters(common);

        for(char c : w1.getGuess().toCharArray()) {
            // Char found. This char is def in the actual word
            if(!l[(int)c-65]) {
                this.botLetters[(int)c-65] = 1;
                removeWordDict(Character.toString(c), true);
            }
        }
        for(char c : w2.getGuess().toCharArray()) {
            // Char found. This char is def NOT in the actual word
            if(!l[(int)c-65]) {
                this.botLetters[(int)c-65] = -1;
                removeWordDict(Character.toString(c), false);
            }
        }

        // Update our list.
        for(Word w : botWords)
            removeLetter(w);
    }

    /*
     * Find the common characters that both str1 & str2 has. Return those values as a string.
     * 
     * @param guess     String1 to look for common letters
     */
    private void filterBotWord(Word word) {
        for(Word w : botWords) {
            // case 1: If the difference of the 2 amtMatch are 1 & the String only have 1 value in difference
            if(w.getAmtMatch() == 4) {
                String union = union(word.getGuess(), w.getGuess());
                if(union.length() != 4)
                    continue;
                // If the union (common chars = 4)
                if(w.getAmtMatch()+1 == word.getAmtMatch()) {
                    caseOne(word, w, union);
                } else if(word.getAmtMatch()+1 == w.getAmtMatch()) {
                    caseOne(w, word, union);
                }
            }
        }
    }

    private void botLogic(String guess) {
        int amtMatch = botMoves.addGuessWord(guess);
        if(amtMatch == -1) {
            // There was an error with the word. Not valid word
        }
        
        // remove all letters we know are in/not in the String.
        Word word = new Word(guess, amtMatch);
        word = removeLetter(word);
        if(amtMatch == 0) {                  // If none of the letters were guessed
            removeWordDict(guess, false);
        } else if(amtMatch == this.size) {          // If all the letters matched
            removeWordDict(guess, true);
        } else {
            filterBotWord(word);
        }
    }
}