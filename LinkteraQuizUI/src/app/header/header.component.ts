import {Component, OnInit, Output, EventEmitter} from '@angular/core';
import {UserService} from '../service/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  @Output() public sidenavToggle = new EventEmitter();

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
  }

  logOut(): void {
    this.userService.logout();
  }

  public onToggleSidenav = () => {
    this.sidenavToggle.emit();
  }
}

