import {Component, ViewChild, OnInit, AfterViewInit, OnDestroy} from '@angular/core';

import {MatTable, MatTableDataSource} from '@angular/material/table';
import {MatDialog} from '@angular/material/dialog';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {Subscription} from 'rxjs';

import RentBookDTOModel from '../model/rentBookDTO.model';
import {RentedBookService} from '../service/rented-book.service';
import {ActivatedRoute} from '@angular/router';
import {UserHistoryDialogBoxComponent} from '../user-history-dialog-box/user-history-dialog-box.component';
import {UserService} from '../service/user.service';
import BookRequestModel from '../model/bookRequest.model';

@Component({
  selector: 'app-user-history',
  templateUrl: './user-history.component.html',
  styleUrls: ['./user-history.component.css']
})
export class UserHistoryComponent implements OnInit, AfterViewInit, OnDestroy {

  showButton: boolean;
  id: string;
  subscriptions: Subscription[] = [];
  displayedColumns: string[] = ['title', 'author', 'firstName', 'lastName', 'startDate', 'endDate', 'return'];
  dataSource: MatTableDataSource<RentBookDTOModel>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  @ViewChild(MatTable, {static: true}) table: MatTable<any>;

  constructor(private route: ActivatedRoute, public dialog: MatDialog, private rentedBookService: RentedBookService,
              private userService: UserService) {
    this.dataSource = new MatTableDataSource<RentBookDTOModel>();
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  ngOnInit(): void {
    console.log(1);
    // get id
    this.route.params.subscribe(params => {
      this.id = params.id;
    });

    // connect to datasource
    const getDataSub = this.rentedBookService.rentedBookDTOList.subscribe((data) => {
      this.dataSource.data = data;
    });

    // then sub
    this.subscriptions.push(
      getDataSub
    );

    this.allRented();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(x => x.unsubscribe());
  }

  historyRented(): void {
    this.rentedBookService.getAllRentedBooksHistoryByUserId(this.id);
  }

  allRented(): void {
    this.rentedBookService.getAllRentedBooksByUserId(this.id);
  }

  returnABook(rowObj): void {

    this.rentedBookService.returnBook(rowObj.rentId).subscribe(() => {
      console.log('updated');

      this.dataSource.data = this.dataSource.data.filter((value, key) => {
        if (value.rentId === rowObj.rentId) {
          value.endDate =  new Date();
        }
        return true;
      });
    });
    // this.rentedBookService.returnBook(rowObj.rentId).subscribe(() => {
    //   this.dataSource.data = this.dataSource.data.filter((value, key) => {
    //     return value.rentId !== rowObj.rentId;
    //   });
    // });
  }

  openDialog(action, obj): void {
    obj.action = action;
    const dialogRef = this.dialog.open(UserHistoryDialogBoxComponent, {
      width: '250px',
      data: obj
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result.event === 'Return') {
        this.returnABook(result.data);
      }
    });
  }

  applyFilter($event: KeyboardEvent): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
}
