import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Commente } from '../shared/comment';

@Injectable({
  providedIn: 'root'
})
export class CommentsService {

  private baseUrl = "http://localhost:8080"

  constructor(private http: HttpClient) { }

  addComment(comment: Commente): Observable<Commente> {
    return this.http.post<Commente>(`${this.baseUrl}/comments/add`, comment);
  }

  getCommentList(): Observable<Commente[]> {
    return this.http.get<Commente[]>(`${this.baseUrl}/comments`);
  }
}
