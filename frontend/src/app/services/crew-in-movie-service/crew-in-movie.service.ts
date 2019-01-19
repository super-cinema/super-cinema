import {Injectable} from '@angular/core';
import {Crew} from '../../crew/model/crew';

@Injectable({
  providedIn: 'root'
})
export class CrewInMovieService {

  crewList;
  actorsList;
  directorsListInCrewInMovieService: Crew[] = [];

  constructor() {
  }

  addCrew(crew: Crew) {
    crew.id = this.crewList.length + 1;
    this.crewList.push(crew);
    console.log('ADD')
  }

  deleteCrew(id: number) {
    const crew = this.crewList.findIndex(c => c.id === id);
    this.crewList.splice(crew, 1);
    console.log('delete')
  }

  getAllCrew() {
    console.log('getAllCrew');
    return this.crewList;

  }

  passCrewList(passedCrewList) {
    console.log('passCrewList')
    this.crewList.push(passedCrewList);

  }

  passActorsList(passedActorsList: Crew[]){
    this.actorsList.push(passedActorsList);
  }

  passedDirectorsList(passedDirectorsList: Crew[]){
    passedDirectorsList.map(director => {
      if(this.directorsListInCrewInMovieService.indexOf(director) === -1){
        this.directorsListInCrewInMovieService.push(director);
      }
    })
  };

  getAllActors(){
    console.log('get all actors');
    return this.actorsList;

  }

  getAllDirectors(){
    console.log('get all directors');
    return this.directorsListInCrewInMovieService;

  }
}
