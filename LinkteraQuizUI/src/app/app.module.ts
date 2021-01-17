import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {MatTableModule} from '@angular/material/table';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatDialogModule} from '@angular/material/dialog';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import {MatFormFieldModule} from '@angular/material/form-field';
import {CommonModule} from '@angular/common';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {BookService} from './service/book.service';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatSortModule} from '@angular/material/sort';
import {HeaderComponent} from './header/header.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BookComponent} from './book/book.component';
import {SignupComponent} from './signup/signup.component';
import {LoginComponent} from './login/login.component';
import {MatSelectModule} from '@angular/material/select';
import {MatCardModule} from '@angular/material/card';
import {UserService} from './service/user.service';
import {UserHistoryComponent} from './user-history/user-history.component';
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';
import {RentedBookService} from './service/rented-book.service';
import {BookHistoryComponent} from './book-history/book-history.component';
import {HistoryComponent} from './history/history.component';
import {UserComponent} from './user/user.component';
import {UserHistoryDialogBoxComponent} from './user-history-dialog-box/user-history-dialog-box.component';
import {BookDialogBoxComponent} from './book-dialog-box/book-dialog-box.component';
import {UserDialogBoxComponent} from './user-dialog-box/user-dialog-box.component';
import {HttpErrorInterceptor} from './Http-error.interceptor';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';
import {NavListComponent} from './nav-list/nav-list.component';
import {ToastrModule, ToastrService} from 'ngx-toastr';
import {LoadingComponent} from './loading/loading.component';
import {LoadingService} from './service/loading.service';


// import 'rxjs/add/operator/map';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    PageNotFoundComponent,

    SignupComponent,
    LoginComponent,
    UserComponent,
    BookComponent,

    HistoryComponent,
    UserHistoryComponent,
    BookHistoryComponent,

    UserHistoryDialogBoxComponent,
    BookDialogBoxComponent,
    UserDialogBoxComponent,
    NavListComponent,
    LoadingComponent,

  ],
  imports: [
    BrowserModule,
    CommonModule,
    AppRoutingModule,
    MatTableModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatToolbarModule,
    MatIconModule,
    HttpClientModule,
    MatPaginatorModule,
    MatSortModule,
    FormsModule,
    MatTableModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatFormFieldModule,
    FormsModule,
    MatSelectModule,
    MatCardModule,
    ReactiveFormsModule,
    MatSidenavModule,
    MatListModule,
    ToastrModule.forRoot({
      timeOut: 10000,
    }),


  ],
  exports: [],
  providers: [BookService, UserService, RentedBookService, LoadingService, ToastrService, {
    provide: HTTP_INTERCEPTORS,
    useClass: HttpErrorInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule {
}
