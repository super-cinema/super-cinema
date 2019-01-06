import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatSort } from '@angular/material';
import { ScheduleScreeningViewTableDataSource } from './schedule-screening-view-table-datasource';

@Component({
  selector: 'app-schedule-screening-view-table',
  templateUrl: './schedule-screening-view-table.component.html',
  styleUrls: ['./schedule-screening-view-table.component.scss'],
})
export class ScheduleScreeningViewTableComponent implements OnInit {
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  dataSource: ScheduleScreeningViewTableDataSource;

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['id', 'name'];

  ngOnInit() {
    this.dataSource = new ScheduleScreeningViewTableDataSource(this.paginator, this.sort);
  }
}
