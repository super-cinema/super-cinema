import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {any} from "codelyzer/util/function";

@Component({
  selector: 'app-add-crew',
  templateUrl: './add-crew.component.html',
  styleUrls: ['./add-crew.component.scss']
})
export class AddCrewComponent implements OnInit {
  name = '';
  surName = '';
  errorMessage = '';
  constructor(private httpClient: HttpClient) {
  }

  setName(event: any) {
    this.name = (<HTMLInputElement>event.target).value;
    console.log(this.name);
  }

  setSurName(event: any) {
    this.surName = event.target.value;
    console.log(this.surName);
  }

  // setCreRole(event: any){
  //
  // }
  addNewCrew(form) {
    this.httpClient.post('http://localhost:8080/crew', {
      name: this.name,
      surName: this.surName,
    })
      .subscribe(
        (data: any) => {
          if (data.lenght) {
            console.log(data);
          }
        }, (error1) => {
          console.log(':(');
          this.errorMessage = error1.error.message;
          console.log(error1.error.message);
        }
      );
    form.reset();
  }

  ngOnInit(): void {
  }

}
