import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {DialogService} from '../../share/dialog.service';
import {NotificationService} from '../../share/notification.service';
import {MovieService} from '../../services/movie-service/movie-service';


@Component({
  selector: 'app-all-movies-view',
  templateUrl: './all-movies-view.component.html',
  styleUrls: ['./all-movies-view.component.scss']
})
export class AllMoviesViewComponent implements OnInit {

  moviesList = [];

  constructor(private httpClient: HttpClient,
              private movieService: MovieService,
              private dialogService: DialogService,
              private notificationService: NotificationService) {
  }

  ngOnInit() {
    this.movieService.getMovieList()
      .subscribe(
        (data: any) => {
          this.moviesList = data;
        }
      );

  }

  displaySearchedMovie(movie: any, findMovieForm: HTMLFormElement) {
    if (movie.title == null) {
      movie.title = '';
    }
    if (movie.title.toUpperCase().includes(findMovieForm.value.search.toUpperCase())) {
      return true;
    }
    return false;
  }

  deleteMovie(id, title) {
    console.log(id);
    const msg: string = 'Do you want delete ' + title + ' movie?';
    this.dialogService.openConfirmDialog(msg)
      .afterClosed()
      .subscribe(resp => {
          if (resp) {
            this.movieService.deleteMovie(id)
              .subscribe(data => {
                this.ngOnInit();
                this.notificationService.success('Deleted ' + title + ' movie successfully');
              }), (error => {
              this.notificationService.warn('Deleting ' + title + ' movie failed');
              console.log(error.message);
            });


          }
        }
      );
  }


}
