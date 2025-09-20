import {Component, OnInit} from '@angular/core';
import {NavbarComponent} from "../../../shared/components/navbar/navbar.component";
import {FooterComponent} from "../../../shared/components/footer/footer.component";
import {SidebarComponent} from "../../../shared/components/sidebar/sidebar.component";
import {OrderComponent} from "../../../features/orders/order/order.component";
import {Router, RouterOutlet} from "@angular/router";
import {KeycloakService} from "keycloak-angular";
import {routes} from "../profil.route";

@Component({
  selector: 'app-profil',
  standalone: true,
  imports: [
    NavbarComponent,
    FooterComponent,
    SidebarComponent,
    OrderComponent,
    RouterOutlet
  ],
  templateUrl: './profil.component.html',
  styleUrl: './profil.component.css'
})
export class ProfilComponent implements OnInit {
  constructor(private keycloakService: KeycloakService, private router: Router) {
  }
  ngOnInit(): void {
    if (this.keycloakService.isLoggedIn()) {
      if (this.keycloakService.getUserRoles().includes("ADMIN")) {
        this.router.navigate(['/admin'])
      }
    }
  }

}
