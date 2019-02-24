import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class GameService {
  // private wordList = [];

  constructor(
    private http: HttpClient
  ) {
    /*
    // Load in valid words
    this.loadWords().subscribe(data => {
      this.wordList = data.body.split('\n');
      this.wordList = this.wordList.filter(word => word !== '');
      this.wordList = this.wordList.map(word => word.trim());
    });*/
  }

  /*
  loadWords(): Observable<any> {
    return this.http.get('../../assets/dictionary.txt', {observe: 'response', responseType: 'text'});
  }

  getWords(): any[] {
    return this.wordList;
  }

  validateWord(word: string): boolean {
    word = word.toUpperCase();
    return this.wordList.includes(word);
  }*/

  /**
   * Picks the word for the AI to start the game. Backend should make sure word is valid.
   *
   * @param word - word that will be passed to the backend to be checked (ALL CAPITALIZED)
   */
  pickWord(word: string): Observable<any> {
    return this.http.post<any>(`${environment.api_path}/pickWord`,
      { word: word.toUpperCase(), username: localStorage.getItem('username') });
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
}
