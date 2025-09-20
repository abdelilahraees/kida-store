import { Component } from '@angular/core';
import {Observable} from "rxjs";
import {NotificationService} from "../../services/notification.service";
import {AsyncPipe, NgIf} from "@angular/common";

@Component({
  selector: 'app-notification',
  standalone: true,
  imports: [
    NgIf,
    AsyncPipe
  ],
  templateUrl: './notification.component.html',
  styleUrl: './notification.component.css'
})
export class NotificationComponent {
// On déclare un Observable qui recevra les messages du service.
  notification$: Observable<string | null>;

  constructor(private notificationService: NotificationService) {
    // On initialise notre observable avec celui du service.
    this.notification$ = this.notificationService.notification$;
  }

  ngOnInit(): void {}

  // Méthode pour fermer manuellement la notification au clic.
  closeNotification(): void {
    this.notificationService.hide();
  }
}
