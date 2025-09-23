import { Component, EventEmitter, Output } from '@angular/core';
import { Input } from '@angular/core';
import { Warehouse } from '../../../../../shared/model/warehouse';
import { CommonModule } from '@angular/common';
import { Dialog } from 'primeng/dialog';

@Component({
  selector: 'app-warehouse-detail',
  imports: [CommonModule, Dialog],
  templateUrl: './warehouse-detail.component.html',
  styleUrl: './warehouse-detail.component.scss',
})
export class WarehouseDetailComponent {
  @Input() selectedWarehouse?: Warehouse;
  dialogInfoVisible = false;
  @Output() dialogInfoVisibleChange = new EventEmitter<boolean>();

  showDialogInfo(warehouse: Warehouse) {
    this.selectedWarehouse = warehouse;
    this.dialogInfoVisible = true;
    this.dialogInfoVisibleChange.emit(this.dialogInfoVisible);
  }
}
