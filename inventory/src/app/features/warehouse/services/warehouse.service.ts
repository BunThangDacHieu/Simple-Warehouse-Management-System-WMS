import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Warehouse } from '../../../shared/model/warehouse';

@Injectable({
  providedIn: 'root',
})
export class WarehouseService {
  private baseUrl: string = 'http://localhost:8080';
  private warehouseUrl: string = `${this.baseUrl}/warehouse`;

  constructor(private http: HttpClient) {}

  getAllWarehouse() {
    return this.http.get<Warehouse[]>(this.baseUrl + `/warehouse`);
  }

  getWarehouseById(id: number) {
    return this.http.get<Warehouse[]>(this.baseUrl + `/warehouse/${id}`);
  }

  createWarehouse(warehouse: any) {
    return this.http.post<Warehouse[]>(this.baseUrl + `/warehouse`, warehouse);
  }

  updateWarehouse(id: number, warehouse: Warehouse) {
    return this.http.put<Warehouse[]>(
      this.baseUrl + `/warehouse/${id}`,
      warehouse
    );
  }

  deleteWarehouse(id: number) {
    return this.http.delete(this.baseUrl + `/warehouse/${id}`);
  }
}
