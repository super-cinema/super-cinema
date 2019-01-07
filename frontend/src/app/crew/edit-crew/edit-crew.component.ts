import {HttpClient} from '@angular/common/http';
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Crew} from '../model-crew/crew';
import {NotificationService} from '../../share/notification.service';

@Component({
  selector: 'app-edit-crew',
  templateUrl: './edit-crew.component.html',
  styleUrls: ['./edit-crew.component.scss']
})
export class EditCrewComponent implements OnInit {

  crewRole = [
    {value: 'ACTOR', name: 'actor', 'checked': false},
    {value: 'DIRECTOR', name: 'director', 'checked': false}
  ];

  private crew: Crew;


  constructor(private httpClient: HttpClient, private route: ActivatedRoute,
              private notification: NotificationService) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.crew = new Crew;
      this.crew.id = params['id'];
    });
    this.getCrewData();
  }


  wasTypeSelected(crewRole: { value: string; name: string; checked: boolean }) {
    if (this.crew.crewRoles.includes(crewRole.value)) {
      crewRole.checked = true;
      return true;
    }
  }

  checkCrewRole(i, event) {
    this.crewRole[i].checked = !this.crewRole[i].checked;
    if (this.crewRole[i].checked) {
      this.crew.crewRoles.push(this.crewRole[i].value);
    } else {
      const indexOf = this.crew.crewRoles.indexOf(this.crewRole[i].value);
      this.crew.crewRoles.splice(indexOf, 1);
    }
  }


  saveChanges(editCrewForm: HTMLFormElement) {
    const checkedCrewRoles = this.crewRole.filter(type => type.checked === true).map(type => type.value);
    if (editCrewForm.value.name === '' || editCrewForm.value.name == null) {
      this.notification.warn('Please give name.');
      return;
    }
    if (editCrewForm.value.surname === '' || editCrewForm.value.surname == null) {
      this.notification.warn('Please give surname.');
      return;
    }
    this.httpClient.put('http://localhost:8080/crew?id=' + this.crew.id, {
      'name': this.crew.name,
      'surname': this.crew.surname,
      'crewRoles': checkedCrewRoles,
    })
      .subscribe(
        (data: any) => {
          console.log(':)', data);
        },
        (error) => {
          console.log(':(', error);
        }
      );
  }

  private getCrewData() {
    this.httpClient.get('http://localhost:8080/crew?id=' + this.crew.id)
      .subscribe(
        (data: any) => {
          console.log(data);
          this.crew.name = data.name;
          this.crew.surname = data.surname;
          this.crew.crewRoles = data.crewRoles;
        });
  }
}

