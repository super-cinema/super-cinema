import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Crew} from '../../crew/model/crew';

@Injectable({
  providedIn: 'root'
})
export class CrewService {

  readonly baseUrl = 'http://51.68.137.192:8080/crew';


  constructor(private http: HttpClient) {
  }

  getCrew(id: number): Observable<Object> {
    return this.http.get(this.baseUrl + '?id=' + id);
  }

  createCrew(crew: Crew): Observable<Object> {
    return this.http.post(this.baseUrl, crew);
  }

  updateCrew(id: number, value: any): Observable<Object> {
    return this.http.put(this.baseUrl + '?idToEdit=' + id, value);
  }

  deleteCrew(id: number): Observable<any> {
    return this.http.delete(this.baseUrl + '?idToDel=' + id);
  }

  getCrewList(): Observable<Array<Crew>> {
    return this.http.get<Array<Crew>>(this.baseUrl);
  }

  deleteAll(): Observable<any> {
    return this.http.delete(`${this.baseUrl}` + `/delete`, {responseType: 'text'});
  }


}
