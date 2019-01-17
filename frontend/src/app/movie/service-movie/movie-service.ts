import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Movie} from '../model/movie';


@Injectable()
export class MovieService {

  readonly baseUrl = 'http://localhost:8080/movie';

  constructor(private httpClient: HttpClient) {
  }

  getMovie(id: number): Observable<Object> {
    return this.httpClient.get(this.baseUrl + '?id=' + id);
  }

  createMovie(movie: Movie): Observable<Object> {
    return this.httpClient.post(this.baseUrl, movie);
  }

  updateMovie(id: number, value: any): Observable<Object> {
    return this.httpClient.put(this.baseUrl + '?id=' + id, value);
  }

  deleteMovie(id: number): Observable<any> {
    return this.httpClient.delete(this.baseUrl + '?id=' + id);
  }

  getMovieList(): Observable<Array<Movie>> {
    return this.httpClient.get<Array<Movie>>(this.baseUrl);
  }

  deleteAll(): Observable<any> {
    return this.httpClient.delete(`${this.baseUrl}` + `/delete`, {responseType: 'text'});
  }
}
