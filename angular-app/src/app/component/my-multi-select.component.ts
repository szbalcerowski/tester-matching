import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { BehaviorSubject, Subject, takeUntil, tap } from 'rxjs';

@Component({
  selector: 'my-multiselect',
  templateUrl: './my-multi-select.component.html',
})
export class MyMultiSelectComponent implements OnInit, OnDestroy {

  ngOnInit() {
    this.results.pipe(
      tap(value => this.result.emit(value),
        takeUntil(this._destroy$)
      )
    ).subscribe();
  }

  @Input()
  label: string;

  @Input()
  placeholder: string;

  @Input()
  data: any[];

  @Output()
  result = new EventEmitter<any>()

  selectedItems: any;
  @Input()
  dropdownSettings: any;

  results = new BehaviorSubject<any[]>([]);

  private _destroy$ = new Subject<void>();

  onItemSelect(item: any) {
    this.results.next([...this.results.getValue(), item[this.dropdownSettings.idField]]);
  }

  onSelectAll(items: any[]) {
    if (items.length == 0) {
      this.results.next([]);
    } else {
      this.results.next(items.map(value => value[this.dropdownSettings.idField]));
    }
  }

  onDeselect(item: any) {
    this.results.next([...this.results.getValue().filter(value => value != item[this.dropdownSettings.idField])]);
  }

  ngOnDestroy() {
    this._destroy$.next();
  }

}
