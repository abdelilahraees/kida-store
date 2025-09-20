import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {env} from "../../../env/enviroment";
import {catchError, Observable, switchMap, throwError} from "rxjs";
import {ResponseApi} from "../../../core/interfaces/response-api";
import {PaginationData} from "../../../core/interfaces/paginationData";
import {Product} from "../../../core/interfaces/product";
import {UploadFileService} from "../../../core/services/upload-file.service";

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  baseUrl!: string;

  constructor(private readonly http: HttpClient, private uploadService: UploadFileService) {
    this.baseUrl = env.url;

  }

  public getProducts(categoryId: number, page: number, size: number): Observable<PaginationData<Product>> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('categoryId', categoryId.toString())
      .set('size', size.toString());
    return this.http.get<PaginationData<Product>>(`http://localhost:8000/api/products`, {params})
  }

  private handleError(error: any): Observable<never> {
    console.error('Une erreur est survenue dans le service', error);
    return throwError(() => new Error('Impossible de communiquer avec le serveur.'));
  }


  public deleteProduct(productId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/products/${productId}`)
      .pipe(
        catchError(this.handleError)
      );
  }

  public getProductById(productId: number): Observable<ResponseApi<Product>> {
    return this.http.get<ResponseApi<Product>>(`${this.baseUrl}/products/${productId}`)
      .pipe(
        catchError(this.handleError)
      );
  }


  public addProduct(product: Product, file: File | null): Observable<ResponseApi<Product>> {
    const formData: FormData = new FormData();
    formData.append('name', product?.name as string);
    formData.append('description', product?.description as string);
    formData.append('price', product?.price?.toString() as string);
    formData.append('categoryId', product?.categoryId?.toString() as string);
    formData.append('quantityStock', product?.quantityStock?.toString() as string);
    if (file) {
      formData.append('image', file);
    }
    return this.http.post<ResponseApi<Product>>(`${this.baseUrl}/products`, formData)
      .pipe(
        catchError(this.handleError)
      );

  }

  public updateProduct(productId: number, product: Product, file: File | null): Observable<ResponseApi<Product>> {
    const formdata: FormData = new FormData();
    formdata.append("name", product?.name as string);
    formdata.append("description", product?.description as string);
    formdata.append("price", product?.price?.toString() as string);
    formdata.append("quantityStock", product?.quantityStock?.toString() as string);
    formdata.append("categoryId", product?.categoryId?.toString() as string);
    if (file) {
      formdata.append("image", file);
    }
    return this.http.put<ResponseApi<Product>>(`${this.baseUrl}/products/${productId}`, formdata)
      .pipe(
        catchError(this.handleError)
      );
  }
}
