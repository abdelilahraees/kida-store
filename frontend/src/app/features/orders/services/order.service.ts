import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {env} from "../../../env/enviroment";
import {BehaviorSubject, Observable} from "rxjs";
import {Order} from "../../../core/interfaces/order";
import {OrderAddress} from "../../../core/interfaces/order-address";
import {Cart} from "../../../core/interfaces/cart";
import {CartService} from "../../cart/services/cart.service";

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private readonly url!: string;
  cartItemsCount$!: BehaviorSubject<number>;

  constructor(private readonly http: HttpClient, private cartService: CartService) {
    this.url = env.url;
    this.cartItemsCount$ = this.cartService.cartItemCount$;
  }


  addOrder(address: OrderAddress): Observable<any> {
    const formData = new FormData();
    formData.append('street', address?.street || '');
    formData.append('city', address?.city || '');
    formData.append('houseNumber', address?.houseNumber || '');
    formData.append("postCode", address?.postCode || '');
    formData.append("country", address?.country || '');
    this.cartItemsCount$.next(0)
    return this.http.post(`${this.url}/profile/orders`, formData);
  }

  getOrderCurrentCustomers(): Observable<Order[]> {
    return this.http.get<Order[]>(`${this.url}/profile/orders`)
  }

  getAllOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(`${this.url}/orders`)
  }

  getOrderById(id: number): Observable<Order> {
    return this.http.get<Order>(`${this.url}/orders/${id}`)
  }

  updateOrder(orderId: number, status: string):Observable<any> {
    const formData = new FormData()
    formData.append("stateOrder", status)
   return this.http.put(`${this.url}/orders/${orderId}`, formData)
  }

}
