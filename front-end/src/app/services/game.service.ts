import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { environment } from 'src/environments/environment';
import { map, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  constructor(
    private http: HttpClient
  ) {}

  /**
   * Picks the word for the AI to start the game as POST request. Backend should make sure word is valid.
   *
   * @param word - word that will be passed to the backend to be checked (ALL CAPITALIZED)
   */
  pickWord(word: string): Observable<any> {
    return this.http.post<any>(`${environment.api_path}/pickWord`,
      { word: word.toUpperCase(), username: localStorage.getItem('username') })
      .pipe(
        map(res => {
          console.log(res);
          return res;
        }),
        catchError(e => of(null))
      );
  }

  /**
   * Sends in a user guess to be processed as POST request. Backend should make sure word is valid.
   *
   * @param word - guess that the backend checks (ALL CAPITALIZED)
   */
  userMove(word: string, gameId: string): Observable<any> {
    return this.http.post<any>(`${environment.api_path}/userMove`, { guess: word.toUpperCase(), gameId: gameId })
      .pipe(
        map(res => {
          return res;
        }),
        catchError(e => of(null))
      );
  }

  /**
   * Gets the move of the computer via GET request. Word is assumed to be valid.
   * 
   * @param gameId - The current game that is being played
   */
  botMove(gameId: string): Observable<any> {
    return this.http.get<any>(`${environment.api_path}/botMove/${gameId}`)
      .pipe(
        map(res => {
          return res;
        }),
        catchError(e => of(null))
      );
  }



  /**
   * Gets the previous game associated with a user as GET request.
   */
  getPreviousGame(): Observable<any> {
    return this.http.get<any>(`${environment.api_path}/previousGame/${localStorage.getItem('username')}`)
      .pipe(
        map(res => res),
        catchError(e => of(null))
      );
  }
}
