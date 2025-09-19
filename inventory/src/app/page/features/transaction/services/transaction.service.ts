import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Transaction } from '../../../../shared/model/transaction';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../core/enviroments/enviroment';

@Injectable({
  providedIn: 'root',
})
export class TransactionService {
  private baseUrl: string = environment.baseUrl;
  private transactionUrl = `${this.baseUrl}/api/transaction`;
  constructor(private http: HttpClient) {}

  getAllTransaction(): Observable<Transaction[]> {
    return this.http.get<Transaction[]>(this.transactionUrl);
  }

  getTransactionById(id: number): Observable<Transaction> {
    const url = `${this.transactionUrl}/${id}`;
    return this.http.get<Transaction>(url);
  }

  createTransaction(transaction: any): Observable<Transaction> {
    return this.http.post<Transaction>(this.transactionUrl, transaction);
  }

  updateTransaction(
    id: number,
    transaction: Transaction
  ): Observable<Transaction> {
    const url = `${this.transactionUrl}/${id}`;
    return this.http.put<Transaction>(url, transaction);
  }

  deleteTransaction(id: number) {
    return this.http.delete(`${this.transactionUrl}/${id}`);
  }
}
