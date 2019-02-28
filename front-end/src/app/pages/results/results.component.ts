import { Component, OnInit } from '@angular/core';
import { GameService } from 'src/app/services/game.service';

@Component({
  selector: 'app-results',
  templateUrl: './results.component.html',
  styleUrls: ['./results.component.scss']
})
export class ResultsComponent implements OnInit {
  private pageNumber = 0;
  private errorMessage = '';
  private playerMoves = []; // The sequence of guesses of the player
  private playerWord = []; // The word that the player had to guess
  private computerMoves = []; // The sequence of guesses of the computer
  private computerWord = []; // The word that the computer had to guess

  constructor(
    private game: GameService
  ) { }

  ngOnInit() {
    this.game.getPreviousGames()
      .subscribe(res => {
        if (res === null) {
          this.errorMessage = 'Server is down.';
        } else if (res.botMovesList.length === 0) {
          this.errorMessage = 'No past game results.';
        } else {
          const botMovesList = res.botMovesList;
          const userMovesList = res.userMovesList;
          botMovesList.forEach(move => {
            this.computerWord.push(move.word);
            const computerGuess = move.guessedWords.map(guess => guess.guess + String(guess.amtMatch));
            const letters = [];
            for (const word of computerGuess) {
              const splitWord = word.split('');
              splitWord.splice(splitWord.length - 1, 0, ' ');
              letters.push(splitWord);
            }
            this.computerMoves.push(letters);
          });
          userMovesList.forEach(move => {
            this.playerWord.push(move.word);
            const playerGuess = move.guessedWords.map(guess => guess.guess + String(guess.amtMatch));
            const letters = [];
            for (const word of playerGuess) {
              const splitWord = word.split('');
              splitWord.splice(splitWord.length - 1, 0, ' ');
              letters.push(splitWord);
            }
            this.playerMoves.push(letters);
          });
          this.computerMoves.reverse();
          this.playerMoves.reverse();
          this.computerWord.reverse();
          this.playerWord.reverse();
        }
      });
  }

  /**
   * Checks whether the letter is in the word that the player had to guess in a certain game.
   * Returns 1 if it is in the word, 0 if not a letter, and -1 if not in the word.
   *
   * @param letter - Letter to be checked.
   * @param pageNum - The game that the user is viewing
   */
  isInPlayerWord(letter: string, pageNum: number): number {
    if (!letter.match(/[a-z]/i)) {
      return 0;
    }
    return this.playerWord[pageNum].indexOf(letter) > -1 ? 1 : -1;
  }

  /**
   * Checks whether the letter is in the word that the computer had to guess in a certain game.
   * Returns 1 if it is in the word, 0 if not a letter, and -1 if not in the word.
   *
   * @param letter - Letter to be checked.
   * @param pageNum - The game that the user is viewing.
   */
  isInComputerWord(letter: string, pageNum: number): number {
    if (!letter.match(/[a-z]/i)) {
      return 0;
    }
    return this.computerWord[pageNum].indexOf(letter) > -1 ? 1 : -1;
  }

  /**
   * Increments or decrements the page number (0 is the first page)
   * 
   * @param increment - How much to increment (or decrement if negative)
   */
  changePageNumber(increment: number): void {
    this.pageNumber += increment;
  }
}
