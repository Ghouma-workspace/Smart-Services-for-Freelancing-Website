import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ProjectWorking } from '../shared/project-working';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProjectWorkingService {

  private baseUrl = "http://localhost:8080/work"

  constructor(private http: HttpClient){}

  getProjectList(): Observable<ProjectWorking[]> {
    return this.http.get<ProjectWorking[]>(`${this.baseUrl}`);
  }

  getProject(projectId : number): Observable<ProjectWorking> {
    console.log(projectId);
    return this.http.get<ProjectWorking>(`${this.baseUrl}/${projectId}`);
  }

  getMyprojects(username: string): Observable<ProjectWorking[]> {
    return this.http.get<ProjectWorking[]>(`${this.baseUrl}/user/${username}`);
  }
}
