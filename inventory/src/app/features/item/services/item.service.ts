import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Item } from '../../../shared/model/item';
import { Observable } from 'rxjs';
import { environment } from '../../../enviroments/enviroment';

@Injectable({
  providedIn: 'root',
})
export class ItemService {
  private baseUrl: string = environment.baseUrl;
  private itemUrl: string = `${this.baseUrl}/api/item`;

  constructor(private http: HttpClient) {}

  getAllItem(): Observable<Item[]> {
    return this.http.get<Item[]>(this.itemUrl);
  }

  getItemById(id: number) {
    return this.http.get<Item>(this.itemUrl + `/${id}`);
  }

  createItem(item: any) {
    return this.http.post<Item>(this.itemUrl, item);
  }

  updateItem(id: number, item: Item) {
    return this.http.put<Item>(this.itemUrl + `/${id}`, item);
  }

  deleteItem(id: number) {
    return this.http.delete(this.itemUrl + `/${id}`);
  }
}
