import { CanActivateFn } from '@angular/router';

export const guardUserGuard: CanActivateFn = (route, state) => {
  return true;
};
