import {Component, ElementRef, OnInit, ViewChild, ViewChildren} from '@angular/core';
import {GraphService} from './graph.service';

@Component({
  selector: 'app-rates-graph-page',
  templateUrl: './rates-graph-page.component.html',
  styleUrls: ['./rates-graph-page.component.css']
})
export class RatesGraphPageComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
