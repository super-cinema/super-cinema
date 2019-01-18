import { Component, OnInit } from '@angular/core';
import { ActivatedRoute} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {NotificationService} from '../../share/notification.service';
import {Movie} from '../model/movie';
import {NgForm} from "@angular/forms";
import {MovieService} from "../../services/service-movie/movie-service";

@Component({
  selector: 'app-edit-movie',
  templateUrl: './edit-movie.component.html',
  styleUrls: ['./edit-movie.component.scss']
})
export class EditMovieComponent implements OnInit {

  //TODO data form database
  movieTypesArray = [
    {value: 'COMEDY', name: 'comedy', checked: false},
    {value: 'HORROR', name: 'horror', checked: false},
    {value: 'SF', name: 'science - fiction', checked: false},
    {value: 'ACTION', name: 'action', checked: false},
    {value: 'THRILLER', name: 'thriller', checked: false},
    {value: 'DRAMA', name: 'drama', checked: false},
    {value: 'CRIME', name: 'crime', checked: false},
    {value: 'FANTASY', name: 'fantasy', checked: false},
    {value: 'MUSICAL', name: 'musical', checked: false},
    {value: 'ANIMATION', name: 'animation', checked: false},
    {value: 'WESTERNS', name: 'western', checked: false}
  ];

  private movie: Movie;

  constructor(private httpClient: HttpClient,
              private route: ActivatedRoute,
              private movieService: MovieService,
              private notification: NotificationService) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.movie = new Movie;
      this.movie.id = params['id'];
    });
    this.getMovieData();
  }

  wasTypeSelected(movieType: { value: string; name: string; checked: boolean }) {

    if (this.movie.types.includes(movieType.value)) {
      movieType.checked = true;
      return true;
    }
    movieType.checked = false;
    return false;
  }

  checkMovieType(i, event) {
    this.movieTypesArray[i].checked = !this.movieTypesArray[i].checked;
    if (this.movieTypesArray[i].checked) {
      this.movie.types.push(this.movieTypesArray[i].value);
    } else {
      const indexOf = this.movie.types.indexOf(this.movieTypesArray[i].value);
      this.movie.types.splice(indexOf, 1);
    }
  }

  saveChanges(editMovieForm: NgForm) {
    const checkedMovieTypes = this.movieTypesArray.filter(type => type.checked === true).map(type => type.value);
    this.formDataValidation(editMovieForm);
    this.movieService.updateMovie(this.movie.id, this.movie)
      .subscribe(
        (data: any) => {
          this.notification.success('Edited ' + this.movie.title + ' movie succesfully' );
        },
        (error) => {
          console.log(error);
          this.notification.warn(error);
        }
        );
  }

  private getMovieData() {
    this.movieService.getMovie(this.movie.id)
      .subscribe(
        (data: any) => {
          console.log(data);
          this.movie.title = data.title;
          this.movie.duration = data.duration;
          this.movie.productionCountry = data.productionCountry;
          this.movie.productionYear = data.productionYear;
          this.movie.cast = data.cast;
          this.movie.directors = data.cast;
          this.movie.movieShow = data.movieShow;
          this.movie.types = data.types;
        });
  }

  private formDataValidation(form: NgForm){
    if (this.movie.title === '' || this.movie.title == null) {
      this.notification.warn('Please give title.');
      return;
    }
    if (this.movie.duration === '' || this.movie.duration == null ) {
      this.notification.warn('Please give movie duration.');
      return;
    }
    if (Number.isNaN(Number(this.movie.duration))) {
      this.notification.warn('Duration must be given as a number');
      return;
    }
  }
}



