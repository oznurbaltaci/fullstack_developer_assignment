import BookModel from './book.model';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';

export default class BookRequestModel {
  constructor(public book: BookModel) {
  }
}
