import { Component, Input, OnDestroy } from '@angular/core';

@Component({
  selector: 'my-multiselect',
  templateUrl: './my-multi-select.component.html',
})
export class MyMultiSelectComponent implements OnDestroy {


  @Input()
  label: string;

}
