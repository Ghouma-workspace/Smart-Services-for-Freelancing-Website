import { Component, ElementRef, NgModule, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  @ViewChild('invisible') invisibleElement: ElementRef;

  constructor() { }

  ngOnInit(): void {
    const animateMeElements = document.querySelectorAll('.animate-me');
    animateMeElements.forEach(element => {
      element.classList.add('invisible');
    });
  }
  

}

export class HomeModule { }