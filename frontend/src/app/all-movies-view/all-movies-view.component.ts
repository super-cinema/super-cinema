import { Component, OnInit } from '@angular/core';
import {MatListModule} from '@angular/material';


@Component({
  selector: 'app-all-movies-view',
  templateUrl: './all-movies-view.component.html',
  styleUrls: ['./all-movies-view.component.scss']
})
export class AllMoviesViewComponent implements OnInit {
  /**movie list from backend*/
  moviesList = [
    {
    'title': 'title'
  },
    {
      'title': 'nextTitle'
    }
  ]

  constructor() { }

  ngOnInit() {
  }

}
