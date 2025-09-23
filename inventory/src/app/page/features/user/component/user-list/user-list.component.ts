import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UsersService } from '../../services/users.service';
import { User } from '../../../../../shared/model/user';
import { FormGroup } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { HttpClientModule } from '@angular/common/http';
import { TableModule } from 'primeng/table';
import { DialogModule } from 'primeng/dialog';

@Component({
  selector: 'app-user-list',
  imports: [TableModule, DialogModule, ButtonModule, HttpClientModule],
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss'],
  providers: [MessageService],
})
export class UserListComponent implements OnInit {
  users: User[] = [];
  user!: FormGroup;
  dialogVisible: boolean = false;

  constructor(
    private usersService: UsersService,
    private router: Router,
    private messageService: MessageService
  ) {}

  ngOnInit(): void {}

  showDialog() {
    this.dialogVisible = true;
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
}
