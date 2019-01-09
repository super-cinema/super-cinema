import {Component, Input, OnInit} from '@angular/core';
import {Crew} from '../../crew/models/model-crew/crew';
import {CrewService} from '../../crew/servic-crew/crew-service';
import {AllCrewWievTestingComponent} from '../all-crew-wiev-testing/all-crew-wiev-testing.component';

@Component({
  selector: 'app-crew-detail-test',
  templateUrl: './crew-detail-test.component.html',
  styleUrls: ['./crew-detail-test.component.scss']
})
export class CrewDetailTestComponent implements OnInit {

  @Input() crew: Crew;

  constructor(private crewService: CrewService,
              private crewListComponent: AllCrewWievTestingComponent) { }

  ngOnInit() {
  }

  deleteCrew() {
    this.crewService.deleteCrew(Number(this.crew.id))
      .subscribe(
        data => {
          console.log(data);
          this.ngOnInit();
          // this.crewListComponent.reloadData();
        this.crewListComponent.ngOnInit();
        },
        error => console.log(error));
  }
// / deleteCrew(id, name, surname) {
  //   const msg: string = 'Do you want delete ' + name + ' ' + surname;
  //   this.dialogService.openConfirmDialog(msg)
  //     .afterClosed()
  //     .subscribe(resp => {
  //         if (resp) {
  //           this.httpClient.delete('http://localhost:8080/crew?id=' + id)
  //             .subscribe(data => {
  //               this.ngOnInit();
  //               this.notificationService.success('Deleted ' + name + ' ' + surname + ' person successfully');
  //             }), (error => {
  //             this.notificationService.warn('Deleting ' + name + ' ' + surname + ' person failed');
  //             console.log(error);
  //           });
  //         }
  //       }
  //     );
  // }
  updateActive() {
    this.crewService.updateCrew(Number(this.crew.id),
      {name: this.crew.name, age: this.crew.surname})
      .subscribe(
        data => {
          console.log(data);
          this.crew = data as Crew;
        },
        error => console.log(error));
  }

  //  saveChanges(editCrewForm: HTMLFormElement) {
  //     const checkedCrewRoles = this.crewRole.filter(type => type.checked === true).map(type => type.value);
  //     if (editCrewForm.value.name === '' || editCrewForm.value.name == null) {
  //       this.notification.warn('Please give name.');
  //       return;
  //     }
  //     if (editCrewForm.value.surname === '' || editCrewForm.value.surname == null) {
  //       this.notification.warn('Please give surname.');
  //       return;
  //     }
  //     this.httpClient.put('http://localhost:8080/crew?id=' + this.crew.id, {
  //       'name': this.crew.name,
  //       'surname': this.crew.surname,
  //       'crewRoles': checkedCrewRoles,
  //     })
  //       .subscribe(
  //         (data: any) => {
  //           console.log(':)', data);
  //         },
  //         (error) => {
  //           console.log(':(', error);
  //         }
  //       );
  //   }



}
