import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Warehouse } from '../../../../shared/model/warehouse';
import { environment } from '../../../../core/enviroments/enviroment';

@Injectable({
  providedIn: 'root',
})
export class WarehouseService {
  private baseUrl: string = environment.baseUrl; //http://localhost:8080/api/warehouse
  private warehouseUrl: string = `${this.baseUrl}/api/warehouse`;
  constructor(private http: HttpClient) {}

  getAllWarehouse() {
    return this.http.get<Warehouse[]>(`${this.warehouseUrl}`);
  }

  getWarehouseById(id: number) {
    return this.http.get<Warehouse>(`${this.warehouseUrl}/${id}`);
  }

  createWarehouse(warehouse: any) {
    return this.http.post<Warehouse>(`${this.warehouseUrl}`, warehouse);
  }

  updateWarehouse(warehouse: Warehouse, id: number) {
    return this.http.put<Warehouse>(`${this.warehouseUrl}/${id}`, warehouse);
  }

  deleteWarehouse(id: number) {
    return this.http.delete(`${this.warehouseUrl}/${id}`);
  }
}
