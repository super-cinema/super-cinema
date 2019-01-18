import {Component, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {NotificationService} from '../../share/notification.service';
import {AllCrewViewComponent} from '../../crew/all-crew-view/all-crew-view.component';
import {Crew} from '../../crew/model/crew';
import {CrewService} from '../../services/crew-service/crew-service';
import {MatDialog} from '@angular/material';
import {CrewInMovieService} from '../../services/crew-in-movie-service/crew-in-movie.service';
import {CrewInMovieComponent} from '../../crew/crew-in-movie/crew-in-movie.component';
import {CrewId} from "../../crew/model/crewId";
import {Movie} from "../model/movie";
import {MovieService} from "../../services/service-movie/movie-service";

@Component({
  selector: 'app-add-movie',
  templateUrl: './add-movie.component.html',
  styleUrls: ['./add-movie.component.scss'],
  providers: [AllCrewViewComponent]
})
export class AddMovieComponent implements OnInit {


  constructor(private httpClient: HttpClient,
              private movieService: MovieService,
              private notification: NotificationService,
              private crewService: CrewService,
              private dialog?: MatDialog,
              private crewInMovieService?: CrewInMovieService) {
  }

  actorsList: Crew[] = [];
  actorsIdsList: CrewId[] = [];
  actorsListVisible: boolean = false;
  directorsList: Crew[] = [];
  directorsIdsList: CrewId[] = [];
  directorsListVisible: boolean = false;
  isPopupOpened = true;
  movie: Movie = new Movie();

//TODO data from database, service and model to do
  movieTypes = [
    {value: 'COMEDY', name: 'comedy', 'checked': false},
    {value: 'HORROR', name: 'horror', 'checked': false},
    {value: 'SF', name: 'science - fiction', 'checked': false},
    {value: 'ACTION', name: 'action', 'checked': false},
    {value: 'THRILLER', name: 'thriller', 'checked': false},
    {value: 'DRAMA', name: 'drama', 'checked': false},
    {value: 'CRIME', name: 'crime', 'checked': false},
    {value: 'FANTASY', name: 'fantasy', 'checked': false},
    {value: 'MUSICAL', name: 'musical', 'checked': false},
    {value: 'ANIMATION', name: 'animation', 'checked': false},
    {value: 'WESTERNS', name: 'western', 'checked': false}
  ];

  ngOnInit() {
  }

  addCrewList(crewRole: string) {
    this.isPopupOpened = true;
    const dialogRef = this.dialog.open(CrewInMovieComponent, {
      width: '700px',
      height: '500px',
      data: {crewRole}
    });
    dialogRef.afterClosed().subscribe(result => {
      this.isPopupOpened = false;
      this.actorsList = this.crewInMovieService.getAllActors();
      this.directorsList = this.crewInMovieService.getAllDirectors();
      console.log('add movie component', this.actorsList, this.directorsList);
      let ifActorsListIsEmpty = this.actorsList.length === 0;
      this.actorsListVisible = !ifActorsListIsEmpty;
      let ifDirectosListIsEmpty = this.directorsList.length === 0;
      this.directorsListVisible = !ifDirectosListIsEmpty;
    });
  }

  checkMovieType(movieType, event) {
    movieType.checked = !movieType.checked;

  }

  mapCrewListIntoCrewIdsList () {
    this.actorsIdsList = this.actorsList.map(crew => new CrewId(crew.id));
  }

  addMovie(addMovieForm: NgForm) {
    this.mapCrewListIntoCrewIdsList();
    const checkedMovieTypes = this.movieTypes.filter(type => type.checked == true).map(type => type.value);
    this.formDataValidation(addMovieForm);
    this.makeMovieObject(addMovieForm, checkedMovieTypes);
    this.movieService.save(this.movie)
      .subscribe(
        (data: any) => {
          this.notification.success('Added ' + addMovieForm.value.title + ' movie successfully ');
          addMovieForm.reset();
        }, (error1) => {
          this.notification.warn(error1);
        }
      );
  }

  private makeMovieObject(addMovieForm: NgForm, checkedMovieTypes) {
    this.movie.title = addMovieForm.value.title;
    this.movie.duration = addMovieForm.value.duration;
    this.movie.productionCountry = addMovieForm.value.productionCountry;
    this.movie.productionYear = addMovieForm.value.productionYear;
    this.movie.directors = null;
    this.movie.types = checkedMovieTypes;
    this.movie.cast = this.actorsIdsList;
    this.movie.movieShow = null;
  }

  showOrHideActors() {
    this.actorsListVisible = !this.actorsListVisible;
  }

  showOrHideDirectors(){
    this.directorsListVisible = !this.directorsListVisible;
  }

  private formDataValidation(form: NgForm){
    if (form.value.title == '' || form.value.title == null) {
      this.notification.warn('Please give title.');
      return;
    }
    if (form.value.duration == '' || form.value.duration == null) {
      this.notification.warn('Please give movie duration.');
      return;
    }
    if (Number.isNaN(Number(form.value.duration))) {
      this.notification.warn('Duration must be given as a number');
      return;
    }
  }
}
