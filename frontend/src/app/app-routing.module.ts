import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AllMoviesViewComponent} from './all-movies-view/all-movies-view.component';
import {ScheduleScreeningComponent} from './schedule-screening/schedule-screening.component';
import {AddCrewComponent} from './add-crew/add-crew.component';
import {AddMovieComponent} from './add-movie/add-movie.component';

const routes: Routes = [
  {path: 'allMoviesView', component: AllMoviesViewComponent},
  {path: 'scheduleScreening',  component: ScheduleScreeningComponent},
  {path: 'addCrew', component: AddCrewComponent},
  {path: 'addMovie', component: AddMovieComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

