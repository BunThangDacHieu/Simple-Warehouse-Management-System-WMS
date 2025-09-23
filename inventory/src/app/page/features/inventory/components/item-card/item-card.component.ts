import { Component, OnInit } from '@angular/core';
import { Item } from '../../../../../shared/model/item';
import { ItemService } from '../../../item/services/item.service';
import { InventoryService } from '../../services/inventory.service';
import { CardModule } from 'primeng/card';
import { MessageService } from 'primeng/api';
import { ButtonModule } from 'primeng/button';

@Component({
  selector: 'app-item-card',
  imports: [CardModule, ButtonModule],
  templateUrl: './item-card.component.html',
  styleUrl: './item-card.component.scss',
})
export class ItemCardComponent implements OnInit {
  items: Item[] = [];

  constructor(
    private itemService: ItemService,
    private inventoryService: InventoryService,
    private messageService: MessageService
  ) {}

  ngOnInit(): void {}

  getItems() {
    this.itemService.getAllItem().subscribe({
      next: (items) => {
        this.items = items;
      },
      error: (err) => {
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: 'Get Items Failed',
        });
      },
    });
  }
}
