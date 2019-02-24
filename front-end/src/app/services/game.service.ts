import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

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
      this.wordList = this.wordList.map(word => word.trim());
    });
  }

  loadWords(): Observable<any> {
    return this.http.get('../../assets/temp_words.txt', {observe: 'response', responseType: 'text'});
  }

  getWords(): any[] {
    return this.wordList;
  }

  validateWord(word: string): boolean {
    return this.wordList.includes(word);
  }
}
