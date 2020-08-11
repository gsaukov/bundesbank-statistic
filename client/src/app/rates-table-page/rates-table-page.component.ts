import {Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator, PageEvent} from '@angular/material/paginator';
import {DataService} from '../shared/data.service';

@Component({
  selector: 'app-rates-table-page',
  templateUrl: './rates-table-page.component.html',
  styleUrls: ['./rates-table-page.component.scss']
})

export class RatesTablePageComponent implements OnInit {

  displayedColumns: string[] = ['CURRENCY', 'DATE', 'RATE', 'DIFF'];
  public dataSource;
  lowValue = 0;
  highValue = 20;

  constructor(private dataService: DataService) { }

  ngOnInit(): void {
    this.dataSource = this.dataService.getData("USD")
  }

  public getPaginatorData(event: PageEvent): PageEvent {
    this.lowValue = event.pageIndex * event.pageSize;
    this.highValue = this.lowValue + event.pageSize;
    return event;
  }

}
