import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {NgForm} from '@angular/forms';
import {NotificationService} from '../../share/notification.service';


@Component({
  selector: 'app-add-crew',
  templateUrl: './add-crew.component.html',
  styleUrls: ['./add-crew.component.scss']
})
export class AddCrewComponent implements OnInit {

  constructor(private httpClient: HttpClient,
              private notification: NotificationService) {
  }

  crewRoles = [
    {value: 'ACTOR', name: 'actor', 'checked': false},
    {value: 'DIRECTOR', name: 'director', 'checked': false}
  ];

  checkCrewRole(crewRole, event) {
    crewRole.checked = !crewRole.checked;
  }

  addCrew(addCrewForm: NgForm) {
    const checkedCrewRole = this.crewRoles.filter(type => type.checked === true).map(type => type.value);
    if (addCrewForm.value.name === '' || addCrewForm.value.name == null) {
      this.notification.warn('Please give name.');
      return;
    }
    if (addCrewForm.value.surname === '' || addCrewForm.value.surname == null) {
      this.notification.warn('Please give surname.');
      return;
    }
    console.log(checkedCrewRole);
    this.httpClient.post('http://localhost:8080/crew', {
      'name': addCrewForm.value.name,
      'surname': addCrewForm.value.surname,
      'crewRoles': checkedCrewRole,
    }).subscribe(
      (data: any) => {
        console.log('ok');
        addCrewForm.reset();
      }, (error1) => {
        console.log('bad');
      }
    );
  }

  ngOnInit(): void {
  }


}
