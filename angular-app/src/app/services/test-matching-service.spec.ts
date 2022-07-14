import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TesterMatchingService } from './tester-matching.service';

describe('TesterMatchingService', () => {


  let service: TesterMatchingService;
  let httpTestingController: HttpTestingController;


  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [TesterMatchingService]
    });
    service = TestBed.inject(TesterMatchingService);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpTestingController.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
    expect(service.getDevices).toBeTruthy();
    expect(service.getTesters).toBeTruthy();
    expect(service.getCountries).toBeTruthy();
  });
});
