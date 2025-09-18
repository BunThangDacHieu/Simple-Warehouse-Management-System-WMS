import { Component, OnInit } from '@angular/core';
import { InventoryService } from '../../services/inventory.service';
import { Inventory } from '../../../../shared/model/inventory';
import { MessageService } from 'primeng/api';
import { TableModule } from 'primeng/table';
import { CommonModule } from '@angular/common';
import { ToastModule } from 'primeng/toast';
import { ReactiveFormsModule } from '@angular/forms';
@Component({
  selector: 'app-inventory-list',
  imports: [TableModule, CommonModule, ToastModule, ReactiveFormsModule],
  templateUrl: './inventory-list.component.html',
  styleUrl: './inventory-list.component.scss',
})
export class InventoryListComponent implements OnInit {
  inventories: Inventory[] = [];

  constructor(
    private inventoryService: InventoryService,
    private messageService: MessageService
  ) {
    this.inventoryService = inventoryService;
    this.messageService = messageService;
  }

  ngOnInit(): void {
    this.getAllInventory();
  }

  getAllInventory() {
    this.inventoryService.getAllInventory().subscribe({
      next: (data) => {
        this.inventories = data;
        this.messageService.add({
          severity: 'success',
          summary: 'Success',
          detail: 'Get All Inventory Success',
        });
      },
      error: (error) => {
        this.messageService.add({
          severity: 'error',
          summary: 'error',
          detail: 'Get All Inventory Failed',
        });
      },
    });
  }
}
