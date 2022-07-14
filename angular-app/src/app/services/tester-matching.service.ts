import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Country } from '../model/country.model';
import { Device } from '../model/device.model';
import { Tester } from '../model/tester.model';

@Injectable()
export class TesterMatchingService {
  constructor(private http: HttpClient,) {
  }

  getCountries(): Observable<Country[]> {
    const url = environment.testerMatchingUrl + '/countries';
    return this.http.get<Country[]>(url, {});
  }

  getDevices(): Observable<Device[]> {
    const url = environment.testerMatchingUrl + '/devices$';
    return this.http.get<Device[]>(url, {});
  }

  getTesters(): Observable<Tester[]> {
    const url = environment.testerMatchingUrl + '/testers';
    return this.http.get<Tester[]>(url, {});
  }
}
