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

  //private userGuesses = [];
  private aiGuesses = [['F', 'I', 'R', 'S', 'T', '', 3], ['A', 'C', 'O', 'R', 'N', '', 0]];

  private userGuesses = [['F', 'I', 'R', 'S', 'T', '', 3], ['A', 'C', 'O', 'R', 'N', '', 0]];

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
    this.game.pickWord(this.enteredWord)
      .subscribe(res => {
        /*
        if (res.status === 'success') {
          this.aiWord = this.enteredWord;
          this.enteredWord = '';
        } else {
          this.enteredError = res.error;
        }*/
      });
      this.aiWord = this.enteredWord.toUpperCase();
      this.enteredWord = '';
  }

  /**
   * Submit a guess for the player. Set an error message if invalid.
   * Otherwise, the payload should include the AI guess
   * and aiGuesses and userGuesses should be updated.
   */
  onGuess(): void {
    this.guessError = '';
    /*
    this.game.guess(this.userGuessWord)
      .subscribe(res => {
        console.log('GUESS RETURN DATA:', res);
        if (res.status === 'success') {
          const resUser = res.user; // # of matches for the user's guess
          const resBot = res.bot; // The actual bot's guess and # of matches

          // If bot guesses correctly, display winning for bot
          if (res.bot.word === this.userWord) {
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

          const newUserGuess = this.userWord.toUpperCase().split('');
          newUserGuess.push(''); // Just for spacing, probably don't need
          newUserGuess.push(resUser);

          const newBotGuess = resBot.word.toUpperCase().split('');
          newBotGuess.push('');
          newBotGuess.push(res.bot.matches);
        } else {
          this.guessError = res.error;
        }
      });*/
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
    //console.log(letter);
    //console.log(this.aiWord);
    return this.aiWord.indexOf(letter) > -1 ? 2 : 3;
  }

}
