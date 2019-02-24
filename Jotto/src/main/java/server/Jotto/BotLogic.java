package server.Jotto;

import java.util.ArrayList;

public class BotLogic {
    // A list of words that the bot can guess.
    private ArrayList<String> botWordList;
    // -1 = not in word. 0 = idk. 1 = is in word. Chars that indicate if it is in the word the bot is guessing.
    private Integer[] botLetters;

    public BotLogic(ArrayList<String> botWordList) {
        this.
    }

    private String generateAnswerWord() {
        return botWordList.get((int)Math.random() * botWordList.size());
    }

    /*
     * Removes all possible guess words for the bot with that certain char -- THIS WAS TESTED AND WORKS -- FANNY
     * @param character     Takes in a char and removes words with/without that char
     * @param flag          True = character is in the word. Find all words that do not have it and remove it
     *                      False = character is not in the word. Find all words that have this char and remove it
     */
    private void removeChar(char character, boolean flag) {
        ArrayList<String> tempBotWordList = new ArrayList<String> ();
        for(String word : botWordList) {
            boolean hasChar = false;
			for(char c: word.toCharArray()) {
                if(character == c)
                    hasChar = true;
            }
            if(hasChar == flag)
                tempBotWordList.add(word);
		}
		botWordList = tempBotWordList;
    }
        
}