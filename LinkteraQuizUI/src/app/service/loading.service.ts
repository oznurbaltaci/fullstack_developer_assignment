import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable, Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoadingService {

  public loadingStatus: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  constructor() {
  }

  startLoading(): void {
    this.loadingStatus.next(true);
  }

  stopLoading(): void {
    this.loadingStatus.next(false);
  }
}
