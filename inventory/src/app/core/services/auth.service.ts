import { Injectable } from '@angular/core';
import { environment } from '../../enviroments/enviroment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private baseUrl = environment.baseUrl;
  private authUrl = `${this.baseUrl}/api/auth`;
  constructor(private http: HttpClient) {}

  login(credentials: any) {
    return this.http.post(`${this.authUrl}` + '/login', credentials);
  }

  register(user: any) {
    return this.http.post(`${this.authUrl}` + `/register`, user);
  }

  logout() {
    localStorage.removeItem('token');
  }
}
