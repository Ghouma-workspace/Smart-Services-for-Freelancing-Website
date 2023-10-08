import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Project } from '../shared/project';

import { map } from 'rxjs/operators';
import { Reclist } from '../shared/recommanded';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  private baseUrl = "http://localhost:8080"

  constructor(private http: HttpClient){}

  getProjectList(): Observable<Project[]> {
    return this.http.get<Project[]>(`${this.baseUrl}/projects`);
  }

  addProject(project: Project): Observable<Project> {
    return this.http.post<Project>(`${this.baseUrl}/projects`, project);
  }

  updateProject(project: Project): Observable<Project> {
    return this.http.put<Project>(`${this.baseUrl}/projects`, project);
  }

  deleteProject(projectId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/projects/delete/${projectId}`);
  }

  getProject(projectId : number): Observable<Project> {
    return this.http.get<Project>(`${this.baseUrl}/projects/${projectId}`);
  }

  getRecommandation(projectTitle: string) : Observable<Reclist> {
    return this.http.get<Reclist>(`http://localhost:5000/recommendations?project=${projectTitle}`);
  }
  
  getAllProjectRec() : Observable<Project[]> {
    return this.http.get<Project[]>(`http://localhost:8080/projectsrec`);
  }

  getProjectRec(id: number) : Observable<Project> {
    return this.http.get<Project>(`http://localhost:8080/projectsrec/${id}`);
  }

  searchProjectsByTitle(title: string): Observable<Project> {
    return this.http.get<Project>(`${this.baseUrl}/projectsrec/search?title=${title}`);
  }
}
