import {Component, OnInit} from '@angular/core';
import {OrderService} from "../services/order.service";
import {Order} from "../../../core/interfaces/order";
import {ResponseApi} from "../../../core/interfaces/response-api";
import {OrderComponent} from "../order/order.component";

@Component({
  selector: 'app-order-page-admin',
  standalone: true,
  imports: [
    OrderComponent
  ],
  templateUrl: './order-page-admin.component.html',
  styleUrl: './order-page-admin.component.css'
})
export class OrderPageAdminComponent {
  public orders: Order[] = []


  constructor(private orderService: OrderService) {
  }


  ngOnInit() {
    this.orderService.getAllOrders().subscribe((res) => {
      this.orders = res
    })
  }

}
