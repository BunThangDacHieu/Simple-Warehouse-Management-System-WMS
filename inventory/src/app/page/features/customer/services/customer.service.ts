import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Customer } from '../../../../shared/model/customer';
import { environment } from '../../../../core/enviroments/enviroment';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  private baseUrl: string = environment.baseUrl;
  private customerUrl: string = `${this.baseUrl}/api/customer`;

  constructor(private http: HttpClient) {}

  getAllCustomer(): Observable<Customer[]> {
    return this.http.get<Customer[]>(this.customerUrl);
  }

  getCustomerById(id: number) {
    return this.http.get<Customer>(`${this.customerUrl}` + `/${id}`);
  }

  createCustomer(customer: any) {
    return this.http.post<Customer>(this.customerUrl, customer);
  }
  updateCustomer(id: number, customer: Customer) {
    return this.http.put<Customer>(`${this.customerUrl}/${id}`, customer);
  }

  deleteCustomer(id: number) {
    return this.http.delete(`${this.customerUrl}/${id}`);
  }
}
