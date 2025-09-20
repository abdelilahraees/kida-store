import {Component, HostListener, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {Router, RouterLink} from "@angular/router";
import {BehaviorSubject, count, Observable, Subscriber, Subscription} from "rxjs";
import {CartService} from "../../../features/cart/services/cart.service";
import {AsyncPipe, NgIf} from "@angular/common";
import {KeycloakService} from "keycloak-angular";


@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    RouterLink,
    AsyncPipe,
    NgIf

  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

  private readonly redirectUrl!:string
  countItem$: Observable<number>;

  constructor(private cartService: CartService, protected keycloakService: KeycloakService, private route: Router) {
    this.countItem$ = this.cartService.getItemsCount();
  }


  logout() {
    this.keycloakService.logout(window.location.origin);
    console.log("L'utilisateur est déconnecté !");
  }



}
