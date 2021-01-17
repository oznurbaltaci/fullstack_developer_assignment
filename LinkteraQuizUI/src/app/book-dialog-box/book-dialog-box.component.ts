import {Component, OnInit, Inject, Optional} from '@angular/core';
import {MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';

export interface UsersData {
  name: string;
  id: number;
}

@Component({
  selector: 'app-user-dialog-box',
  templateUrl: './book-dialog-box.component.html',
  styleUrls: ['./book-dialog-box.component.css']
})
export class BookDialogBoxComponent implements OnInit {

  action: string;
  local_data: any;

  constructor(public dialogRef: MatDialogRef<BookDialogBoxComponent>,
              @Optional() @Inject(MAT_DIALOG_DATA) public data: UsersData) {
    console.log(data);
    this.local_data = {...data};
    this.action = this.local_data.action;

  }

  doAction(): void {
    this.dialogRef.close({event: this.action, data: this.local_data});
  }

  closeDialog(): void {
    this.dialogRef.close({event: 'Cancel'});
  }

  ngOnInit(): void {
  }

}
