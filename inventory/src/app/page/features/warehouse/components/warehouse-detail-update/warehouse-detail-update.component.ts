import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Warehouse } from '../../../../../shared/model/warehouse';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Dialog } from 'primeng/dialog';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { ToastModule } from 'primeng/toast';
import { InputTextModule } from 'primeng/inputtext';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-warehouse-detail-update',
  imports: [
    Dialog,
    TableModule,
    ButtonModule,
    ToastModule,
    Dialog,
    ReactiveFormsModule,
    InputTextModule,
    CommonModule,
  ],
  templateUrl: './warehouse-detail-update.component.html',
  styleUrl: './warehouse-detail-update.component.scss',
})
export class WarehouseDetailUpdateComponent {
  @Input() warehouse?: Warehouse;
  @Input() dialogUpdateVisible: boolean = false;

  //Output ra đóng và mở dialog cho phía component cha ở đây là Warehouse List
  @Output() dialogUpdateVisibleChange = new EventEmitter<boolean>();
  //Output ra lập dữ liệu cho phía component con trả về cho phía componnet cha ở đây là Warehouse List
  @Output() warehouseUpdated = new EventEmitter<any>();
  //Output ra dữ liệu của nhà kho- warehouse cần được xác định để update Warehouse cho component con

  warehouseForm!: FormGroup;

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.inForm();
  }

  inForm(): void {
    this.warehouseForm = this.fb.group({
      name: [this.warehouse?.name, Validators.required],
      location: [this.warehouse?.location, Validators.required],
      capacity: [this.warehouse?.capacity, Validators.required],
    });
  }

  ngOnChanges(): void {
    if (this.warehouse && this.warehouseForm) {
      this.warehouseForm.patchValue({
        name: this.warehouse.name,
        location: this.warehouse.location,
        capacity: this.warehouse.capacity,
      });
    }
  }

  handleWarehouseUpdated(): void {
    if (this.warehouseForm.valid && this.warehouse) {
      this.warehouseUpdated.emit({
        id: this.warehouse?.id,
        ...this.warehouseForm.value,
      });

      this.dialogUpdateVisible = false;
      this.dialogUpdateVisibleChange.emit(this.dialogUpdateVisible);

      this.warehouseForm.reset();
    }
  }
}
