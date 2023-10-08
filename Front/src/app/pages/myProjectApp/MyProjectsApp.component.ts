import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { ProjectAppService } from 'src/app/services/project-app.service';
import { ProjectApp } from 'src/app/shared/projectApp';

@Component({
  selector: 'app-myProjectsApp',
  templateUrl: './MyProjectsApp.component.html',
  styleUrls: ['./MyProjectsApp.component.scss']
})
export class MyProjectsAppComponent implements OnInit {
  public projectsApp: ProjectApp[] = [];
  project: ProjectApp;
  username: string;

  constructor(private projectAppservice: ProjectAppService,  private authService: AuthService, private router: Router) {}
  ngOnInit(){
    this.authService.username.subscribe((data: string) => this.username = data);
    this.username = this.authService.getUserName();
    this.listAppProjects();
  }

  listAppProjects(){
    this.projectAppservice.getMyprojects(this.username).subscribe(
      data => {
        this.projectsApp = data;
      }
    )
  }

  goToDetail(id: number){
    this.router.navigate(['projectApp-detail', id]);
  }

  cancel(id: number){
    this.projectAppservice.getProject(id).subscribe(data => {
      this.project = data;
      this.projectAppservice.cancel(this.username, this.project); 
    });
  }
}
