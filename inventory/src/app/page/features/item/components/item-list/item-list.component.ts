import {Component, OnInit} from '@angular/core';
import { TableModule } from 'primeng/table';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ItemService} from '../../services/item.service';
import {MessageService} from 'primeng/api';
import {Item} from '../../../../../shared/model/item';

@Component({
  selector: 'app-item-list',
  imports: [TableModule],
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

  GetAllItems(): void{}
}
