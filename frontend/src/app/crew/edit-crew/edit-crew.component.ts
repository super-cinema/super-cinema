import {HttpClient} from '@angular/common/http';
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Crew} from '../model-crew/crew';

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


  constructor(private httpClient: HttpClient, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.crew = new Crew;
      this.crew.id = params['id'];
    });
    this.getCrewData();
  }


  wasTypeSelected(crewRole: { value: string; name: string; checked: boolean }) {

    if (this.crew.crewRoleDtos.includes(crewRole.value)) {
      crewRole.checked = true;
      return true;
    }
    crewRole.checked = false;
    return false;
  }

  checkCrewRole(i, event) {
    this.crewRole[i].checked = !this.crewRole[i].checked;
    if (this.crewRole[i].checked) {
      this.crew.crewRoleDtos.push(this.crewRole[i].value);
    } else {
      const indexOf = this.crew.crewRoleDtos.indexOf(this.crewRole[i].value);
      this.crew.crewRoleDtos.splice(indexOf, 1);
    }
  }


  saveChanges(editMovieForm: HTMLFormElement) {
    const checkedMovieTypes = this.crewRole.filter(type => type.checked === true).map(type => type.value);
    this.httpClient.put('http://localhost:8080/crew?id=' + this.crew.id, {
      'name': this.crew.name,
      'surname': this.crew.surname,
      'crewRoleDtos': checkedMovieTypes,
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
          this.crew.crewRoleDtos = data.crewRoleDtos;
        });
  }
}

