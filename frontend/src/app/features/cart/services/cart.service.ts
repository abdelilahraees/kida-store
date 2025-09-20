import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {env} from "../../../env/enviroment";
import {BehaviorSubject, catchError, EMPTY, Observable, of, switchMap, tap} from "rxjs";
import {Cart} from "../../../core/interfaces/cart";
import {ResponseApi} from "../../../core/interfaces/response-api";
import {KeycloakService} from "keycloak-angular";

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private readonly baseUrl: string;

  private readonly cartSource = new BehaviorSubject<Cart | null>(null);

  cart$: Observable<Cart | null> = this.cartSource.asObservable();

   cartItemCount$ = new BehaviorSubject<number>(0);

  constructor(private readonly http: HttpClient,private keycloakService:KeycloakService) {
    this.cartSource.getValue()
    this.baseUrl = env.url;
    this.refreshCartCount().subscribe()

  }

  fetchCart(): Observable<ResponseApi<Cart>> {
    return this.http.get<ResponseApi<Cart>>(`${this.baseUrl}/profile/cart`).pipe(
      tap(response => {
        // La mise à jour de l'état se fait ici !
        this.cartSource.next(response.data ?? null);
      }),
      catchError(err => {
        console.error('Failed to fetch cart', err);
        // En cas d'erreur, on peut émettre un état vide ou gérer l'erreur
        this.cartSource.next(null);
        return EMPTY; // Empêche l'observable de se "casser"
      })
    );
  }

  addToCart(productId: number):Observable<void> {
    const params = new HttpParams().set('productId', productId.toString());

    return this.http.post<void>(`${this.baseUrl}/profile/cart/cart-items`, null, {params}).pipe(
      tap(()=>{
        this.refreshCartCount().subscribe()
      })
    );
  }

  updateCart(productId: number, quantity: number): Observable<ResponseApi<Cart>> {
    const params = new HttpParams().set('quantity', quantity.toString());
    return this.http.put(`${this.baseUrl}/profile/cart/cart-items/${productId}`, null, {params}).pipe(
      switchMap(() => this.fetchCart())
    );
  }

  deleteItem(productItem: number): Observable<ResponseApi<Cart>> {
    return this.http.delete(`${this.baseUrl}/profile/cart/cart-items/${productItem}`).pipe(
      tap(()=>{
        this.refreshCartCount().subscribe()
      }),
      switchMap(() => this.fetchCart())
    );
  }






  getItemsCount(): Observable<number> {
    return this.cartItemCount$.asObservable();
  }

  refreshCartCount(): Observable<number> {
    if(!this.keycloakService.isLoggedIn()){
      this.cartItemCount$.next(0);
      return of(0);
    }
    return this.http.get<number>(`${this.baseUrl}/profile/cart/items-count`).pipe(
      tap(count => {
        this.cartItemCount$.next(count);
      })
    );
  }

}
