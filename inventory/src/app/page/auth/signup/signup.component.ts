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
      role: ['CUSTOMER', Validators.required],
      name: ['', [Validators.required, Validators.minLength(10), Validators.maxLength(20)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$')]],
      address: [''],
      phone: [''],
      contractPerson: [''],
    });
  }

  register() {
    const role = this.registerForm.get('role')?.value;
    console.log(role);

    // Reset validators cho address và phone trước
    this.registerForm.get('address')?.clearValidators();
    this.registerForm.get('phone')?.clearValidators();

    // Nếu là SUPPLIER thì thêm validators
    if (role === 'SUPPLIER') {
      this.registerForm.get('address')?.setValidators([
        Validators.required,
        Validators.pattern('^[\\p{L}0-9 ,.\\-]+$')
      ]);
      this.registerForm.get('phone')?.setValidators([
        Validators.required,
        Validators.pattern('^0[0-9]{9}$')
      ]);
      this.registerForm.get('contractPerson')?.setValidators([
        Validators.required,
        Validators.pattern('^[\\p{L}0-9 ,.\\-]+$')
      ]);

      // Cập nhật trạng thái validation
      this.registerForm.get('address')?.updateValueAndValidity();
      this.registerForm.get('phone')?.updateValueAndValidity();
    } else {
      // Nếu là CUSTOMER, cập nhật để xóa errors cũ
      this.registerForm.get('address')?.updateValueAndValidity();
      this.registerForm.get('phone')?.updateValueAndValidity();
    }

    // Kiểm tra form hợp lệ
    // if (this.registerForm.invalid) {
    //   this.messageService.add({
    //     severity: 'warn',
    //     summary: 'Cảnh báo',
    //     detail: 'Vui lòng điền đầy đủ thông tin hợp lệ.'
    //   });
    //   return;
    // }

    // Tạo request object
    let request: any = {
      name: this.registerForm.get('name')?.value,
      email: this.registerForm.get('email')?.value,
      password: this.registerForm.get('password')?.value,
      role: role
    }

    // Chỉ thêm address và phone nếu là SUPPLIER
    if (role === 'SUPPLIER') {
      request.address = this.registerForm.get('address')?.value;
      request.phone = this.registerForm.get('phone')?.value;
    }

    this.authService.register(request).subscribe({
      next: (res) => {
        this.messageService.add({
          severity: 'success',
          summary: 'Thành công',
          detail: 'Đăng ký thành công! Vui lòng đăng nhập.',
        });
        this.router.navigate(['/login']);
      },
      error: (err) => {
        if (err.error && typeof err.error === 'object') {
          for (const field in err.error) {
            if (err.error.hasOwnProperty(field)) {
              this.messageService.add({
                severity: 'error',
                summary: 'Lỗi',
                detail: `${field}: ${err.error[field]}`
              });
            }
          }
        } else {
          this.messageService.add({
            severity: 'error',
            summary: 'Lỗi',
            detail: err.error?.message || 'Đăng ký thất bại. Vui lòng thử lại.'
          });
        }
      }
    });
  }

}
