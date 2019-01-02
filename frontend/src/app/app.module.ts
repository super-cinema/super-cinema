import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AlertModule} from 'ngx-bootstrap';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NavBarComponent} from './nav-bar/nav-bar.component';
import {AllMoviesViewComponent} from './all-movies-view/all-movies-view.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatListModule, MatPaginatorModule, MatSortModule, MatTableModule} from '@angular/material';
import {ScheduleScreeningComponent} from './schedule-screening/schedule-screening.component';
import {ScheduleScreeningViewTableComponent} from './schedule-screening-view-table/schedule-screening-view-table.component';
import {AddCrewComponent} from './crew/add-crew/add-crew.component';
import {AddMovieComponent} from './add-movie/add-movie.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {InterceptorModule} from './interceptor.module';
import { AllCrewViewComponent } from './crew/all-crew-view/all-crew-view.component';

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    AllMoviesViewComponent,
    ScheduleScreeningComponent,
    ScheduleScreeningViewTableComponent,
    AddCrewComponent,
    ScheduleScreeningViewTableComponent,
    AddMovieComponent,
    AllCrewViewComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AlertModule.forRoot(),
    BrowserAnimationsModule,
    MatListModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    FormsModule,
    HttpClientModule,
    InterceptorModule
  ],
  exports: [
    MatListModule
  ],
  providers: [HttpClientModule, InterceptorModule],
  bootstrap: [AppComponent]
})
export class AppModule {
}
