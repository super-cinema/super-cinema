import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {NgForm} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {NotificationService} from '../../share/notification.service';
import {AllCrewViewComponent} from '../../crew/all-crew-view/all-crew-view.component';
import {Observable} from "rxjs";
import {Crew} from "../../crew/models/model-crew/crew";
import {CrewService} from "../../crew/servic-crew/crew-service";

@Component({
  selector: 'app-add-movie',
  templateUrl: './add-movie.component.html',
  styleUrls: ['./add-movie.component.scss'],
  providers: [AllCrewViewComponent]
})
export class AddMovieComponent implements OnInit {

  isHidden = false;
  crewList: Observable<Array<Crew>>;
  actorsFromCrew = [Crew];

  hidden() {
    this.isHidden = !this.isHidden;
  }

  selected(crew) {
    this.actorsFromCrew.push(crew);
    console.log(this.actorsFromCrew);
  }


  ngOnInit() {
    this.crewService.getCrewList().subscribe((data: any) => {
      this.crewList = data;
    });
  }

  constructor(private httpClient: HttpClient,
              private notification: NotificationService,
              private crewService: CrewService) {
  }

  movieTypes = [
    {value: "COMEDY", name: "comedy", "checked": false},
    {value: "HORROR", name: 'horror', "checked": false},
    {value: "SF", name: "science - fiction", "checked": false},
    {value: "ACTION", name: "action", "checked": false},
    {value: "THRILLER", name: "thriller", "checked": false},
    {value: "DRAMA", name: "drama", "checked": false},
    {value: "CRIME", name: "crime", "checked": false},
    {value: "FANTASY", name: "fantasy", "checked": false},
    {value: "MUSICAL", name: "musical", "checked": false},
    {value: "ANIMATION", name: "animation", "checked": false},
    {value: "WESTERNS", name: "western", "checked": false}
  ];

  directors = [
    {name: "name"},
    {name: "name"},
    {name: "name"}
  ];
  crew = [];

  checkMovieType(movieType, event) {
    movieType.checked = !movieType.checked;

  }

  addMovie(addMovieForm: NgForm) {
    let checkedMovieTypes = this.movieTypes.filter(type => type.checked == true).map(type => type.value)
    if (addMovieForm.value.title == '' || addMovieForm.value.title == null) {
      this.notification.warn("Please give title.")
      return;
    }
    if (addMovieForm.value.duration == '' || addMovieForm.value.duration == null) {
      this.notification.warn("Please give movie duration.");
      return;
    }
    if (Number.isNaN(Number(addMovieForm.value.duration))) {
      this.notification.warn("Duration must be given as a number")
      return;
    }
    this.httpClient.post("http://localhost:8080/movie", {
      "title": addMovieForm.value.title,
      "duration": addMovieForm.value.duration,
      "productionCountry": addMovieForm.value.productionCountry,
      "productionYear": addMovieForm.value.productionYear,
      "types": checkedMovieTypes,
      "directors": null,
      "cast": null,
      "movieShow": null
    })
      .subscribe(
        (data: any) => {
          this.notification.success("Added " + addMovieForm.value.title + " movie successfully ")
          addMovieForm.reset();
        }, (error1) => {
          this.notification.warn("bad request");
          this.notification.warn(error1.error.message);
        }
      );
  }

}
