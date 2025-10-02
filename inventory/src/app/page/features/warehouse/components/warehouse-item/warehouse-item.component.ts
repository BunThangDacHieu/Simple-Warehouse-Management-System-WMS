import {Component, OnInit} from '@angular/core';
import {ButtonModule} from 'primeng/button';
import {ToastModule} from 'primeng/toast';
import {CardModule} from 'primeng/card';
import {CommonModule} from '@angular/common';
import {ActivatedRoute, Router} from '@angular/router';
import {ItemService} from '../../../item/services/item.service';
import {WarehouseService} from '../../services/warehouse.service';
import {MessageService} from 'primeng/api';
import {Warehouse} from '../../../../../shared/model/warehouse';
import {Item} from '../../../../../shared/model/item';

@Component({
  selector: 'app-warehouse-item',
  imports: [ButtonModule, CardModule, ToastModule, CommonModule],
  templateUrl: './warehouse-item.component.html',
  styleUrl: './warehouse-item.component.scss'
})
export class WarehouseItemComponent implements OnInit {
  warehouseId!: number;
  warehouse?: Warehouse;
  items: Item[] = [];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private itemService: ItemService,
    private warehouseService: WarehouseService,
    private messageService: MessageService
  ) {}

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.warehouseId = +params['id'];
      this.loadWarehouseData();
      this.loadWarehouseItems();
    });
  }

  loadWarehouseData() {
    // Load thông tin warehouse (nếu service có method này)
    this.warehouseService.getWarehouseById(this.warehouseId).subscribe({
      next: (data) => {
        this.warehouse = data;
      },
      error: (error) => {
        console.error('Error loading warehouse:', error);
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: 'Failed to load warehouse information'
        });
      }
    });
  }

  loadWarehouseItems() {
    this.itemService.getItemByWarehouseId(this.warehouseId).subscribe({
      next: (data) => {
        this.items = data;
        this.messageService.add({
          severity: 'success',
          summary: 'Success',
          detail: `Loaded ${data.length} items`
        });
      },
      error: (error) => {
        console.error('Error loading items:', error);
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: 'Failed to load items'
        });
      }
    });
  }

  goBack() {
    this.router.navigate(['/home']);
  }
}
