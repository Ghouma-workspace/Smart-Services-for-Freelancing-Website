import { Component, OnInit } from '@angular/core';
import { ProjectWorkingService } from 'src/app/services/project-working.service';
import { ProjectWorking } from 'src/app/shared/project-working';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-my-project-working',
  templateUrl: './my-project-working.component.html',
  styleUrls: ['./my-project-working.component.scss']
})
export class MyProjectWorkingComponent implements OnInit {

  public projectsApp: ProjectWorking[] = [];
  project: ProjectWorking;
  username: string;

  constructor(private projectWorkingservice: ProjectWorkingService,  private authService: AuthService, private router: Router) {}
  ngOnInit(){
    this.authService.username.subscribe((data: string) => this.username = data);
    this.username = this.authService.getUserName();
    this.listAppProjects();
  }

  listAppProjects(){
    this.projectWorkingservice.getMyprojects(this.username).subscribe(
      data => {
        this.projectsApp = data;
      }
    )
  }

  contract(){
    this.router.navigate(['/contract']);
  }
  
  check(id: number){
    this.router.navigate(['project-working-detail', id]);
  }

}
