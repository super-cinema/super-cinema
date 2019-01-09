import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AlertModule} from 'ngx-bootstrap';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NavBarComponent} from './nav-bar/nav-bar.component';
import {AllMoviesViewComponent} from './movie/all-movies-view/all-movies-view.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {
  MatDialogModule,
  MatListModule,
  MatPaginatorModule,
  MatSnackBarModule,
  MatSortModule,
  MatTableModule
} from '@angular/material';
import {ScheduleScreeningComponent} from './schedule/schedule-screening/schedule-screening.component';
import {ScheduleScreeningViewTableComponent} from './schedule/schedule-screening-view-table/schedule-screening-view-table.component';
import {AddCrewComponent} from './crew/add-crew/add-crew.component';
import {AddMovieComponent} from './movie/add-movie/add-movie.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {InterceptorModule} from './interceptor.module';
import {AllCrewViewComponent} from './crew/all-crew-view/all-crew-view.component';
import {EditCrewComponent} from './crew/edit-crew/edit-crew.component';
import {ConfirmDialogComponent} from './share/confirm-dialog/confirm-dialog.component';
import {EditMovieComponent} from './movie/edit-movie/edit-movie.component';
import {TransformPipe} from './share/transform.pipe';
import {CrewService} from './crew/servic-crew/crew-service';
import { AddCrewTestComponent } from './crew-testing/add-crew-test/add-crew-test.component';
import { AllCrewWievTestingComponent } from './crew-testing/all-crew-wiev-testing/all-crew-wiev-testing.component';
import { CrewDetailTestComponent } from './crew-testing/crew-detail-test/crew-detail-test.component';

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    AllMoviesViewComponent,
    ScheduleScreeningComponent,
    AddCrewComponent,
    ScheduleScreeningViewTableComponent,
    AddMovieComponent,
    EditMovieComponent,
    ConfirmDialogComponent,
    AllCrewViewComponent,
    EditCrewComponent,
    TransformPipe,
    AddCrewTestComponent,
    AllCrewWievTestingComponent,
    CrewDetailTestComponent
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
    MatSnackBarModule,
    FormsModule,
    ReactiveFormsModule
  ],
  exports: [
    MatListModule,
    MatDialogModule,
    MatSnackBarModule,
    TransformPipe
  ],
  providers: [CrewService],
  bootstrap: [AppComponent],
  entryComponents: [ConfirmDialogComponent]
})
export class AppModule {
}
