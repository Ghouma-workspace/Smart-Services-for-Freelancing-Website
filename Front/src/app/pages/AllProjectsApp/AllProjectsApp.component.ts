import { Component, NgModule, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProjectAppService } from 'src/app/services/project-app.service';
import { ProjectApp } from 'src/app/shared/projectApp';

@Component({
  selector: 'app-allProjectsApp',
  templateUrl: './AllProjectsApp.component.html',
  styleUrls: ['./AllProjectsApp.component.scss']
})
export class AllProjectsAppComponent implements OnInit {
  public projectsApp: ProjectApp[] = [];
  
    test: String ;
  
    //Ena zedtou !!!!
    constructor(private projectAppservice: ProjectAppService,  private route: ActivatedRoute, private router: Router) {}
    ////////////
  ngOnInit(){
    //Ena zedtha !!!!
    this.listAppProjects();
    this.test="chihemk";
    /////////////////////
    console.log(this.projectsApp);

  }

  //Ena zedtha !!!!
   listAppProjects(){
    this.projectAppservice.getProjectList().subscribe(
      data => {
        this.projectsApp = data;
      }
    )
  }
  goToDetail(id: number){
    this.router.navigate(['projectApp-detail', id]);
  }

}
