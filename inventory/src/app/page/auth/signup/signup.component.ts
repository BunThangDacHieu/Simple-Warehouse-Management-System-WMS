import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { CheckboxModule } from 'primeng/checkbox';
import { InputTextModule } from 'primeng/inputtext';
import { ToastModule } from 'primeng/toast';
import { CardModule } from 'primeng/card';
import { PasswordModule } from 'primeng/password';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import {DropdownModule} from 'primeng/dropdown';
import {ImageLoginSignupComponent} from '../../../layout/image-login-signup/image-login-signup.component';
@Component({
  selector: 'app-signup',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    InputTextModule,
    PasswordModule,
    CheckboxModule,
    ButtonModule,
    CardModule,
    ToastModule,
    DropdownModule,
    ImageLoginSignupComponent
  ],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.scss',
})
export class SignupComponent {
  registerForm: FormGroup;

  roles = [
    { label: 'Người mua', value: 'CUSTOMER' },
    { label: 'Nhà cung cấp', value: 'SUPPLIER' }
  ];

  constructor(
    private authService: AuthService,
    private router: Router,
    private fb: FormBuilder,
    private messageService: MessageService
  ) {
    this.registerForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(10), Validators.maxLength(20)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$')]],
      address: ['', [Validators.required, Validators.pattern('^[\\p{L}0-9 ,.\\-]+$')]],
      phone: ['', [Validators.pattern('^0[0-9]{9}$')]],
      role: [null, [Validators.required]],
    });
  }

  fieldLabels: {[key: string]: string} = {
    Name: 'Tên',
    Email: ' Email',
    Password: 'Mật Khẩu',
    Phone: ' Số điện thoại',
    Address: 'Địa Chỉ',
    Role: 'Vai trò'
  }

  register() {
    this.authService.register(this.registerForm.value).subscribe(
      (response) => {
        this.messageService.add({
          severity: 'success',
          summary: 'Thành công',
          detail: 'Đăng ký tài khoản thành công'
        });
        this.router.navigate(['/login']);
      },
      (error) => {
        const errors = error.error;
        console.log(errors);
        if (errors && typeof errors === 'object') {
          Object.keys(errors).forEach(key => {
            const label = this.fieldLabels[key] || key;
            const messages = Array.isArray(errors[key])
              ? errors[key].join(', ')
              : errors[key];
            this.messageService.add({
              severity: 'error',
              summary: label,
              detail: messages
            });
          });
        } else {
          this.messageService.add({
            severity: 'error',
            summary: 'Lỗi',
            detail: error.message || 'Unknown Error'
          });
        }
      }
    );
  }

}
