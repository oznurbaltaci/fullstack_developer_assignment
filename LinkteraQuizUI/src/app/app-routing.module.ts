import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {BookComponent} from './book/book.component';
import {SignupComponent} from './signup/signup.component';
import {LoginComponent} from './login/login.component';
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';
import {UserHistoryComponent} from './user-history/user-history.component';
import {UserComponent} from './user/user.component';
import {BookHistoryComponent} from './book-history/book-history.component';
import {HistoryComponent} from './history/history.component';

const routes: Routes = [

  {
    path: 'books',
    component: BookComponent,
  },
  {
    path: 'signUp',
    component: SignupComponent,
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'user-history',
    component: UserHistoryComponent,
  },
  {
    path: 'book-history',
    component: BookHistoryComponent,
  },
  {
    path: 'users',
    component: UserComponent,
  },
  {
    path: 'history',
    component: HistoryComponent,
  },

  {path: '**', component: PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
