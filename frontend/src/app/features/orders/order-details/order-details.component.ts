import {Component} from '@angular/core';
import {OrderService} from "../services/order.service";
import {ActivatedRoute} from "@angular/router";
import {Order} from "../../../core/interfaces/order";
import {CurrencyPipe, DatePipe, NgClass, NgForOf, NgIf} from "@angular/common";
import {UpdateOrderComponent} from "../update-order/update-order.component";

@Component({
  selector: 'app-order-details',
  standalone: true,
  imports: [
    DatePipe,
    NgClass,
    CurrencyPipe,
    NgForOf,
    NgIf,
    UpdateOrderComponent
  ],
  templateUrl: './order-details.component.html',
  styleUrl: './order-details.component.css'
})
export class OrderDetailsComponent {
  order: Order = {} as Order
  isModalOpen = false;

  openModal(): void {
    this.isModalOpen = true;
  }

  closeModal(): void {
    this.isModalOpen = false;
  }


  handleStatusSave(newStatus: string): void {
    this.orderService.updateOrder(this.order.id, newStatus).subscribe(() => {
      this.order.state = newStatus;
      this.closeModal();
    });
    this.order.state = newStatus;
    console.log(this.order.state)
    this.closeModal();
  }

  constructor(private orderService: OrderService, private route: ActivatedRoute) {
    const orderId = this.route.snapshot.paramMap.get('id');
    if (orderId) {
      this.orderService.getOrderById(+orderId).subscribe((res) => {
        this.order = res
      })
    }
  }


  calcTotalItem(price: number | undefined, quantity:number): number {
    if (price!=undefined){
     return  price*quantity
    }else {
      return 0
    }
  }

}
