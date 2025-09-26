import {Component, OnInit} from '@angular/core';
import {User} from '../../../../../shared/model/user';
import {HttpClient} from '@angular/common/http';
import {UsersService} from '../../services/users.service';
import {ActivatedRoute} from '@angular/router';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

@Component({
  selector: 'app-user-detail',
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './user-detail.component.html',
  styleUrl: './user-detail.component.scss'
})
export class UserDetailComponent implements OnInit {
  user?: User;

  constructor(
    private userService: UsersService,
    private route: ActivatedRoute
  ) {}
  ngOnInit(): void {
    const id = Number(this.route.snapshot.params['id']);
      this.userService.getUserById(id).subscribe(data => {
      this.user = data;
    })
  }
}
