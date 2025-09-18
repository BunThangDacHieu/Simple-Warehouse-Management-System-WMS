import { Component, OnInit } from '@angular/core';
import { ToastModule } from 'primeng/toast';
import { TableModule } from 'primeng/table';
import { WarehouseService } from '../../services/warehouse.service';
import { ButtonModule } from 'primeng/button';
import { InventoryService } from '../../../inventory/services/inventory.service';
import { Warehouse } from '../../../../shared/model/warehouse';
import { WarehouseDetailComponent } from '../../components/warehouse-detail/warehouse-detail.component';
import { Router } from '@angular/router';
import { Dialog } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';
import { RippleModule } from 'primeng/ripple';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MessageService } from 'primeng/api';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-warehouse-list',
  imports: [
    TableModule,
    WarehouseDetailComponent,
    ButtonModule,
    ToastModule,
    Dialog,
    ReactiveFormsModule,
    InputTextModule,
    CommonModule,
  ],
  standalone: true,
  templateUrl: './warehouse-list.component.html',
  styleUrl: './warehouse-list.component.scss',
})
export class WarehouseListComponent implements OnInit {
  warehouses: Warehouse[] = [];
  warehouse!: FormGroup;

  selectedWarehouse?: Warehouse;
  visibleCreate = false;
  visibleUpdate = false;

  constructor(
    private warehouseService: WarehouseService,
    private router: Router,
    private messageService: MessageService
  ) {}

  UpdateSuccess() {
    this.messageService.add({
      severity: 'success',
      summary: 'Success',
      detail: 'Update Warehouse Success',
    });
  }

  warehouseForm = new FormGroup({
    id: new FormControl(),
    name: new FormControl('', [
      Validators.required,
      Validators.minLength(5),
      Validators.pattern('[a-zA-Z]*'),
    ]),
    location: new FormControl('', [
      Validators.required,
      Validators.minLength(5),
      Validators.pattern('[a-zA-Z]*'),
    ]),
    capacity: new FormControl(0, [
      Validators.required,
      Validators.min(1),
      Validators.max(5000),
    ]),
  });
  get nameController() {
    return this.warehouseForm.controls;
  }
  viewDetail(id: number) {
    this.warehouseService.getWarehouseById(id).subscribe({
      next: (data) => {
        this.messageService.add({
          severity: 'success',
          summary: 'Success',
          detail: 'Get Warehouse ' + `${data.name}` + ' Success',
        });
      },
      error: (err) => console.error('Error somewhere', err),
    });
  }
  showDialogCreate() {
    this.warehouseForm.reset({ name: '', location: '', capacity: 0 });
    this.visibleCreate = true;
  }

  deleteWarehouse(id: number) {
    this.warehouseService.deleteWarehouse(id).subscribe({
      next: () => {
        this.ngOnInit();
        this.messageService.add({
          severity: 'success',
          summary: 'Success',
          detail: 'Delete Warehouse Success',
        });
      },
      error: (err) => {
        this.ngOnInit();
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: 'Delete Warehouse Failed',
        });
      },
    });
  }
  getAllWarehouse() {
    this.warehouseService.getAllWarehouse().subscribe({
      next: (data) => (this.warehouses = data),
      error: (err) => console.error('Error somewhere', err),
    });
  }
  ngOnInit(): void {
    this.getAllWarehouse();
  }
  editWarehouse(warehouse: Warehouse) {
    this.warehouseForm.patchValue(warehouse);
    this.visibleUpdate = true;
  }

  SaveandCreateWarehouse() {
    if (this.warehouseForm.invalid) {
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: 'Form Invalid',
      });
      return;
    }
    const data = this.warehouseForm.value;

    const warehouseValue: Warehouse = {
      id: data.id,
      name: data.name ?? '',
      location: data.location ?? '',
      capacity: data.capacity ?? 0,
    };
    this.warehouseService.createWarehouse(warehouseValue).subscribe({
      next: () => {
        this.ngOnInit(),
          (this.visibleCreate = false),
          this.messageService.add({
            severity: 'success',
            summary: 'Success',
            detail: 'Create Warehouse Success',
          });
      },
      error: () => {
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: 'Create Warehouse Failed',
        });
      },
    });
  }
  SaveandUpdateWarehouse() {
    if (this.warehouseForm.invalid) {
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: 'Form Invalid',
      });
      return;
    }
    const data = this.warehouseForm.value;
    const warehouseValue: Warehouse = {
      id: data.id ?? null,
      name: data.name ?? '',
      location: data.location ?? '',
      capacity: data.capacity ?? 0,
    };
    this.warehouseService
      .updateWarehouse(warehouseValue, warehouseValue.id)
      .subscribe({
        next: () => {
          this.ngOnInit(),
            (this.visibleUpdate = false),
            this.messageService.add({
              severity: 'success',
              summary: 'Success',
              detail: 'Update Warehouse Success',
            });
        },
        error: () => {
          this.messageService.add({
            severity: 'error',
            summary: 'Error',
            detail: 'Update Warehouse Failed',
          });
        },
      });
  }
}
