import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {env} from "../../env/enviroment";

@Injectable({
  providedIn: 'root'
})
export class UploadFileService {
  url: string = env.url;

  constructor(private readonly http: HttpClient) {
  }


  upload(file: File): Observable<string> {
    console.log(file)
    const formData: FormData = new FormData();
    formData.append('file', file);
    return this.http.post(`${this.url}/upload/file`, formData, {responseType: 'text'});
  }
}
