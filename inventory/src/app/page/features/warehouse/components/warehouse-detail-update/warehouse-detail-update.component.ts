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
  @Input() selectedWarehouse?: Warehouse;
  @Input() dialogUpdateVisible: boolean = false;

  //Output ra đóng và mở dialog cho phía component cha ở đây là Warehouse List
  @Output() dialogUpdateVisibleChange = new EventEmitter<boolean>();
  //Output ra lập dữ liệu cho phía component con trả về cho phía componnet cha ở đây là Warehouse List
  @Output() warehouseUpdated = new EventEmitter<any>();

  warehouseForm!: FormGroup;

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.inForm();
  }

  inForm(): void {
    this.warehouseForm = this.fb.group({
      name: [this.selectedWarehouse?.name, Validators.required],
      location: [this.selectedWarehouse?.location, Validators.required],
      capacity: [this.selectedWarehouse?.capacity, Validators.required],
    });
  }

  ngOnChanges(): void {
    if (this.selectedWarehouse && this.warehouseForm) {
      this.warehouseForm.patchValue({
        name: this.selectedWarehouse.name,
        location: this.selectedWarehouse.location,
        capacity: this.selectedWarehouse.capacity,
      });
    }
  }

  handleWarehouseUpdated(): void {
    if (this.warehouseForm.valid && this.selectedWarehouse) {
      this.warehouseUpdated.emit({
        id: this.selectedWarehouse?.id,
        ...this.warehouseForm.value,
      });

      // Đóng dialog sau khi lưu
      this.dialogUpdateVisible = false;
      this.dialogUpdateVisibleChange.emit(this.dialogUpdateVisible);

      this.warehouseForm.reset();
    }
  }
}
