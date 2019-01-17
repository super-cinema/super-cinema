import {Component, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {NotificationService} from '../../share/notification.service';
import {AllCrewViewComponent} from '../../crew/all-crew-view/all-crew-view.component';
import {Crew} from '../../crew/model/crew';
import {CrewService} from '../../services/crew-servic/crew-service';
import {MatDialog} from '@angular/material';
import {CrewInMovieService} from '../../services/crew-in-movie-service/crew-in-movie.service';
import {CrewInMovieComponent} from '../../crew/crew-in-movie/crew-in-movie.component';
import {CrewId} from "../../crew/model/crewId";

@Component({
  selector: 'app-add-movie',
  templateUrl: './add-movie.component.html',
  styleUrls: ['./add-movie.component.scss'],
  providers: [AllCrewViewComponent]
})
export class AddMovieComponent implements OnInit {

  constructor(private httpClient: HttpClient,
              private notification: NotificationService,
              private crewService: CrewService,
              private dialog?: MatDialog,
              private crewInMovieService?: CrewInMovieService) {
  }

  crewList: Crew[] = [];
  crewIdsList: CrewId[] = [];

  isPopupOpened = true;


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

  directors = [
    {name: 'name'},
    {name: 'name'},
    {name: 'name'}
  ];

  ngOnInit() {
  }

  addActor() {
    this.isPopupOpened = true;
    const dialogRef = this.dialog.open(CrewInMovieComponent, {
      width: '700px',
      height: '500px',
      data: {}
    });


    dialogRef.afterClosed().subscribe(result => {
      this.isPopupOpened = false;
      this.crewList = this.crewInMovieService.getAllCrew();
      console.log('afterClose', this.crewList)
    });
  }

  checkMovieType(movieType, event) {
    movieType.checked = !movieType.checked;

  }

  mapCrewListIntoCrewIdsList () {
    this.crewIdsList = this.crewList.map(crew => new CrewId(crew.id));
  }

  addMovie(addMovieForm: NgForm) {
    this.mapCrewListIntoCrewIdsList();
    console.log('addMovie', this.crewIdsList)
    const checkedMovieTypes = this.movieTypes.filter(type => type.checked == true).map(type => type.value);
    if (addMovieForm.value.title == '' || addMovieForm.value.title == null) {
      this.notification.warn('Please give title.');
      return;
    }
    if (addMovieForm.value.duration == '' || addMovieForm.value.duration == null) {
      this.notification.warn('Please give movie duration.');
      return;
    }
    if (Number.isNaN(Number(addMovieForm.value.duration))) {
      this.notification.warn('Duration must be given as a number');
      return;
    }
    this.httpClient.post('http://localhost:8080/movie', {
      'title': addMovieForm.value.title,
      'duration': addMovieForm.value.duration,
      'productionCountry': addMovieForm.value.productionCountry,
      'productionYear': addMovieForm.value.productionYear,
      'types': checkedMovieTypes,
      'directors': null,
      'cast': this.crewIdsList,
      'movieShow': null
    })
      .subscribe(
        (data: any) => {
          this.notification.success('Added ' + addMovieForm.value.title + ' movie successfully ');
          addMovieForm.reset();
        }, (error1) => {
          console.log(error1.error)
          //this.notification.warn('bad request');
          this.notification.warn(error1.error.message);
        }
      );
  }

}
