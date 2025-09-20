import {Routes} from "@angular/router";
import {AdminHomeComponent} from "./admin-home/admin-home.component";
import {ProductListComponent} from "../../features/products/product-list/product-list.component";
import {AddProductComponent} from "../../features/products/add-product/add-product.component";
import {OrderPageClientComponent} from "../../features/orders/order-page-client/order-page-client.component";
import {OrderPageAdminComponent} from "../../features/orders/order-page-admin/order-page-admin.component";
import {OrderDetailsComponent} from "../../features/orders/order-details/order-details.component";

export const routes: Routes = [{
  path: '', component: AdminHomeComponent, children: [
    {
      path: '', redirectTo: 'orders', pathMatch: "full"
    },
    {
      path: 'products', component: ProductListComponent
    },
    {
      path: 'orders', component: OrderPageAdminComponent
    },
    {
      path: 'orders/:id', component: OrderDetailsComponent
    }
    ,
    {
      path: 'add-product', component: AddProductComponent
    },
    {
      path: 'edit-product/:id', component: AddProductComponent
    }

  ]
}]
