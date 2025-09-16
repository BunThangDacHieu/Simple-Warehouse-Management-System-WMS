import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { Inventory } from '../../../shared/model/inventory';
@Injectable({
  providedIn: 'root',
})
export class InventoryService {
  private baseUrl: string = 'http://localhost:8080';
  private inventoryUrl: string = `${this.baseUrl}/inventory`;
  private handlerError(error: HttpErrorResponse) {
    console.log(error.message);
    return throwError('Something went wrong');
  }
  constructor(private http: HttpClient) {}

  getAllInventory(): Observable<Inventory[]> {
    return this.http
      .get<Inventory[]>(this.inventoryUrl)
      .pipe(catchError(this.handlerError));
  }

  getInventoryById(id: number) {
    return this.http
      .get<Inventory[]>(this.baseUrl + `/inventory/${id}`)
      .pipe(catchError(this.handlerError));
  }

  createInventory(inventory: any) {
    return this.http
      .post<Inventory[]>(this.inventoryUrl, inventory)
      .pipe(catchError(this.handlerError));
  }

  updateInventory(id: number, inventory: Inventory) {
    return this.http
      .put<Inventory[]>(`${this.inventoryUrl}/${id}`, inventory)
      .pipe(catchError(this.handlerError));
  }

  deleteInventory(id: number) {
    return this.http
      .delete(`${this.inventoryUrl}/${id}`)
      .pipe(catchError(this.handlerError));
  }
}
