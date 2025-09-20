import { Component } from '@angular/core';
import {FooterComponent} from "../../../shared/components/footer/footer.component";
import {NavbarComponent} from "../../../shared/components/navbar/navbar.component";
import {OrderComponent} from "../../../features/orders/order/order.component";
import {SidebarComponent} from "../../../shared/components/sidebar/sidebar.component";
import {RouterOutlet} from "@angular/router";

@Component({
  selector: 'app-admin-home',
  standalone: true,
  imports: [
    FooterComponent,
    NavbarComponent,
    OrderComponent,
    SidebarComponent,
    RouterOutlet
  ],
  templateUrl: './admin-home.component.html',
  styleUrl: './admin-home.component.css'
})
export class AdminHomeComponent {

}
