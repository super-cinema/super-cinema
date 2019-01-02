import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-all-crew-view',
  templateUrl: './all-crew-view.component.html',
  styleUrls: ['./all-crew-view.component.scss']
})
export class AllCrewViewComponent implements OnInit {

    crewList = [];

  constructor(private httpClient: HttpClient) {
  }

  ngOnInit() {
    this.httpClient.get('http://localhost:8080/crew')
      .subscribe(
        (data: any) => {
          this.crewList = data;
        }
      );
  }

  displaySearchedCrew(crew: any, findMovieForm: HTMLFormElement) {
    if (crew.surname.toUpperCase().includes(findMovieForm.value.search.toUpperCase())) {
      return true;
    }
    return false;
  }

}
