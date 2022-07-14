import { Component, OnInit } from '@angular/core';
import { TesterMatchingService } from './services';
import { BehaviorSubject, combineLatest, share, switchMap, tap } from 'rxjs';
import { Country } from './model/country.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'angular-app';

  devices$ = this.testerMatchingService.getDevices().pipe(share());
  testers$ = this.testerMatchingService.getTesters().pipe(share());
  selectedCountry = new BehaviorSubject<string[]>([]);
  selectedDevice = new BehaviorSubject<number>(0);
  countries: Country[] = [];

  constructor(private testerMatchingService: TesterMatchingService) {
  }

  selectedItems: any;
  dropdownSettings: any;

  ngOnInit() {

    this.testerMatchingService.getCountries().pipe(tap(countries => this.countries = countries)).subscribe();

    combineLatest(this.selectedCountry, this.selectedDevice)
    .pipe(
      switchMap((values) => {

        console.log(values[0]);
        console.log(values[1]);

        return this.testerMatchingService.getTesters();
      })
    ).subscribe()

    this.dropdownSettings = {
      singleSelection: false,
      idField: 'name',
      textField: 'name',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      itemsShowLimit: 3,
      allowSearchFilter: true
    };
  }

  onItemSelect(item: any) {
    this.selectedCountry.next([...this.selectedCountry.getValue(), item.name]);
  }

  onSelectAll(items: any[]) {
    if (items.length == 0) {
      this.selectedCountry.next([]);
    } else {
      this.selectedCountry.next(items.map(value => value.name));
    }
  }

  onDeselect(item: any) {
    this.selectedCountry.next([...this.selectedCountry.getValue().filter(value => value != item.name)]);
  }
}
