import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from './layout/header/header.component';
import { SidebarComponent } from './layout/sidebar/sidebar.component';
import { WarehouseListComponent } from './page/features/warehouse/pages/warehouse-list/warehouse-list.component';
import { WarehouseDetailComponent } from './page/features/warehouse/components/warehouse-detail/warehouse-detail.component';
import { InventoryListComponent } from './page/features/inventory/pages/inventory-list/inventory-list.component';
import { LoginComponent } from './page/login/login.component';
@Component({
  selector: 'app-root',
  imports: [
    RouterOutlet,
    HeaderComponent,
    InventoryListComponent,
    WarehouseListComponent,
    LoginComponent,
  ],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {
  title = 'WMS';
}
