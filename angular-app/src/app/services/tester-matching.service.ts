import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Country } from '../model/country.model';
import { Device } from '../model/device.model';
import { TestersResponse } from '../model/testers-response.model';

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

  getTesters(countries: string[], devices: number[], page: number, rowsPerPage: number): Observable<TestersResponse> {
    const url = environment.testerMatchingUrl + '/testers';
    return this.http.get<TestersResponse>(this.buildSearchUrl(url, countries, devices, page - 1, rowsPerPage), {});
  }

  private buildSearchUrl(baseUrl: string, countries: string[], devices: number[], page: number, rowsPerPage: number) {
    let url = `${baseUrl}?`;
    if (countries.length) {
      url += `countries=${countries}&`;
    }
    if (devices.length) {
      url += `devices=${devices}&`;
    }
    url += `page=${page}&`;
    url += `limit=${rowsPerPage}`;
    return url;
  }
}
