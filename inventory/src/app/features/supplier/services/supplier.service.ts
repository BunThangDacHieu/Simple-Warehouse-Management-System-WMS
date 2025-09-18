import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Supplier } from '../../../shared/model/supplier';
import { environment } from '../../../enviroments/enviroment';

@Injectable({
  providedIn: 'root',
})
export class SupplierService {
  private baseUrl: string = environment.baseUrl;
  private supplierUrl: string = `${this.baseUrl}/api/supplier`;

  constructor(private http: HttpClient) {}

  getAllSupplier(): Observable<Supplier[]> {
    return this.http.get<Supplier[]>(this.supplierUrl);
  }

  getSupplierById(id: number) {
    return this.http.get<Supplier>(this.supplierUrl + `/${id}`);
  }

  updateSupplier(id: number, supplier: Supplier) {
    return this.http.put<Supplier>(this.supplierUrl + `/${id}`, supplier);
  }

  deleteSupplier(id: number) {
    return this.http.delete(`${this.supplierUrl}/${id}`);
  }
}
