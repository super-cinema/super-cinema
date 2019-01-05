import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {NotificationService} from '../../utils/notification.service';
import {DialogService} from '../../utils/dialog.service';

@Component({
  selector: 'app-all-crew-view',
  templateUrl: './all-crew-view.component.html',
  styleUrls: ['./all-crew-view.component.scss']
})
export class AllCrewViewComponent implements OnInit {

  crewList = [];

  constructor(private httpClient: HttpClient,
              private dialogService: DialogService,
              private notificationService: NotificationService) {
  }

  ngOnInit() {
    this.httpClient.get('http://localhost:8080/crew')
      .subscribe(
        (data: any) => {
          this.crewList = data;
        }
      );
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
              })(error => {
                this.notificationService.warn('Deleting ' + name + ' ' + surname + ' person failed');
                console.log(error);
              });
          }
        }
      );
  }
}
