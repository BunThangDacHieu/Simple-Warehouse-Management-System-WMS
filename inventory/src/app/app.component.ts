import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from './layout/header/header.component';
import { SidebarComponent } from './layout/sidebar/sidebar.component';
import { WarehouseListComponent } from './page/features/warehouse/pages/warehouse-list/warehouse-list.component';
import { WarehouseDetailComponent } from './page/features/warehouse/components/warehouse-detail/warehouse-detail.component';
import { InventoryListComponent } from './page/features/inventory/pages/inventory-list/inventory-list.component';
import { LoginComponent } from './page/auth/login/login.component';
import { WarehouseDetailCreateComponent } from './page/features/warehouse/components/warehouse-detail-create/warehouse-detail-create.component';
import { WarehouseDetailUpdateComponent } from './page/features/warehouse/components/warehouse-detail-update/warehouse-detail-update.component';
import { ItemCardComponent } from './page/features/inventory/components/item-card/item-card.component';
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    HeaderComponent,
    InventoryListComponent,
    WarehouseListComponent,
    ItemCardComponent,
    LoginComponent,
    WarehouseDetailComponent,
    WarehouseDetailCreateComponent,
    WarehouseDetailUpdateComponent,
  ],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {
  title = 'WMS';
}
