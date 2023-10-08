import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Freelancer } from '../shared/freelancer';

@Injectable({
  providedIn: 'root'
})
export class FreelancerService {

  private baseUrl = "http://localhost:8080/auth";

  constructor(private http: HttpClient) { }

  getAllFreelancers(): Observable<Freelancer[]>{
    return this.http.get<Freelancer[]>(`${this.baseUrl}/freelancers`);
  }
}
