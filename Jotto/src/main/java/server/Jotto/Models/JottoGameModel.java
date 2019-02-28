package server.Jotto.Models;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * POJO representation of Jotto game
 * 
 * This is where the game takes place.
 */
public class JottoGameModel {
    @Id
    public String id;
    private int size;
    private String answerWord;

    // A list of words that the bot can guess.
    private ArrayList<String> botDict;
    // Words that the bot has to calclate
    private ArrayList<Word> botWords;
    // -1 = not in word. 0 = idk. 1 = is in word. Chars that indicate if it is in the word the bot is guessing.
    private int[] botLetters;

    private JottoMoveModel userMoves;
    private JottoMoveModel botMoves;
    
    /*
     * @param answerWord    the word which user wants the bot to guess
     * @botDict             a complete dictionary with valid words
     */
    public JottoGameModel(String answerWord, ArrayList<String> botDict) {
        this.size = 5;
        this.botDict = botDict;
        this.answerWord = answerWord;
        this.botWords = new ArrayList<Word>();
        this.botLetters = new int[26];
        Arrays.fill(this.botLetters, 0);

        this.userMoves = new JottoMoveModel(generateAnswerWord(), this.size);
        this.botMoves = new JottoMoveModel(answerWord, this.size);
    }

    private String generateAnswerWord() {
        return botDict.get((int)(Math.random() * botDict.size()));
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
    public void setSize(int size) {
        this.size = size;
    }

    /*
     * #########################################################################################################
     *                                  THIS IS WHERE THE User LOGIC BEGINS
     * #########################################################################################################
     */
  

    /*
     * This handles the users logic for the game. We do not need to do anything except let the user know
     * if their word is valid or not. And if is valid, it will return the amt of letters matched to the 
     * actual word.
     * 
     * @param guess     The word which the user gave
     * @return          -1 if the word is not valid
     *                  6 if the user won
     *                  0-5 the amt of letters matched   
     */
    public int userLogic(String guess) {
        if(userMoves.won(guess)) {
            userMoves.addGuessWord(guess);
            return 6;
        }
        return userMoves.addGuessWord(guess);
    }

    /*
     * #########################################################################################################
     *                                  THIS IS WHERE THE BOT LOGIC BEGINS
     * #########################################################################################################
     */

    /*
     * Fills out a boolean array where each index is associated with a letter
     * If str has this letter, the index associated will = true
     * o.w. this associated index = false
     * helper method for the other methods in this class
     * 
     * @param str       A string to fill the boolean array
     * @return          A boolean array with the associated values of string
     */
    private boolean[] letters(String str) {
        boolean[] l = new boolean[26];
        Arrays.fill(l, Boolean.FALSE);
        for(char c : str.toCharArray())
            l[(int)c - 65] = true;
        return l;
    }

    /*
     * Removes all possible guess words for the bot with that certain char using the botLetters
     */
    private void removeWordDict() {
        ArrayList<String> tempBotWordList = new ArrayList<String> ();

        // Fills out the boolean array. Also sets botLetters to the correct value.
        boolean[] got = new boolean[26];
        boolean[] nogot = new boolean[26];
        Arrays.fill(got, Boolean.FALSE);
        Arrays.fill(nogot, Boolean.FALSE);
        boolean gotCounter = false;

        // fill in the arrays accordingly
        for(int num=0; num<botLetters.length; num++) {
            int val = botLetters[num];
            if(val<0) {             // no got
                nogot[num] = true;
            } else if(val>0) {      // got
                got[num] = true;
                gotCounter = true;
            }
        }

        for(String w : botDict) {
            boolean hasChar = false;
            boolean noHasChar = false;
            for(char c : w.toCharArray()) {
                if(nogot[(int)c - 65]) {
                    noHasChar = true;
                }  else if(got[(int)c - 65]) {        // There is a letter which we found is def in the actual string
                    hasChar = true;
                }
            }

            // !noHasChar means all letters are either 0 or 1
            // If we have 1 -> that means that that letter must be in the word. Thus, we must add it in.
            // If there are no 1's ... only 0 & -1 then we only add in 0's
            if((!noHasChar && hasChar && gotCounter) || (!noHasChar && !gotCounter))
                tempBotWordList.add(w);
		}
        botDict = tempBotWordList;
    }

    /*
     * Removes all letters (botLetters) from given word.
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
                temp += c;      // Add into the string. We are keeping
            } else if(botLetters[(int)c-65]>0) {
                amtMatch --;    // Do not add into temp (remove the letter) & amtMatch-- bc letter is in the actual word.
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
                // removeWordDict(Character.toString(c), true);
            }
        }
        for(char c : w2.getGuess().toCharArray()) {
            // Char found. This char is def NOT in the actual word
            if(!l[(int)c-65]) {
                this.botLetters[(int)c-65] = -1;
                // removeWordDict(Character.toString(c), false);
            }
        }
    }

    /*
     * Find the common characters that both str1 & str2 has. Return those values as a string.
     * The case with Ramps3 and Lamps2
     * 
     * @param word     The word which we are checking with the other strings
     */
    private void filterBotWord(Word word) {
        for(Word w : botWords) {
            // Make sure you are not comparing the same word
            if(w.getGuess().equals(word.getGuess()))
                continue;
            // case 1: If the difference of the 2 amtMatch are 1 & the String only have 1 value in difference
            String union = union(word.getGuess(), w.getGuess());
            if(w.getGuess().length() == word.getGuess().length() && union.length() == w.getGuess().length()-1) {
                if (w.getAmtMatch() + 1 == word.getAmtMatch()) {
                    caseOne(word, w, union);
                } else if (word.getAmtMatch() + 1 == w.getAmtMatch()) {
                    caseOne(w, word, union);
                }
            }
        }
    }

    /*
     * Bot won
     * User won
     * -User enter word that is invalid
     * Continue game
     */
    public String botLogic() {
        // The bots random word
        String botWord = generateAnswerWord();
        int amtMatch = botMoves.addGuessWord(botWord);
        while(amtMatch == -1) {
            botWord = generateAnswerWord();
            amtMatch = botMoves.addGuessWord(botWord);
        }
        if(botMoves.won(botWord)) {
            botMoves.addGuessWord(botWord);
            return "Bot won!";
        }
        botDict.remove(botWord);

        // remove all letters we know are in/not in the String.
        Word word = new Word(botWord, amtMatch);
        botWords.add(word);

        if(amtMatch == 0) {                         // If none of the letters were guessed
            for(char c : botWord.toCharArray())
                botLetters[(int)c-65] = -1;
        } else if(amtMatch == this.size) {          // If all the letters matched
            for(char c : botWord.toCharArray())
                botLetters[(int)c-65] = 1;
        } else {
            filterBotWord(word);
        }

        for(Word w: botWords)
            filterBotWord(w);
        // Update our list -- according to the letters
        for(Word w : botWords)
            w = removeLetter(w);
        // Remove words we don't need
        removeWordDict();

        return botWord+amtMatch;
    }
}