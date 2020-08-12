import {Component, ElementRef, OnInit, ViewChild, ViewChildren} from '@angular/core';
import {GraphService} from './graph.service';

@Component({
  selector: 'app-rates-graph-page',
  templateUrl: './rates-graph-page.component.html',
  styleUrls: ['./rates-graph-page.component.css']
})
export class RatesGraphPageComponent implements OnInit {

  @ViewChildren('rateGraph') rateGraph: ElementRef

  constructor(private graphService: GraphService) { }

  ngOnInit(): void {
    this.graphService.cleanObservable.subscribe(e => {this.cleanGraph()})
  }

  cleanGraph(): void {
    const childElements = this.rateGraph.nativeElement.children;
    debugger
    for (let child of childElements) {
      this.rateGraph.nativeElement.removeChild(this.rateGraph.nativeElement, child);
    }
  }
}
