import {Component, OnInit} from '@angular/core';
import {Form, FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {JsonPipe, NgForOf, NgIf} from "@angular/common";
import {Category} from "../../../core/interfaces/category";
import {CategoriesService} from "../../categories/categories.service";
import {Product} from "../../../core/interfaces/product";
import {ProductService} from "../services/product.service";
import {NotificationService} from "../../notification/services/notification.service";
import {ActivatedRoute, Router} from "@angular/router";
import {KeycloakService} from "keycloak-angular";

interface ProductForm {
  name: FormControl<string>,
  description: FormControl<string>,
  price: FormControl<number>
  quantityStock: FormControl<number>
  category: FormControl<number>
  image: FormControl<string>
}

@Component({
  selector: 'app-add-product',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    NgIf,
    NgForOf
  ],
  templateUrl: './add-product.component.html',
  styleUrl: './add-product.component.css'
})
export class AddProductComponent implements OnInit {
  isCategoryChange: boolean = false
  isLoading: boolean = false
  selectedFile: File | null = null;
  public isUpdateMode: boolean = false
  public categoryId: number | undefined;
  public currentProductId: number | null = null;
  productForm: FormGroup<ProductForm>
  categories!: Category[]
  product: Product = {} as Product

  constructor(private readonly categoryService: CategoriesService, private readonly productService: ProductService, private readonly notificationService: NotificationService, private readonly router: Router, private readonly route: ActivatedRoute, private fb: FormBuilder, private keycloakService: KeycloakService) {
    this.productForm = this.fb.group({
      name: this.fb.nonNullable.control("", [Validators.minLength(3), Validators.required]),
      description: this.fb.nonNullable.control('', [ Validators.minLength(10)]),
      price: this.fb.nonNullable.control(0, [Validators.min(1), Validators.required]),
      quantityStock: this.fb.nonNullable.control(0, [Validators.required, Validators.min(1)]),
      category: this.fb.nonNullable.control(0, Validators.required),
      image: this.fb.nonNullable.control("", [Validators.required])
    })


  }

  ngOnInit()
    :
    void {
    this.categoryService.getCategories().subscribe((res) => {
      this.categories = res.data || []
    })

    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id) {
        this.productForm.controls.image.setValidators([]);
        this.currentProductId = +id;
        this.isUpdateMode = true
        this.productService.getProductById(this.currentProductId).subscribe(product => {
          this.categoryId = product?.data?.category?.id
          this.productForm.patchValue({
            name: product.data?.name,
            description: product.data?.description,
            price: product.data?.price,
            quantityStock: product.data?.quantityStock,
            category: product?.data?.category?.id,
          })
        });
      }
    });
  }


  onFileSelected(event: any): void {
    if (event.target.files && event.target.files.length > 0) {
      this.selectedFile = event.target.files[0];
      this.product.image = this.selectedFile
    }
  }


  categoryChange() {
    this.isCategoryChange = true
  }

  addProduct() {

    if (!this.keycloakService.isLoggedIn()) {
      this.keycloakService.login()
    }
    this.isLoading = true
    this.product.name = this.productForm.value.name
    this.product.description = this.productForm.value.description
    this.product.price = this.productForm.value.price
    this.product.quantityStock = this.productForm.value.quantityStock
    this.product.categoryId = this.categoryId
    if (this.isCategoryChange) {
      this.product.categoryId = this.productForm.value.category
    }


    if (this.isUpdateMode) {
      this.productService.updateProduct(this.currentProductId as number, this.product, this.selectedFile as File).subscribe({
        next: (res) => {
          this.isLoading = false
          this.notificationService.show("Product Updated successfully")
          this.router.navigate(['/admin/products'])

        },
        error: (err) => {
          console.log(err)
        }
      })
    } else {
      this.productService.addProduct(this.product, this.selectedFile as File).subscribe({
        next: (res) => {
          this.isLoading = false
          this.notificationService.show("Product added successfully")
          this.router.navigate(['/admin/products'])
        },
        error: (err) => {
          console.log(err)
        }
      })
    }
  }


  get f() {
    return this.productForm.controls
  }

  get name() {
    return this.productForm.get('name');
  }

  get description() {
    return this.productForm.get('description');
  }

  get price() {
    return this.productForm.get('price');
  }

  get quantityStock() {
    return this.productForm.get('quantityStock');
  }

  get category() {
    return this.productForm.get('category');
  }

  get image() {
    return this.productForm.get('image');
  }

}
