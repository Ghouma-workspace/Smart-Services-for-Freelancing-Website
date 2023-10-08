import { Component, NgModule, OnInit } from '@angular/core';
import { CommentsService } from 'src/app/services/comments.service';
import { AuthService } from '../auth.service';
import { Commente } from 'src/app/shared/comment'
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-forum',
  templateUrl: './forum.component.html',
  styleUrls: ['./forum.component.scss']
})
export class ForumComponent implements OnInit {
  comments: Commente[];
  comment: Commente;
  username: string;
  myForm: FormGroup;
  text: string;
  formData: any = {};

  constructor(private commentService: CommentsService, private authService: AuthService) { }

  ngOnInit(): void {
    this.comment = new Commente();
    this.authService.username.subscribe((data: string) => this.username = data);
    this.username = this.authService.getUserName();
    this.getListComments();
  }

  getListComments() {
    this.commentService.getCommentList().subscribe(data => {
      this.comments = data;
    })
  }

  submitClick(text: string): void {
    this.comment.username = this.authService.getUserName();
    this.comment.text = text;
    if (this.comment.text!=''){
      this.commentService.addComment(this.comment).subscribe();
    }
  }

}
export class ForumModule { }