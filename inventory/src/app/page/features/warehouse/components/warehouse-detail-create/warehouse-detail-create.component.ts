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
  selector: 'app-warehouse-detail-create',
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
  templateUrl: './warehouse-detail-create.component.html',
  styleUrl: './warehouse-detail-create.component.scss',
})
export class WarehouseDetailCreateComponent {
  @Input() selectedWarehouse?: Warehouse;
  @Input() dialogVisible: boolean = false;

  @Output() dialogCreateVisibleChange = new EventEmitter<boolean>();
  @Output() newWarehouseCreated = new EventEmitter<Warehouse>();

  warehouseForm!: FormGroup;

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.warehouseForm = this.fb.group({
      name: ['', Validators.required],
      location: ['', Validators.required],
      capacity: [null, Validators.required],
    });
  }

  // Phương thức lưu và tạo mới
  handleWarehouseCreated(): void {
    if (this.warehouseForm.valid) {
      // Gửi dữ liệu form về component cha
      this.newWarehouseCreated.emit(this.warehouseForm.value as Warehouse);

      // Đóng dialog sau khi lưu
      this.dialogVisible = false;
      this.dialogCreateVisibleChange.emit(this.dialogVisible);

      // Reset form
      this.warehouseForm.reset();
    }
  }
}
