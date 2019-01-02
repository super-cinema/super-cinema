import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AllMoviesViewComponent} from './all-movies-view/all-movies-view.component';
import {ScheduleScreeningComponent} from './schedule-screening/schedule-screening.component';
import {AddCrewComponent} from './crew/add-crew/add-crew.component';
import {AddMovieComponent} from './add-movie/add-movie.component';
import {AllCrewViewComponent} from './crew/all-crew-view/all-crew-view.component';
import {EditCrewComponent} from './crew/edit-crew/edit-crew.component';

const routes: Routes = [
  {path: 'allMoviesView', component: AllMoviesViewComponent},
  {path: 'scheduleScreening',  component: ScheduleScreeningComponent},
  {path: 'addCrew', component: AddCrewComponent},
  {path: 'addMovie', component: AddMovieComponent},
  {path: 'addCrew/allCrewView', component: AllCrewViewComponent},
  {path: 'crew/:id/edit', component: EditCrewComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

