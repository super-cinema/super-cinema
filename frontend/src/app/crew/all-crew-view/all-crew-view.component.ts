import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {NotificationService} from '../../share/notification.service';
import {DialogService} from '../../share/dialog.service';
import {Crew} from '../models/model-crew/crew';
import {CrewService} from '../servic-crew/crew-service';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-all-crew-view',
  templateUrl: './all-crew-view.component.html',
  styleUrls: ['./all-crew-view.component.scss']
})
export class AllCrewViewComponent implements OnInit {

  crewList: Observable<Array<Crew>>;

  actorList = [];
  private crew: Crew;

  constructor(private httpClient: HttpClient,
              private dialogService: DialogService,
              private notificationService: NotificationService,
              private crewService: CrewService) {
  }

  ngOnInit() {
    this.crewService.getCrewList().subscribe((data: any) => {
      this.crewList = data;
    });
  }

  addActor(crew: Crew, $event) {
    this.actorList.push(crew);
    console.log(this.actorList);
  }

  checkCrewRole(i, event) {
    this.crewList[i].checked = !this.crewList[i].checked;
    if (this.crewList[i].checked) {
      this.crew.crewRoles.push(this.crewList[i].value);
    } else {
      const indexOf = this.crew.crewRoles.indexOf(this.crewList[i].value);
      this.crew.crewRoles.splice(indexOf, 1);
    }
  }

  displaySearchedCrew(crew: any, findCrewForm: HTMLFormElement) {
    return crew.surname.toUpperCase().includes(findCrewForm.value.search.toUpperCase());
  }

  deleteCrew(id, name, surname) {
    const msg: string = 'Do you want delete ' + name + ' ' + surname;
    this.dialogService.openConfirmDialog(msg)
      .afterClosed()
      .subscribe(resp => {
          if (resp) {
            this.httpClient.delete('http://localhost:8080/crew?id=' + id)
              .subscribe(data => {
                this.ngOnInit();
                this.notificationService.success('Deleted ' + name + ' ' + surname + ' person successfully');
              }), (error => {
              this.notificationService.warn('Deleting ' + name + ' ' + surname + ' person failed');
              console.log(error);
            });
          }
        }
      );
  }

}
