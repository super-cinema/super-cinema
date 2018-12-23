import { Component, OnInit } from '@angular/core';
import {NgForm} from "@angular/forms";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-add-movie',
  templateUrl: './add-movie.component.html',
  styleUrls: ['./add-movie.component.scss']
})
export class AddMovieComponent implements OnInit {



  ngOnInit() {
  }

  constructor(private httpClient: HttpClient) { }

  addMovie(addMovieForm: NgForm) {
    this.httpClient.post("http://localhost:8080/movie",  {
      "title" : addMovieForm.value.title,
      "duration" : addMovieForm.value.duration,
      "productionCountry" : addMovieForm.value.productionCountry,
      "productionYear" : addMovieForm.value.productionYear,
      "types" : ["COMEDY"],
      "directors" : null,
      "cast" : null,
      "movieShow" :null
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
