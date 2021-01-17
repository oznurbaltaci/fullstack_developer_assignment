import {Component, ViewChild, OnInit, AfterViewInit, OnDestroy} from '@angular/core';

import {MatTable, MatTableDataSource} from '@angular/material/table';
import {MatDialog} from '@angular/material/dialog';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {Subscription} from 'rxjs';
import RentBookDTOModel from '../model/rentBookDTO.model';
import {RentedBookService} from '../service/rented-book.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-book-history',
  templateUrl: './book-history.component.html',
  styleUrls: ['./book-history.component.css']
})
export class BookHistoryComponent implements OnInit, AfterViewInit, OnDestroy {

  id: string;
  subscriptions: Subscription[] = [];
  displayedColumns: string[] = ['title', 'author', 'firstName', 'lastName', 'startDate', 'endDate'];
  dataSource: MatTableDataSource<RentBookDTOModel>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  @ViewChild(MatTable, {static: true}) table: MatTable<any>;

  constructor(private route: ActivatedRoute, public dialog: MatDialog, private rentedBookService: RentedBookService) {
    this.dataSource = new MatTableDataSource<RentBookDTOModel>();
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  ngOnInit(): void {
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
    this.rentedBookService.getAllRentedBooksHistoryByBookId(this.id);
  }

  allRented(): void {
    this.rentedBookService.getAllRentedBooksByBookId(this.id);
  }

  applyFilter($event: KeyboardEvent): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
}
