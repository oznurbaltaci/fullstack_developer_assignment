import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {UserService} from '../service/user.service';

@Component({
  selector: 'app-nav-list',
  templateUrl: './nav-list.component.html',
  styleUrls: ['./nav-list.component.css']
})
export class NavListComponent implements OnInit {

  @Output() public sidenavToggle = new EventEmitter();

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
  }

  public onToggleSidenav = () => {
    this.sidenavToggle.emit();
  };

  isEquals(userType: number): boolean {
    return this.userService.jwt.getValue() === null ? false : (this.userService.jwt.getValue().userType === userType);

  }
}
