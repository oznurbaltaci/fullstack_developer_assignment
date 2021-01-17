import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import BookModel from '../model/book.model';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {UserService} from './user.service';
import {environment} from '../../environments/environment';
import {
  BOOK_RENTED_CURRENT_URL,
  CURRENT_RENTED_BOOK_URL,
  RENT_URL, RENTED_BOOK_HISTORY_URL,
  RENTED_HISTORY_BOOK_URL,
  USER_RENTED_BOOK_HISTORY_URL,
  USER_RENTED_CURRENT_BOOK_URL
} from '../app.config';

import {Router} from '@angular/router';
import RentedBookResponseModel from '../model/rentedBookResponse.model';
import RentedBookDTOResponseModel from '../model/rentedBookDTOResponse.model';
import RentBookDTOModel from '../model/rentBookDTO.model';
import {LoadingService} from './loading.service';

@Injectable()
export class RentedBookService {
  public rentedBookDTO: BehaviorSubject<RentBookDTOModel> = new BehaviorSubject<RentBookDTOModel>(null);
  public rentedBookDTOList: BehaviorSubject<RentBookDTOModel[]> = new BehaviorSubject<RentBookDTOModel[]>([]);
  public header: HttpHeaders;

  constructor(private router: Router, private http: HttpClient, private userService: UserService, private loadingService: LoadingService) {
    // jwt sub
    this.userService.jwt.subscribe(
      (jwt) => this.header = new HttpHeaders(
        {Authorization: (jwt == null ? '' : jwt.token)})
    );
  }

  public createBookRent(bookId: string): Observable<any> {
    return this.http.post(RENT_URL + '/' + bookId, null, {headers: this.header});
  }

  public returnBook(rentId: string): Observable<any> {
    return this.http.delete(RENT_URL + '/' + rentId, {headers: this.header});
  }

  public getAllRentedBooks(): void {
    this.loadingService.startLoading();

    this.http.get<RentedBookDTOResponseModel>(RENTED_HISTORY_BOOK_URL, {headers: this.header})
      .subscribe(data => {
        this.rentedBookDTOList.next(data.books);

        this.loadingService.stopLoading();
        console.log('getAllRentedBooks');
        console.log(data);
      });
  }

  public getCurrentRentedBooks(): void {
    this.loadingService.startLoading();

    this.http.get<RentedBookDTOResponseModel>(CURRENT_RENTED_BOOK_URL, {headers: this.header})
      .subscribe(data => {
        this.rentedBookDTOList.next(data.books);

        this.loadingService.stopLoading();
        console.log('getCurrentRentedBooks');
        console.log(data);
      });
  }

  public getAllRentedBooksHistoryByUserId(userId: string): void {
    userId = userId ?? '';
    this.loadingService.startLoading();

    this.http.get<RentedBookDTOResponseModel>(USER_RENTED_BOOK_HISTORY_URL + '/' + userId, {headers: this.header})
      .subscribe(data => {
        this.rentedBookDTOList.next(data.books);

        this.loadingService.stopLoading();
        console.log('getAllRentedBooksHistoryByUserId');
        console.log(data);
      });
  }

  public getAllRentedBooksByUserId(userId: string): void {
    userId = userId ?? '';
    this.loadingService.startLoading();

    this.http.get<RentedBookDTOResponseModel>(USER_RENTED_CURRENT_BOOK_URL + '/' + userId, {headers: this.header})
      .subscribe(data => {
        this.rentedBookDTOList.next(data.books);
        this.loadingService.stopLoading();

        console.log('getAllRentedBooksByUserId');
        console.log(data);
      });
  }

  public getAllRentedBooksHistoryByBookId(bookId: string): void {
    this.loadingService.startLoading();

    this.http.get<RentedBookDTOResponseModel>(RENTED_BOOK_HISTORY_URL + '/' + bookId, {headers: this.header})
      .subscribe(data => {
        this.rentedBookDTOList.next(data.books);
        this.loadingService.stopLoading();

        console.log('getAllRentedBooks');
        console.log(data);
      });
  }

  public getAllRentedBooksByBookId(bookId: string): void {
    this.loadingService.startLoading();

    this.http.get<RentedBookDTOResponseModel>(BOOK_RENTED_CURRENT_URL + '/' + bookId, {headers: this.header})
      .subscribe(data => {
        this.rentedBookDTOList.next(data.books);
        this.loadingService.stopLoading();

        console.log('getAllRentedBooks');
        console.log(data);
      });
  }
}
