import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { of, Observable } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private http: HttpClient
  ) { }

  /**
   * Login user using POST request to back-end server.
   *
   * @param username - name of user
   * @param password - password of user
   */
  login(username: string, password: string): Observable<any> {
    return this.http.post<any>(`${environment.api_path}/login`, { username: username, password: password })
      .pipe(
        map(result => {
          console.log(result);
          return result;
        }),
        catchError(err => null)
      );
  }

  /**
   * Register user using POST request to back-end server.
   *
   * @param username - name of user
   * @param password - password of user
   */
  register(username: string, password: string): Observable<any> {
    return this.http.post<any>(`${environment.api_path}/register`, { username: username, password: password })
      .pipe(
        map(result => {
          console.log(result);
          return result;
        }),
        catchError(err => null)
      );
  }

  logout(): void {
    localStorage.removeItem('username');
  }
}
