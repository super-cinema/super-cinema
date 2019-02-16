import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AlertModule} from 'ngx-bootstrap';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NavBarComponent} from './nav-bar/nav-bar.component';
import {AllMoviesViewComponent} from './movie/all-movies-view/all-movies-view.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {
    MatCardModule,
    MatDialogModule,
    MatIconModule,
    MatListModule,
    MatPaginatorModule,
    MatSnackBarModule,
    MatSortModule,
    MatTableModule,
    MatToolbarModule
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
import {CrewService} from './services/crew-service/crew-service';
import {CrewDetailComponent} from './crew/crew-detail/crew-detail.component';
import {MovieService} from './services/movie-service/movie-service';
import {CrewInMovieComponent} from './crew/crew-in-movie/crew-in-movie.component';
import {CrewInMovieService} from './services/crew-in-movie-service/crew-in-movie.service';
import {SortNamePipe} from './share/sort-name.pipe';

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
        CrewDetailComponent,
        CrewInMovieComponent,
        SortNamePipe
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        AlertModule.forRoot(),
        BrowserAnimationsModule,
        FormsModule,
        HttpClientModule,
        InterceptorModule,
        MatListModule,
        MatTableModule,
        MatPaginatorModule,
        MatSortModule,
        MatDialogModule,
        MatCardModule,
        MatSnackBarModule,
        MatToolbarModule,
        MatIconModule,
        ReactiveFormsModule,
        BrowserAnimationsModule,
    ],
    exports: [
        MatListModule,
        MatDialogModule,
        MatSnackBarModule,
        TransformPipe,
    ],
    providers: [CrewService, MovieService, CrewInMovieService],
    bootstrap: [AppComponent],
    entryComponents: [ConfirmDialogComponent, CrewInMovieComponent]
})
export class AppModule {
}
