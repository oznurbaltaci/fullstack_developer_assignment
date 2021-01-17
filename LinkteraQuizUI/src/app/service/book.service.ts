import {Injectable} from '@angular/core';
import {BehaviorSubject, from, Observable} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {environment} from 'src/environments/environment';
import CreditRecourseModel from '../model/book.model';
import BookModel from '../model/book.model';
import BookResponseModel from '../model/bookResponse.model';
import BookRequestModel from '../model/bookRequest.model';
import {UserService} from './user.service';
import {BOOK_URL} from '../app.config';
import {LoadingService} from './loading.service';

@Injectable()
export class BookService {

  public header: HttpHeaders;
  public book: BehaviorSubject<BookModel> = new BehaviorSubject<BookModel>(null);
  public bookList: BehaviorSubject<BookModel[]> = new BehaviorSubject<BookModel[]>([]);

  constructor(private http: HttpClient, private userService: UserService, private loadingService: LoadingService) {
    // jwt sub
    this.userService.jwt.subscribe(
      (jwt) => this.header = new HttpHeaders(
        {Authorization: (jwt == null ? '' : jwt.token)})
    );
  }

  public get(bookId: string): void {
    this.loadingService.startLoading();

    this.http.get<BookResponseModel>(BOOK_URL + '/' + bookId,
      {headers: this.header})
      .subscribe(data => {
        this.book.next(data.book);
        console.log(data);

        this.loadingService.stopLoading();
      });
  }

  public getAll(): void {
    this.loadingService.startLoading();

    this.http.get<BookResponseModel>(BOOK_URL,
      {headers: this.header})
      .subscribe(data => {
        this.bookList.next(data.books);
        console.log(data);

        this.loadingService.stopLoading();
      });
  }

  public create(bookRequestModel: BookRequestModel): Observable<any> {
    return this.http.post(BOOK_URL, bookRequestModel,
      {headers: this.header}
    );
  }

  public update(bookRequestModel: BookRequestModel): Observable<any> {
    return this.http.put(BOOK_URL + '/' + bookRequestModel.book.bookId, bookRequestModel,
      {headers: this.header}
    );
  }

  public delete(bookId: string): Observable<any> {
    return this.http.delete(BOOK_URL + '/' + bookId, {headers: this.header});
  }

  public findAll(): Observable<BookModel[]> {
    return this.http.get<BookModel[]>(BOOK_URL, {headers: this.header});
  }
}
