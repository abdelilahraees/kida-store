import { Component } from '@angular/core';
import {RouterLink, RouterOutlet} from '@angular/router';
import { Tooltip, initTWE } from 'tw-elements';
import {FooterComponent} from "./shared/components/footer/footer.component";
import {NavbarComponent} from "./shared/components/navbar/navbar.component";

import {AllProductComponent} from "./features/products/all-product/all-product.component";
import {NotificationComponent} from "./features/notification/components/notification/notification.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    NavbarComponent,
    FooterComponent,
    RouterOutlet,
    AllProductComponent,
    NotificationComponent
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'store-kida';
  ngOnInit() {
    initTWE({ Tooltip });
  }

}
