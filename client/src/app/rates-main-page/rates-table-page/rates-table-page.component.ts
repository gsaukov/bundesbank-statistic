import {Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator, PageEvent} from '@angular/material/paginator';
import {DataService} from '../../shared/data.service';

@Component({
  selector: 'app-rates-table-page',
  templateUrl: './rates-table-page.component.html',
  styleUrls: ['./rates-table-page.component.scss']
})

export class RatesTablePageComponent implements OnInit {

  displayedColumns: string[] = ['CURRENCY', 'DATE', 'RATE', 'DIFF'];
  lowValue = 0;
  highValue = 20;

  constructor(public dataService: DataService) { }

  ngOnInit(): void {
  }

  public getPaginatorData(event: PageEvent): PageEvent {
    this.lowValue = event.pageIndex * event.pageSize;
    this.highValue = this.lowValue + event.pageSize;
    return event;
  }


}
