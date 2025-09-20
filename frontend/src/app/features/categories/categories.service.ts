import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ResponseApi} from "../../core/interfaces/response-api";
import {Category} from "../../core/interfaces/category";
import {env} from "../../env/enviroment";

@Injectable({
  providedIn: 'root'
})
export class CategoriesService {
  private readonly url: string;

  constructor(private readonly http: HttpClient) {
    this.url = env.url;
  }

  getCategories(): Observable<ResponseApi<Category[]>> {
    return this.http.get<ResponseApi<Category[]>>(`${this.url}/categories`)
  }


}
