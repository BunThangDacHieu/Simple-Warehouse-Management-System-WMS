import { Component } from '@angular/core';
import { Input } from '@angular/core';
import { Warehouse } from '../../../../../shared/model/warehouse';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-warehouse-detail',
  imports: [CommonModule],
  templateUrl: './warehouse-detail.component.html',
  styleUrl: './warehouse-detail.component.scss',
})
export class WarehouseDetailComponent {
  @Input() warehouse?: Warehouse;
}
