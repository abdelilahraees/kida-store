import { Component } from '@angular/core';
import {RouterLink} from "@angular/router";
import {KeycloakService} from "keycloak-angular";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [
    RouterLink,
    NgIf
  ],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent {
  constructor(protected readonly keycloakService:KeycloakService) {
  }

}
