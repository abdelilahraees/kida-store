import {Component, OnInit} from '@angular/core';
import {Cart} from "../../../../core/interfaces/cart";
import {CartService} from "../../services/cart.service";
import {ResponseApi} from "../../../../core/interfaces/response-api";
import {Observable, tap} from "rxjs";
import {FormsModule} from "@angular/forms";
import {AsyncPipe, NgIf} from "@angular/common";
import {OrderService} from "../../../orders/services/order.service";
import {Router, RouterLink} from "@angular/router";
import {NotificationService} from "../../../notification/services/notification.service";

@Component({
  selector: 'app-cart-details',
  standalone: true,
  imports: [
    FormsModule,
    NgIf,
    AsyncPipe,
    RouterLink
  ],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponentDetails implements OnInit {

  cart$!: Observable<Cart | null> ;

  constructor(private cartService: CartService,private orderService:OrderService,private router:Router,private notification:NotificationService) {}

  ngOnInit(): void {
    this.cart$ = this.cartService.cart$; // On se connecte à l'état
    this.cartService.fetchCart().subscribe(); // On charge les données initiales
  }


  updateQuantity(productId: number, newQuantity: number): void {
    this.cartService.updateCart(productId, newQuantity).subscribe();
  }



  increment(id: number, quantity: number) {
    console.log(`Incrementing ${id} quantity: ${quantity}`);
    this.cartService.updateCart(id, quantity + 1).subscribe()
  }

  decrement(id: number, quantity: number) {
    if (quantity > 1) {
      this.cartService.updateCart(id, quantity - 1).subscribe()
    }
  }

  deleteProductItem(productItem: number) {
    this.cartService.deleteItem(productItem).subscribe()
  }



}
