import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";


@Component({
  selector: 'app-all-movies-view',
  templateUrl: './all-movies-view.component.html',
  styleUrls: ['./all-movies-view.component.scss']
})
export class AllMoviesViewComponent implements OnInit {

  moviesList = []

  constructor(private httpClient: HttpClient) { }

  ngOnInit() {
    this.httpClient.get('http://localhost:8080/movie')
      .subscribe(
        (data: any) => {
          this.moviesList = data;
        }
      );
    }

  displaySearchedMovie(movie: any, findMovieForm: HTMLFormElement) {
    if(movie.title.toUpperCase().includes(findMovieForm.value.search.toUpperCase())){
      return true;
    }
    return false;
  }
}
