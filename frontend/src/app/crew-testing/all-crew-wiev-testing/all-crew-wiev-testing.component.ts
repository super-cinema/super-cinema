import { Component, OnInit } from '@angular/core';
import {Crew} from '../../crew/models/model-crew/crew';
import {HttpClient} from '@angular/common/http';
import {DialogService} from '../../share/dialog.service';
import {NotificationService} from '../../share/notification.service';
import {Observable} from 'rxjs';
import {CrewService} from '../../crew/servic-crew/crew-service';

@Component({
  selector: 'app-all-crew-wiev-testing',
  templateUrl: './all-crew-wiev-testing.component.html',
  styleUrls: ['./all-crew-wiev-testing.component.scss']
})
export class AllCrewWievTestingComponent implements OnInit {
  crewList: Observable<Array<Crew>>;
  // crewList = [];
  // actorList = [];


  private crew: Crew;
  constructor(private httpClient: HttpClient,
              private dialogService: DialogService,
              private crewService: CrewService,
              private notificationService: NotificationService) {
  }

  ngOnInit() {
    this.crewService.getCrewList().subscribe((data: any) => {
      this.crewList = data;
    });
  }

  reloadData() {
    this.crewList = this.crewService.getCrewList();
  }

  displaySearchedCrew(crew: any, findCrewForm: HTMLFormElement) {
    return crew.surname.toUpperCase().includes(findCrewForm.value.search.toUpperCase());
  }

// todo not impmementet in backend yet
  deleteAllCrews() {
    this.crewService.deleteAll()
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log('ERROR: ' + error));
  }


  // ngOnInit() {
  //   this.httpClient.get('http://localhost:8080/crew')
  //     .subscribe(
  //       (data: any) => {
  //         this.crewList = data;
  //       }
  //     );
  // }
  //



  //


}
