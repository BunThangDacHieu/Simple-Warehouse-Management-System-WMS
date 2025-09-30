import {Component, OnInit} from '@angular/core';
import { TableModule } from 'primeng/table';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ItemService} from '../../services/item.service';
import {MessageService} from 'primeng/api';
import {Item} from '../../../../../shared/model/item';
import {Button} from 'primeng/button';
import {Dialog} from 'primeng/dialog';
import {Toast} from 'primeng/toast';
import {WarehouseDetailComponent} from '../../../warehouse/components/warehouse-detail/warehouse-detail.component';
import {
  WarehouseDetailCreateComponent
} from '../../../warehouse/components/warehouse-detail-create/warehouse-detail-create.component';
import {
  WarehouseDetailUpdateComponent
} from '../../../warehouse/components/warehouse-detail-update/warehouse-detail-update.component';

@Component({
  selector: 'app-item-list',
  imports: [TableModule, Button, Dialog, Toast, WarehouseDetailComponent, WarehouseDetailCreateComponent, WarehouseDetailUpdateComponent],
  templateUrl: './item-list.component.html',
  styleUrl: './item-list.component.scss',
})
export class ItemListComponent implements OnInit{
  items: Item[] = [];
  itemForm: FormGroup;

  constructor(
    private itemService: ItemService,
    private messageService: MessageService,
    private fb: FormBuilder
  ) {
    this.itemForm = this.fb.group({
      id: [null],
      name: ['', [Validators.required]],
      description: ['', [Validators.required]],
      quantity: [0, Validators.required],
      itemId: ['', [Validators.required]],
      unit: ['', [Validators.required]],
    })
  }
  ngOnInit(){}

  GetAllItems(): void{
    this.itemService.getAllItem().subscribe({
      next: data => {this.items = data; },
      error: error => console.log(error),
    })
  }

  handleCreateNewItem(newItem: Item): void{
    if(this.itemForm.invalid) return;
    this.items.push(newItem);
    this.itemService.createItem(newItem).subscribe({
      next: data => {
        this.messageService.add({severity:'success', summary: 'Thành công', detail: 'Tạo mới sản phẩm thành công!'});
      },
      error: error => {
        this.messageService.add({
          severity: 'error', summary: 'Thất bại', detail: error.error?.message || 'Tạo mới sản phẩm thất bại. Vui lòng thử lại.'
        })
      },
    })
  }

  handlerUpdateItem(updatedItem: Item): void {
    if (this.itemForm.invalid) return;
    const index = this.items.findIndex(item => item.id === updatedItem.id);
    if (index !== -1) {
      this.items[index] = updatedItem;
    }
    this.itemService.updateItem(updatedItem, updatedItem.id).subscribe({
      next: data => {
        this.messageService.add({severity: 'success', summary: 'Thành công', detail: 'Cập nhật sản phẩm thành công!'});
      },
      error: error => {
        this.messageService.add({
          severity: 'error',
          summary: 'Thất bại',
          detail: error.error?.message || 'Cập nhật sản phẩm thất bại. Vui lòng thử lại.'
        })
      },
    })
  }
}
