import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { SignupRequestPayload } from './SignupRequestPayload';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  
  signupRequestPayload: SignupRequestPayload;
  signupForm: FormGroup;
  
  constructor(private authService: AuthService, private router: Router,
    private toastr: ToastrService) {
    this.signupRequestPayload = {
      username: '',
      email: '',
      password: '',
      firstName: '',
      lastName: '',
      gender: '',
      age: 0
    };
  }
  ngOnInit() {
    this.signupForm = new FormGroup({
      username: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', Validators.required),
      firstName: new FormControl('', Validators.required),
      lastName: new FormControl('', Validators.required),
      gender: new FormControl('', Validators.required),
      age: new FormControl('', Validators.required),
    });
  }

  signup() {
    this.signupRequestPayload.email = this.signupForm.get('email').value;
    this.signupRequestPayload.username = this.signupForm.get('username').value;
    this.signupRequestPayload.password = this.signupForm.get('password').value;
    this.signupRequestPayload.firstName = this.signupForm.get('firstName').value;
    this.signupRequestPayload.lastName = this.signupForm.get('lastName').value;
    this.signupRequestPayload.age = this.signupForm.get('age').value;
    this.signupRequestPayload.gender = this.signupForm.get('gender').value;

    console.log(this.signupRequestPayload);
    this.authService.test();

    this.authService.signup(this.signupRequestPayload)
      .subscribe({
        next: data => {
        this.router.navigate(['/login'],
          { queryParams: { registered: 'true' } });
        },
        error: error => {
        console.log(error);
        this.toastr.error('Registration Failed! Please try again');
      }
      });
  }
}
