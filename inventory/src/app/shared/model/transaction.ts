import { Item } from './item';
import { Warehouse } from './warehouse';

export interface Transaction {
  id: number;
  quantity: number;
  type: string;
  item: Item;
  warehouse: Warehouse;
  date: string;
  note: string;
}
