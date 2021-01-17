import {Component, ViewChild, OnInit, AfterViewInit, OnDestroy} from '@angular/core';

import {MatTable, MatTableDataSource} from '@angular/material/table';
import {MatDialog} from '@angular/material/dialog';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import BookModel from '../model/book.model';
import {DataSource} from '@angular/cdk/collections';
import {BehaviorSubject, Observable, Subscription} from 'rxjs';
import {BookService} from '../service/book.service';
import BookResponseModel from '../model/bookResponse.model';
import BookRequestModel from '../model/bookRequest.model';
import {UserService} from '../service/user.service';
import {Router} from '@angular/router';
import {BookDialogBoxComponent} from '../book-dialog-box/book-dialog-box.component';
import {RentedBookService} from '../service/rented-book.service';
import {LoadingService} from '../service/loading.service';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit, AfterViewInit, OnDestroy {

  subscriptions: Subscription[] = [];

  displayedColumns: string[];

  dataSource: MatTableDataSource<BookModel>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  @ViewChild(MatTable, {static: true}) table: MatTable<any>;

  constructor(public dialog: MatDialog, private bookService: BookService, public userService: UserService,
              private router: Router, private rentedBookService: RentedBookService, private loadingService: LoadingService) {
    this.dataSource = new MatTableDataSource<BookModel>();
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  ngOnInit(): void {
    const getDataSub = this.bookService.bookList.subscribe((data) => {
      this.dataSource.data = data;
    });

    // jwt
    const getJwtSub = this.userService.jwt.subscribe((jwt) => {
      if (jwt.userType === 0) { // Personel
        this.displayedColumns = ['bookId', 'title', 'author', 'count', 'edit', 'delete', 'history'];
      } else {
        this.displayedColumns = ['bookId', 'title', 'author', 'count', 'rent'];
      }
    });

    this.subscriptions.push(
      getDataSub, getJwtSub
    );

    this.refresh();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(x => x.unsubscribe());
  }

  openDialog(action, obj): void {
    obj.action = action;
    const dialogRef = this.dialog.open(BookDialogBoxComponent, {
      width: '250px',
      data: obj
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result.event === 'Add') {
        this.addRowData(result.data);
      } else if (result.event === 'Update') {
        this.updateRowData(result.data);
      } else if (result.event === 'Delete') {
        this.deleteRowData(result.data);
      } else if (result.event === 'Rent') {
        this.rentABook(result.data);
      }
    });
  }

  rentABook(rowObj): void {
    this.loadingService.startLoading();

    this.rentedBookService.createBookRent(rowObj.bookId).subscribe(() => {
      console.log('rentABook');
      this.loadingService.stopLoading();

      this.dataSource.data = this.dataSource.data.filter((value, key) => {
        if (value.bookId === rowObj.bookId) {
          value.count = rowObj.count - 1;
        }
        return true;
      });
    });
  }

  addRowData(rowObj): void {
    this.loadingService.startLoading();

    this.bookService.create(new BookRequestModel(
      {
        title: rowObj.title,
        author: rowObj.author,
        count: rowObj.count
      } as BookModel)).subscribe(() => {
      console.log('addRowData');
      this.loadingService.stopLoading();

      this.refresh();
    });
  }

  refresh(): void {
    this.bookService.getAll();
  }

  updateRowData(rowObj): void {
    this.loadingService.startLoading();

    this.bookService.update(new BookRequestModel(rowObj)).subscribe(() => {
      console.log('updated');
      this.loadingService.stopLoading();

      this.dataSource.data = this.dataSource.data.filter((value, key) => {
        if (value.bookId === rowObj.bookId) {
          value.title = rowObj.title;
          value.author = rowObj.author;
          value.count = rowObj.count;
        }
        return true;
      });
    });
  }

  deleteRowData(rowObj): void {
    this.loadingService.startLoading();

    this.bookService.delete(rowObj.bookId).subscribe(() => {
      console.log('deleted');
      this.loadingService.stopLoading();

      this.dataSource.data = this.dataSource.data.filter((value, key) => {
        return value.bookId !== rowObj.bookId;
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
    this.router.navigate(['./book-history', {id: rowObj.bookId}]);
  }
}
