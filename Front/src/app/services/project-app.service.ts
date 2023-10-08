import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ProjectApp } from '../shared/projectApp';

@Injectable({
  providedIn: 'root'
})
export class ProjectAppService {

  private baseUrl = "http://localhost:8080/apply"

  constructor(private http: HttpClient){}

  getProjectList(): Observable<ProjectApp[]> {
    return this.http.get<ProjectApp[]>(`${this.baseUrl}`);
  }

  updateProject(projectId: number, projectApp: ProjectApp): Observable<ProjectApp> {
    return this.http.put<ProjectApp>(`${this.baseUrl}/update/${projectId}`, projectApp);
  }

  deleteProject(projectId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/delete/${projectId}`);
  }

  getProject(projectId : number): Observable<ProjectApp> {
    return this.http.get<ProjectApp>(`${this.baseUrl}/${projectId}`);
  }

  getMyprojects(username: string): Observable<ProjectApp[]> {
    return this.http.get<ProjectApp[]>(`${this.baseUrl}/user/${username}`);
  }

  postulate(username: String, projectApp: ProjectApp): void {
    this.http.post<ProjectApp>(`${this.baseUrl}/postulate/${username}`, projectApp, { observe: 'response' })
      .subscribe((response) => {
        if (response.status === 200) {
          console.log("successful");
        } else {
          console.log("unsuccessful");
        }
      }
    );
  }

  cancel(username: String, projectApp: ProjectApp): void {
    this.http.post<ProjectApp>(`${this.baseUrl}/cancel/${username}`, projectApp, { observe: 'response' })
      .subscribe((response) => {
        if (response.status === 200) {
          console.log("successful");
        } else {
          console.log("unsuccessful");
        }
      }
    );
  }

  isPostulated(projectId: number, username: string): Observable<boolean> {
    return this.http.get<boolean>(`${this.baseUrl}/checkpostulation/${projectId}/${username}`);
  }
}
