import { Component, OnInit } from '@angular/core';
import { ToastModule } from 'primeng/toast';
import { TableModule } from 'primeng/table';
import { WarehouseService } from '../../services/warehouse.service';
import { ButtonModule } from 'primeng/button';
import { InventoryService } from '../../../inventory/services/inventory.service';
import { Warehouse } from '../../../../../shared/model/warehouse';
import { WarehouseDetailComponent } from '../../components/warehouse-detail/warehouse-detail.component';
import { Router } from '@angular/router';
import { Dialog } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';
import { RippleModule } from 'primeng/ripple';
import { WarehouseDetailCreateComponent } from '../../components/warehouse-detail-create/warehouse-detail-create.component';
import { WarehouseDetailUpdateComponent } from '../../components/warehouse-detail-update/warehouse-detail-update.component';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MessageService } from 'primeng/api';
import { CommonModule } from '@angular/common';
import {UserListComponent} from '../../../user/component/user-list/user-list.component';

@Component({
  selector: 'app-warehouse-list',
  imports: [
    TableModule,
    WarehouseDetailComponent,
    ButtonModule,
    WarehouseDetailCreateComponent,
    WarehouseDetailUpdateComponent,
    ToastModule,
    Dialog,
    ReactiveFormsModule,
    InputTextModule,
    CommonModule,
    UserListComponent
  ],
  standalone: true,
  templateUrl: './warehouse-list.component.html',
  styleUrls: ['./warehouse-list.component.scss'],
})
export class WarehouseListComponent implements OnInit {
  warehouses: Warehouse[] = [];
  warehouseForm: FormGroup;

  selectedWarehouse?: Warehouse;
  dialogVisible = false;
  dialogInfoVisible = false;
  dialogCreateVisible = false;
  dialogUpdateVisible = false;

  constructor(
    private warehouseService: WarehouseService,
    private messageService: MessageService,
    private fb: FormBuilder
  ) {
    this.warehouseForm = this.fb.group({
      id: [null],
      name: ['', [Validators.required, Validators.minLength(3)]],
      location: ['', Validators.required],
      capacity: [0, [Validators.required, Validators.min(1)]],
    });
  }

  ngOnInit() {
    this.getAllWarehouse();
  }

  getAllWarehouse() {
    this.warehouseService.getAllWarehouse().subscribe({
      next: (data) => (this.warehouses = data),
      error: (err) => console.error(err),
    });
  }

  showDialog() {
    this.dialogVisible = true;
  }
  // Hiển thị dialog
  showDialogInfo(warehouse: Warehouse) {
    this.selectedWarehouse = warehouse;
    this.dialogInfoVisible = true;
  }

  showDialogCreate() {
    this.dialogCreateVisible = true;
  }

  showDialogUpdate(warehouse: Warehouse) {
    this.selectedWarehouse = warehouse;
    this.warehouseForm.patchValue(warehouse);
    this.dialogUpdateVisible = true;
  }

  onChangeDialogUpdate() {
    this.dialogUpdateVisible = !this.dialogUpdateVisible;
  }

  handleWarehouseCreated(newWarehouse: Warehouse): void {
    if (this.warehouseForm.invalid) return;
    this.warehouses.push(newWarehouse);
    this.warehouseService.createWarehouse(newWarehouse).subscribe({
      next: () => {
        this.getAllWarehouse();
        this.dialogCreateVisible = false;
        this.messageService.add({
          severity: 'success',
          summary: 'Success',
          detail: 'Warehouse Created',
        });
      },
      error: () => {
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: 'Create Failed',
        });
      },
    });
  }

  handleWarehouseUpdated(updateWarehouse: Warehouse) {
    if (this.warehouseForm.invalid) return;
    this.warehouseService
      .updateWarehouse(updateWarehouse, updateWarehouse.id)
      .subscribe({
        next: () => {
          this.getAllWarehouse();
          this.dialogUpdateVisible = false;
          this.messageService.add({
            severity: 'success',
            summary: 'Success',
            detail: 'Warehouse Updated',
          });
        },
        error: () => {
          this.messageService.add({
            severity: 'error',
            summary: 'Error',
            detail: 'Update Failed',
          });
        },
      });
  }

  deleteWarehouse(id: number) {
    this.warehouseService.deleteWarehouse(id).subscribe({
      next: () => {
        this.getAllWarehouse();
        this.messageService.add({
          severity: 'success',
          summary: 'Success',
          detail: 'Warehouse Deleted',
        });
      },
      error: () => {
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: 'Delete Failed',
        });
      },
    });
  }
}
