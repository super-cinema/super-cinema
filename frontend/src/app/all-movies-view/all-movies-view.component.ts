import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {DialogService} from "../share/dialog.service";


@Component({
  selector: 'app-all-movies-view',
  templateUrl: './all-movies-view.component.html',
  styleUrls: ['./all-movies-view.component.scss']
})
export class AllMoviesViewComponent implements OnInit {

  moviesList = []

  constructor(private httpClient: HttpClient,
              private dialogService: DialogService) { }

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

  deleteMovie(id, title) {
    console.log(id)
    let msg: string = "Do you want delete " + title + " movie?";
    this.dialogService.openConfirmDialog(msg)
      .afterClosed()
      .subscribe(resp =>
        {
          if(resp) {
            this.httpClient.delete("http://localhost:8080/movie?idToDelete=" + id)
              .subscribe(data => {
                console.log(":)", data);
                this.ngOnInit();
              }),(error => {
                console.log(":(", error);
            })
          }
        }
      )
  }
}
