import { Component, NgModule, OnInit } from '@angular/core';

@Component({
  selector: 'app-forum',
  templateUrl: './FreelancerDetail.component.html',
  styleUrls: ['./FreelancerDetail.component.scss']
})
export class FreelancerDetailComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}

@NgModule({
  declarations: [FreelancerDetailComponent],
  imports: [],
  exports: [FreelancerDetailComponent]
})
export class ForumModule { }