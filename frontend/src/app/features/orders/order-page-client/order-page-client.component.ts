import {Component, OnInit} from '@angular/core';
import {ResponseApi} from "../../../core/interfaces/response-api";
import {OrderService} from "../services/order.service";
import {JsonPipe} from "@angular/common";
import {Order} from "../../../core/interfaces/order";
import {OrderComponent} from "../order/order.component";

@Component({
  selector: 'app-order-page-client',
  standalone: true,
  imports: [
    JsonPipe,
    OrderComponent
  ],
  templateUrl: './order-page-client.component.html',
  styleUrl: './order-page-client.component.css'
})
export class OrderPageClientComponent implements OnInit {
  public orders: Order[] = []


  constructor(private orderService: OrderService) {
  }


  ngOnInit() {
    this.orderService.getOrderCurrentCustomers().subscribe((res) => {
      console.log(res)
      this.orders = res
    })
  }


}
