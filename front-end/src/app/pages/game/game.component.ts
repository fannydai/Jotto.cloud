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

  constructor(
    private user: UserService,
    private game: GameService
  ) { }

  ngOnInit() {
  }

  checkForDuplicates(word: string): boolean {
    const map = new Map();
    for (const w of word) {
      if (map.get(w)) {
        return false;
      }
      map.set(w, 1);
    }
    return true;
  }

  submitAIWord(): void {
    // TODO: Check if word is valid
    this.enteredError = '';
    if (this.enteredWord.length !== 5 || !this.checkForDuplicates(this.enteredWord)) {
      this.enteredError = 'The word must have exactly 5 distinct letters';
    } else {
      console.log('Entered word valid');
      this.aiWord = this.enteredWord;
      this.enteredWord = '';
    }
  }

}
