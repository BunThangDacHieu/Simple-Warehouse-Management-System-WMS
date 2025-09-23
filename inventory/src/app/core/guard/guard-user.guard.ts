import { CanActivateFn } from '@angular/router';
import { AuthService } from '../../page/auth/services/auth.service';
import { inject } from '@angular/core';
import { Router } from '@angular/router';

export const guardUserGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const authService = inject(AuthService);
  const token = authService.getToken();

  if (!token) {
    router.navigate(['login']);
    return false;
  }

  const roles = JSON.parse(sessionStorage.getItem('roles') || '[]');
  const expectedRoles: Array<string> = route.data['expectedRoles'];

  if (
    expectedRoles.length === 0 ||
    roles.some((r: any) => expectedRoles.includes(r.authority))
  ) {
    return true;
  } else {
    router.navigate(['home']);
    return false;
  }
};
