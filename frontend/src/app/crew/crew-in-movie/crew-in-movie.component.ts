import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {FormArray, FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {CrewInMovieService} from '../../services/crew-in-movie-service/crew-in-movie.service';
import {AllCrewViewComponent} from '../all-crew-view/all-crew-view.component';
import {HttpClient} from '@angular/common/http';
import {CrewService} from '../../services/crew-servic/crew-service';
import {Crew} from "../model/crew";

@Component({
  selector: 'app-crew-in-movie',
  templateUrl: './crew-in-movie.component.html',
  styleUrls: ['./crew-in-movie.component.scss']
})
export class CrewInMovieComponent implements OnInit {
  crewList = [];
  crewListToPass = [];
  constructor(private formBuilder: FormBuilder,
              private dialogRef: MatDialogRef<AllCrewViewComponent>,
              private httpClient: HttpClient,
              private crewService: CrewService,
              private crewInMovieService: CrewInMovieService,
              @Inject(MAT_DIALOG_DATA) public data: any) {}


  ngOnInit() {
    this.crewService.getCrewList().subscribe((data: any) => {
      this.crewList = data;
    });
  }

  onSubmit(){
    console.log(this.crewListToPass);
    this.crewInMovieService.passCrewList(this.crewListToPass);
    this.dialogRef.close(true);
  }

  checkCrew(crew: Crew, $event) {
    let index = this.crewListToPass.indexOf(crew);
    if(index !== -1){
      this.crewListToPass.splice(index, 1);
      return;
    }
    this.crewListToPass.push(crew);
  }
}
