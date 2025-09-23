import { Injectable } from '@angular/core';
import { environment } from '../../../core/enviroments/enviroment';
import { HttpClient } from '@angular/common/http';
import { TokenResponse } from '../../../shared/interface/tokenResponse';
import { BehaviorSubject, Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private baseUrl = environment.baseUrl;
  private authUrl = `${this.baseUrl}/api/auth`;
  private loggedIn = new BehaviorSubject<boolean>(
    !!localStorage.getItem('accessToken')
  );
  isLoggedIn$ = this.loggedIn.asObservable();
  constructor(private http: HttpClient) {}

  login(email: string, password: string): Observable<TokenResponse> {
    return this.http
      .post<TokenResponse>(`${this.authUrl}` + '/login', {
        email,
        password,
      })
      .pipe(tap((res) => this.loggedIn.next(true)));
  }

  register(user: any): Observable<TokenResponse> {
    return this.http.post<TokenResponse>(`${this.authUrl}` + `/register`, user);
  }

  logout() {
    localStorage.removeItem('accessToken');
    this.loggedIn.next(false);
  }
  setLoggedIn(status: boolean) {
    this.loggedIn.next(status);
  }

  isUserLoggedIn() {
    return !!localStorage.getItem('accessToken');
  }

  getToken() {
    return sessionStorage.getItem('accessToken');
  }
}
