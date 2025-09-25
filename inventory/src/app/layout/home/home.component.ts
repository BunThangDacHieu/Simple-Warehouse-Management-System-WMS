import { Component } from '@angular/core';
import {WarehouseListComponent} from '../../page/features/warehouse/pages/warehouse-list/warehouse-list.component';
import {Toast, ToastModule} from 'primeng/toast';
import {UserListComponent} from '../../page/features/user/component/user-list/user-list.component';
import {ChartUserComponent} from '../../shared/chart/chart-user/chart-user.component';
import {ButtonModule,Button} from 'primeng/button';
import {DialogModule} from 'primeng/dialog';
import {ChartWarehouseComponent} from '../../shared/chart/chart-warehouse/chart-warehouse.component';

@Component({
  selector: 'app-home',
  imports: [
    WarehouseListComponent,
    UserListComponent,
    ToastModule,
    Toast,
    ChartUserComponent,
    ButtonModule,
    Button,
    DialogModule,
    ChartWarehouseComponent,
  ],
  templateUrl: './home.component.html',
  standalone: true,
  styleUrl: './home.component.scss'
})
export class HomeComponent {
  constructor() {}
  showWarehouseDialog = false;
  showUserDialog = false;
}
