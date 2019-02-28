import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.ArrayList;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;

import java.util.Scanner;

public class gameTest {
    public static void main(String[] args) {
        System.out.println("Starting new game");

        int amtMoves = 100;
        int sum = 0;
        for(int i=0; i<amtMoves; i++) {
            sum += runGame();
        }
        System.out.println("The avg of " + amtMoves + " games for bot moves are:\t" + sum/amtMoves);
    }

    private static int runGame() {
        JottoGameModel game = new JottoGameModel("STRAY", fillUpWords());

        Scanner stdin = new Scanner(System.in);

        int counter = 0;
        while(true) {
//            int userResult = -1;
//            while(userResult == -1) {
//                userResult = game.userLogic(stdin.nextLine().trim());
//                if (userResult == 6) {
//                    System.out.println("User Won!");
//                    return counter;
//                }
//            }
//            System.out.println("Your match is:\t" + userResult);

            String status = game.botLogic();
            System.out.println(status);
            if(status.charAt(0)=='-') {
                System.out.println(counter + "\tBot Won!");
                return counter;
            }

            if (game.userLogic(status) == 6) {
                System.out.println("User Won!" + counter);
                return counter;
            }
            counter++;
        }
    }

    private static ArrayList<String> fillUpWords() {
        ArrayList <String> dictionary = new ArrayList<String>();
        try {
            FileReader fileReader = new FileReader(new File(System.getProperty("user.dir") + "/src/dictionary.txt"));
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
/*
 * When the user gives it's actual word, make sure it is valid before you pass it into JOttoGameModel
 *
 * Make sure all words are uppercase -- handle in the front-end when passed to backend
 */