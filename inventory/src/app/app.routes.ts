import { Routes } from '@angular/router';
import { LoginComponent } from './page/auth/login/login.component';
import { SignupComponent } from './page/auth/signup/signup.component';
import { ForgotpasswordComponent } from './page/auth/forgotpassword/forgotpassword.component';
import { guardUserGuard } from './core/guard/guard-user.guard';
import {HomeComponent} from './layout/home/home.component';
import {ErrorpageComponent} from './layout/errorpage/errorpage.component';
import {UserDetailComponent} from './page/features/user/pages/user-detail/user-detail.component';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  {
    path: 'home',
    component: HomeComponent,
    canActivate: [guardUserGuard],
    data: { roles: ["ROLE_CUSTOMER", "ROLE_MANAGER", "ROLE_SUPPLIER"] },
  },
  { path: 'user/:id', component: UserDetailComponent},
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'forgot-password', component: ForgotpasswordComponent },
  { path: '404',  component: ErrorpageComponent },
  { path: '**', redirectTo: '/404' }
];
