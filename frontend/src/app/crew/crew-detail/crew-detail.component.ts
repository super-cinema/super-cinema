import {Component, Input, OnInit} from '@angular/core';
import {Crew} from '../models/model-crew/crew';
import {CrewService} from '../servic-crew/crew-service';
import {AllCrewViewComponent} from '../all-crew-view/all-crew-view.component';
import {NotificationService} from '../../share/notification.service';
import {DialogService} from '../../share/dialog.service';

@Component({
  selector: 'app-crew-detail',
  templateUrl: './crew-detail.component.html',
  styleUrls: ['./crew-detail.component.scss']
})
export class CrewDetailComponent implements OnInit {

  @Input()
  crew: Crew;

  constructor(private crewService: CrewService,
              private dialogService: DialogService,
              private notificationService: NotificationService,
              private crewListComponent: AllCrewViewComponent) {
  }

  ngOnInit() {
  }

  deleteCrew() {
    const msg: string = 'Do you want delete ' + this.crew.name + ' ' + this.crew.surname;
    this.dialogService.openConfirmDialog(msg)
      .afterClosed()
      .subscribe(resp => {
        if (resp) {
          this.crewService.deleteCrew(Number(this.crew.id))
            .subscribe(
              data => {
                console.log(data);
                this.ngOnInit();
                this.crewListComponent.ngOnInit();
              },
              error => console.log(error));
        }
      });
  }

  updateCrew() {
    this.crewService.updateCrew(Number(this.crew.id),
      {name: this.crew.name, age: this.crew.surname})
      .subscribe(
        data => {
          console.log(data);
          this.crew = data as Crew;
        },
        error => console.log(error));
  }

}
