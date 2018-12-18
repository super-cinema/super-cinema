import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {AlertModule} from 'ngx-bootstrap';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { AllMoviesViewComponent } from './all-movies-view/all-movies-view.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatListModule, MatTableModule, MatPaginatorModule, MatSortModule} from '@angular/material';
import { ScheduleScreeningComponent } from './schedule-screening/schedule-screening.component';
import { ScheduleScreeningViewTableComponent } from './schedule-screening-view-table/schedule-screening-view-table.component';
import { AddCrewComponent } from './add-crew/add-crew.component';

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    AllMoviesViewComponent,
    ScheduleScreeningComponent,
    ScheduleScreeningViewTableComponent,
    AddCrewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AlertModule.forRoot(),
    BrowserAnimationsModule,
    MatListModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule
  ],
  exports: [
    MatListModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
