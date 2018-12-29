import { Component, OnInit } from '@angular/core';
import { ActivatedRoute} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-edit-movie',
  templateUrl: './edit-movie.component.html',
  styleUrls: ['./edit-movie.component.scss']
})
export class EditMovieComponent implements OnInit {

  movieTypesArray = [
    {value: "COMEDY", name: "comedy", checked: false},
    {value: "HORROR", name: "horror", checked: false},
    {value: "SF", name: "science - fiction", checked: false},
    {value: "ACTION", name: "action", checked: false},
    {value: "THRILLER", name: "thriller", checked: false},
    {value: "DRAMA", name: "drama", checked: false},
    {value: "CRIME", name: "crime", checked: false},
    {value: "FANTASY", name: "fantasy", checked: false},
    {value: "MUSICAL", name: "musical", checked: false},
    {value: "ANIMATION", name: "animation", checked: false},
    {value: "WESTERNS", name: "western", checked: false}
  ]

  private movie: Movie;



  constructor(private httpClient: HttpClient, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.movie = new Movie;
      this.movie.id = params['id'];
        }
      )
    this.httpClient.get("http://localhost:8080/movie?movieId=" + this.movie.id)
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
        })


  }



  wasTypeSelected(movieType: { value: string; name: string; checked: boolean }) {
    if(this.movie.types.includes(movieType.value)) {
      movieType.checked = true;
      return true;
    }
    movieType.checked = false;
    return false;
  }

  checkMovieType(i, event) {
    this.movieTypesArray[i].checked = !this.movieTypesArray[i].checked;
    if(this.movieTypesArray[i].checked){
      this.movie.types.push(this.movieTypesArray[i].value);
    } else {
      let indexOf = this.movie.types.indexOf(this.movieTypesArray[i].value);
      this.movie.types.splice(indexOf, 1);
    }
  }


  saveChanges(editMovieForm: HTMLFormElement) {
    let checkedMovieTypes = this.movieTypesArray.filter(type => type.checked == true).map(type => type.value);
    this.httpClient.put("http://localhost:8080/movie?id=" + this.movie.id, {
      "title" : this.movie.title,
      "duration" : this.movie.duration,
      "productionCountry" : this.movie.productionCountry,
      "productionYear" : this.movie.productionYear,
      "types" : checkedMovieTypes,
      "directors" : null,
      "cast" : null,
      "movieShow" :null
    })
      .subscribe(
        (data: any) => {
          console.log(":)", data);
        },
        (error) => {
          console.log(":(", error);
        }

      )
  }
}

export class Movie {
  id;
  title: string;
  duration;
  productionCountry: string;
  productionYear: string;
  directors: string;
  cast: string;
  movieShow: string;
  types: string[] = [];
}

