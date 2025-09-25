import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../core/enviroments/enviroment';
import { User } from '../../../../shared/model/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UsersService {
  private baseUrl: string = environment.baseUrl;
  private userUrl: string = `${this.baseUrl}/api/user`;
  constructor(private http: HttpClient) {}
  /*-----------------------------CRUD cơ bản--------------------------*/
  getAllUser(): Observable<User[]> {
    return this.http.get<User[]>(this.userUrl);
  }

  getUserById(id: number): Observable<User> {
    return this.http.get<User>(this.userUrl + `/${id}`);
  }

  updateUser(id: User, user: any): Observable<User> {
    return this.http.put<User>(this.userUrl + `/${id}`, user);
  }

  deleteUser(id: number) {
    return this.http.delete(this.userUrl + `/${id}`);
  }
  /*--------------------------Logic cao cấp-----------------------*/

  /*--------------------------Other-------------------------------*/
  getUserRolebyCount(): Observable<any[]> {
    return this.http.get<any[]>(this.userUrl + `/get-count-role`);
  }
}
