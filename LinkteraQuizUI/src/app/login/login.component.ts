import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import UserRegisterRequestModel from '../model/userRegisterRequest.model';
import {UserService} from '../service/user.service';
import {Subscription} from 'rxjs';
import UserLoginRequestModel from '../model/userLoginRequest.model';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  hide = true;
  form: FormGroup;
  userLoginRequestModel: UserLoginRequestModel;
  subscriptions: Subscription[] = [];

  constructor(private formBuilder: FormBuilder, private userService: UserService, private router: Router) {
    this.buildForm();
  }

  ngOnInit(): void {
  }

  login(): void {
    this.userLoginRequestModel = new UserLoginRequestModel();
    this.userLoginRequestModel.username = this.form.controls['username'].value;
    this.userLoginRequestModel.password = this.form.controls['password'].value;
    this.subscriptions.push(
      this.userService.login(this.userLoginRequestModel).subscribe(() => {
        this.router.navigate(['./books']);
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
