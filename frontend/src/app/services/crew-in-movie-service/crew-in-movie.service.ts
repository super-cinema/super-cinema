import {Injectable, OnInit} from '@angular/core';
import {Crew} from '../../crew/model/crew';

@Injectable()
export class CrewInMovieService implements OnInit {

  crewList = [];
  actorsList = [];
  directorsList: Crew[] = [];

  constructor() {
  }

  addCrew(crew: Crew) {
    crew.id = this.crewList.length + 1;
    this.crewList.push(crew);
    console.log('ADD');
  }

  deleteCrew(id: number) {
    const crew = this.crewList.findIndex(c => c.id === id);
    this.crewList.splice(crew, 1);
  }

  getAllCrew() {
    return this.crewList;
  }

  passCrewList(passedCrewList) {
    this.crewList.push(passedCrewList);

  }

  passActorsList(passedActorsList: Crew[]) {
    passedActorsList.map(actor => {
      if (this.actorsList.filter(existingActor => existingActor.id === actor.id).length == 0) {
        this.actorsList.push(actor);
      }
    });

  }

  passedDirectorsList(passedDirectorsList: Crew[]) {
    passedDirectorsList.map(director => {
      if (this.directorsList.filter(existingDirector => existingDirector.id === director.id).length === 0) {
        this.directorsList.push(director);
      }
    });
  }

  getAllActors() {
    return this.actorsList;
  }

  getAllDirectors() {
    return this.directorsList;
  }

  clearActorList() {
    this.actorsList = [];
  }

  clearDirectorsList() {
    this.directorsList = [];
  }

  clearAllList() {
    this.clearActorList();
    this.clearDirectorsList();
  }

  ngOnInit(): void {
  }

}
