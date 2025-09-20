import {Routes} from '@angular/router';
import {AuthGuard} from "./core/guards/auth.guard";
import {ProductListComponent} from "./features/products/product-list/product-list.component";
import {HomeComponent} from "./layouts/public-layout/home/home.component";

export const routes: Routes = [
  {
    path: '', component: HomeComponent


  },

  {
    path: 'products',
    loadComponent: () => import("./features/products/all-product/all-product.component").then(m => m.AllProductComponent)
  },
  {
    path: "profile", loadChildren: () => import("./layouts/public-layout/profil.route").then(m => m.routes),
    canActivate:[AuthGuard]
  },
  {
    path: 'admin',
    loadChildren: () => import("./layouts/admin-layout/admin.routes").then(m => m.routes),
    canActivate: [AuthGuard],
    data: {
      roles: ['ADMIN']
    }
  }

];
