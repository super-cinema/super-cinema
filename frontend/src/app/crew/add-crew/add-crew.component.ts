import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {NotificationService} from '../../share/notification.service';
import {CrewService} from '../../services/crew-service/crew-service';
import {Crew} from '../model/crew';


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
    {value: 'ACTOR', name: 'actor', 'checked': false},
    {value: 'DIRECTOR', name: 'director', 'checked': false}
  ];

  ngOnInit(): void {
    this.addCrewForm = new FormGroup({
      name: new FormControl(null),
      surname: new FormControl(null),
      crewRole: new FormControl(this.crewRoles[1])
    });
  }

  save() {
    const checkedCrewRole = this.crewRoles.filter(role => role.checked === true).map(role => role.value);
    const fieldsAreEmpty = this.areFieldsEmpty(checkedCrewRole);
    if (fieldsAreEmpty !== true) {
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

  areFieldsEmpty(checkedCrewRole) {
    if (this.addCrewForm.value.name === '' || this.addCrewForm.value.name == null) {
      this.notification.warn('Please give name.');
      return true;
    }
    if (this.addCrewForm.value.surname === '' || this.addCrewForm.value.surname == null) {
      this.notification.warn('Please give surname.');
      return true;
    }
    if (checkedCrewRole == false) {
      this.notification.warn('Please choice role.');
      return true;
    }
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
