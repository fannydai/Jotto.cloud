import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class GameService {
  private wordList = [];

  constructor(
    private http: HttpClient
  ) {
    // Load in valid words
    this.loadWords().subscribe(data => {
      this.wordList = data.body.split('\n');
      this.wordList = this.wordList.filter(word => word !== '');
      this.wordList = this.wordList.map(word => word.trim());
    });
  }

  loadWords(): Observable<any> {
    return this.http.get('../../assets/dictionary.txt', {observe: 'response', responseType: 'text'});
  }

  getWords(): any[] {
    return this.wordList;
  }

  validateWord(word: string): boolean {
    word = word.toUpperCase();
    return this.wordList.includes(word);
  }

  guess(word: string): Observable<any> {
    return this.http.post<any>(`${environment.api_path}/playGame`, { guess: word.toUpperCase() });
  }
}
