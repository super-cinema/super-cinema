import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {NotificationService} from '../../share/notification.service';
import {DialogService} from '../../share/dialog.service';
import {Crew} from '../model/crew';
import {CrewService} from '../../services/crew-service/crew-service';
import {Observable} from 'rxjs';
import {CrewInMovieService} from '../../services/crew-in-movie-service/crew-in-movie.service';

@Component({
  selector: 'app-all-crew-view',
  templateUrl: './all-crew-view.component.html',
  styleUrls: ['./all-crew-view.component.scss']
})
export class AllCrewViewComponent implements OnInit {

  crewList: Observable<Array<Crew>>;

  constructor(private httpClient: HttpClient,
              private dialogService: DialogService,
              private notificationService: NotificationService,
              private crewService: CrewService,
              private crewInMovieService: CrewInMovieService) {
  }

  ngOnInit() {
    this.crewService.getCrewList().subscribe((data: any) => {
      this.crewList = data;
    });
    this.crewInMovieService.clearAllList();
  }

  displaySearchedCrew(crew: any, findCrewForm: HTMLFormElement) {
    return crew.surname.toUpperCase().includes(findCrewForm.value.search.toUpperCase());
  }

  deleteAllCrew() {
    const msg = 'Are you sure you want delete the entire crew?  ';
    this.dialogService.openConfirmDialog(msg)
      .afterClosed()
      .subscribe(resp => {
        if (resp) {
          this.crewService.deleteAll()
            .subscribe(
              data => {
                console.log(data);
                this.ngOnInit();
              },
              error => console.log('ERROR: ' + error));
        }
      });
  }
}
