import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AllMoviesViewComponent} from './all-movies-view/all-movies-view.component';
import {ScheduleScreeningComponent} from './schedule-screening/schedule-screening.component';

const routes: Routes = [
  {path: 'allMoviesView', component: AllMoviesViewComponent},
  {path: 'scheduleScreening',  component: ScheduleScreeningComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
