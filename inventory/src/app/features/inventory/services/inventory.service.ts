import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { Inventory } from '../../../shared/model/inventory';
import { environment } from '../../../enviroments/enviroment';
@Injectable({
  providedIn: 'root',
})
export class InventoryService {
  private baseUrl: string = environment.baseUrl;
  public inventoryUrl: string = `${this.baseUrl}/api/inventory`;
  constructor(private http: HttpClient) {}

  getAllInventory(): Observable<Inventory[]> {
    return this.http.get<Inventory[]>(this.inventoryUrl);
  }

  getInventoryById(id: number) {
    return this.http.get<Inventory>(this.baseUrl + `/inventory/${id}`);
  }

  createInventory(inventory: any) {
    return this.http.post<Inventory>(this.inventoryUrl, inventory);
  }

  updateInventory(id: number, inventory: Inventory) {
    return this.http.put<Inventory>(`${this.inventoryUrl}/${id}`, inventory);
  }

  deleteInventory(id: number) {
    return this.http.delete(`${this.inventoryUrl}/${id}`);
  }

  /*-------------------------------------------------Nâng cao--------------------------------------------------------*/
  getInventoryByWarehouseId(id: number) {
    return this.http.get<Inventory[]>(`${this.inventoryUrl}/warehouse/${id}`);
  }
}
