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
   * Picks the word for the AI to start the game. Backend should make sure word is valid.
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
        });
      )
  }

  /**
   * Sends in a user guess to be processed. Backend should make sure word is valid.
   *
   * @param word - guess that the backend checks (ALL CAPITALIZED)
   */
  guess(word: string): Observable<any> {
    return this.http.post<any>(`${environment.api_path}/playGame`,
      { guess: word.toUpperCase(), username: localStorage.getItem('username') });
  }

  /**
   * Gets the previous game associated with a user.
   */
  getPreviousGame(): Observable<any> {
    return this.http.post<any>(`${environment.api_path}/previousGame`, { username: localStorage.getItem('username') })
      .pipe(
        map(res => res),
        catchError(e => of(null))
      );
  }
}
