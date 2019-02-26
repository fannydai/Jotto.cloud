import { Component, OnInit } from '@angular/core';
import { GameService } from 'src/app/services/game.service';

@Component({
  selector: 'app-results',
  templateUrl: './results.component.html',
  styleUrls: ['./results.component.scss']
})
export class ResultsComponent implements OnInit {
  private errorMessage = '';
  // private playerMoves = []; // The sequence of guesses of the player
  private playerWord = 'GRASP'; // The word that the player had to guess
  // private computerMoves = []; // The sequence of guesses of the computer
  private computerWord = 'STONE'; // The word that the computer had to guess

  private playerMoves = [
    ['P', 'L', 'A', 'C', 'E'],
    ['L', 'A', 'C', 'E', 'S'],
    ['S', 'P', 'A', 'C', 'E'],
    ['S', 'P', 'E', 'A', 'K'],
    ['S', 'P', 'U', 'R', 'N'],
    ['S', 'T', 'O', 'R', 'K'],
    ['S', 'P', 'E', 'A', 'R'],
    ['P', 'R', 'A', 'N', 'K'],
    ['S', 'P', 'R', 'A', 'Y'],
    ['G', 'R', 'A', 'S', 'P'],
    ['G', 'R', 'A', 'S', 'P'],
    ['G', 'R', 'A', 'S', 'P'],
    ['G', 'R', 'A', 'S', 'P'],
    ['G', 'R', 'A', 'S', 'P'],
    ['G', 'R', 'A', 'S', 'P'],
    ['G', 'R', 'A', 'S', 'P'],
    ['G', 'R', 'A', 'S', 'P'],
  ];

  private computerMoves = [
    ['A', 'B', 'O', 'D', 'E'],
    ['B', 'O', 'D', 'E', 'S'],
    ['P', 'R', 'A', 'Y', 'S'],
    ['S', 'P', 'R', 'I', 'T'],
    ['S', 'T', 'I', 'N', 'G'],
    ['P', 'I', 'T', 'C', 'H'],
    ['P', 'I', 'T', 'C', 'H'],
    ['P', 'I', 'T', 'C', 'H'],
    ['P', 'I', 'T', 'C', 'H'],
    ['P', 'I', 'T', 'C', 'H'],
    ['P', 'I', 'T', 'C', 'H'],
    ['P', 'I', 'T', 'C', 'H'],
    ['P', 'I', 'T', 'C', 'H'],
    ['P', 'I', 'T', 'C', 'H'],
    ['P', 'I', 'T', 'C', 'H'],
    ['P', 'I', 'T', 'C', 'H'],
  ];

  constructor(
    private game: GameService
  ) { }

  ngOnInit() {
    this.game.getPreviousGame()
      .subscribe(res => {
        if (res === null) {
          this.errorMessage = 'Server is down.';
        } else if (res.status === 'success') {
          this.playerMoves = res.playerMoves;
          this.playerMoves = res.computerMoves;
          this.playerWord = res.playerWord;
          this.computerWord = res.computerWord;
        } else {
          this.errorMessage = 'No past game results.';
        }
      });
  }

  /**
   * Checks whether the letter is in the word that the player had to guess.
   * Returns true if it is in the word, false otherwise.
   *
   * @param letter - Letter to be checked.
   */
  isInPlayerWord(letter: string): boolean {
    return this.playerWord.indexOf(letter) > -1;
  }

  /**
   * Checks whether the letter is in the word that the computer had to guess.
   * Returns true if it is in the word, false otherwise.
   * 
   * @param letter - Letter to be checked.
   */
  isInComputerWord(letter: string): boolean {
    return this.computerWord.indexOf(letter) > -1;
  }
}
