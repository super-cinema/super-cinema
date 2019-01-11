import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Crew} from '../models/model-crew/crew';

@Component({
  selector: 'app-crew-detail-in-movie',
  templateUrl: './crew-detail-in-movie.component.html',
  styleUrls: ['./crew-detail-in-movie.component.scss']
})
export class CrewDetailInMovieComponent implements OnInit {

  @Input()
  crew;

  @Output()
  eventCrew = new EventEmitter<Crew>();

  select(crew) {
  this.eventCrew.emit(crew);
  }

  constructor() { }

  ngOnInit() {
  }

}
