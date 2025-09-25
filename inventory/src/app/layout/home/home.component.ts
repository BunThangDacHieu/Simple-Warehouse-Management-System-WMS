import { Component } from '@angular/core';
import {WarehouseListComponent} from '../../page/features/warehouse/pages/warehouse-list/warehouse-list.component';
import {UserRoutingModule} from '../../page/features/user/user-routing.module';
import {Toast, ToastModule} from 'primeng/toast';
import {UserListComponent} from '../../page/features/user/component/user-list/user-list.component';
import {FormBuilder} from '@angular/forms';
import {ChartUserComponent} from '../../shared/util/chart-user/chart-user.component';

@Component({
  selector: 'app-home',
  imports: [WarehouseListComponent, UserListComponent, ToastModule, Toast, ChartUserComponent],
  templateUrl: './home.component.html',
  standalone: true,
  styleUrl: './home.component.scss'
})
export class HomeComponent {
  constructor() {}
}
