import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { GameService } from 'src/app/services/game.service';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent implements OnInit {
  private enteredWord = ''; // While use is typing
  private enteredError = '';
  private aiWord = '';
  private userWord = '';

  private guessWord = '';
  private guessError = '';

  constructor(
    private user: UserService,
    private game: GameService
  ) { }

  ngOnInit() {
  }

  /**
   * Checks to ensure word is exactly length 5 and all letters are unique and are a-z or A-Z
   */
  /*
  validateWord(word: string): boolean {
    if (word.length !== 5) {
      return false;
    }
    const map = new Map();
    for (const w of word) {
      if (!w.match(/[a-z]/i)) {
        return false;
      }
      if (map.get(w)) {
        return false;
      }
      map.set(w, 1);
    }
    return true;
  }*/

  submitAIWord(): void {
    this.enteredError = '';
    /*
    if (!this.validateWord(this.enteredWord)) {
      this.enteredError = 'The word must have exactly 5 distinct letters';
    } else if (!this.game.validateWord(this.enteredWord)) {
      this.enteredError = 'The word is not valid';
    } else {
      console.log('Entered word valid');
      this.aiWord = this.enteredWord;
      this.enteredWord = '';
    }*/
    this.game.pickWord(this.enteredWord)
      .subscribe(res => {
        if (res.status === 'success') {
          this.aiWord = this.enteredWord;
          this.enteredWord = '';
        } else {
          this.enteredError = res.error;
        }
      });
  }

  onGuess(): void {
    this.guessError = '';
    /*
    if (!this.validateWord(this.guessWord)) {
      this.guessError = 'The word must have exactly 5 distinct letters';
    } else if (!this.game.validateWord(this.enteredWord)) {
      this.guessError = 'The word is not valid';
    } else {
      console.log('Guessing...');
      this.game.guess(this.guessWord)
        .subscribe(data => {
          console.log('GUESS RETURN DATA:', data);
        });
    }*/
    this.game.guess(this.guessWord)
      .subscribe(res => {
        console.log('GUESS RETURN DATA:', res);
        if (res.status === 'success') {

        } else {
          this.guessError = res.error;
        }
      });
  }

}
