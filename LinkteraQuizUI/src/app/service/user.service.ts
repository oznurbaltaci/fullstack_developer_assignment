import {AfterViewInit, Injectable, ViewChild} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import BookModel from '../model/book.model';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {environment} from '../../environments/environment';
import BookResponseModel from '../model/bookResponse.model';
import BookRequestModel from '../model/bookRequest.model';
import UserRegisterRequestModel from '../model/userRegisterRequest.model';
import UserLoginRequestModel from '../model/userLoginRequest.model';
import TokenModel from '../model/token.model';
import {AUTH_URL, BOOK_URL, USER_URL} from '../app.config';
import {map} from 'rxjs/operators';
import UserDTOModel from '../model/userDTO.model';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {LoadingService} from './loading.service';

@Injectable()
export class UserService {

  public jwt: BehaviorSubject<TokenModel> = new BehaviorSubject<TokenModel>(null);
  public user: BehaviorSubject<UserDTOModel> = new BehaviorSubject<UserDTOModel>(null);
  public userList: BehaviorSubject<UserDTOModel[]> = new BehaviorSubject<UserDTOModel[]>([]);
  public header: HttpHeaders;

  constructor(private http: HttpClient, private router: Router, private toasterService: ToastrService,
              private loadingService: LoadingService) {
    console.log(1);
    const storageJwt = JSON.parse(localStorage.getItem('token'));

    if (storageJwt === null) {
      this.router.navigate(['./login']);
    } else {
      this.jwt.next(storageJwt);
    }

    // jwt sub
    this.jwt.subscribe(
      (jwt) => this.header = new HttpHeaders(
        {Authorization: (jwt == null ? '' : jwt.token)})
    );
  }

  public get(userId: string): void {
    this.http.get<UserDTOModel>(USER_URL + '/' + userId,
      {headers: this.header}
    ).subscribe(data => {
      this.user.next(data);

      console.log(data);
    });
  }

  public getAll(): void {
    this.http.get<UserDTOModel[]>(USER_URL,
      {headers: this.header})
      .subscribe(data => {
        this.userList.next(data);

        console.log(data);
      });
  }

  public delete(userId: string): Observable<any> {
    return this.http.delete<UserDTOModel>(USER_URL + '/' + userId,
      {headers: this.header}
    );
  }

  public create(userRegisterRequestModel: UserRegisterRequestModel): Observable<any> {
    return this.http.post(AUTH_URL + '/register', userRegisterRequestModel);
  }

  public login(userLoginRequestModel: UserLoginRequestModel): Observable<TokenModel> {
    this.loadingService.startLoading();

    return this.http.post<TokenModel>(AUTH_URL + '/login', userLoginRequestModel)
      .pipe(map(
        (response: TokenModel) => {
          console.log(response);
          localStorage.setItem('token', JSON.stringify(response));
          this.jwt.next(response);

          this.loadingService.stopLoading();
          this.toasterService.success('Giris yaptiniz.', '', {positionClass: 'toast-bottom-right', enableHtml: true});

          return response;
        })
      );
  }

  public logout(): void {
    this.loadingService.startLoading();

    this.router.navigate(['./login']).then(() => {
      this.jwt.next(null);
      localStorage.removeItem('token');

      this.loadingService.stopLoading();
      this.toasterService.success('Cikis yaptiniz.', '', {positionClass: 'toast-bottom-right', enableHtml: true});
    });
  }
}
