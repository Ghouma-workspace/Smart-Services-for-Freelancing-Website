import { Component, NgModule, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { ProjectApp } from 'src/app/shared/projectApp';
import { ProjectAppService } from 'src/app/services/project-app.service';
import { AuthService } from '../auth.service';


@Component({
  selector: 'app-projectApp-detail',
  templateUrl: './projectAppDetail.component.html',
  styleUrls: ['./projectAppDetail.component.scss']
})

export class ProjectAppDetailComponent implements OnInit {

  project: ProjectApp;
  projectId :number;
  showModal = false;
  username: string;
  isOn: boolean;

  constructor(
    private route: ActivatedRoute, private dialog: MatDialog,
    private projectAppService: ProjectAppService,
    private authService: AuthService
  ) {   }

  ngOnInit() {
    this.project = new ProjectApp();
    
    this.authService.username.subscribe((data: string) => this.username = data);
    this.username = this.authService.getUserName();

    this.projectId=this.route.snapshot.params['id'];
    this.projectAppService.getProject(this.projectId).subscribe(data => {
      this.project = data;
    })
    this.isPostulated(this.projectId);
  
  }

  isPostulated(projectId: number): void {
    this.projectAppService.isPostulated(projectId, this.username)
      .subscribe(data => {
        this.isOn = data;
      })
  }

  postulate(){
    this.projectAppService.postulate(this.username, this.project);
  }
  
}