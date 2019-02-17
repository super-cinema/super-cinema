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
import {MovieService} from '../../services/movie-service/movie-service';
import {Movie} from '../model/movie';

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

  public actorsList: Crew[] = [];
  public actorsListVisible = false;
  public directorsList: Crew[] = [];
  public directorsListVisible = false;
  public isPopupOpened = false;
  public movie: Movie = new Movie();

// TODO data from database, service and models to do
  movieTypes = [
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

  ngOnInit() {
    this.crewInMovieService.clearAllList();
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
      const ifActorsListIsEmpty = this.actorsList.length === 0;
      this.actorsListVisible = !ifActorsListIsEmpty;
      this.directorsList = this.crewInMovieService.getAllDirectors();
      const ifDirectorsListIsEmpty = this.directorsList.length === 0;
      this.directorsListVisible = !ifDirectorsListIsEmpty;
    });
  }

  checkMovieType(movieType, event) {
    movieType.checked = !movieType.checked;
  }

  addMovie(addMovieForm: NgForm) {
    const checkedMovieTypes = this.movieTypes.filter(type => type.checked === true).map(type => type.value);
    if (this.isDataSufficient(addMovieForm, checkedMovieTypes)) {
      this.makeMovieObject(addMovieForm, checkedMovieTypes);
      this.movieService.save(this.movie)
        .subscribe(
          (data: any) => {
            this.notification.success('Added ' + addMovieForm.value.title + ' movie successfully ');
            addMovieForm.reset();
          }, (error) => {
            this.notification.warn(error);
          }
        );
      this.clearCrewList();
    }
  }

  private makeMovieObject(addMovieForm: NgForm, checkedMovieTypes) {
    this.movie.title = addMovieForm.value.title;
    this.movie.directors = this.directorsList;
    this.movie.cast = this.actorsList;
    this.movie.types = checkedMovieTypes;
    this.movie.duration = addMovieForm.value.duration;
    this.movie.productionCountry = addMovieForm.value.productionCountry;
    this.movie.productionYear = addMovieForm.value.productionYear;
    this.movie.movieShow = null;
  }

  showOrHideActors() {
    this.actorsListVisible = !this.actorsListVisible;
  }

  showOrHideDirectors() {
    this.directorsListVisible = !this.directorsListVisible;
  }

  private isDataSufficient(form: NgForm, checkedMovieTypes): boolean {
    if (form.value.title === '' || form.value.title == null) {
      this.notification.warn('Please give title.');
      return false;
    }
    if (this.directorsList.length === 0) {
      this.notification.warn('Please add director.');
      return false;
    }
    if (this.actorsList.length === 0) {
      this.notification.warn('Please add actors.');
      return false;
    }
    if (checkedMovieTypes == false) {
      this.notification.warn('Please give movie type.');
      return false;
    }
    if (form.value.productionCountry === '' || form.value.productionCountry == null) {
      this.notification.warn('Please give production country.');
      return false;
    }
    if (form.value.productionYear === '' || form.value.productionYear == null || Number.isNaN(Number(form.value.productionYear))) {
      this.notification.warn('Please give production year.');
      return false;
    }
    if (form.value.duration === '' || form.value.duration == null) {
      this.notification.warn('Please give movie duration.');
      return false;
    }
    if (Number.isNaN(Number(form.value.duration))) {
      this.notification.warn('Duration must be given as a number');
      return false;
    }
    return true;
  }

  deleteDirectorFromDirectorsList(director: Crew) {
    const index = this.directorsList.indexOf(director);
    this.directorsList.splice(index, 1);
  }

  deleteActorFormActorsList(actor: Crew) {
    const index = this.actorsList.indexOf(actor);
    this.actorsList.splice(index, 1);
  }

  clearCrewList() {
    this.crewInMovieService.clearAllList();
    this.actorsList = [];
    this.directorsList = [];
  }

  clearCrewListAndResetForm(addMovieForm: NgForm) {
    addMovieForm.reset();
    this.clearCrewList();
  }
}
