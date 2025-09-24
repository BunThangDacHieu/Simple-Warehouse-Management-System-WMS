import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UsersService } from '../../services/users.service';
import { User } from '../../../../../shared/model/user';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { MessageService } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { HttpClientModule } from '@angular/common/http';
import { TableModule } from 'primeng/table';
import {Dialog, DialogModule} from 'primeng/dialog';
import {CommonModule} from '@angular/common';
import {Warehouse} from '../../../../../shared/model/warehouse';
import {Toast, ToastModule} from 'primeng/toast';

@Component({
  selector: 'app-user-list',
  imports: [TableModule, DialogModule, ButtonModule, HttpClientModule, Dialog, CommonModule, Toast, ToastModule],
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss'],
  providers: [MessageService],
})
export class UserListComponent implements OnInit {
  users: User[] = [];
  userForm: FormGroup;
  dialogVisible = false;
  dialogInfoVisible = false;
  dialogUpdateVisible = false;

  constructor(
    private usersService: UsersService,
    private router: Router,
    private messageService: MessageService,
    private fb: FormBuilder
  ) {
    this.userForm = this.fb.group({
      // id, nam, email, password, role, contract_person,phone, address
      id: [null],
      name: ['', [Validators.minLength(10), Validators.maxLength(20)]],
      email: ['',[Validators.email]],
      password: ['', [Validators.minLength(8)]],
      contract_person: ['', [Validators.minLength(10), Validators.maxLength(20)]],
      phone: ['', [ Validators.pattern('^0[0-9]{9}$')]],
      address: ['', [Validators.pattern('^(?=.*\\p{L})[\\p{L}0-9 ,.-]+$')]]
    })
  }

  ngOnInit(): void {
    this.getAllUser();
  }

  showDialog() {
    this.dialogVisible = true;
  }

  showDialogInfo(user: User) {
    this.dialogInfoVisible = true;
  }

  showDialogUpdate(user: User) {
    this.userForm.patchValue(user);
    this.dialogUpdateVisible = true;
  }

  getAllUser() {
    this.usersService.getAllUser().subscribe({
      next: (data) => (this.users = data),
      error: (err) => console.error(err.error),
    });
    this.messageService.add({
      severity: 'success',
      summary: 'Success',
      detail: 'Get User Success',
    });
  }



  handleUpdateUser(updatedUser: User){
    if(this.userForm.invalid) return;
    this.usersService
      .updateUser(updatedUser, updatedUser.id).subscribe({
      next: () => {
        this.getAllUser();
        this.dialogVisible = false;
        this.messageService.add({
          severity: 'success',
          summary: 'success',
          detail: 'Update Failed'
        });
      },
      error: () => {
        this.messageService.add({
          severity: 'error',
          summary: 'error',
          detail: 'Update Failed'
        })
      }
    })
  }

  findUserbyId(id: number){
    this.usersService.getUserById(id).subscribe({
      next: (user) => {
        this.messageService.add({
          severity: 'success',
          summary: 'Success',
          detail: `User: ${user.name} is selected`
        })
      }
    })
  }

  deleteUser(id: number){
    this.usersService.deleteUser(id).subscribe({
      next: ()=>{
        this.findUserbyId(id);
        this.messageService.add({
          severity: 'success',
          summary: 'success',
          detail: `Delete user with id ${id} successfully`
        })
      },
      error: () => {
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: 'Delete Failed',
        });
      },
    })
  }
}
