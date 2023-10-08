import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Project } from '../shared/project';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  private baseUrl = 'http://localhost:8080';
  searchEvent: any;

  constructor(private http: HttpClient) { }

  

  searchProjectsByTitle(title: string): Observable<Project[]> {
    const url = `${this.baseUrl}/projects/search?title=${title}`;
    return this.http.get<Project[]>(url);
  }
}
