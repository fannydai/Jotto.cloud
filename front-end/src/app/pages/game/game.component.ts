import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { GameService } from 'src/app/services/game.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent implements OnInit {
  private winner = ''; // Either player or computer
  private enteredWord = ''; // While use is typing
  private enteredError = '';
  private aiWord = ''; // Chosen by the player for the computer
  private userWord = ''; // Chosen by the computer for the player

  private userGuessWord = ''; // The current guess of the user
  private guessError = '';

  private gameId = '';

  private userGuesses = [];
  private aiGuesses = [];

  // State of each button. 1 is default, 2 is green, 3 is red
  private alphaToggle = {'A': 1, 'B': 1, 'C': 1, 'D': 1, 'E': 1, 'F': 1, 'G': 1, 'H': 1, 'I': 1,
    'J': 1, 'K': 1, 'L': 1, 'M': 1, 'N': 1, 'O': 1, 'P': 1, 'Q': 1, 'R': 1, 'S': 1, 'T': 1, 'U': 1,
  'V': 1, 'W': 1, 'X': 1, 'Y': 1, 'Z': 1 };

  @ViewChild('win') modalContent: TemplateRef<any>;

  constructor(
    private user: UserService,
    private game: GameService,
    private modal: NgbModal,
    private router: Router
  ) { }

  ngOnInit() {
  }

  /**
   * Submit a word for the AI. Set an error message message if invalid.
   * Otherwise aiWord is set, updating the view to show the game.
   */
  submitAIWord(): void {
    this.enteredError = '';
    if (this.enteredWord.length !== 5) {
      this.enteredError = 'Must be 5 letters.';
    } else if (!this.enteredWord.match(/^[a-z]+$/i)) {
      this.enteredError = 'Must be all letters.';
    } else if (this.enteredWord.match(/(.).*\1/i)) {
      this.enteredError = 'Must have distinct letters.';
    } else {
      this.game.pickWord(this.enteredWord)
        .subscribe(res => {
          if (res === null) {
            this.enteredError = 'Server Error.';
          } else if (res.valid === true) {
            this.aiWord = this.enteredWord.toUpperCase();
            this.enteredWord = '';
            this.gameId = res.gameId;
          } else {
            this.enteredError = 'Word is invalid.';
          }
        });
    }
  }

  /**
   * Submit a guess for the player. Set an error message if invalid.
   * Otherwise, the payload should include the AI guess
   * and aiGuesses and userGuesses should be updated.
   */
  onGuess(): void {
    this.guessError = '';
    if (this.userGuessWord.length !== 5) {
      this.guessError = 'Must be 5 letters.';
    } else if (!this.userGuessWord.match(/^[a-z]+$/i)) {
      this.guessError = 'Must be all letters.';
    } else if (this.userGuessWord.match(/(.).*\1/i)) {
      this.guessError = 'Must have distinct letters.';
    } else {
      this.game.userMove(this.userGuessWord, this.gameId)
      .subscribe(res => {
        if (res === null) {
          this.guessError = 'Server is down.';
        } else if (res.result === -1) {
          this.guessError = 'Word is invalid.';
        } else if (res.result === -2) {
          this.guessError = 'Game ID is invalid.';
        } else if (res.result === 6) {
          this.winner = 'player';
          this.userWord = this.userGuessWord.toUpperCase();
          this.showWinScreen();
        } else {
          const newUserGuess = this.userGuessWord.toUpperCase().split('');
          newUserGuess.push(''); // Just for spacing, probably don't need
          newUserGuess.push(res.result); // Number of correct letters
          this.userGuesses.unshift(newUserGuess);
          // Ask for bot's guess
          this.game.botMove(this.gameId)
            .subscribe(result => {
              if (result.result === 'Bot won!') {
                this.winner = 'computer';
                this.showWinScreen();
              } else {
                const newBotGuess = result.result.toUpperCase().split('');
                newBotGuess.splice(newBotGuess.length - 1, 0, ''); // Insert space into array
                this.aiGuesses.unshift(newBotGuess);
              }
            });
        }
      });
    }
  }

  /**
   * Cycles through the colors when an alphabet button is pressed.
   * 1 - default (black)
   * 2 - user thinks is in word (green)
   * 3 - user thinks is not in word (red)
   *
   * @param event - The mouse click event.
   */
  toggleAlpha(event): void {
    const letter = event.path[0].innerHTML;
    const toggleNumber = this.alphaToggle[letter];
    if (toggleNumber === 1) {
      this.alphaToggle[letter] = 2;
    } else if (toggleNumber === 2) {
      this.alphaToggle[letter] = 3;
    } else {
      this.alphaToggle[letter] = 1;
    }
  }

  /**
   * Check to see which buttons are pressed, color each letter accordingly.
   *
   * @param letter - Letter to check for again the buttons
   */
  checkColor(letter: string): number {
    if (!('' + letter).match(/[a-z]/i)) {
      return 1;
    }
    return this.alphaToggle[letter];
  }

  checkAIColor(letter: string): number {
    if (!('' + letter).match(/[a-z]/i)) {
      return 1;
    }
    return this.aiWord.indexOf(letter) > -1 ? 2 : 3;
  }

  /**
   * Shows the win modal on user win or bot win.
   */
  showWinScreen() {
    this.modal.open(this.modalContent, { centered: true }).result.then((result) => {
      if (result === 'Play again') {
        // Reset everything
        this.winner = '';
        this.aiWord = '';
        this.userWord = '';
        this.userGuessWord = '';
        this.aiGuesses = [];
        this.userGuesses = [];
        Object.keys(this.alphaToggle).forEach(key => this.alphaToggle[key] = 1);
      } else {
        this.router.navigate(['/']);
      }
    });
  }
}
