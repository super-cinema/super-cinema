import {Observable} from 'rxjs';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Crew} from '../models/model-crew/crew';

@Injectable({
  providedIn: 'root'
})
export class HttpServiceCrew {

  constructor(private httpClient: HttpClient) {
  }

  readonly URL_DB = 'http://localhost:8080';
  readonly param = new HttpParams().set('/crew', 'id' );

  getCrew(): Observable<Array<Crew>> {
    return this.httpClient.get<Array<Crew>>(this.URL_DB, {params: this.param});
  }

  saveCrew(list: Array<Crew>) {
    this.httpClient.put(this.URL_DB, list, {params: this.param})
      .subscribe(data => {
        console.log(data);
      });
  }
}
