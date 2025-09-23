import { HttpInterceptorFn } from '@angular/common/http';
import { Injectable } from '@angular/core';
export const apiKeyInterceptor: HttpInterceptorFn = (req, next) => {
  const authToken = localStorage.getItem('token');
  if (authToken) {
    const modifiedReq = req.clone({
      headers: req.headers.set('Authorization', `Bearer ${authToken}`),
    });
  }
  return next(req);
};
