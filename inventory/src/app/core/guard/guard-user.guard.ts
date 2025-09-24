import { CanActivateFn } from '@angular/router';
import { AuthService } from '../../page/auth/services/auth.service';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';
import { CustomeTokenResponseGuard } from '../../shared/interface/customeTokenResponseGuard';
import { MessageService } from 'primeng/api';

export const guardUserGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const messageService = inject(MessageService);
  const authService = inject(AuthService);
  const token = authService.getToken();

  if (!token) {
    router.navigate(['/login']);
    return false;
  }

  try {
    const decoded = jwtDecode<CustomeTokenResponseGuard>(token);
    const now = Math.floor(Date.now() / 1000);

    // Check hết hạn
    if (decoded.exp < now) {
      messageService.add({
        severity: 'error',
        summary: 'Lỗi',
        detail: 'Token đã quá thời hạn'
      });
      router.navigate(['/login']);
      return false;
    }

    // Lấy roles trong token
    const userRoles = decoded.roles.map(r => r.authority);
    const requiredRoles = route.data['roles'] as string[]; // roles yêu cầu ở route config
    if(requiredRoles.length == 0 || !requiredRoles){
      return true;
    }
    // Kiểm tra giao nhau giữa userRoles và requiredRoles
    const hasAccess: any = requiredRoles.some(role => userRoles.includes(role));
    console.log("User roles:", userRoles);
    console.log("Required roles:", requiredRoles);
    console.log("Has access:", hasAccess);
    if (hasAccess) {
      return true;
    } else {
      messageService.add({
        severity: 'warn',
        summary: 'Cảnh báo',
        detail: 'Bạn không có quyền truy cập trang này'
      });
      router.navigate(['/login']); // route riêng cho 403
      return false;
    }
  } catch (error) {
    console.error("Token không hợp lệ", error);
    router.navigate(['/login']);
    return false;
  }
};
