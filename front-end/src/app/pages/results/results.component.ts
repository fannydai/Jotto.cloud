import { Component, OnInit } from '@angular/core';
import { GameService } from 'src/app/services/game.service';

@Component({
  selector: 'app-results',
  templateUrl: './results.component.html',
  styleUrls: ['./results.component.scss']
})
export class ResultsComponent implements OnInit {
  private errorMessage = '';
  private playerMoves = [];
  private computerMoves = [];

  constructor(
    private game: GameService
  ) { }

  ngOnInit() {
    this.game.getPreviousGame()
      .subscribe(res => {
        if (res === null) {
          this.errorMessage = 'Server is down.';
        } else if (res.status === 'success') {

        } else {
          this.errorMessage = 'No past game results.';
        }
      });
  }

}
