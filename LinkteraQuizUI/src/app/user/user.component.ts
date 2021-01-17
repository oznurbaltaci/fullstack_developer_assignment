import {Component, ViewChild, OnInit, AfterViewInit, OnDestroy} from '@angular/core';

import {MatTable, MatTableDataSource} from '@angular/material/table';
import {MatDialog} from '@angular/material/dialog';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {Subscription} from 'rxjs';

import {UserService} from '../service/user.service';
import UserDTOModel from '../model/userDTO.model';
import {Router} from '@angular/router';
import {UserDialogBoxComponent} from '../user-dialog-box/user-dialog-box.component';
import {ToastrService} from "ngx-toastr";
import {LoadingService} from "../service/loading.service";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit, AfterViewInit, OnDestroy {

  subscriptions: Subscription[] = [];

  displayedColumns: string[] = ['firstName', 'lastName', 'username', 'userType', 'delete', 'history'];

  dataSource: MatTableDataSource<UserDTOModel>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  @ViewChild(MatTable, {static: true}) table: MatTable<any>;

  constructor(public dialog: MatDialog, private userService: UserService, private router: Router, private loadingService: LoadingService) {
    this.dataSource = new MatTableDataSource<UserDTOModel>();
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  ngOnInit(): void {
    const getDataSub = this.userService.userList.subscribe((data) => {
      this.dataSource.data = data;
      console.log('here');

    });

    this.subscriptions.push(
      getDataSub
    );

    this.refresh();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(x => x.unsubscribe());
  }

  refresh(): void {
    console.log(this.userService.jwt.getValue());

    this.userService.getAll();
  }

  openDialog(action, obj): void {
    obj.action = action;
    const dialogRef = this.dialog.open(UserDialogBoxComponent, {
      width: '250px',
      data: obj
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result.event === 'Delete') {
        this.deleteRowData(result.data);
      }
    });
  }

  deleteRowData(rowObj): void {
    this.loadingService.startLoading();

    this.userService.delete(rowObj.userId).subscribe(() => {
      console.log('deleted');
      this.loadingService.stopLoading();

      this.dataSource.data = this.dataSource.data.filter((value, key) => {
        return value.userId !== rowObj.userId;
      });
    });
  }

  applyFilter($event: KeyboardEvent): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  goTo(rowObj): void {
    this.router.navigate(['./user-history', {id: rowObj.userId }]);
  }
}
