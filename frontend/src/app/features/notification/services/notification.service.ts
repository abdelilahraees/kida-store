import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor() { }

  // BehaviorSubject garde en mémoire la dernière valeur pour les nouveaux abonnés.
  // On initialise avec `null` pour dire qu'aucune notification n'est visible au début.
  private messageSource = new BehaviorSubject<string | null>(null);

  // On expose un Observable public que les composants pourront écouter.
  public notification$: Observable<string | null> = this.messageSource.asObservable();


  show(message: string, duration: number = 3000): void {
    this.messageSource.next(message);

    // Masque automatiquement la notification après la durée spécifiée.
    setTimeout(() => {
      this.hide();
    }, duration);
  }

  /**
   * Masque la notification.
   */
  hide(): void {
    this.messageSource.next(null);
  }
}
