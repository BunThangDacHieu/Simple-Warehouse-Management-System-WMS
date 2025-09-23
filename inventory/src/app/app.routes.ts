import { Routes } from '@angular/router';
import { WarehouseListComponent } from './page/features/warehouse/pages/warehouse-list/warehouse-list.component';
import { LoginComponent } from './page/auth/login/login.component';
import { SignupComponent } from './page/auth/signup/signup.component';
import { ForgotpasswordComponent } from './page/auth/forgotpassword/forgotpassword.component';
import { guardUserGuard } from './core/guard/guard-user.guard';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  {
    path: 'home',
    component: WarehouseListComponent,
    canActivate: [guardUserGuard],
    data: { expectedRoles: ['ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_SUPPLIER'] },
  },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'forgot-password', component: ForgotpasswordComponent },
];
