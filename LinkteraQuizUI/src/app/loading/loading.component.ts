import {Component, OnInit} from '@angular/core';
import {LoadingService} from '../service/loading.service';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-loading',
  templateUrl: './loading.component.html',
  styleUrls: ['./loading.component.css']
})
export class LoadingComponent implements OnInit {

  public loading: boolean = true;

  constructor(private loadingService: LoadingService) {
    this.loadingService.loadingStatus.subscribe((b) => this.loading = b);
  }

  ngOnInit(): void {
  }

}
