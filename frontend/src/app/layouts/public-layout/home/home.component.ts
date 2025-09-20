import { Component } from '@angular/core';
import {NavbarComponent} from "../../../shared/components/navbar/navbar.component";
import {AllProductComponent} from "../../../features/products/all-product/all-product.component";
import {FooterComponent} from "../../../shared/components/footer/footer.component";
import {CategoriesComponent} from "../../../shared/components/categories/categories.component";
import {CartService} from "../../../features/cart/services/cart.service";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    NavbarComponent,
    AllProductComponent,
    FooterComponent,
    CategoriesComponent
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  constructor(private cartService: CartService) {
  }

}
