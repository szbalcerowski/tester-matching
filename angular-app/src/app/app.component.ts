import { Component, OnDestroy, OnInit } from '@angular/core';
import { TesterMatchingService } from './services';
import {
  BehaviorSubject,
  combineLatest,
  distinctUntilChanged,
  map,
  Observable,
  of,
  ReplaySubject,
  startWith,
  Subject,
  switchMap,
  takeUntil,
  tap
} from 'rxjs';
import { Country } from './model/country.model';
import { Device } from './model/device.model';
import { Tester } from './model/tester.model';
import { TestersResponse } from './model/testers-response.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, OnDestroy {

  rowsPerPage = 5;

  pageNumber = 1;
  total = 6;
  testers$: Observable<Tester[]> = of([]);
  selectedCountry$ = new BehaviorSubject<string[]>([]);
  selectedDevice$ = new BehaviorSubject<number[]>([]);
  countries: Country[] = [];
  devices: Device[] = [];

  pageSubject$: ReplaySubject<number> = new ReplaySubject(1);
  pageChanged$: Observable<number>;

  countriesDropdownSettings = {
    singleSelection: false,
    idField: 'name',
    textField: 'name',
    selectAllText: 'Select All',
    unSelectAllText: 'UnSelect All',
    itemsShowLimit: 3,
    allowSearchFilter: true
  }

  devicesDropdownSettings = {
    singleSelection: false,
    idField: 'id',
    textField: 'description',
    selectAllText: 'Select All',
    unSelectAllText: 'UnSelect All',
    itemsShowLimit: 3,
    allowSearchFilter: true
  }
  private _destroy$ = new Subject<void>();

  constructor(private testerMatchingService: TesterMatchingService) {
  }

  ngOnInit() {

    this.testerMatchingService.getCountries().pipe(tap(countries => this.countries = countries), takeUntil(this._destroy$)).subscribe();
    this.testerMatchingService.getDevices().pipe(tap(devices => this.devices = devices), takeUntil(this._destroy$)).subscribe();

    this.pageChanged$ = this.pageSubject$.pipe(
      distinctUntilChanged(),
      startWith(this.pageNumber)
    );

    this.testers$ = combineLatest(this.selectedCountry$, this.selectedDevice$, this.pageChanged$)
    .pipe(
      switchMap((values) => {
        this.pageNumber = values[2];
        return this.testerMatchingService.getTesters(values[0], values[1], this.pageNumber, this.rowsPerPage);
      }),
      tap((response: TestersResponse) => this.total = response.size),
      map((response: TestersResponse) => response.testers)
    )
    ;

  }

  onPageChange(pageNumber: number) {
    this.pageSubject$.next(pageNumber);
  }

  ngOnDestroy() {
    this._destroy$.next();
  }
}
