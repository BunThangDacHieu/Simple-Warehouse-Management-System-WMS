import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { CheckboxModule } from 'primeng/checkbox';
import { InputTextModule } from 'primeng/inputtext';
import { ToastModule } from 'primeng/toast';
import { CardModule } from 'primeng/card';
import { TokenResponse } from '../../../shared/interface/tokenResponse';
import { PasswordModule } from 'primeng/password';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';
import {MessageService} from 'primeng/api';
import {FooterComponent} from '../../../layout/footer/footer.component';
import {ImageLoginSignupComponent} from '../../../layout/image-login-signup/image-login-signup.component';
@Component({
  selector: 'app-login',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    InputTextModule,
    PasswordModule,
    CheckboxModule,
    ButtonModule,
    CardModule,
    ToastModule,
    FooterComponent,
    ImageLoginSignupComponent,
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent {
  loginForm: FormGroup;
  isLoading = false;

  constructor(
    private authService: AuthService,
    private router: Router,
    private fb: FormBuilder,
    private messageService: MessageService
  ) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
      rememberMe: false
    });
  }
  login() {
    const val = this.loginForm.value;
    if (val.email && val.password) {
      this.authService.login(val.email, val.password).subscribe(
        (response) => {
          this.isLoading = true;
          sessionStorage.setItem('accessToken', response.accessToken);
          sessionStorage.setItem('refreshToken', response.refreshToken);

          const decoded: any = jwtDecode(response.accessToken);
          console.log(decoded);
          sessionStorage.setItem('roles', JSON.stringify(decoded.roles));
          this.authService.setLoggedIn(true);
          this.isLoading = false;
          this.router.navigate(['/home']);
        },
        (error) => {
          this.isLoading = false;
          const errors = error.error;
          if(errors && typeof errors === 'object'){
            Object.keys(errors).forEach(key =>{
              this.messageService.add({
                severity: 'error',
                summary: key,
                detail: errors[key]
              })
            });
          } else{
            this.messageService.add({
              severity: 'error',
              summary: errors,
              detail: error.error?.message || 'Unknown Error'
            })
          }
        }
      );
    }
  }

  isUserLoggedIn() {
    return true;
  }

  getLoginUrl() {
    return 'login';
  }
}
