import {HttpClient} from '@angular/common/http';
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Crew} from '../model/crew';
import {NotificationService} from '../../share/notification.service';
import {CrewService} from '../../services/crew-service/crew-service';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-edit-crew',
  templateUrl: './edit-crew.component.html',
  styleUrls: ['./edit-crew.component.scss']
})
export class EditCrewComponent implements OnInit {


  crew = new Crew();
  editCrewForm: FormGroup;

  crewRoles = [
    {value: 'ACTOR', name: 'actor', 'checked': false, id: 1},
    {value: 'DIRECTOR', name: 'director', 'checked': false, id: 2}
  ];

  constructor(private httpClient: HttpClient,
              private route: ActivatedRoute,
              private notification: NotificationService,
              private crewService: CrewService) {
  }

  ngOnInit() {
    this.setRouteParam();
    this.setFieldsToFormGroup();
    this.getCrew();
  }

  private setFieldsToFormGroup() {
    this.editCrewForm = new FormGroup({
      name: new FormControl(null),
      surname: new FormControl(null)
    });
  }

  private setRouteParam() {
    this.route.params.subscribe(params => {
      this.crew = new Crew();
      this.crew.id = params['id'];
    });
  }

  private getCrew() {
    this.crewService.getCrew(Number(this.crew.id)).subscribe(
      (data: any) => {
        console.log(data);
        this.crew.name = data.name;
        this.crew.surname = data.surname;
        this.crew.crewRoles = data.crewRoles;
        this.editCrewForm.get('name').setValue(this.crew.name);
        this.editCrewForm.get('surname').setValue(this.crew.surname);
      }
    );
  }


  wasTypeSelected(crewRole: { value: string; name: string; checked: boolean }) {
    if (this.crew.crewRoles.includes(crewRole.value)) {
      crewRole.checked = true;
      return true;
    }
  }

  checkCrewRole(i, event) {
    this.crewRoles[i].checked = !this.crewRoles[i].checked;
    if (this.crewRoles[i].checked) {
      this.crew.crewRoles.push(this.crewRoles[i].value);
    } else {
      const indexOf = this.crew.crewRoles.indexOf(this.crewRoles[i].value);
      this.crew.crewRoles.splice(indexOf, 1);
    }
  }

  areFieldsEmpty() {
    if (this.editCrewForm.value.name === '' || this.editCrewForm.value.name == null) {
      this.notification.warn('Please give name.');
      return true;
    }
    if (this.editCrewForm.value.surname === '' || this.editCrewForm.value.surname == null) {
      this.notification.warn('Please give surname.');
      return true;
    }
  }

// todo validate checkbox here and in addCrewComponent
  updateCrew() {
    const fieldsAreEmpty = this.areFieldsEmpty();
    if (fieldsAreEmpty !== true) {
      const checkedCrewRoles = this.crewRoles.filter(type => type.checked === true).map(type => type.value);
      this.crewService.updateCrew(Number(this.crew.id),
        {
          name: this.editCrewForm.value.name,
          surname: this.editCrewForm.value.surname,
          crewRoles: checkedCrewRoles
        })
        .subscribe(data => {
            console.log(data);
            this.crew = data as Crew;
          },
          error => console.log(error));
    } else {
      return;
    }
  }
}

