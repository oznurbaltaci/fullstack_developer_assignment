import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UserService} from '../service/user.service';
import BookRequestModel from '../model/bookRequest.model';
import BookModel from '../model/book.model';
import UserRegisterRequestModel from '../model/userRegisterRequest.model';
import {Subscription} from 'rxjs';
import {Router} from '@angular/router';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  hide = true;
  form: FormGroup;
  userRegisterRequestModel: UserRegisterRequestModel;
  subscriptions: Subscription[] = [];


  constructor(private formBuilder: FormBuilder, private userService: UserService, private router: Router) {
    this.buildForm();

  }

  ngOnInit(): void {
  }

//   this.bookService.create(new BookRequestModel(
//     {
//       title: rowObj.title,
//       author: rowObj.author,
//       count: rowObj.count
//     } as BookModel)).subscribe(() => {
//   console.log('addRowData');
// });

  signUp(): void {
    this.userRegisterRequestModel = new UserRegisterRequestModel();
    this.userRegisterRequestModel.username = this.form.controls['username'].value;
    this.userRegisterRequestModel.firstName = this.form.controls['name'].value;
    this.userRegisterRequestModel.lastName = this.form.controls['surname'].value;
    this.userRegisterRequestModel.password = this.form.controls['password'].value;
    this.userRegisterRequestModel.userType = this.form.controls['userType'].value;
    this.subscriptions.push(
      this.userService.create(this.userRegisterRequestModel).subscribe(() => {
        console.log('kayit olundu');
        this.router.navigate(['./login']);
      }));
  }

  buildForm(): void {
    this.form = this.formBuilder.group({
      username: ['', Validators.required],
      name: ['', Validators.required],
      surname: ['', Validators.required],
      password: ['', Validators.required],
      userType: ['', Validators.required],
    });
  }
}
