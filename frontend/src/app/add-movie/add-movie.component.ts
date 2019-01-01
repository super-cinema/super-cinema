import {Component, OnInit} from '@angular/core';
import {NgForm} from "@angular/forms";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-add-movie',
  templateUrl: './add-movie.component.html',
  styleUrls: ['./add-movie.component.scss']
})
export class AddMovieComponent implements OnInit {

  constructor(private httpClient: HttpClient) {
  }

  movieTypes = [
    {value: "COMEDY", name: "comedy", "checked": false},
    {value: "HORROR", name: "horror", "checked": false},
    {value: "SF", name: "science - fiction", "checked": false},
    {value: "ACTION", name: "action", "checked": false},
    {value: "THRILLER", name: "thriller", "checked": false},
    {value: "DRAMA", name: "drama", "checked": false},
    {value: "CRIME", name: "crime", "checked": false},
    {value: "FANTASY", name: 'fantasy', "checked": false},
    {value: "MUSICAL", name: "musical", "checked": false},
    {value: "ANIMATION", name: "animation", "checked": false},
    {value: "WESTERNS", name: "western", "checked": false}
  ]


  ngOnInit() {
  }

  checkMovieType(movieType, event) {
    movieType.checked = !movieType.checked;
  }

  addMovie(addMovieForm: NgForm) {
    let checkedMovieTypes = this.movieTypes.filter(type => type.checked === true).map(type => type.value)
    console.log(checkedMovieTypes);
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
          console.log(':)');
          addMovieForm.reset();
        }, (error1) => {
          console.log(':(');
        }
      );
  }

}
