import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private http: HttpClient
  ) { }

  login(username: string, password: string) {
    return this.http.post<any>(`${environment.api_path}/login`, { username: username, password: password });
  }

  register(username: string, password: string) {
    return this.http.post<any>(`${environment.api_path}/register`, { username: username, password: password });
  }
}
