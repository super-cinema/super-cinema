import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AllMoviesViewComponent} from './movie/all-movies-view/all-movies-view.component';
import {ScheduleScreeningComponent} from './schedule/schedule-screening/schedule-screening.component';
import {AddCrewComponent} from './crew/add-crew/add-crew.component';
import {AddMovieComponent} from './movie/add-movie/add-movie.component';
import {EditMovieComponent} from './movie/edit-movie/edit-movie.component';
import {AllCrewViewComponent} from './crew/all-crew-view/all-crew-view.component';
import {EditCrewComponent} from './crew/edit-crew/edit-crew.component';

const routes: Routes = [
  {path: 'allMoviesView', component: AllMoviesViewComponent},
  {path: 'scheduleScreening',  component: ScheduleScreeningComponent},
  {path: 'addMovie', component: AddMovieComponent},
  {path: 'movie/:id/edit', component: EditMovieComponent},
  {path: 'allCrewView', component: AllCrewViewComponent},
  {path: 'crew/:id/edit', component: EditCrewComponent },
  {path: 'addCrew', component: AddCrewComponent},


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

