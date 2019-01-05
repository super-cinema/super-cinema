import {Observable} from 'rxjs';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Crew} from '../model-crew/crew';

@Injectable({
  providedIn: 'root'
})
export class HttpServiceDb {

  constructor(private httpDB: HttpClient) {
  }

  readonly URL_DB = 'http://localhost:8080';
  readonly param = new HttpParams().set('/crew', 'sdsd' );

  getTask(): Observable<Array<Crew>> {
    return this.httpDB.get<Array<Crew>>(this.URL_DB, {params: this.param});

  }

  saveCrev(list: Array<Crew>) {
    this.httpDB.put(this.URL_DB, list, {params: this.param})
      .subscribe(data => {
        console.log(data);
      });
  }
}
