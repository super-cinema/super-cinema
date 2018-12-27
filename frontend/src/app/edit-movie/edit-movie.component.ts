import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-edit-movie',
  templateUrl: './edit-movie.component.html',
  styleUrls: ['./edit-movie.component.scss']
})
export class EditMovieComponent implements OnInit {

  movieTypes = [
    {value: "COMEDY", name: "comedy", "checked": false},
    {value: "HORROR", name: "horror", "checked": false},
    {value: "SF", name: "science - fiction", "checked": false},
    {value: "ACTION", name: "action", "checked": false},
    {value: "THRILLER", name: "thriller", "checked": false},
    {value: "DRAMA", name: "drama", "checked": false},
    {value: "CRIME", name: "crime", "checked": false},
    {value: "FANTASY", name: "fantasy", "checked": false},
    {value: "MUSICAL", name: "musical", "checked": false},
    {value: "ANIMATION", name: "animation", "checked": false},
    {value: "WESTERNS", name: "western", "checked": false}
  ]

  constructor() { }

  ngOnInit() {
  }

}
