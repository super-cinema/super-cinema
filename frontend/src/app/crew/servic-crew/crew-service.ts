import {BehaviorSubject, Observable} from 'rxjs';
import {Injectable} from '@angular/core';
import {Crew} from '../models/model-crew/crew';
import {HttpServiceCrew} from './http-service-crew';

@Injectable()
export class CrewService {

  taskListObs = new BehaviorSubject<Array<Crew>>([]);

  constructor(private httpService: HttpServiceCrew) {
    this.httpService.getCrew().subscribe(list => {
      this.taskListObs.next(list);
      // tym spoosbem pobierzemy sb wszystkie dane do observable subjecta
    });
  }


  addCrew(crew: Crew) {
    const list = this.taskListObs.getValue();
    list.push(crew);
    this.taskListObs.next(list);
  }

  removeCrew(crew: Crew) {
    const taskListDB = this.taskListObs.getValue().filter(e => e !== crew);
    this.taskListObs.next(taskListDB);
  }

  getCrewList(): Observable<Array<Crew>> {
    return this.taskListObs.asObservable();
  }

  saveTasksInDB() {
    this.httpService.saveCrew(this.taskListObs.getValue());
  }

}
