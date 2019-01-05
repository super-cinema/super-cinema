import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {NgForm} from '@angular/forms';


@Component({
  selector: 'app-add-crew',
  templateUrl: './add-crew.component.html',
  styleUrls: ['./add-crew.component.scss']
})
export class AddCrewComponent implements OnInit {

  constructor(private httpClient: HttpClient) {
  }

  crewRole = [
    {value: 'ACTOR', name: 'actor', 'checked': false},
    {value: 'DIRECTOR', name: 'director', 'checked': false}
  ];

  checkCrewRole(crewRole, event) {
    crewRole.checked = !crewRole.checked;
  }

  addCrew(addCrewForm: NgForm) {
    const checkedCrewRole = this.crewRole.filter(type => type.checked === true).map(type => type.value);

    this.httpClient.post('http://localhost:8080/crew', {
      'name': addCrewForm.value.name,
      'surname': addCrewForm.value.surname,
      'crewRoleDtos': checkedCrewRole,
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
