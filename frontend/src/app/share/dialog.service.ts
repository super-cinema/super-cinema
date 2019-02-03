import { Injectable } from '@angular/core';
import {MatDialog} from '@angular/material';
import {ConfirmDialogComponent} from './confirm-dialog/confirm-dialog.component';


@Injectable({
  providedIn: 'root'
})
export class DialogService {

  constructor(private dialog: MatDialog) { }

  openConfirmDialog(msg: string) {
    return this.dialog.open(ConfirmDialogComponent, {
      width: '400px',
      height: '150px',
      data: {
        message: msg
      }
    });
  }
}
