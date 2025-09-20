import {OrderItems} from "./order-items";
import {OrderAddress} from "./order-address";

export interface Order {
  id: number;
  total: number
  createdAt: string
  state: string
  orderItems?: OrderItems[]
  address?:OrderAddress
}
