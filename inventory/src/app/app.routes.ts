import { Routes } from '@angular/router';
import { WarehouseListComponent } from './features/warehouse/pages/warehouse-list/warehouse-list.component';
import { LoginComponent } from './page/login/login.component';
import { SignupComponent } from './page/signup/signup.component';
import { ForgotpasswordComponent } from './page/forgotpassword/forgotpassword.component';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'home', component: WarehouseListComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'forgot-password', component: ForgotpasswordComponent },
];
