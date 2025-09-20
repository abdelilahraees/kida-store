import {Component, OnInit} from '@angular/core';
import {
  BehaviorSubject,
  catchError,
  combineLatest, map, Observable, of, scan, startWith,
  switchMap,
} from "rxjs";
import {ProductService} from "../services/product.service";
import {Product} from "../../../core/interfaces/product";
import {PaginationData} from "../../../core/interfaces/paginationData";
import {AsyncPipe, CurrencyPipe, NgClass, NgForOf, NgIf} from "@angular/common";
import {CartService} from "../../cart/services/cart.service";
import {NotificationService} from "../../notification/services/notification.service";
import {ResponseState} from "../../../core/interfaces/ResponseState";
import {CategoriesService} from "../../categories/categories.service";
import {Category} from "../../../core/interfaces/category";
import {ResponseApi} from "../../../core/interfaces/response-api";
import {KeycloakService} from "keycloak-angular";

@Component({
  selector: 'app-all-product',
  standalone: true,
  imports: [
    NgIf,
    NgClass,
    AsyncPipe,
    NgForOf,
    CurrencyPipe
  ],
  templateUrl: './all-product.component.html',
  styleUrl: './all-product.component.css'
})
export class AllProductComponent implements OnInit {
  isModalOpen = false;
  selectedProduct: Product | null = null;
  public selectedCategoryId: number = 0;
  categories!: Category[]
  page$ = new BehaviorSubject<number>(1);
  size$ = new BehaviorSubject<number>(8);
  state$!: Observable<ResponseState<PaginationData<Product>>>;
  initialState: ResponseState<PaginationData<Product>> = {
    loading: true, // On commence en état de chargement initial
  };


  constructor(private readonly productService: ProductService, private readonly cartService: CartService, private readonly notificationService: NotificationService, private readonly categoryService: CategoriesService, private keycloakService: KeycloakService) {
  }

  fetch(categoryId: number): any {
    return combineLatest([this.page$, this.size$]).pipe(
      switchMap(([page, size]) =>
        this.productService.getProducts(categoryId, page, size).pipe(
          map(response => ({data: response, loading: false})),
          catchError(err => of({error: 'Failed to load products.', loading: false})),
          startWith({loading: true})
        )),
      scan((p, v) => ({
        ...p,
        ...v,
      }), this.initialState)
    );
  }

  ngOnInit() {
    this.categoryService.getCategories().subscribe((res) => {
      this.categories = res.data || []
    })
    this.state$ = this.fetch(0)


  }


  nextPage(): void {
    this.page$.next(this.page$.getValue() + 1)
  }

  previousPage(): void {
    console.log(this.page$.getValue())
    this.page$.next(this.page$.getValue() - 1)
  }


  addToCart(productId: number) {

    if (!this.keycloakService.isLoggedIn()) {
      this.keycloakService.login()
    }

    this.notificationService.show('Produit ajouté au panier avec succès !');
    this.cartService.addToCart(productId).subscribe();
    console.log(`Product with ID ${productId} added to cart.`);
  }


  getProductsByCategory(categoryId: number) {
    this.page$.next(1)
    this.selectedCategoryId = categoryId;
    this.state$ = this.fetch(categoryId)
  }







  // Méthode pour ouvrir la modale
  openModal(product: Product): void {
    this.selectedProduct = product;
    this.isModalOpen = true;
  }

  // Méthode pour fermer la modale
  closeModal(): void {
    this.isModalOpen = false;
    this.selectedProduct = null;
  }
}
