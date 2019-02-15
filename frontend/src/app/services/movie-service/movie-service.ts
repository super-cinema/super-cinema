import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Movie} from '../../movie/model/movie';


@Injectable()
export class MovieService {

  readonly baseUrl = 'http://51.68.137.192:8080/movie';

  constructor(private httpClient: HttpClient) {
  }

  getMovie(id: number): Observable<Object> {
    return this.httpClient.get(this.baseUrl + '?movieId=' + id);
  }

  save(movie: Movie): Observable<Object> {
    return this.httpClient.post(this.baseUrl, movie);
  }

  updateMovie(id: number, movie: Movie): Observable<Object> {
    return this.httpClient.put(this.baseUrl + '?id=' + id, movie);
  }

  deleteMovie(id: number): Observable<any> {
    return this.httpClient.delete(this.baseUrl + '?idToDelete=' + id);
  }

  getMovieList(): Observable<Array<Movie>> {
    return this.httpClient.get<Array<Movie>>(this.baseUrl);
  }

  deleteAll(): Observable<any> {
    return this.httpClient.delete(`${this.baseUrl}` + `/delete`, {responseType: 'text'});
  }
}
