import {Injectable} from '@angular/core';
import {Crew} from '../../crew/model/crew';

@Injectable({
  providedIn: 'root'
})
export class CrewInMovieService {

  crewList: Crew[] = [];

  constructor() {
  }

  addCrew(crew: Crew) {
    crew.id = this.crewList.length + 1;
    this.crewList.push(crew);
  }

  deleteCrew(id: number) {
    const crew = this.crewList.findIndex(c => c.id === id);
    this.crewList.splice(crew, 1);
  }

  getAllContacts() {
    return this.crewList;
  }
}
