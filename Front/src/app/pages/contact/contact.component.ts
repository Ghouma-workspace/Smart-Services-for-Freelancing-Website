import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.scss']
})
export class ContactComponent implements OnInit {
  name: string = "moez";
  email: string = "hama@gmail.com";

  constructor() { }

  ngOnInit(): void {
  }
  onSubmit() {
    if (this.name && this.email) {
      alert('Thank you, ' + this.name + '! We will contact you at ' + this.email + ' shortly.');
    } else {
      alert('Please enter your name and email address.');
    }
  }

}
