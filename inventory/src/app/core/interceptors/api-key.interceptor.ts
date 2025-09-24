import { HttpInterceptorFn } from '@angular/common/http';
export const apiKeyInterceptor: HttpInterceptorFn = (req, next) => {
  if(req.url.includes('/login') || req.url.includes('/register')){
    return next(req)
  }
  const authToken = sessionStorage.getItem('accessToken');
  if (authToken) {
    const cloned = req.clone({
      setHeaders:{
        Authorization: `Bearer ${authToken}`
      }
    });
    return next(cloned);
  }
  return next(req);
};
