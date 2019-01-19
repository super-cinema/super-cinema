import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {FormArray, FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {CrewInMovieService} from '../../services/crew-in-movie-service/crew-in-movie.service';
import {AllCrewViewComponent} from '../all-crew-view/all-crew-view.component';
import {HttpClient} from '@angular/common/http';
import {CrewService} from '../../services/crew-service/crew-service';
import {Crew} from "../model/crew";

@Component({
  selector: 'app-crew-in-movie',
  templateUrl: './crew-in-movie.component.html',
  styleUrls: ['./crew-in-movie.component.scss']
})
export class CrewInMovieComponent implements OnInit {
  private crewList = [];
  private actorsListToPass: Crew[] = [];
  private crewRole;
  private directorsListToPass: Crew[] = [];
  constructor(private formBuilder: FormBuilder,
              private dialogRef: MatDialogRef<AllCrewViewComponent>,
              private httpClient: HttpClient,
              private crewService: CrewService,
              private crewInMovieService: CrewInMovieService,
              @Inject(MAT_DIALOG_DATA) public data: any) {
              this.crewRole = this.data;
  }


  ngOnInit() {
    this.crewService.getCrewList().subscribe((data) => {
      this.crewList = data;
    });
  }

  onSubmit(){
    //this.crewInMovieService.passActorsList(this.actorsListToPass);
    if(this.crewRole.crewRole === "ACTOR"){
      this.crewInMovieService.passActorsList(this.actorsListToPass);
    } else {
      this.crewInMovieService.passedDirectorsList(this.directorsListToPass)
    }
    this.dialogRef.close(true);
  }

  checkCrew(crew: Crew, $event) {
    if(this.crewRole.crewRole === "ACTOR"){
      console.log('actor list', this.directorsListToPass)
      let index = this.actorsListToPass.indexOf(crew);
      if(index !== -1){
        this.actorsListToPass.splice(index, 1);
        return;
      }
      this.actorsListToPass.push(crew);
    }else {
      console.log('//directors list', this.directorsListToPass)
      let index = this.directorsListToPass.indexOf(crew);
      if(index !== -1){
        this.directorsListToPass.splice(index, 1);
        return;
      }
      console.log('both list', this.directorsListToPass)
      this.directorsListToPass.push(crew);
      console.log('both list after', this.directorsListToPass)
    }

  }

}
