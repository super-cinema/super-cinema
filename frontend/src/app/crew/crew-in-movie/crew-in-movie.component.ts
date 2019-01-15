import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {FormArray, FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {CrewInMovieService} from '../../services/crew-in-movie-service/crew-in-movie.service';
import {AllCrewViewComponent} from '../all-crew-view/all-crew-view.component';
import {HttpClient} from '@angular/common/http';
import {CrewService} from '../../services/crew-servic/crew-service';

@Component({
  selector: 'app-crew-in-movie',
  templateUrl: './crew-in-movie.component.html',
  styleUrls: ['./crew-in-movie.component.scss']
})
export class CrewInMovieComponent implements OnInit {
  // crewList = [Crew];
  crewList = [{id: 100, name: 'order 1'},
    {id: 200, name: 'order 2'},
    // {id: 300, name: 'order 3'},
    {id: 400, name: 'order 4'}];
  // I have a problem with doing this list dynamic
  // with number of objects equal to amount from DB
  // when i making this array subscribe (which should observe how much i have objects in db)
  // and built enough number of check boxes then a I have problem with appropriate map  arrays in constructor
  // for now if you have 3 objects crew in your DB ten this array must contains 3 records, if you have 4 objects in DB
  // then you array must contains 4 records... etc
  form: FormGroup;
  constructor(private formBuilder: FormBuilder,
              private dialogRef: MatDialogRef<AllCrewViewComponent>,
              private httpClient: HttpClient,
              private crewService: CrewService,
              private crewInMovieService: CrewInMovieService,
              @Inject(MAT_DIALOG_DATA) public data: any) {

    const formControls = this.crewList.map(control => new FormControl(false));
    this.form = this.formBuilder.group({
      crewList: new FormArray(formControls),
    });
  }


  ngOnInit() {
    this.crewService.getCrewList().subscribe((data: any) => {
      this.crewList = data;
    });
    // searchBar: new FormControl(null)
  }
  addCrewListCheckBox() {
    const arr = <FormArray>this.form.get('crewList');
    arr.push(new FormControl(null));
  }
  onNoClick(): void {
    this.dialogRef.close();
  }

  displaySearchedCrew(crew: any, findCrewForm: HTMLFormElement) {
    return crew.surname.toUpperCase().includes(findCrewForm.value.search.toUpperCase());
  }

    onSubmit() {
    const selectedCrews = this.form.value.crewList
      .map((v, i) => v ? this.crewList[i].id : null)
      .filter(v => v !== null);
    this.crewInMovieService.addCrew(selectedCrews);
    this.dialogRef.close();
    console.log(selectedCrews);
  }
}
