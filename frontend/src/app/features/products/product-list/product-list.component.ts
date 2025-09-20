import {AfterViewInit, ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {CategoriesComponent} from "../../../shared/components/categories/categories.component";
import {ActivatedRoute, Route, RouterLink} from "@angular/router";
import {AllProductComponent} from "../all-product/all-product.component";
import {Category} from "../../../core/interfaces/category";
import {BehaviorSubject, catchError, combineLatest, map, Observable, of, scan, startWith, switchMap} from "rxjs";
import {ResponseState} from "../../../core/interfaces/ResponseState";
import {PaginationData} from "../../../core/interfaces/paginationData";
import {Product} from "../../../core/interfaces/product";
import {ProductService} from "../services/product.service";
import {CartService} from "../../cart/services/cart.service";
import {NotificationService} from "../../notification/services/notification.service";
import {CategoriesService} from "../../categories/categories.service";
import {AsyncPipe, NgClass, NgForOf, NgIf} from "@angular/common";

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [

    RouterLink,

    NgIf,
    AsyncPipe,
    NgForOf,
    NgClass
  ],
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.css'
})
export class ProductListComponent implements OnInit {
  public selectedCategoryId: number = 0;
  public isModalOpen = false;
  public productNameToDelete: string | undefined;
  categories!: Category[]
  page$ = new BehaviorSubject<number>(1);
  size$ = new BehaviorSubject<number>(4);
  state$!: Observable<ResponseState<PaginationData<Product>>>;
  initialState: ResponseState<PaginationData<Product>> = {
    loading: true,
  };


  constructor(private readonly productService: ProductService, private readonly categoryService: CategoriesService, private cd: ChangeDetectorRef, private route: ActivatedRoute) {
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


    this.categoryService.getCategories()
      .subscribe((res) => {
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

  getProductsByCategory(categoryId: string | undefined) {

    if (categoryId) {
      this.selectedCategoryId = parseInt(categoryId);
      this.page$.next(1)
      this.state$ = this.fetch(this.selectedCategoryId)
    }
  }

  deleteProduct(id: number) {
    this.productService.deleteProduct(id).subscribe({
      next: () => {
        this.state$ = this.fetch(this.selectedCategoryId)
        this.closeModal()
      },
      error: (err) => {
        this.closeModal()
      }
    })
  }


  openModal(name: string | undefined): void {
    this.productNameToDelete = name
    this.isModalOpen = true;

  }

  closeModal(): void {
    this.isModalOpen = false;
  }


}
