import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {NotificationService} from '../../share/notification.service';
import {CrewService} from '../servic-crew/crew-service';
import {Crew} from '../models/model-crew/crew';


@Component({
  selector: 'app-add-crew',
  templateUrl: './add-crew.component.html',
  styleUrls: ['./add-crew.component.scss']
})
export class AddCrewComponent implements OnInit {

  constructor(private crewService: CrewService,
              private notification: NotificationService) {
  }

  crew = new Crew();
  addCrewForm: FormGroup;
  crewRoles = [
    {value: 'ACTOR', name: 'actor', 'checked': false, id: 1},
    {value: 'DIRECTOR', name: 'director', 'checked': false, id: 2}
  ];

  ngOnInit(): void {
    this.addCrewForm = new FormGroup({
      name: new FormControl(null),
      surname: new FormControl(null),
      crewRole: new FormControl(this.crewRoles[1])
    });
  }

  save() {
    const fieldsAreEmpty = this.areFieldsEmpty();
    if (fieldsAreEmpty !== true) {
      const checkedCrewRole = this.crewRoles.filter(role => role.checked === true).map(role => role.value);
      this.crew.name = this.addCrewForm.value.name;
      this.crew.surname = this.addCrewForm.value.surname;
      this.crew.crewRoles = checkedCrewRole;
      this.crewService.createCrew(this.crew)
        .subscribe(data => console.log(data), error => console.log(error));
      this.onReset();
    } else {
      return;
    }
  }

  areFieldsEmpty() {
    if (this.addCrewForm.value.name === '' || this.addCrewForm.value.name == null) {
      this.notification.warn('Please give name.');
      return true;
    }
    if (this.addCrewForm.value.surname === '' || this.addCrewForm.value.surname == null) {
      this.notification.warn('Please give surname.');
      return true;
    }

    // if (this.crewRoles.filter(role => role.checked === false)) {
    //   this.notification.warn('Please choice role.');
    //   return true;
    // }
// todo validate checkbox using notificationService

  }

  checkCrewRole(crewRole, event) {
    crewRole.checked = !crewRole.checked;
  }

  onReset() {
    this.addCrewForm.reset({
      course: this.crewRoles[1]
    });
  }


}
