import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AlertModule} from 'ngx-bootstrap';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NavBarComponent} from './nav-bar/nav-bar.component';
import {AllMoviesViewComponent} from './all-movies-view/all-movies-view.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {
  MatDialogModule,
  MatListModule,
  MatPaginatorModule,
  MatSnackBarModule,
  MatSortModule,
  MatTableModule
} from '@angular/material';
import {ScheduleScreeningComponent} from './schedule-screening/schedule-screening.component';
import {ScheduleScreeningViewTableComponent} from './schedule-screening-view-table/schedule-screening-view-table.component';
import {AddCrewComponent} from './crew/add-crew/add-crew.component';
import {AddMovieComponent} from './add-movie/add-movie.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {InterceptorModule} from './interceptor.module';
import {AllCrewViewComponent} from './crew/all-crew-view/all-crew-view.component';
import {EditCrewComponent} from './crew/edit-crew/edit-crew.component';
import {ConfirmDialogComponent} from './confirm-dialog/confirm-dialog.component';

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
    EditMovieComponent,
    ConfirmDialogComponent
    AddMovieComponent,
    AllCrewViewComponent,
    EditCrewComponent,
    ConfirmDialogComponent
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
    InterceptorModule,
    MatDialogModule,
    MatSnackBarModule
  ],
  exports: [
    MatListModule,
    MatDialogModule,
    MatSnackBarModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents: [ConfirmDialogComponent]
})
export class AppModule { }
