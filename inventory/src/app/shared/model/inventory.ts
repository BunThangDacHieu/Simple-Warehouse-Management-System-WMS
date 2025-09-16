import { Item } from './item';
import { Warehouse } from './warehouse';

export interface Inventory {
  id: number;
  item: Item;
  warehouse: Warehouse;
  quantity: number;
}
