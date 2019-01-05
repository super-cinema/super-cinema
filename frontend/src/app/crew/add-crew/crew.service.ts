import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({providedIn: 'root'})
export class CrewService {
  public API = '//localhost:8080';
  public CREW_API = this.API + '/crew';

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<any> {
    return this.http.get(this.CREW_API);
  }

  get(id: string) {
    return this.http.get(this.CREW_API + '/' + id);
  }

  save(crew: any): Observable<any> {
    let result: Observable<Object>;
    if (crew['href']) {
      result = this.http.put(crew.href, crew);
    } else {
      result = this.http.post(this.CREW_API, crew);
    }
    return result;
  }

  remove(href: string) {
    return this.http.delete(href);
  }
}
