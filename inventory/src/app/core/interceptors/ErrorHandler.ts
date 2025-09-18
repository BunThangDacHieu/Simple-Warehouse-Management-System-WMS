import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';
import { Injectable } from '@angular/core';

@Injectable()
export class ErrorHandler implements HttpInterceptor {
  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    return next
      .handle(req)
      .pipe(catchError((error: HttpErrorResponse) => this.handlerError(error)));
  }
  protected handlerError(error: HttpErrorResponse) {
    console.log(error.message);
    return throwError('Something went wrong');
  }
}
