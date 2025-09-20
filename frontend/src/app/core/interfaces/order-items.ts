import {Product} from "./product";

export interface OrderItems {
  id?: number;
  product: Product;
  quantity: number;
}
