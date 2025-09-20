import {Routes} from "@angular/router";
import {ProfilComponent} from "./profil/profil.component";
import {CartComponentDetails} from "../../features/cart/components/cart/cart.component";
import {OrderPageClientComponent} from "../../features/orders/order-page-client/order-page-client.component";
import {CreateOrderComponent} from "../../features/orders/create-order/create-order.component";

export const routes: Routes = [
  {
    path: '', component: ProfilComponent, children: [
      {
        path: '', redirectTo: 'orders', pathMatch: "full"
      },
      {
        path: 'create-order', component: CreateOrderComponent
      }
      ,


      {
        path: 'orders', component: OrderPageClientComponent
      },
      {
        path: 'cart', component: CartComponentDetails,

      }
    ]
  }
]
